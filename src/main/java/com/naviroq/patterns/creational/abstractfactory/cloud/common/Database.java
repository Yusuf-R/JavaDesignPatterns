package com.naviroq.patterns.creational.abstractfactory.cloud.common;

public interface Database {
    void connect();
    void executeQuery(String query);
    void disconnect();
    String getDatabaseType();
}
