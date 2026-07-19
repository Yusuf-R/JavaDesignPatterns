# Adapter Pattern

> **"Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces."**
> — *Gang of Four*

---

## Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Structure](#structure)
4. [Key Components](#key-components)
5. [Object Adapter vs Class Adapter](#object-adapter-vs-class-adapter)
6. [Running the Demo](#running-the-demo)
7. [Trade-offs](#trade-offs)
8. [When to Use This Pattern](#when-to-use-this-pattern)

---

## The Problem

You have existing code that expects one interface, but the class you want to plug in speaks a completely different, incompatible interface — and you can't (or shouldn't) modify either one.

**Classic example:** Your application expects electricity from a `SocketPlug` (Type A, US-style), but the appliance you bought only has a `EuropeanPlug` (Type C). Neither the wall socket nor the appliance can be redesigned — you need something in between.

```java
// What your system expects
public interface SocketPlug {
    void connect();
}

// What you actually have — incompatible shape
public class EuropeanPlug {
    public void plugIntoEuropeanSocket() {
        System.out.println("Connected via European round-pin socket.");
    }
}
```

There's no way to pass a `EuropeanPlug` where a `SocketPlug` is expected — the method names don't even match.

---

## The Solution

Introduce an **Adapter** — a class that implements the interface the client expects (`SocketPlug`), while internally holding and delegating to the incompatible object (`EuropeanPlug`).

The client never knows an adapter is involved. It just calls `connect()`, and the Adapter translates that call into whatever the wrapped object actually understands.

---

## Structure

```text
adapter/
├── target/
│   └── SocketPlug.java         (The Interface the Client Expects)
├── adaptee/
│   └── EuropeanPlug.java       (The Incompatible Existing Class)
├── adapter/
│   └── PlugAdapter.java        (Translates SocketPlug calls into EuropeanPlug calls)
└── demo/
    └── Main.java                (The Client)
```

---

## Key Components

### 1. The Target Interface (`SocketPlug`)

This is the interface the client code already knows how to use.

```java
public interface SocketPlug {
    void connect();
}
```

### 2. The Adaptee (`EuropeanPlug`)

The existing, incompatible class — we can't change its method names or signatures.

```java
public class EuropeanPlug {
    public void plugIntoEuropeanSocket() {
        System.out.println("Connected via European round-pin socket.");
    }
}
```

### 3. The Adapter (`PlugAdapter`)

Implements the interface the client expects, and internally delegates to the adaptee.

```java
public class PlugAdapter implements SocketPlug {
    private final EuropeanPlug europeanPlug;

    public PlugAdapter(EuropeanPlug europeanPlug) {
        this.europeanPlug = europeanPlug;
    }

    @Override
    public void connect() {
        // Translates the client's call into the adaptee's actual method
        europeanPlug.plugIntoEuropeanSocket();
    }
}
```

### 4. The Client (`Main`)

```java
EuropeanPlug myAppliance = new EuropeanPlug();
SocketPlug adapter = new PlugAdapter(myAppliance);

adapter.connect(); // Client only ever talks to SocketPlug.
```

The client calls `connect()` and has no idea a `EuropeanPlug` is even involved underneath.

---

## Object Adapter vs Class Adapter

Java only supports single inheritance, so there are two ways to build an Adapter:

| Approach | How It Works | Trade-off |
| :--- | :--- | :--- |
| **Object Adapter** (shown above) | Adapter *holds a reference* to the Adaptee and delegates to it. | ✅ Flexible — can adapt subclasses of the Adaptee too. Preferred in Java. |
| **Class Adapter** | Adapter *extends* the Adaptee and implements the Target interface. | ⚠️ Only works if the Adaptee isn't `final`, and burns your one shot at inheritance. |

**Verdict:** Favor the **Object Adapter** approach — it's more flexible and doesn't lock up your inheritance chain.

---

## Running the Demo

```bash
cd src/main/java/com/naviroq/patterns/structural/adapter/demo
javac Main.java && java Main
```

**Expected Output:**

```text
=== ADAPTER PATTERN DEMO ===
Connected via European round-pin socket.
✅ Appliance powered on successfully through the adapter.
```

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Lets incompatible interfaces work together without modifying either. | ⚠️ Adds an extra layer of indirection. |
| ✅ Keeps the Adaptee's original code untouched. | ⚠️ Overuse can make the codebase harder to trace (too many small wrapper classes). |
| ✅ Client code stays clean and decoupled from third-party/legacy classes. | |

---

## When to Use This Pattern

- You need to integrate a third-party library or legacy class whose interface doesn't match what your system expects.
- You want to reuse an existing class but can't modify its source code.
- You're migrating from an old interface to a new one and need both to coexist temporarily.