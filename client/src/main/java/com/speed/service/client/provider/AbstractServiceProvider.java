package com.speed.service.client.provider;

import com.speed.service.client.utils.NamingUtils;
import com.speed.service.common.protocol.ServiceDefinition;
import com.speed.service.common.protocol.ServiceStatus;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * process with service detail
 * User: gais.ch
 * Date: 15-4-22
 * Time: 下午8:44
 */
public abstract class AbstractServiceProvider implements ServiceProvider {

    //target map key:serviceName_version value:target
    private final Map<String, Object> targetMap = new ConcurrentHashMap<String, Object>();
    //key:serviceName_version_methodHash value:method
    private final Map<String, Method> methodMap = new ConcurrentHashMap<String, Method>();

    public void destroyService(ServiceDefinition serviceDefinition) {

    }

    public ServiceDefinition getServiceDefinition() {
        return null;
    }

    public ServiceStatus getServiceStatus(ServiceDefinition serviceDefinition) {
        return null;
    }

    /**
     * init the service:cache method and target to memory
     *
     * @param serviceDefinition
     * @return
     */
    public boolean initalizeService(ServiceDefinition serviceDefinition) {
        if (!checkDefinition(serviceDefinition)) {
            return false;
        }
        Class<?> clz = serviceDefinition.getServiceInterface();
        Object target = serviceDefinition.getTargetServiceObject();
        if (!clz.isAssignableFrom(target.getClass())) {
            throw new IllegalStateException("the target is not assignable from interface!");
        }
        if (!clz.isInterface()) {
            return false;
        }
        //cache the target object
        String nameKey = NamingUtils.uniqueServiceName(serviceDefinition.getServiceName(), serviceDefinition.getServiceVersion());
        targetMap.put(nameKey, target);
        //cache the methods
        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            String key = nameKey + "_" + method.toString(); //FIXME
            methodMap.put(key, method);
        }
        return true;
    }


    public boolean setServiceStatus(ServiceDefinition serviceDefinition, ServiceStatus serviceStatus) {

        return false;
    }

    /**
     * TODO check
     *
     * @param serviceDefinition
     * @return
     */
    private boolean checkDefinition(ServiceDefinition serviceDefinition) {
        return null == serviceDefinition
                || serviceDefinition.getServiceInterface() == null
                || serviceDefinition.getTargetServiceObject() == null;
    }
}
