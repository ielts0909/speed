package com.speed.service.client.events;

import java.util.EventObject;

/**
 * User: gais.ch
 * Date: 15-4-29
 * Time: 上午7:30
 */
public class SpeedServiceProviderEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SpeedServiceProviderEvent(SpeedServiceProviderSource source) {
        super(source);
    }
}
