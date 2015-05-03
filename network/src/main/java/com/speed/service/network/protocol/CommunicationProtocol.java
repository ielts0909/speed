package com.speed.service.network.protocol;

import com.speed.service.common.protocol.InvokeDefinition;

import java.io.Serializable;

/**
 * communication protocol
 * <p/>
 * User: gais.ch
 * Date: 15-5-2
 * Time: 上午6:48
 */
public class CommunicationProtocol implements Serializable {

    private byte magic;
    private String callFrom;//consumerIp
    private int callPort;//port

    private InvokeDefinition invokeDefinition;
    private Object value;//return value

    private long cost;//cost time
    private String crc32;

    public String getCallFrom() {
        return callFrom;
    }

    public void setCallFrom(String callFrom) {
        this.callFrom = callFrom;
    }

    public int getCallPort() {
        return callPort;
    }

    public void setCallPort(int callPort) {
        this.callPort = callPort;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public InvokeDefinition getInvokeDefinition() {
        return invokeDefinition;
    }

    public void setInvokeDefinition(InvokeDefinition invokeDefinition) {
        this.invokeDefinition = invokeDefinition;
    }

    public byte getMagic() {
        return magic;
    }

    public void setMagic(byte magic) {
        this.magic = magic;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getCrc32() {
        return crc32;
    }

    public void setCrc32(String crc32) {
        this.crc32 = crc32;
    }
}
