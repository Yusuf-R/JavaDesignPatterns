package com.naviroq.patterns.behavioural.command.remoteControl.invoker;

import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;

import java.util.Stack;

public class RemoteControl {

    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    public void pressButton( Command command ) {
        System.out.println("\n[REMOTE] Pressing: " + command.getDescription());
        command.execute();
        undoStack.push(command);
        redoStack.clear();  // NewState command clears redo history (branching)
        System.out.println("[REMOTE] Command stored. Undoable: " + undoStack.size());
    }

    public void pressUndo() {
        if (undoStack.isEmpty()) {
            System.out.println("\n[REMOTE] Nothing to undo");
            return;
        }
        // get the last item on the stack and pop it out
        Command command = undoStack.pop();
        System.out.println("\n[REMOTE] UNDO: " + command.getDescription());
        // execute that command as its pooped out
        command.undo();
        // push it to the redoStack
        redoStack.push(command);
        System.out.println("[REMOTE] Undoable: " + undoStack.size() + ", Redoable: " + redoStack.size());


    }

    public void pressRedo() {
        if (redoStack.isEmpty()) {
            System.out.println("\n[REMOTE] Nothing to redo");
            return;
        }
        Command command = redoStack.pop();
        System.out.println("\n[REMOTE] REDO: " + command.getDescription());
        command.execute();
        undoStack.push(command);
        System.out.println("[REMOTE] Undoable: " + undoStack.size() + ", Redoable: " + redoStack.size());
    }

    // Show current state
    public void showStatus() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║  REMOTE STATUS                     ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  Undoable commands: " + undoStack.size());
        System.out.println("║  Redoable commands: " + redoStack.size());
        System.out.println("╚════════════════════════════════════╝");
    }

}
