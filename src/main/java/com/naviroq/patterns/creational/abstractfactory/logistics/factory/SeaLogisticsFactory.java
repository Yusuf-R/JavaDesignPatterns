package com.naviroq.patterns.creational.abstractfactory.logistics.factory;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.Manifest;
import com.naviroq.patterns.creational.abstractfactory.logistics.common.RoutePlanner;
import com.naviroq.patterns.creational.abstractfactory.logistics.common.Transport;
import com.naviroq.patterns.creational.abstractfactory.logistics.sea.SeaGPS;
import com.naviroq.patterns.creational.abstractfactory.logistics.sea.SeaManifest;
import com.naviroq.patterns.creational.abstractfactory.logistics.sea.Ship;

public class SeaLogisticsFactory implements LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new Ship();
    }

    @Override
    public RoutePlanner createRoutePlanner() {
        return new SeaGPS();
    }

    @Override
    public Manifest createManifest() {
        return new SeaManifest();
    }

}
