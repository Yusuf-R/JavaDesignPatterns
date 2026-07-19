# Decorator Pattern

> **"Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality."**
> — *Gang of Four*

---

## Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Structure](#structure)
4. [Key Components](#key-components)
5. [Stacking Decorators](#stacking-decorators)
6. [Running the Demo](#running-the-demo)
7. [Trade-offs](#trade-offs)
8. [When to Use This Pattern](#when-to-use-this-pattern)

---

## The Problem

Imagine ordering a coffee. A base `Coffee` can have any combination of add-ons: milk, sugar, whipped cream, caramel. If you try to model every combination with subclassing, you get a **class explosion**:

```text
Coffee, CoffeeWithMilk, CoffeeWithSugar, CoffeeWithMilkAndSugar,
CoffeeWithMilkAndSugarAndCream, CoffeeWithCaramel, ...
```

Every new topping doubles the number of possible subclasses. And it's all fixed at compile time — a customer can't dynamically add "extra caramel" to an order that's already been built.

---

## The Solution

Instead of subclassing for every combination, wrap the base object in **Decorator** objects — one per add-on. Each decorator implements the *same interface* as the object it wraps, adds its own behavior, and then delegates to the object inside it.

Decorators can be **stacked in any order, at runtime**, giving you every combination without a single extra subclass.

---

## Structure

```text
decorator/
├── common/
│   └── Coffee.java              (The Component Interface)
├── concrete/
│   └── SimpleCoffee.java        (The Concrete Component — base object)
├── decorator/
│   ├── CoffeeDecorator.java     (Abstract Decorator — implements Coffee, wraps a Coffee)
│   ├── MilkDecorator.java
│   ├── SugarDecorator.java
│   └── WhippedCreamDecorator.java  (Concrete Decorators)
└── demo/
    └── Main.java                 (The Client)
```

---

## Key Components

### 1. The Component Interface (`Coffee`)

Both the base object and every decorator implement this same interface.

```java
public interface Coffee {
    String getDescription();
    double getCost();
}
```

### 2. The Concrete Component (`SimpleCoffee`)

The base object being decorated.

```java
public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public double getCost() {
        return 2.00;
    }
}
```

### 3. The Abstract Decorator (`CoffeeDecorator`)

Implements `Coffee`, but also **holds a reference to another `Coffee`** — this is what allows decorators to wrap either the base object or each other.

```java
public abstract class CoffeeDecorator implements Coffee {
    protected final Coffee wrappedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.wrappedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription(); // Delegates by default
    }

    @Override
    public double getCost() {
        return wrappedCoffee.getCost(); // Delegates by default
    }
}
```

### 4. Concrete Decorators (`MilkDecorator`, `SugarDecorator`, etc.)

Each one overrides the methods to **add its own behavior on top of** the delegated call.

```java
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return wrappedCoffee.getDescription() + " + Milk";
    }

    @Override
    public double getCost() {
        return wrappedCoffee.getCost() + 0.50;
    }
}
```

### 5. The Client (`Main`)

```java
Coffee order = new SimpleCoffee();
order = new MilkDecorator(order);
order = new SugarDecorator(order);
order = new WhippedCreamDecorator(order);

System.out.println(order.getDescription() + " = $" + order.getCost());
```

Each layer wraps the previous one, adding its own description and cost on top — the client builds up the object piece by piece, entirely at runtime.

---

## Stacking Decorators

The order you wrap in matters for *description order*, but the math always adds up the same way:

```text
SimpleCoffee                          → "Coffee"                          → $2.00
MilkDecorator(SimpleCoffee)           → "Coffee + Milk"                   → $2.50
SugarDecorator(MilkDecorator(...))    → "Coffee + Milk + Sugar"           → $2.75
WhippedCreamDecorator(SugarDecorator(...)) → "Coffee + Milk + Sugar + Whipped Cream" → $3.25
```

Each decorator only knows about the object directly inside it — it has no idea how many other layers are wrapped further in, or how many are wrapped around it.

---

## Running the Demo

```bash
cd src/main/java/com/naviroq/patterns/structural/decorator/demo
javac Main.java && java Main
```

**Expected Output:**

```text
=== DECORATOR PATTERN DEMO ===
Coffee = $2.0
Coffee + Milk = $2.5
Coffee + Milk + Sugar = $2.75
Coffee + Milk + Sugar + Whipped Cream = $3.25
```

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Add responsibilities to objects dynamically, at runtime. | ⚠️ Can result in many small classes that are hard to track. |
| ✅ Avoids a combinatorial class explosion from subclassing every combination. | ⚠️ Debugging can be harder — a call passes through several stacked layers. |
| ✅ Follows the Open/Closed Principle — new decorators don't touch existing code. | ⚠️ Order of wrapping can matter and needs to be well understood by whoever builds the stack. |

---

## When to Use This Pattern

- You need to add optional, combinable behaviors to an object (toppings, formatting, logging, compression).
- Subclassing would produce too many rigid combinations.
- You want responsibilities to be added or removed **at runtime**, not fixed at compile time.