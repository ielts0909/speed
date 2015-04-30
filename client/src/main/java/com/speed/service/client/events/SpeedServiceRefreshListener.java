package com.speed.service.client.events;

import java.util.EventListener;

/**
 * User: gais.ch
 * Date: 15-4-29
 * Time: 下午5:34
 */
public interface SpeedServiceRefreshListener extends EventListener {

    void onRefresh(SpeedServiceProviderEvent providerEvent);

}
