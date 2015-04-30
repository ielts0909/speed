package com.speed.service.container;

import com.speed.service.common.protocol.ServiceDefinition;
import com.speed.service.common.protocol.ServiceStatus;

import java.lang.reflect.Method;

/**
 * User: gais.ch
 * Date: 15-5-1
 * Time: 上午6:38
 */
public class DefaultServiceDefinition implements ServiceDefinition {

    private String serviceName;

    private String serviceVersion;

    private String serviceGroup;

    private Class<?> serviceInterface;

    private Object targetObject;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String version) {
        this.serviceVersion = version;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String group) {
        this.serviceGroup = group;
    }

    public void setServiceInterface(Class<?> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public Class<?> getServiceInterface() {
        return this.serviceInterface;
    }

    public Object getTargetServiceObject() {
        return targetObject;
    }

    public void setTargetServiceObject(Object target) {
        this.targetObject = target;
    }


    public Method[] getServiceMethods() {
        return new Method[0];
    }

    public void setServiceMethods(Method[] methods) {

    }

    public ServiceStatus getServiceStatus() {
        return null;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {

    }
}
