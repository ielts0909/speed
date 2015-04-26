package com.speed.service.events;

import java.util.EventObject;

/**
 * User: gais.ch
 * Date: 15-4-26
 * Time: 下午3:12
 */
public class ProviderEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ProviderEvent(ProviderEventSource source) {
        super(source);
    }
}
