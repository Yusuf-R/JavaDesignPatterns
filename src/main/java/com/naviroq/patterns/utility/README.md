# Object-Oriented Relationships: The 3 Core Pillars

> A quick reference guide for distinguishing class architectures based on **Identity**, **Structure**, and **Interaction**.

---

## Table of Contents

1. [IS-A (Identity)](#1-is-a-identity)
2. [HAS-A (Structure)](#2-has-a-structure)
3. [USES-A (Interaction)](#3-uses-a-interaction)
4. [Quick Comparison](#quick-comparison)
5. [Why This Matters](#why-this-matters)
6. [Rule of Thumb](#rule-of-thumb)

---

## 1. IS-A (Identity)

- **Architectural Term:** Inheritance / Realization
- **The Blueprint Code:** Uses `extends` or `implements`.
- **The Rule:** This defines what an object fundamentally *is* down at its core DNA layer. The child class inherits everything from the parent.

### Simple Example

```java
// Parent Blueprint
public class Vehicle {
    public void move() { System.out.println("Moving..."); }
}

// Child Class: A Tesla fundamentally IS A Vehicle
public class Tesla extends Vehicle {
    // Inherits all DNA properties automatically
}
```

```java
Tesla myTesla = new Tesla();
myTesla.move(); // Works! Inherited directly from Vehicle.
```

**Key Insight:** IS-A is permanent and structural. A `Tesla` doesn't just *contain* a `Vehicle` or *borrow* its behavior — it literally *is* one. This relationship is fixed at compile time and can't change at runtime.

---

## 2. HAS-A (Structure)

- **Architectural Term:** Composition / Aggregation
- **The Blueprint Code:** A class holds a **reference field** to another class.
- **The Rule:** This defines what an object *owns* or is *made of*. The object is built from other objects, but it doesn't inherit their behavior — it delegates to them.

### Simple Example

```java
// The Part
public class Engine {
    public void start() { System.out.println("Engine roaring to life..."); }
}

// The Whole: A Car HAS AN Engine
public class Car {
    private Engine engine; // Car is built FROM an Engine

    public Car() {
        this.engine = new Engine();
    }

    public void start() {
        engine.start(); // Delegates the work to the Engine
    }
}
```

```java
Car myCar = new Car();
myCar.start(); // Car delegates to its internal Engine.
```

**Key Insight:** HAS-A is about ownership, not identity. A `Car` is **not** an `Engine` — it just contains one as a field. If the `Car` is destroyed, its `Engine` typically goes with it (this tighter form is called **Composition**). If the part could outlive or be swapped independently of the whole — like a `Car` holding a reference to a `Driver` — that looser form is called **Aggregation**.

---

## 3. USES-A (Interaction)

- **Architectural Term:** Dependency / Association
- **The Blueprint Code:** A class receives another object as a **method parameter** (not a stored field).
- **The Rule:** This defines a temporary, momentary interaction. The object is borrowed just long enough to complete a task, then let go.

### Simple Example

```java
// The Tool
public class Key {
    public void turn() { System.out.println("Key turning in ignition..."); }
}

// The Actor: A Car USES A Key, but doesn't own or store it
public class Car {
    public void start(Key key) { // Key is only passed in, not stored
        key.turn();
        System.out.println("Car started!");
    }
}
```

```java
Car myCar = new Car();
Key myKey = new Key();
myCar.start(myKey); // Car "uses" the Key for this one call, then forgets it.
```

**Key Insight:** USES-A is the loosest relationship. The `Car` doesn't own a `Key` field — it simply needs one to perform a specific action. Once `start()` returns, the `Car` has no lingering connection to that particular `Key` object.

---

## Quick Comparison

| Relationship | Java Syntax | Real-World Analogy | Lifespan of the Bond |
| :--- | :--- | :--- | :--- |
| **IS-A** | `extends` / `implements` | A Tesla *is* a Vehicle. | Permanent — fixed at compile time. |
| **HAS-A** | Field reference (`private Engine engine;`) | A Car *has* an Engine. | Tied to the owning object's lifetime. |
| **USES-A** | Method parameter (`start(Key key)`) | A Car *uses* a Key to start. | Momentary — only for the duration of the call. |

---

## Why This Matters

Choosing the wrong relationship is a common source of bad design:

- **Overusing IS-A** (inheritance) creates rigid class hierarchies that are hard to change later — this is why the design principle "favor composition over inheritance" exists.
- **Confusing HAS-A with USES-A** leads to classes storing objects they don't actually need to hold onto, bloating memory and creating hidden dependencies.
- **Correctly identifying the relationship** up front makes your class diagrams — and your code — much easier to reason about and extend.

---

## Rule of Thumb

Ask yourself these three questions about any two classes:

1. **"Is Class A fundamentally a type of Class B?"** → If yes, it's **IS-A** (inheritance).
2. **"Does Class A own/contain Class B as part of its structure?"** → If yes, it's **HAS-A** (composition/aggregation).
3. **"Does Class A just need Class B temporarily to do something?"** → If yes, it's **USES-A** (dependency).

> When in doubt, default to **HAS-A** or **USES-A** over **IS-A**. Composition is more flexible and easier to refactor than inheritance.