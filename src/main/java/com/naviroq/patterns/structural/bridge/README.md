# Bridge Pattern

> **"Decouple an abstraction from its implementation so that the two can vary independently."**
> — *Gang of Four*

---

## Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Structure](#structure)
4. [Key Components](#key-components)
5. [Bridge vs Adapter](#bridge-vs-adapter)
6. [Running the Demo](#running-the-demo)
7. [Trade-offs](#trade-offs)
8. [When to Use This Pattern](#when-to-use-this-pattern)

---

## The Problem

Imagine you're modeling remote controls for TVs. You have two dimensions that both grow independently:

- **Abstraction:** `BasicRemote`, `AdvancedRemote`
- **Implementation:** `SonyTV`, `SamsungTV`, `LGTV`

If you try to model this with plain inheritance, you get a **class explosion** — one subclass for every combination:

```text
BasicRemoteForSony, BasicRemoteForSamsung, BasicRemoteForLG,
AdvancedRemoteForSony, AdvancedRemoteForSamsung, AdvancedRemoteForLG, ...
```

Add one more remote type or one more TV brand, and the number of classes multiplies. This is rigid, repetitive, and impossible to scale.

---

## The Solution

Split the two dimensions into **two separate class hierarchies** connected by a "bridge" — an object reference held inside the abstraction, pointing to the implementation.

- The **Abstraction** (`Remote`) defines high-level operations and *delegates* the actual work to an **Implementor** (`TV`) it holds a reference to.
- Both hierarchies can now grow independently: add a new TV brand without touching the Remote classes, or add a new Remote type without touching the TV classes.

---

## Structure

```text
bridge/
├── implementor/
│   ├── TV.java                  (The Implementor Interface)
│   ├── SonyTV.java
│   └── SamsungTV.java           (Concrete Implementors)
├── abstraction/
│   ├── Remote.java              (The Abstraction — holds a TV reference)
│   └── AdvancedRemote.java      (Refined Abstraction)
└── demo/
    └── Main.java                 (The Client)
```

---

## Key Components

### 1. The Implementor Interface (`TV`)

Defines the low-level operations that concrete implementations must provide.

```java
public interface TV {
    void turnOn();
    void turnOff();
    void setChannel(int channel);
}
```

### 2. Concrete Implementors (`SonyTV`, `SamsungTV`)

```java
public class SonyTV implements TV {
    @Override
    public void turnOn() { System.out.println("[Sony] Powering on."); }

    @Override
    public void turnOff() { System.out.println("[Sony] Powering off."); }

    @Override
    public void setChannel(int channel) {
        System.out.println("[Sony] Switching to channel " + channel);
    }
}
```

### 3. The Abstraction (`Remote`)

Holds a reference to a `TV` and delegates the actual work to it — this reference *is* the bridge.

```java
public class Remote {
    protected TV tv; // The "bridge" to the implementation

    public Remote(TV tv) {
        this.tv = tv;
    }

    public void powerOn() {
        tv.turnOn();
    }

    public void powerOff() {
        tv.turnOff();
    }

    public void goToChannel(int channel) {
        tv.setChannel(channel);
    }
}
```

### 4. The Refined Abstraction (`AdvancedRemote`)

Extends the abstraction with more functionality — but still just delegates to whatever `TV` it was given.

```java
public class AdvancedRemote extends Remote {
    public AdvancedRemote(TV tv) {
        super(tv);
    }

    public void mute() {
        tv.setChannel(0); // Simplified example
        System.out.println("Muted.");
    }
}
```

### 5. The Client (`Main`)

```java
TV sony = new SonyTV();
Remote basicRemote = new Remote(sony);
basicRemote.powerOn();
basicRemote.goToChannel(5);

TV samsung = new SamsungTV();
Remote advancedRemote = new AdvancedRemote(samsung);
advancedRemote.powerOn();
advancedRemote.goToChannel(10);
```

Any combination of Remote type and TV brand works — with zero extra classes needed for the combination itself.

---

## Bridge vs Adapter

These two patterns look structurally similar (both involve one class holding/wrapping another), but they solve different problems and are used at different times:

| | Adapter | Bridge |
| :--- | :--- | :--- |
| **When applied** | After the fact — reconciling two interfaces that already exist and don't match. | Up front — designed in from the start to prevent class explosion. |
| **Goal** | Make incompatible interfaces work together. | Let an abstraction and implementation evolve independently. |
| **Typical Use** | Wrapping legacy/third-party code. | Building extensible hierarchies (e.g., remotes + TVs, shapes + renderers). |

---

## Running the Demo

```bash
cd src/main/java/com/naviroq/patterns/structural/bridge/demo
javac Main.java && java Main
```

**Expected Output:**

```text
=== BRIDGE PATTERN DEMO ===
[Sony] Powering on.
[Sony] Switching to channel 5
[Samsung] Powering on.
[Samsung] Switching to channel 10
✅ Remote and TV hierarchies varied independently — no class explosion.
```

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Avoids a combinatorial explosion of subclasses. | ⚠️ Adds an extra layer of indirection, which can feel over-engineered for simple cases. |
| ✅ Abstraction and implementation can be extended independently. | ⚠️ Requires upfront design — harder to retrofit onto existing rigid inheritance. |
| ✅ Implementation details can be swapped at runtime. | |

---

## When to Use This Pattern

- You have two (or more) independent dimensions of variation that would otherwise multiply into dozens of subclasses.
- You want to switch implementations at runtime (e.g., swap the TV a Remote controls without changing the Remote's class).
- You're designing a system upfront and want to keep abstraction and implementation loosely coupled from the start.