package com.speed.service.client.provider;


import com.speed.service.common.protocol.ServiceDefinition;
import com.speed.service.common.protocol.ServiceStatus;

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
    void initalizeService(ServiceDefinition serviceDefinition);

    /**
     * destroy service
     */
    void destroyService(ServiceDefinition serviceDefinition);

    /**
     * Get current service definition
     *
     * @return
     */
    ServiceDefinition getServiceDefinition();

    /**
     * get current service status
     *
     * @return
     */
    ServiceStatus getServiceStatus(ServiceDefinition serviceDefinition);

    /**
     * set service status
     *
     * @param serviceDefinition
     * @param serviceStatus
     * @return
     */
    boolean setServiceStatus(ServiceDefinition serviceDefinition, ServiceStatus serviceStatus);
}
