package com.speed.service.web.framework.plugins;



import com.speed.service.web.framework.plugins.events.DataEvent;
import com.speed.service.web.framework.plugins.events.EventHandler;

import java.util.Map;

/**
 * ������
 * User: gais.ch
 * Date: 15/5/21
 * Time: ����7:51
 */
public interface PluginDefinition {

    /**
     * �����¼���ȡhandler
     *
     * @param dataEvent
     * @return
     */
    EventHandler getEventHandler(DataEvent dataEvent);

    /**
     * ���ü������¼��Ͷ�Ӧ�Ĵ�����
     *
     * @param handlerMap
     */
    void setHandlerMap(Map<String, EventHandler> handlerMap);


    /**
     * ����
     * @param eventName
     * @param close
     * @return
     */
    boolean changePluginEventState(String eventName, boolean close);

}
