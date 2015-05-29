package com.speed.service.threads;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * User: gais.ch
 * Date: 15/5/27
 * Time: 上午9:42
 */
public class TestFrom {

    /**
     * Lock held by take, poll, etc
     */
    private final ReentrantLock takeLock = new ReentrantLock();
    /**
     * Wait queue for waiting takes
     */
    private final Condition notEmpty = takeLock.newCondition();

    public static void main(String[] args) {

    }

    public void test1() {
        try {
            notEmpty.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
