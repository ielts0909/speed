package com.speed.service.network.server;

import com.speed.service.network.handler.DefaultHandler;

/**
 * keep long connection for servicing
 * <p/>
 * User: gais.ch
 * Date: 15-5-2
 * Time: 上午6:39
 */
public interface ServiceServer {


    void bootstrap(int port) throws InterruptedException;

    /**
     * set default handler
     *
     * @param defaultHandler
     */
    void setDefaultHandler(DefaultHandler defaultHandler);

    /**
     * shutdown the server
     *
     * @param rightNow
     */
    void shutdown(boolean rightNow);

}
