package com.speed.service.web.framework.plugins.databus;


import com.speed.service.web.framework.plugins.events.DataEvent;
import com.speed.service.web.framework.plugins.events.EventHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �첽�ִ����¼���data bus
 * <p/>
 * User: gais.ch
 * Date: 15/5/22
 * Time: ����8:55
 */
public class AsyncDataBus extends AbstractDataBusManager {

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    protected void post(EventHandler handler, DataEvent dataEvent) {
        executorService.submit(new AsyncEvent(handler, dataEvent));
    }

    /**
     * �첽������
     */
    class AsyncEvent implements Runnable {

        private EventHandler handler;
        private DataEvent event;

        public AsyncEvent(EventHandler handler, DataEvent dataEvent) {
            this.handler = handler;
            this.event = dataEvent;
        }

        public void run() {
            handler.handle(event);
        }
    }
}
