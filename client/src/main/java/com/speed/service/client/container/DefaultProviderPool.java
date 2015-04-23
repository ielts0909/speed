package com.speed.service.client.container;

import com.speed.service.common.protocol.ServiceDefinition;

/**
 * default provider pool
 * User: gais.ch
 * Date: 15-4-23
 * Time: 下午6:46
 */
public class DefaultProviderPool extends AbstractContainerSupport implements ProviderPoolContainer {

    public void initialize() {

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
}
