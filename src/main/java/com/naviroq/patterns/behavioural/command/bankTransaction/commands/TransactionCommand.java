package com.naviroq.patterns.behavioural.command.bankTransaction.commands;

import java.time.Instant;

public interface TransactionCommand {
    void execute();
    void rollback();
    String getId();
    String getDescription();
    Instant getTimestamp();
}