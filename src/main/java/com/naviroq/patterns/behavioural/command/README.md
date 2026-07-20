# Command Pattern

> **"Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations."**
> — *Gang of Four*

---

## Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Structure](#structure)
4. [Example 1: Remote Control](#example-1-remote-control)
5. [Example 2: Order Processor (Saga Pattern)](#example-2-order-processor-saga-pattern)
6. [The "Aha" Moment](#the-aha-moment)
7. [Trade-offs](#trade-offs)
8. [Command vs Strategy](#command-vs-strategy)
9. [Real-World Applications](#real-world-applications)
10. [Known Examples in the JDK](#known-examples-in-the-jdk)
11. [Key Takeaway](#key-takeaway)

---

## The Problem

You have a button (invoker) that needs to do different things depending on context. The naive approach is to use `if-else` or `switch`:

```java
if (mode.equals("light")) { light.on(); }
else if (mode.equals("thermostat")) { thermostat.up(); }
else if (mode.equals("garage")) { garage.open(); }
```

**The Problems:**

| Issue | Why it's bad |
| :--- | :--- |
| **Tight Coupling** | The invoker knows about every device. |
| **Violates Open/Closed** | Adding a new device means modifying the invoker. |
| **No Undo/Redo** | You can't reverse an action. |
| **No Queuing** | You can't queue commands for later execution. |
| **No Logging** | You can't log which actions were performed. |
| **No Macros** | You can't combine multiple actions into one. |

---

## The Solution

You encapsulate each action into its own **Command** object. The invoker calls `execute()` on the command. The command knows which device to call and what method to invoke.

**The Components:**

| Component | Role |
| :--- | :--- |
| **Command** | Interface with `execute()` and `undo()`. |
| **Concrete Command** | Implements `execute()` and `undo()`. Holds a reference to a receiver. |
| **Receiver** | The actual device/service that does the work. |
| **Invoker** | The button/remote/orchestrator. Stores a command and calls `execute()`. |
| **Client** | Creates commands and assigns them to the invoker. |

---

## Structure

```text
                          ┌────────────────────────┐
                          │        CLIENT          │
                          │   (Creates Commands)   │
                          └───────────┬────────────┘
                                      │
                                      ▼
                          ┌────────────────────────┐
                          │        INVOKER         │
                          │    (RemoteControl)     │
                          │                        │
                          │ pressButton(Command)   │
                          │   → command.execute()  │
                          │ pressUndo()            │
                          │   → lastCommand.undo() │
                          │ pressRedo()            │
                          │   → redoCommand.execute│
                          └───────────┬────────────┘
                                      │
                                      ▼
                          ┌────────────────────────┐
                          │   COMMAND (interface)  │
                          │                        │
                          │ execute()  → do it     │
                          │ undo()     → reverse it│
                          │ getDescription()       │
                          └───────────┬────────────┘
                                      │
                ┌─────────────────────┼─────────────────────┐
                ▼                     ▼                     ▼
      ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐
      │  LightOnCommand  │  │ GarageOpenCommand│  │  ThermostatUp    │
      │  execute():      │  │  execute():      │  │  execute():      │
      │    light.on()    │  │    garage.open() │  │    thermo.up()   │
      │  undo():         │  │  undo():         │  │  undo():         │
      │    light.off()   │  │    garage.close()│  │    thermo.down() │
      └────────┬─────────┘  └────────┬─────────┘  └────────┬─────────┘
               │                     │                     │
               ▼                     ▼                     ▼
      ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐
      │      Light       │  │    GarageDoor    │  │    Thermostat    │
      │   (Receiver)      │  │   (Receiver)     │  │   (Receiver)     │
      │  on() / off()    │  │ open() / close() │  │ setTemp()/getTemp│
      └──────────────────┘  └──────────────────┘  └──────────────────┘
```

---

## Example 1: Remote Control

The classic **Smart Home Remote Control** with undo/redo, macros, and branching.

### Key Features

| Feature | Implementation |
| :--- | :--- |
| **Undo/Redo** | Two stacks (`undoStack`, `redoStack`). |
| **Macro Commands** | `MacroCommand` groups multiple commands. |
| **Branching** | New command clears the redo stack. |
| **Status Display** | `showStatus()` shows stack sizes. |

### Code Snippet

**Command Interface:**

```java
public interface Command {
    void execute();
    void undo();
    String getDescription();
}
```

**Remote Control (Invoker):**

```java
public class RemoteControl {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void pressButton(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // New command clears redo (branching)
    }

    public void pressUndo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
        }
    }

    public void pressRedo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
        }
    }
}
```

**Macro Command:**

```java
public class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();
    private String name;

    public MacroCommand(String name) { this.name = name; }
    public void addCommand(Command cmd) { commands.add(cmd); }

    @Override
    public void execute() {
        for (Command cmd : commands) cmd.execute();
    }

    @Override
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
```

---

## Example 2: Order Processor (Saga Pattern)

A **transaction orchestrator** that executes tasks in sequence and rolls back on failure.

### The Pattern

```text
┌────────────────────────────────────────────────────┐
│               ORDER PROCESSOR                       │
│           (Orchestrator / Invoker)                  │
│                                                      │
│  1. Reserve Inventory  ─┐                           │
│  2. Process Payment     ├── Execute in sequence     │
│  3. Create Shipping    ─┘                           │
│                                                      │
│  If ANY step fails → Rollback in reverse order      │
│  (Compensating transactions)                        │
└──────────────────────────────────────────────────────┘
```

### Code Snippet

**Task Interface:**

```java
public interface Task {
    void execute();
    void rollback();
    String getDescription();
    boolean isSuccessful();
}
```

**Order Processor (Invoker/Orchestrator):**

```java
public class OrderProcessor {
    private List<Task> tasks = new ArrayList<>();
    private List<Task> executedTasks = new ArrayList<>();

    public void addTask(Task task) { tasks.add(task); }

    public boolean processOrder() {
        for (Task task : tasks) {
            task.execute();
            if (!task.isSuccessful()) {
                rollbackAll(); // Rollback in reverse order
                return false;
            }
            executedTasks.add(task);
        }
        return true;
    }

    private void rollbackAll() {
        for (int i = executedTasks.size() - 1; i >= 0; i--) {
            executedTasks.get(i).rollback();
        }
    }
}
```

**Concrete Task:**

```java
public class ReserveInventoryTask implements Task {
    private OrderContext context;
    private boolean success = false;

    @Override
    public void execute() {
        // Reserve inventory logic...
        success = true;
    }

    @Override
    public void rollback() {
        if (context.isInventoryReserved()) {
            // Release inventory...
            context.setInventoryReserved(false);
        }
    }

    @Override
    public boolean isSuccessful() {
        return success;
    }
}
```

---

## The "Aha" Moment

**Remote Control:**

- Each device action is a self-contained command.
- The remote knows nothing about the devices.
- You can swap commands at runtime.
- Undo/redo works because commands store enough state to reverse themselves.

**Order Processor:**

- Each task is a self-contained unit of work.
- The processor knows nothing about the specifics.
- Rollback walks backwards through successful tasks.
- The shared `OrderContext` flows through all tasks.

**The Command pattern lets you:**

- Queue commands for later execution.
- Log commands for auditing.
- Undo/Redo actions.
- Combine commands into macros.
- Orchestrate complex workflows with rollback.
- Decouple the invoker from the receiver.

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Decouples invoker from receiver. | ⚠️ More classes (one per command). |
| ✅ Supports undo/redo. | ⚠️ Can be overkill for simple actions. |
| ✅ Supports queuing and logging. | ⚠️ Commands can grow complex if they hold too much state. |
| ✅ Supports macro commands. | |
| ✅ Supports transactional rollback. | |
| ✅ Follows the Open/Closed Principle. | |

> **Note on the "more classes" trade-off:** in modern Java, this can be significantly reduced by using lambdas — a `Command` interface with a single `execute()` method is a functional interface, so `Command lightOn = light::on;` replaces an entire `LightOnCommand` class. Undo and macro support still benefit from real classes when a command needs to hold onto state, but plenty of simple, stateless commands don't need a dedicated file at all.

---

## Command vs Strategy

People mix these up because both use interfaces and composition.

| | Strategy | Command |
| :--- | :--- | :--- |
| **Intent** | Pick a different algorithm. | Encapsulate a request (action). |
| **Focus** | *How* to do something. | *What* to do. |
| **State** | Stateless (usually). | Can hold state (undo data). |
| **Use** | Payment methods, sorting. | Undo/redo, job queues, macros. |
| **Return** | Returns a result. | Performs an action (`void`). |

---

## Real-World Applications

| Use Case | How Command Is Used |
| :--- | :--- |
| **Undo/Redo** | Text editors store commands in a history stack. |
| **Job Queues** | Tasks are command objects sent to a queue. Workers execute them. |
| **GUI Buttons** | Each button has a command object. Clicking it executes the command. |
| **Transaction Systems** | Each database operation is a command. Rollback is `undo()`. |
| **Macro Recording** | Record user actions as commands, replay them later. |
| **CI/CD Pipelines** | Each stage (Build, Test, Deploy) is a command. Rollback on failure. |
| **Microservices (Saga)** | Each service operation is a command. Compensating actions for rollback. |
| **Batch Processing** | Each step is a command. If one fails, rollback previous steps. |
| **File Upload Pipelines** | Validate → Compress → Upload → Notify. Rollback on failure. |

---

## Known Examples in the JDK

| Class | Pattern |
| :--- | :--- |
| `java.lang.Runnable` | Command pattern (`execute`-style method, `run()`). |
| `javax.swing.Action` | Command pattern with GUI actions. |
| `java.util.TimerTask` | Command pattern for scheduled execution. |
| `java.util.concurrent.Callable` | Command pattern with a return value. |

---

## Key Takeaway

The Command pattern is the foundation of job processing systems. Once you understand it, you see it everywhere:

- Task queues (RabbitMQ, SQS, Celery) → Commands.
- Transaction systems → Commands with rollback.
- Undo/Redo → Commands with reverse actions.
- Macros → Groups of commands.
- Batch jobs → Sequences of commands.

**The essence:** Encapsulate a request as an object so you can treat it like data — queue it, log it, undo it, combine it, or orchestrate it.