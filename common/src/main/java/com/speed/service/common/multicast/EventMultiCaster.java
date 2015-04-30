package com.speed.service.common.multicast;

import java.util.EventListener;

/**
 * User: gais.ch
 * Date: 15-4-30
 * Time: 上午9:20
 */
public class EventMultiCaster {

    public EventMultiCaster() {

    }


    public void addListener(EventListener eventListener) {
        SpeedEventListenerManager.getInstance().getListenerList().add(eventListener);
    }

}
