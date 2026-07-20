package com.naviroq.patterns.structural.proxy.accessControl;

public interface Document {
    void view();
    void edit(String content);
    void delete();
}

