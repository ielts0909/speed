package com.speed.service.web.framework.plugins.shopsystem;


import com.speed.service.web.framework.plugins.events.DataEvent;
import com.speed.service.web.framework.plugins.events.EventHandler;
import com.speed.service.web.framework.plugins.events.NewArrivalUpShelfEvent;

/**
 * User: gais.ch
 * Date: 15/5/22
 * Time: ионГ9:56
 */
public class ShopSystemNewArrivalHandler implements EventHandler {

    public void handle(DataEvent event) {
        if (!(event instanceof NewArrivalUpShelfEvent)) {
            return;
        }
        NewArrivalUpShelfEvent upShelfEvent = (NewArrivalUpShelfEvent) event;
    }
}
