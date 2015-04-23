package com.speed.service.client.container;

import com.speed.service.client.utils.CollectionUtils;
import com.speed.service.client.utils.RecordLog;
import com.speed.service.common.protocol.ServiceDefinition;
import com.speed.service.common.protocol.ServiceProviderDefinition;

import java.net.SocketException;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * default provider pool
 * User: gais.ch
 * Date: 15-4-23
 * Time: 下午6:46
 */
public abstract class DefaultProviderPool extends AbstractContainerSupport implements ProviderPoolContainer {


    private String group; // group the speed service
    private String appName;
    //has bean init
    private AtomicBoolean initialized = new AtomicBoolean(false);
    //container
    private final Map<String, ServiceDefinition> serviceMap = new ConcurrentHashMap<String, ServiceDefinition>(64);


    protected DefaultProviderPool(String appName, String group) {
        this.appName = appName;
        this.group = group;
    }

    protected DefaultProviderPool(String appName) {
        this(appName, null);
    }

    /**
     * init processors
     * 1 get the project and group basic info
     * 2 connect to the config server
     * 3 get return register info
     * 4 start the pool
     */
    public void initialize() {
        if (initialized.compareAndSet(false, true)) {
            //get project basic info
            ServiceProviderDefinition providerDefinition = buildProviderDefinition();
            //connect to config server
            asyncConnectConfigServer();
            //start pool

        } else {
            throw new IllegalStateException("the service provider pool has bean initialized");
        }
    }

    public void destroy() {

    }

    public boolean registerService(ServiceDefinition serviceDefinition) {
        return false;
    }

    public void destroyService(ServiceDefinition serviceDefinition) {

    }

    public ServiceDefinition[] getService(String serviceName) {
        return new ServiceDefinition[0];
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
        } catch (SocketException e) {
            RecordLog.error("error in get ipv4 address", e);
        }

        return null;
    }
}
