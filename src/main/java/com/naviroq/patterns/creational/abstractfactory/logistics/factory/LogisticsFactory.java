package com.naviroq.patterns.creational.abstractfactory.logistics.factory;

import com.naviroq.patterns.creational.abstractfactory.logistics.common.Manifest;
import com.naviroq.patterns.creational.abstractfactory.logistics.common.RoutePlanner;
import com.naviroq.patterns.creational.abstractfactory.logistics.common.Transport;

public interface LogisticsFactory {
    Transport createTransport();
    RoutePlanner createRoutePlanner();
    Manifest createManifest();
}
