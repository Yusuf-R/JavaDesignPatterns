package com.naviroq.patterns.behavioural.command.eCommerce.task;

public interface Task {
    void execute();
    void rollback();
    String getDescription();
    boolean isSuccessful();
}
