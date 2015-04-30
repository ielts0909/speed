package com.speed.service.client.invoking;

import com.speed.service.common.protocol.InvokeDefinition;

/**
 * the style of invoke
 * <p/>
 * User: gais.ch
 * Date: 15-4-22
 * Time: 下午9:38
 */
public interface Invoking {

    void invoke(InvokeDefinition invokeDefinition);

}
