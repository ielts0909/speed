package com.speed.service.events;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

/**
 * User: gais.ch
 * Date: 15-4-29
 * Time: 下午3:49
 */
public class TestMem {

    public static void main(String[] args) {
        MemoryUsage unHeap = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        MemoryUsage mu = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        System.out.println("used:" + mu.getUsed() / 1024 / 1024 + " MB");
        System.out.println("commited:" + mu.getCommitted() / 1024 / 1024 + " MB");
        System.out.println("init:" + mu.getInit() / 1024 / 1024 + " MB");
        System.out.println("max:" + mu.getMax() / 1024 / 1024 + " MB");

        System.out.println("un heap used" + unHeap.getUsed() / 1024 / 1024 + " MB");
        System.out.println("un heap max" + unHeap.getMax() / 1024 / 1024 + " MB");
    }
}
