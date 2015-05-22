package com.speed.service.web.framework.plugins.databus;


import com.speed.service.client.utils.CollectionUtils;
import com.speed.service.client.utils.StringUtils;
import com.speed.service.web.framework.plugins.PluginDefinition;
import com.speed.service.web.framework.plugins.PluginUtils;
import com.speed.service.web.framework.plugins.events.DataEvent;
import com.speed.service.web.framework.plugins.events.EventHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * User: gais.ch
 * Date: 15/5/21
 * Time: 下午7:56
 */
public abstract class AbstractDataBusManager implements DataBusManager {

    //key event name value:监听该事件的plugin
    private Map<String, Set<PluginDefinition>> eventsMap = new HashMap<String, Set<PluginDefinition>>(32);
    protected BlockingQueue<DataEvent> eventQueue = new ArrayBlockingQueue<DataEvent>(1000);
    private Set<String> blackList = new HashSet<String>();//应急开关黑名单

    public void registerEvent(PluginDefinition definition, String eventName) {
        if (null == definition || StringUtils.isBlank(eventName)) {
            throw new IllegalStateException("");
        }
        if (eventsMap.containsKey(eventName)) {
            eventsMap.get(eventName).add(definition);
        } else {
            Set<PluginDefinition> pluginDefinitionSet = new HashSet<PluginDefinition>();
            pluginDefinitionSet.add(definition);
            eventsMap.put(eventName, pluginDefinitionSet);
        }
    }

    public void postEvent(DataEvent dataEvent) throws OutOfRangeException {
        if (null == dataEvent || blackList.contains(PluginUtils.className(dataEvent.getClass().getName()))) {
            return;
        }
        Set<PluginDefinition> set = eventsMap.get(PluginUtils.className(dataEvent.getClass().getName()));
        if (CollectionUtils.isEmpty(set)) {
            return;
        }
        for (PluginDefinition definition : set) {
            if (null == definition) {
                continue;
            }
            EventHandler handler = definition.getEventHandler(dataEvent);
            if (null == handler) {
                return;
            }
            //分发数据
            post(handler, dataEvent);
        }
    }

    public boolean changeEventState(String eventName, boolean close) {
        if (!eventsMap.containsKey(eventName)) {
            return false;
        }
        if (close) {
            blackList.add(eventName);
            return true;
        } else {
            blackList.remove(eventName);
            return true;
        }
    }

    /**
     * 分发数据，由底层去实现
     *
     * @param handler
     * @param dataEvent
     */
    protected abstract void post(EventHandler handler, DataEvent dataEvent);

}
