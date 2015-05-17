package test.databus;

import com.google.common.eventbus.AsyncEventBus;

/**
 * User: gais.ch
 * Date: 15/5/17
 * Time: 上午9:38
 */
public class AsyncEventBusFacade {
    private AsyncEventBus asyncEventBus;

    public AsyncEventBusFacade() {
        asyncEventBus = new AsyncEventBus(ThreadPoolUtil.asynExecutor);
    }

    public AsyncEventBus getEventBus() {
        return asyncEventBus;
    }
}
