package com.speed.service.network.server;

import com.speed.service.network.features.ServiceFeature;
import com.speed.service.network.protocol.ClientMeta;
import com.speed.service.network.protocol.CommunicationProtocol;

import java.util.List;

/**
 * keep long connection for servicing
 * <p/>
 * User: gais.ch
 * Date: 15-5-2
 * Time: 上午6:39
 */
public interface ServiceServer {


    void bootstrap(int port);

    /**
     * send message to client
     *
     * @param meta
     * @param protocol
     * @return
     */
    ServiceFeature send(ClientMeta meta, CommunicationProtocol protocol);

    /**
     *
     *
     * @param protocol
     * @return
     */
    List<ServiceFeature> broadcast(CommunicationProtocol protocol);
}
