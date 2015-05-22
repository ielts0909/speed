package com.speed.service.web.framework.plugins;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * User: gais.ch
 * Date: 15/5/22
 * Time: 下午3:25
 */
public class PluginUtils {

    private static Map<String, String> namingCahe = new WeakHashMap<String, String>();

    /**
     * 名字转换,有缓存支持
     *
     * @param packageName
     * @return
     */
    public static String className(String packageName) {
        if (namingCahe.containsKey(packageName)) {
            return namingCahe.get(packageName);
        }
        String clzName = packageName.substring(packageName.lastIndexOf(".") + 1);
        namingCahe.put(packageName, clzName);
        return clzName;
    }

}
