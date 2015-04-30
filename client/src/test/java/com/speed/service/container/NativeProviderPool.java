package com.speed.service.container;

import com.speed.service.client.container.DefaultProviderPool;
import com.speed.service.client.provider.ServiceProvider;
import com.speed.service.common.protocol.ConfigServiceLifeCycle;

/**
 * User: gais.ch
 * Date: 15-5-1
 * Time: 上午6:26
 */
public class NativeProviderPool extends DefaultProviderPool {

    public NativeProviderPool(ServiceProvider provider, String appName, String group) {
        super(provider, appName, group);
    }

    @Override
    protected void configServerRtnCallback(ConfigServiceLifeCycle lifeCycle) {

    }
}
