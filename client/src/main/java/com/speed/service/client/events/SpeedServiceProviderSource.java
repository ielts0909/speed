package com.speed.service.client.events;

import com.speed.service.common.protocol.ServiceDefinition;

import java.util.Map;

/**
 * User: gais.ch
 * Date: 15-4-29
 * Time: 上午7:28
 */
public class SpeedServiceProviderSource {


    public SpeedServiceProviderSource(Map<String, ServiceDefinition> serviceMap) {
        this.serviceMap = serviceMap;
    }

    /**
     * service map which service has been stated
     */
    private Map<String, ServiceDefinition> serviceMap;

    public Map<String, ServiceDefinition> getServiceMap() {
        return serviceMap;
    }
}
