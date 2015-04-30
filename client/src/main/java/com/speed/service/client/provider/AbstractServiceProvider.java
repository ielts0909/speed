package com.speed.service.client.provider;

import com.speed.service.client.utils.NamingUtils;
import com.speed.service.common.protocol.InvokeDefinition;
import com.speed.service.common.protocol.ServiceDefinition;
import com.speed.service.common.protocol.ServiceStatus;

import java.lang.reflect.InvocationTargetException;
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

    /**
     * init the service:cache method and target to memory
     *
     * @param serviceDefinition
     * @return
     */
    public boolean initalizeService(ServiceDefinition serviceDefinition) {
        if (checkDefinition(serviceDefinition)) {
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
        serviceDefinition.setServiceStatus(ServiceStatus.ONLINE);
        return true;
    }

    /**
     * default invoke method
     *
     * @param definition
     * @return
     */
    public Object invoke(InvokeDefinition definition) {
        if (null == definition) {
            return null;
        }
        String nameKey = NamingUtils.uniqueServiceName(definition.getServiceName(), definition.getVersion());
        Object target = targetMap.get(nameKey);
        if (null == target) {
            return null;
        }
        Method method = definition.getCallMethod(); //TODO change the style
        String key = nameKey + "_" + method.toString();
        Method cacheMethod = methodMap.get(key);
        if (cacheMethod != null) {
            try {
                return cacheMethod.invoke(target, definition.getArgs());
            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            }
        }
        return null;
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
