# Behavioral Design Patterns

> **"Behavioral patterns are concerned with algorithms and the assignment of responsibilities between objects — not just their structure, but how they communicate."**
> — *Gang of Four*

---

## Intent

**Creational** patterns focus on *how objects are built*, and **Structural** patterns focus on *how objects are composed together*. **Behavioral** patterns focus on a different question entirely: *how do objects talk to each other, and who's responsible for what, once the structure is in place?*

They answer questions like:

- How do I swap an algorithm's implementation without changing the code that uses it?
- How do I let one object notify many others of a change, without those objects being tightly coupled?
- How do I let an object's behavior change based on its internal state?
- How do I decouple the sender of a request from the object that handles it?

---

## Patterns in This Folder

| Pattern | Core Idea | Real-World Analogy |
| :--- | :--- | :--- |
| **[Strategy](./strategy/README.md)** | Encapsulates interchangeable algorithms behind a common interface, selectable at runtime. | Choosing how to pay at checkout — card, PayPal, or crypto. |
| **[Observer](./observer/README.md)** | Lets one object (the subject) notify many dependents (observers) automatically when its state changes. | A weather station broadcasting readings to a phone, a dashboard, and an alert system at once. |
| Command | Turns a request into a standalone object, so it can be queued, logged, or undone. | A restaurant order slip — decoupled from who cooks it. |
| State | Lets an object change its behavior when its internal state changes, as if it changed class. | A traffic light behaving differently depending on whether it's red, yellow, or green. |
| Template Method | Defines the skeleton of an algorithm, letting subclasses override specific steps. | A recipe outline where the steps are fixed but the ingredients vary. |
| Chain of Responsibility | Passes a request along a chain of handlers until one handles it. | Escalating a support ticket through tiers until someone can resolve it. |
| Mediator | Centralizes complex communication between objects through a single mediator object. | An air traffic controller coordinating planes that don't talk directly to each other. |
| Iterator | Provides a way to access elements of a collection sequentially without exposing its structure. | A TV remote's "next channel" button — no need to know how channels are stored. |
| Memento | Captures and restores an object's internal state without violating encapsulation. | A "save game" checkpoint you can reload later. |
| Visitor | Lets you add new operations to a group of classes without modifying them. | A tax auditor who "visits" different account types and applies different rules to each. |

---

## Behavioral vs Structural vs Creational (Quick Recap)

| | Creational | Structural | Behavioral |
| :--- | :--- | :--- | :--- |
| **Concern** | How objects are *built*. | How objects are *composed*. | How objects *communicate and share responsibility*. |
| **Example Question** | "Which class should I instantiate?" | "How do these classes fit together?" | "Who's responsible for this, and how do these objects talk?" |
| **Patterns Covered So Far** | Singleton, Factory Method, Abstract Factory, Builder | Adapter, Bridge, Composite, Decorator, Facade, Proxy | Strategy, Observer |

---

## How to Use This Folder

Each pattern has its own subfolder with its own `README.md`, walking through:

- The problem it solves
- The structure/participants
- A simple, runnable Java example (matching the actual implementation in that folder)
- Trade-offs and when to use it

Start with **Strategy** if you're new to behavioral patterns — it's the most approachable, since it just swaps out algorithms behind one interface. **Observer** builds on a similar "interface + interchangeable implementations" idea, but adds a one-to-many notification mechanism on top.

---

## Next Steps

Command, State, and Template Method are natural next stops — each tackles a different flavor of "who owns this behavior, and how does it change at runtime."