package com.speed.service.client.container;

import com.speed.service.common.protocol.ServiceDefinition;

import java.util.List;

/**
 * Manager the life cycle of container
 * <p/>
 * User: gais.ch
 * Date: 15-4-22
 * Time: 下午8:57
 */
public interface ProviderPoolContainer {

    /**
     * init pool container
     */
    void initialize();

    /**
     * destroy container
     */
    void destroy();

    /**
     * you should register your provider service
     * <p/>
     * 1、check service definition
     * 2、register to config server
     * 3、init service
     *
     * @param serviceDefinition
     * @return
     */
    boolean registerService(ServiceDefinition serviceDefinition);

    /**
     * destroy  one service
     *
     * @param serviceDefinition
     */
    void destroyService(ServiceDefinition serviceDefinition);

    /**
     * Get service definition
     *
     * @param serviceName
     * @param version     if null return all  versions of services
     * @return
     */
    List<ServiceDefinition> getService(String serviceName, String version);
}
