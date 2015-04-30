package com.speed.service.container;

/**
 * User: gais.ch
 * Date: 15-4-30
 * Time: 上午10:24
 */
public class FavoriteServiceImpl implements FavoriteService {

    public boolean addService(String service) {
        System.out.println("addService ---- " + service);
        return false;
    }

    public boolean addServiceTwo(String service, String version) {
        System.out.println("addServiceTwo ---- " + service + " ---- " + version);
        return false;
    }
}
