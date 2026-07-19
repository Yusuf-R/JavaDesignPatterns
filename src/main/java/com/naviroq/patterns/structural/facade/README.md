# Facade Pattern

> **"Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use."**
> — *Gang of Four*

---

## Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Structure](#structure)
4. [Key Components](#key-components)
5. [Facade Doesn't Hide the Subsystem](#facade-doesnt-hide-the-subsystem)
6. [Running the Demo](#running-the-demo)
7. [Trade-offs](#trade-offs)
8. [When to Use This Pattern](#when-to-use-this-pattern)

---

## The Problem

Starting a car involves coordinating several independent subsystems: the `Engine`, the `FuelInjector`, the `Battery`, and the `Starter`. If the client had to orchestrate all of this manually every time, it would look like this:

```java
Battery battery = new Battery();
FuelInjector fuelInjector = new FuelInjector();
Starter starter = new Starter();
Engine engine = new Engine();

battery.checkVoltage();
fuelInjector.pumpFuel();
starter.engage();
engine.ignite();
```

This is a lot to know and get right, in the exact correct order, every single time you just want to "start the car." The client is coupled to four different classes and their internal call sequence.

---

## The Solution

Introduce a **Facade** — a single, simplified class (`CarFacade`) that wraps the whole subsystem and exposes one clean method: `startCar()`. Internally, it still coordinates all four subsystem classes in the correct order — but the client no longer needs to know any of that.

---

## Structure

```text
facade/
├── subsystem/
│   ├── Battery.java
│   ├── FuelInjector.java
│   ├── Starter.java
│   └── Engine.java              (The Complex Subsystem Classes)
├── facade/
│   └── CarFacade.java           (The Simplified Interface)
└── demo/
    └── Main.java                 (The Client)
```

---

## Key Components

### 1. The Subsystem Classes

Each one handles a narrow piece of the overall startup process, independently of the others.

```java
public class Battery {
    public void checkVoltage() {
        System.out.println("[Battery] Voltage OK.");
    }
}

public class FuelInjector {
    public void pumpFuel() {
        System.out.println("[FuelInjector] Fuel pumped to cylinders.");
    }
}

public class Starter {
    public void engage() {
        System.out.println("[Starter] Starter motor engaged.");
    }
}

public class Engine {
    public void ignite() {
        System.out.println("[Engine] Ignition successful. Engine running.");
    }
}
```

### 2. The Facade (`CarFacade`)

Holds references to each subsystem class and coordinates them in the correct order, behind one simple method.

```java
public class CarFacade {
    private final Battery battery = new Battery();
    private final FuelInjector fuelInjector = new FuelInjector();
    private final Starter starter = new Starter();
    private final Engine engine = new Engine();

    public void startCar() {
        battery.checkVoltage();
        fuelInjector.pumpFuel();
        starter.engage();
        engine.ignite();
        System.out.println("✅ Car started successfully.");
    }
}
```

### 3. The Client (`Main`)

```java
CarFacade car = new CarFacade();
car.startCar(); // One call. The Facade handles the rest.
```

The client interacts with a single method call instead of four separate classes and a specific sequence it would otherwise need to memorize.

---

## Facade Doesn't Hide the Subsystem

A common misconception is that Facade makes the subsystem classes private or inaccessible. It doesn't — `Battery`, `FuelInjector`, `Starter`, and `Engine` are all still there and still usable directly if a client needs finer control.

| Misconception | Reality |
| :--- | :--- |
| "Facade replaces the subsystem classes." | ❌ No. The subsystem classes still exist and work independently. |
| "Clients can no longer access the subsystem directly." | ❌ No. Facade is an *additional*, simplified entry point — not the only one. |
| "Facade adds new behavior." | ❌ No. It only coordinates existing behavior; it doesn't introduce new logic of its own. |

---

## Running the Demo

```bash
cd src/main/java/com/naviroq/patterns/structural/facade/demo
javac Main.java && java Main
```

**Expected Output:**

```text
=== FACADE PATTERN DEMO ===
[Battery] Voltage OK.
[FuelInjector] Fuel pumped to cylinders.
[Starter] Starter motor engaged.
[Engine] Ignition successful. Engine running.
✅ Car started successfully.
```

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Simplifies client code by hiding subsystem complexity behind one interface. | ⚠️ Can become a "god object" if it grows to do too much. |
| ✅ Reduces coupling between client code and multiple subsystem classes. | ⚠️ Adds a layer that may be unnecessary for simple subsystems. |
| ✅ Subsystem classes remain independently usable — nothing is hidden or restricted. | |

---

## When to Use This Pattern

- A subsystem is made up of many classes that must be used together in a specific order.
- You want to give client code a simple entry point without removing access to the underlying complexity for advanced use cases.
- You're integrating with a complex third-party library and want to expose only what your application actually needs.