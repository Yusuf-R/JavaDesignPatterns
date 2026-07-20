package com.naviroq.patterns.behavioural.command.remoteControl.command;

public interface Command {
    // every command is barebone to be any of this underlying expectation
    void execute();
    void undo();
    String getDescription();
}