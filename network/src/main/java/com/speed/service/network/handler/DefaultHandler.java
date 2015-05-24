package com.speed.service.network.handler;

import com.speed.service.network.protocol.CommunicationProtocol;

/**
 * default handler both for server and client
 * User: gais.ch
 * Date: 15/5/20
 * Time: 下午9:34
 */
public interface DefaultHandler {

    /**
     * handle when receive the protocol
     *
     * @param protocol
     */
    void receive(CommunicationProtocol protocol);
}
