package com.speed.service.client.container;

import com.speed.service.client.events.SpeedServiceProviderEvent;
import com.speed.service.client.events.SpeedServiceProviderSource;
import com.speed.service.client.events.SpeedServiceRefreshListener;
import com.speed.service.client.provider.ServiceProvider;
import com.speed.service.client.utils.CollectionUtils;
import com.speed.service.client.utils.NamingUtils;
import com.speed.service.client.utils.RecordLog;
import com.speed.service.common.multicast.SpeedEventListenerManager;
import com.speed.service.common.protocol.ServiceDefinition;
import com.speed.service.common.protocol.ServiceProviderDefinition;
import com.speed.service.common.protocol.ServiceStatus;
import com.speed.service.network.server.ProviderServiceServer;

import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * default provider pool
 * User: gais.ch
 * Date: 15-4-23
 * Time: 下午6:46
 */
public abstract class DefaultProviderPool extends AbstractContainerSupport implements ProviderPoolContainer {


    private ServiceProvider serviceProvider;
    private String group; // group the speed service
    private String appName;
    //has bean init
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private final ProviderServiceServer serviceServer = new ProviderServiceServer();

    //put the service which is unService
    private final Set<ServiceDefinition> unStartService = Collections.synchronizedSet(new HashSet<ServiceDefinition>());
    //container  key:serviceName+ '_' +version
    private final Map<String, ServiceDefinition> serviceMap = new ConcurrentHashMap<String, ServiceDefinition>(64);

    protected DefaultProviderPool(ServiceProvider provider, String appName, String group) {
        if (provider == null) {
            throw new IllegalArgumentException("ServiceProvider cannot be null!");
        }
        this.serviceProvider = provider;
        this.appName = appName;
        this.group = group;
    }

    protected DefaultProviderPool(ServiceProvider provider, String appName) {
        this(provider, appName, null);
    }

    /**
     * init processors
     * 1 get the project and group basic info
     * 2 connect to the config server
     * 3 get return register info
     * 4 start the pool
     */
    public final void initialize() {
        if (!initialized.get()) {
            //get project basic info
            ServiceProviderDefinition providerDefinition = buildProviderDefinition();
            //connect to config server
            asyncConnectConfigServer();
            //start pool
            refresh();
            initialized.set(true);
        } else {
            throw new IllegalStateException("the service provider pool has bean initialized");
        }
    }


    public void destroy() {

    }

    /**
     * registerService
     * <p/>
     * TODO 需要处理容器初始化和服务注册的顺序
     *
     * @param serviceDefinition
     * @return
     */
    public final boolean registerService(ServiceDefinition serviceDefinition) {
        if (checkServiceIllegal(serviceDefinition)) {
            throw new IllegalStateException("register service failed of definition:" + serviceDefinition);
        }
        //String rowkey = getServiceUniqueKey(serviceDefinition);
        if (unStartService.size() > 65535) {
            RecordLog.debug("service size is over 65535", null);
            return false;
        }
        serviceDefinition.setServiceStatus(ServiceStatus.OFFLINE);
        unStartService.add(serviceDefinition);
        return true;
    }

    public void destroyService(ServiceDefinition serviceDefinition) {

    }

    /**
     * get service
     *
     * @param serviceName
     * @param version     if null return all  versions of services
     * @return
     */
    public List<ServiceDefinition> getService(String serviceName, String version) {
        List<ServiceDefinition> tmp = null;
        if (version != null) {//point version
            tmp = new ArrayList<ServiceDefinition>(1);
            String key = NamingUtils.uniqueServiceName(serviceName, version);
            ServiceDefinition definition = serviceMap.get(key);
            if (null == definition) return Collections.emptyList();
            tmp.add(definition);
            return tmp;
        }
        tmp = new ArrayList<ServiceDefinition>();
        for (Map.Entry<String, ServiceDefinition> entry : serviceMap.entrySet()) {
            if (entry.getKey().contains(serviceName)) {
                tmp.add(entry.getValue());
            }
        }
        //ServiceDefinition[] definitions = CollectionUtils.asArray(tmp);
        return tmp;
    }

    /**
     * refresh to start pool
     */
    private void refresh() {
        if (initialized.get()) {
            throw new IllegalStateException("before refresh the container has been inited!");
        }
        //refresh service
        if (!CollectionUtils.isEmpty(unStartService)) {
            for (Iterator<ServiceDefinition> iterator = unStartService.iterator(); iterator.hasNext(); ) {
                ServiceDefinition definition = iterator.next();
                if (serviceProvider.initalizeService(definition)) { //start service
                    serviceMap.put(NamingUtils.uniqueServiceName(definition.getServiceName(),
                            definition.getServiceVersion()), definition);
                    iterator.remove();
                } else {
                    RecordLog.debug("service of " + definition.getServiceName() + " init error", null);
                }
            }
        }
        //clean un start

        //event mock
        SpeedServiceProviderSource providerSource = new SpeedServiceProviderSource(serviceMap);
        SpeedServiceProviderEvent event = new SpeedServiceProviderEvent(providerSource);
        List<EventListener> listeners = SpeedEventListenerManager.getInstance().getListenerList();
        for (EventListener listener : listeners) {
            if (listener instanceof SpeedServiceRefreshListener) {
                SpeedServiceRefreshListener refreshListener = (SpeedServiceRefreshListener) listener;
                refreshListener.onRefresh(event);
            }
        }
        //if all service is started, online service and container
        try {
            serviceServer.bootstrap(7001);//service run
        } catch (InterruptedException e) {
            RecordLog.error("", e);
        }
        //connect config server to publish the container
    }

    /**
     * @return
     */
    private ServiceProviderDefinition buildProviderDefinition() {
        ServiceProviderDefinition definition = new ServiceProviderDefinition();
        definition.setAppName(this.appName);
        definition.setGroup(this.group);
        try {
            List<String> ipv4 = getIPV4();
            if (CollectionUtils.isEmpty(ipv4)) {
                throw new IllegalStateException("can not get the ipv4 info");
            }
            for (String ip : ipv4) {
                if ("127.0.0.1".equals(ip)) {
                    continue;
                } else {
                    definition.setUsefulAddress(ip);
                    break;
                }
            }
        } catch (SocketException e) {
            RecordLog.error("error in get ipv4 address", e);
        }
        return definition;
    }
}
