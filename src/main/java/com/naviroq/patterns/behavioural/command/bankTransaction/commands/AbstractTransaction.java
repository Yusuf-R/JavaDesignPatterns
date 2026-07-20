package com.naviroq.patterns.behavioural.command.bankTransaction.commands;

import java.time.Instant;
import java.util.UUID;

public abstract class AbstractTransaction implements TransactionCommand {
    protected final String id;
    protected final Instant timestamp;
    protected boolean executed = false;

    public AbstractTransaction() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.timestamp = Instant.now();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    protected void markExecuted() {
        this.executed = true;
    }

    protected boolean isExecuted() {
        return executed;
    }
}