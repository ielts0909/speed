package test.databus;

import com.google.common.eventbus.Subscribe;

/**
 * User: gais.ch
 * Date: 15/5/21
 * Time: 下午7:04
 */
public class SubAddListener extends AddListener{

    public SubAddListener(AsyncEventBusFacade facade) {
        super(facade);
    }

    @Subscribe
    public void aaa(AddEvent str){
       System.out.println("aaaaxxxx");
    }
}
