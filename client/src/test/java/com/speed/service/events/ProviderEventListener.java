package com.speed.service.events;

import java.util.EventListener;

/**
 * User: gais.ch
 * Date: 15-4-26
 * Time: 下午3:45
 */
public interface ProviderEventListener extends EventListener {

    void onRefreshed(ProviderEvent providerEvent);

}
