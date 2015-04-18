package com.speed.service.client;

import com.speed.service.client.utils.RecordLog;

/**
 * User: gais.ch
 * Date: 15-4-18
 * Time: 下午4:07
 */
public class SpeedClient {

    private final static String VERSION = "1.0.0";

    public void test() {
        RecordLog.debug("aaaaa", new Throwable("aaa"));
    }

    public static void main(String[] args){
        RecordLog.debug("aaaaa", new Throwable("aaa"));
    }
}
