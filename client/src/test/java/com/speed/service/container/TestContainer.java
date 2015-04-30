package com.speed.service.container;

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
        serviceDefinition.setTargetServiceObject(new FavoriteServiceImpl());
        providerPool.registerService(serviceDefinition);
        providerPool.initialize();
    }
}
