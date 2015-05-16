package com.speed.service.network.server;

/**
 * keep long connection for servicing
 * <p/>
 * User: gais.ch
 * Date: 15-5-2
 * Time: 上午6:39
 */
public abstract class ServiceServer {

    private int port = 7878;//default listener port


    /**
     * run
     */
    protected void run() {

    }

    /**
     * start the server
     */
    public abstract void bootstrap();
}
