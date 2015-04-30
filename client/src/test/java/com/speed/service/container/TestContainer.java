package com.speed.service.container;

import com.speed.service.common.protocol.InvokeDefinition;
import org.junit.Test;


/**
 * User: gais.ch
 * Date: 15-5-1
 * Time: 上午6:25
 */
public class TestContainer {

    @Test
    public void testContainer() throws ClassNotFoundException {
        NativeServiceProvider provider = new NativeServiceProvider();
        NativeProviderPool providerPool = new NativeProviderPool(provider, "a2", "speed");
        DefaultServiceDefinition serviceDefinition = new DefaultServiceDefinition();
        serviceDefinition.setServiceGroup("hsf");
        serviceDefinition.setServiceInterface(Class.forName("com.speed.service.container.FavoriteService"));
        serviceDefinition.setServiceName("FavoriteService");
        serviceDefinition.setServiceVersion("1.0.0");
        FavoriteServiceImpl target = new FavoriteServiceImpl();
        serviceDefinition.setTargetServiceObject(target);
        providerPool.registerService(serviceDefinition);
        providerPool.initialize();

        InvokeDefinition invokeDefinition = new InvokeDefinition();
        invokeDefinition.setArgs(new String[]{"test"});
        invokeDefinition.setServiceName("FavoriteService");
        invokeDefinition.setVersion("1.0.0");
        invokeDefinition.setCallMethod(FavoriteService.class.getMethods()[0]);
        provider.invoke(invokeDefinition);
    }
}
