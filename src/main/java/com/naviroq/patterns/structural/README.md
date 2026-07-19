# Structural Design Patterns

> **"Structural patterns are concerned with how classes and objects are composed to form larger structures."**
> — *Gang of Four*

---

## Intent

While **Creational** patterns (Singleton, Factory Method, Abstract Factory, Builder) focus on *how objects are created*, **Structural** patterns focus on *how objects and classes are combined* to form bigger, more capable structures — without making that structure rigid or hard to change.

They answer questions like:

- How do I make two incompatible interfaces work together?
- How do I let an abstraction and its implementation evolve independently?
- How do I add responsibilities to an object without subclassing it?
- How do I treat a group of objects the same way I treat a single object?

---

## Patterns in This Folder

| Pattern | Core Idea | Real-World Analogy |
| :--- | :--- | :--- |
| **[Adapter](./adapter/README.md)** | Converts one interface into another that a client expects. | A power plug adapter for foreign sockets. |
| **[Bridge](./bridge/README.md)** | Decouples an abstraction from its implementation so both can vary independently. | A universal remote that works across different TV brands. |
| **Composite** *(in progress)* | Treats individual objects and groups of objects uniformly. | A folder that can contain files or more folders. |
| Decorator | Adds new behavior to an object dynamically, without altering its structure. | Adding toppings to a base pizza. |
| Facade | Provides a simplified interface to a complex subsystem. | A car's ignition button hiding the engine's complexity. |
| Flyweight | Shares common state across many objects to save memory. | Reusing the same character glyphs in a text editor. |
| Proxy | Provides a stand-in/placeholder that controls access to another object. | A credit card acting as a proxy for cash in a bank account. |

---

## Structural vs Creational (Quick Recap)

| | Creational | Structural |
| :--- | :--- | :--- |
| **Concern** | How objects are *built*. | How objects are *composed*. |
| **Example Question** | "Which class should I instantiate?" | "How do these classes fit together?" |
| **Patterns Covered So Far** | Singleton, Factory Method, Abstract Factory, Builder | Adapter, Bridge (Composite in progress) |

---

## How to Use This Folder

Each pattern has its own subfolder with its own `README.md`, walking through:

- The problem it solves
- The structure/participants
- A simple, runnable Java example
- Trade-offs and when to use it

Start with **Adapter** if you're new to structural patterns — it's the most intuitive entry point. **Bridge** builds on similar ideas but solves a slightly different problem (decoupling abstraction from implementation rather than reconciling mismatched interfaces).

---

## Next Steps

Once Composite is complete, this table and folder structure will be updated to include it alongside Adapter and Bridge.