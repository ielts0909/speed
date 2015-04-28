package com.speed.service.events;

import com.speed.service.common.protocol.ServiceDefinition;

/**
 * User: gais.ch
 * Date: 15-4-26
 * Time: 下午3:47
 */
public class Context implements ProviderEventListener {

    public void onRefreshed(ProviderEvent providerEvent) {
        ProviderEventSource eventSource = (ProviderEventSource) providerEvent.getSource();
        ServiceDefinition definitions = eventSource.getServiceDefinitions();
        System.out.println("------" + definitions.getServiceName());
    }
}
