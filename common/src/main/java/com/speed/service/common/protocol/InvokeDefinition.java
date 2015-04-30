package com.speed.service.common.protocol;

import java.lang.reflect.Method;

/**
 * User: gais.ch
 * Date: 15-4-30
 * Time: 下午5:02
 */
public class InvokeDefinition {

    private String serviceName;
    private String version;
    private String group;

    private Method callMethod;
    private Object[] args;

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Method getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(Method callMethod) {
        this.callMethod = callMethod;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
