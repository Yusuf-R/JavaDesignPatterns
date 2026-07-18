package com.naviroq.patterns.creational.abstractfactory.logistics.factory;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.Manifest;
import com.naviroq.patterns.creational.abstractfactory.logistics.common.RoutePlanner;
import com.naviroq.patterns.creational.abstractfactory.logistics.common.Transport;
import com.naviroq.patterns.creational.abstractfactory.logistics.land.RoadGPS;
import com.naviroq.patterns.creational.abstractfactory.logistics.land.RoadManifest;
import com.naviroq.patterns.creational.abstractfactory.logistics.land.Truck;

public class LandLogisticsFactory implements LogisticsFactory {
    @Override
    public Transport createTransport() {
        return new Truck();
    }

    @Override
    public RoutePlanner createRoutePlanner() {
        return new RoadGPS();
    }

    @Override
    public Manifest createManifest() {
        return new RoadManifest();
    }
}
