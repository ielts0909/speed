package com.speed.service.common.multicast;

import java.util.EventListener;
import java.util.List;
import java.util.ArrayList;

/**
 * listener holds manager
 * <p/>
 * User: gais.ch
 * Date: 15-4-30
 * Time: 上午9:27
 */
public class SpeedEventListenerManager {

    private List<EventListener> listenerList = new ArrayList<EventListener>();

    private SpeedEventListenerManager() {
    }

    private static SpeedEventListenerManager manager;

    public static SpeedEventListenerManager getInstance() {
        if (null == manager) {
            manager = new SpeedEventListenerManager();
            return manager;
        }
        return manager;
    }

    public void addListener(EventListener listener) {
        this.listenerList.add(listener);
    }

    public List<EventListener> getListenerList() {
        return listenerList;
    }
}
