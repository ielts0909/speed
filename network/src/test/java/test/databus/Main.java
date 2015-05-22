package test.databus;

import java.util.Random;

/**
 * User: gais.ch
 * Date: 15/5/17
 * Time: 下午12:03
 */
public class Main {
    public static void main(String[] args) {
        AsyncEventBusFacade facade = new AsyncEventBusFacade();
        System.out.println(AsyncEventBusFacade.class.getName());
        AddListener listener = new AddListener(facade);
        for (int i = 0; i < 100; i++)
            facade.getEventBus().post(new SubAddEvent(new Random().nextInt()));
    }
}
