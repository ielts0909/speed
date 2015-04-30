package com.speed.service.ref;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * User: gais.ch
 * Date: 15-4-30
 * Time: 上午10:28
 */
public class TestMain {

    @Test
    public void testRef() throws Exception {
        Class<?> inf = Class.forName("com.speed.service.ref.FavoriteService"); //use interface to declare methods
        Object service = new FavoriteServiceImpl();
        System.out.println(inf.isAssignableFrom(service.getClass()));

        Method[] methods = service.getClass().getDeclaredMethods();
        //Method[] methods = inf.getMethods();
        if (null != methods && methods.length > 1) {
            for (Method method : methods) {
                if (method.getParameterTypes().length == 1) {
                    method.invoke(service, "aaa");
                }
                System.out.println(method);
            }
        }
    }
}
