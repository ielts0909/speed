package com.speed.service.web.framework.plugins.events;

/**
 * User: gais.ch
 * Date: 15/5/22
 * Time: ионГ9:39
 */
public class NewArrivalUpShelfEvent implements DataEvent {


    public NewArrivalUpShelfEvent(long itemId) {
        this.itemId = itemId;
    }

    private long itemId;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getKey() {
        return null;
    }
}
