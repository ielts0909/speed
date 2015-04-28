package com.speed.service.events;

import java.util.List;
import java.util.ArrayList;

/**
 * User: gais.ch
 * Date: 15-4-26
 * Time: 下午4:06
 */
public class MultiCaster {

    public void multicast(ProviderEvent providerEvent) {
        List<ProviderEventListener> listenerList = getEventListener(providerEvent);
        if (null != listenerList) {
            for (ProviderEventListener listener : listenerList) {
                listener.onRefreshed(providerEvent);
            }
        }
    }

    private List<ProviderEventListener> getEventListener(ProviderEvent providerEvent) {
        ProviderEventSource eventSource = (ProviderEventSource) providerEvent.getSource();

        List<ProviderEventListener> list = new ArrayList<ProviderEventListener>();
        list.add(new Context());
        return list;
    }
}
