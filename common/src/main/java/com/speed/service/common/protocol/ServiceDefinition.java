package com.speed.service.common.protocol;

import java.lang.reflect.Method;

/**
 * Definite the speed service.
 * All Service of Speed should be Allowed this Protocol
 * A service define by three points:
 * 1 service name
 * 2 service version
 * 3
 * <p/>
 * User: gais.ch
 * Date: 15-4-19
 * Time: 上午7:23
 */
public interface ServiceDefinition {


    /**
     * get service name
     * this name should be unique in one project
     * the full service name is define of GROUP_PROJECT_SERVICE
     *
     * @return
     */
    String getServiceName();

    /**
     * set service name
     *
     * @param serviceName
     */
    void setServiceName(String serviceName);

    /**
     * get service version
     *
     * @return
     */
    String getServiceVersion();

    /**
     * set service version
     *
     * @param version
     */
    void setServiceVersion(String version);

    /**
     * service group
     *
     * @return
     */
    String getServiceGroup();

    /**
     * set group
     *
     * @param group
     */
    void setServiceGroup(String group);


    /**
     * set service interface
     *
     * @param serviceInterface
     */
    void setServiceInterface(Class<?> serviceInterface);

    /**
     * get service interface
     *
     * @return
     */
    Class<?> getServiceInterface();

    /**
     * get method of service
     *
     * @return
     */
    Method[] getServiceMethods();
}
