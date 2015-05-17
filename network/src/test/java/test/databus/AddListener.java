package test.databus;

import com.google.common.eventbus.Subscribe;

/**
 * User: gais.ch
 * Date: 15/5/17
 * Time: 下午12:04
 */
public class AddListener {

    private AsyncEventBusFacade facade;

    public AddListener(AsyncEventBusFacade facade) {
        this.facade = facade;
        this.facade.getEventBus().register(this);
    }

    @Subscribe
    public void add(AddEvent addEvent) {
        System.out.println("add...." + addEvent.toString());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
