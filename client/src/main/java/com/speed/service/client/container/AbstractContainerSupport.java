package com.speed.service.client.container;


import com.speed.service.client.utils.StringUtils;
import com.speed.service.common.protocol.ConfigServiceLifeCycle;
import com.speed.service.common.protocol.ServiceDefinition;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;

/**
 * User: gais.ch
 * Date: 15-4-23
 * Time: 下午6:44
 */
public abstract class AbstractContainerSupport {

    /**
     * get ipv4
     *
     * @return
     * @throws SocketException
     */
    protected List<String> getIPV4() throws SocketException {
        List<String> ips = new ArrayList<String>();
        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            System.out.println(netInterface.getName());
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address) {
                    ips.add(ip.getHostAddress());
                }
            }
        }
        return ips;
    }

    /**
     * check the service is illegal
     *
     * @param serviceDefinition
     * @return
     */
    protected boolean checkServiceIllegal(ServiceDefinition serviceDefinition) {
        return null == serviceDefinition
                || StringUtils.isBlank(serviceDefinition.getServiceName())
                || StringUtils.isBlank(serviceDefinition.getServiceVersion())
                || null == serviceDefinition.getServiceInterface();
    }

    /**
     *
     */
    protected void asyncConnectConfigServer() {

        String emp = "";
        configServerRtnCallback(null);
    }

    /**
     * connect config server callback
     *
     * @param lifeCycle
     */
    protected abstract void configServerRtnCallback(ConfigServiceLifeCycle lifeCycle);

}
