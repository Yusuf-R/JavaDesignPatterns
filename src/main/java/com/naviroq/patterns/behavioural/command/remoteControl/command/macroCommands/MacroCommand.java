package com.naviroq.patterns.behavioural.command.remoteControl.command.macroCommands;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MacroCommand implements Command {
    private final List<Command> commands = new ArrayList<>();
    private final String name;

    public MacroCommand(String name) {
        this.name = name;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public void execute() {
        System.out.println("\n--- Running Macro: " + name + " ---");
        for (Command cmd : commands) {
            cmd.execute();
        }
        System.out.println("--- Macro Complete ---\n");
    }

    @Override
    public void undo() {
        System.out.println("\n--- Undoing Macro: " + name + " ---");
        // Undo in reverse order
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
        System.out.println("--- Macro Undone ---\n");
    }

    @Override
    public String getDescription() {
        return "Macro: " + name + " (" + commands.size() + " commands)";
    }
}
