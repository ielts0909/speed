package com.speed.service.web.framework.plugins;



import com.speed.service.web.framework.plugins.events.DataEvent;
import com.speed.service.web.framework.plugins.events.EventHandler;

import java.util.Map;

/**
 * 定义插件
 * User: gais.ch
 * Date: 15/5/21
 * Time: 下午7:51
 */
public interface PluginDefinition {

    /**
     * 根据事件获取handler
     *
     * @param dataEvent
     * @return
     */
    EventHandler getEventHandler(DataEvent dataEvent);

    /**
     * 设置监听的事件和对应的处理器
     *
     * @param handlerMap
     */
    void setHandlerMap(Map<String, EventHandler> handlerMap);


    /**
     * 设置
     * @param eventName
     * @param close
     * @return
     */
    boolean changePluginEventState(String eventName, boolean close);

}
