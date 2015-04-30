package com.speed.service.client.provider;


import com.speed.service.common.protocol.InvokeDefinition;
import com.speed.service.common.protocol.ServiceDefinition;

/**
 * service provider define
 * User: gais.ch
 * Date: 15-4-22
 * Time: 下午8:45
 */
public interface ServiceProvider {

    /**
     * init service
     */
    boolean initalizeService(ServiceDefinition serviceDefinition);

    /**
     * destroy service
     */
    void destroyService(ServiceDefinition serviceDefinition);


    /**
     * sync invoke
     *
     * @param invokeDefinition
     * @return
     */
    Object invoke(InvokeDefinition invokeDefinition);

}
