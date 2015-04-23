package com.speed.service.common.protocol;


import java.io.Serializable;

/**
 * User: gais.ch
 * Date: 15-4-23
 * Time: 下午9:28
 */
public class ServiceProviderDefinition implements Serializable {

    private String appName;
    private String group;
    private String usefulAddress;
    private int port = 2884;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsefulAddress() {
        return usefulAddress;
    }

    public void setUsefulAddress(String usefulAddress) {
        this.usefulAddress = usefulAddress;
    }
}
