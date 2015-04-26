package com.speed.service.events;

import com.speed.service.common.protocol.ServiceDefinition;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * User: gais.ch
 * Date: 15-4-26
 * Time: 下午3:49
 */
public class Main {



    @Test
    public void testListener() {
        //Context context = new Context();

        ProviderEventSource source = new ProviderEventSource();
        ServiceDefinition definition = new ServiceDefinition() {
            public String getServiceName() {
                return "service";
            }

            public void setServiceName(String serviceName) {

            }

            public String getServiceVersion() {
                return null;
            }

            public void setServiceVersion(String version) {

            }

            public String getServiceGroup() {
                return null;
            }

            public void setServiceGroup(String group) {

            }

            public void setServiceInterface(Class<?> serviceInterface) {

            }

            public Class<?> getServiceInterface() {
                return null;
            }

            public Method[] getServiceMethods() {
                return new Method[0];
            }
        };
        source.setServiceDefinitions(definition);
        ProviderEvent event = new ProviderEvent(source);
        MultiCaster caster = new MultiCaster();
        caster.multicast(event);
    }
}
