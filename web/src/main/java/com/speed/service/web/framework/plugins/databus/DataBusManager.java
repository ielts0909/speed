package com.speed.service.web.framework.plugins.databus;


import com.speed.service.web.framework.plugins.PluginDefinition;
import com.speed.service.web.framework.plugins.events.DataEvent;

/**
 * �¼�����������Ҫ�����¼���ע����¼��ķ���
 * <p/>
 * User: gais.ch
 * Date: 15/5/21
 * Time: ����7:52
 */
public interface DataBusManager {

    /**
     * ��pluginע�������
     *
     * @param definition
     * @param eventName  �¼������ƣ�������
     */
    void registerEvent(PluginDefinition definition, String eventName);

    /**
     * post event
     *
     * @param dataEvent
     */
    void postEvent(DataEvent dataEvent) throws OutOfRangeException;

    /**
     * ����event name�������¼��������˵��¼������ᱻ�����У����������
     * ������diamondֱ�ӿ��ƿ���
     *
     * @param eventName �¼�������
     * @param close     true �ر� false ����
     * @return
     */
    boolean changeEventState(String eventName, boolean close);
}
