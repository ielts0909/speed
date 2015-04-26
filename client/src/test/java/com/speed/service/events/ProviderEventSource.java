package com.speed.service.events;

import com.speed.service.common.protocol.ServiceDefinition;

/**
 * User: gais.ch
 * Date: 15-4-26
 * Time: 下午3:43
 */
public class ProviderEventSource {

    private ServiceDefinition serviceDefinitions;

    public ServiceDefinition getServiceDefinitions() {
        return serviceDefinitions;
    }

    public void setServiceDefinitions(ServiceDefinition serviceDefinitions) {
        this.serviceDefinitions = serviceDefinitions;
    }
}
