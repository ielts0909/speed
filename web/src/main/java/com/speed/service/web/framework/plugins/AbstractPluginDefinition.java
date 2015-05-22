package com.speed.service.web.framework.plugins;


import com.speed.service.web.framework.plugins.databus.DataBusManager;
import com.speed.service.web.framework.plugins.events.DataEvent;
import com.speed.service.web.framework.plugins.events.EventHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User: gais.ch
 * Date: 15/5/21
 * Time: ����8:23
 */
public abstract class AbstractPluginDefinition implements PluginDefinition {

    private DataBusManager dataBusManager;//�ṩע�������

    private final static String DEFAULT_HANDLER_KEY = "UAC_DEFAULT_HANDLER";
    //handler map key:event name value:��Ӧ��event handler
    protected Map<String, EventHandler> handlerMap = new HashMap<String, EventHandler>();
    private Set<String> blackList = new HashSet<String>();//�������ʵ��¼�������

    /**
     * construct
     */
    public AbstractPluginDefinition(DataBusManager dataBusManager) {
        this.dataBusManager = dataBusManager;
        init();//init
    }

    /**
     * init plugin
     */
    protected abstract void init();


    public final EventHandler getEventHandler(DataEvent dataEvent) {
        if (null == dataEvent
                || blackList.contains(PluginUtils.className(dataEvent.getClass().getName()))) {
            //����ں������У��Ͳ����ض�Ӧ��handler
            return null;
        }
        String key = PluginUtils.className(dataEvent.getClass().getName());
        return handlerMap.get(key);
    }

    public void setHandlerMap(Map<String, EventHandler> handlerMap) {
        this.handlerMap = handlerMap;//ע�ᴦ��������
        if (this.handlerMap == null || this.handlerMap.isEmpty()) {
            return;
        }
        //ע���¼��ͼ�����
        for (Map.Entry<String, EventHandler> entry : handlerMap.entrySet()) {
            this.dataBusManager.registerEvent(this, entry.getKey());
        }
    }

    public boolean changePluginEventState(String eventName, boolean close) {
        if (!handlerMap.containsKey(eventName)) {
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
}
