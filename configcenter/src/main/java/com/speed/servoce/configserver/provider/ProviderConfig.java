package com.speed.servoce.configserver.provider;

import com.speed.service.common.protocol.ServiceDefinition;
import com.speed.service.common.protocol.ServiceProviderDefinition;

import java.util.List;

/**
 * service provider register
 * User: gais.ch
 * Date: 15-4-26
 * Time: 上午10:33
 */
public interface ProviderConfig {

    /**
     * register to config server
     *
     * @param providerDefinition
     * @return
     */
    boolean registerToConfigServer(ServiceProviderDefinition providerDefinition);


    /**
     * push service of online to config server
     *
     * @param serviceDefinitionList
     * @return
     */
    boolean pushServiceDefinitions(List<ServiceDefinition> serviceDefinitionList);
}
