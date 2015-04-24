package com.speed.service.client.container;

import com.speed.service.client.provider.ServiceProvider;
import com.speed.service.client.utils.CollectionUtils;
import com.speed.service.client.utils.RecordLog;
import com.speed.service.common.protocol.ServiceDefinition;
import com.speed.service.common.protocol.ServiceProviderDefinition;
import com.speed.service.common.protocol.ServiceStatus;

import java.net.SocketException;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
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

    private final Set<ServiceDefinition> unStartService = Collections.synchronizedSet(new HashSet<ServiceDefinition>());
    //container  key:serviceName+ '_' +version
    private final Map<String, ServiceDefinition> serviceMap = new ConcurrentHashMap<String, ServiceDefinition>(64);

    protected DefaultProviderPool(ServiceProvider provider, String appName, String group) {
        if (serviceProvider == null) {
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
        serviceProvider.setServiceStatus(serviceDefinition, ServiceStatus.OFFLINE);
        unStartService.add(serviceDefinition);
        return true;
    }

    public void destroyService(ServiceDefinition serviceDefinition) {

    }

    public ServiceDefinition[] getService(String serviceName) {
        return new ServiceDefinition[0];
    }

    /**
     * refresh to start pool
     */
    private void refresh() {

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

    public final String getServiceUniqueKey(ServiceDefinition serviceDefinition) {
        return serviceDefinition.getServiceName() + "_" + serviceDefinition.getServiceVersion();
    }
}
