package com.speed.service.web.framework.plugins.databus;


import com.speed.service.web.framework.plugins.PluginDefinition;
import com.speed.service.web.framework.plugins.events.DataEvent;

/**
 * 事件管理中心主要用于事件的注册和事件的发送
 * <p/>
 * User: gais.ch
 * Date: 15/5/21
 * Time: 下午7:52
 */
public interface DataBusManager {

    /**
     * 给plugin注册服务用
     *
     * @param definition
     * @param eventName  事件的名称（类名）
     */
    void registerEvent(PluginDefinition definition, String eventName);

    /**
     * post event
     *
     * @param dataEvent
     */
    void postEvent(DataEvent dataEvent) throws OutOfRangeException;

    /**
     * 根据event name来过滤事件，被过滤的事件将不会被（所有）插件侦听到
     * 可以用diamond直接控制开关
     *
     * @param eventName 事件的名称
     * @param close     true 关闭 false 开启
     * @return
     */
    boolean changeEventState(String eventName, boolean close);
}
