# Design Patterns in Java

All 23 Gang of Four (GoF) design patterns, implemented one by one in **plain Java** — no frameworks, no magic. Every pattern is runnable, explained, and covered by tests.

## Why plain Java and not Spring Boot?

Spring's container already implements many of these patterns for you behind the scenes (singleton-scoped beans, `BeanFactory`, `JdbcTemplate`, AOP proxies...). That's great for production but terrible for *learning*: the framework hides exactly the code you're trying to understand. Here, everything is written by hand so the pattern itself is the star of the show. A Spring Boot module ("patterns in the wild") may be added later as a phase 2.

## Requirements

- JDK 17+
- Maven 3.6+

## Build, test, run

```bash
mvn test          # run all tests (each pattern comes with its own)

mvn exec:java     # run the demo of the pattern currently in progress

# or run any demo explicitly:
mvn exec:java -Dexec.mainClass=com.example.patterns.creational.singleton.SingletonDemo
```

## Progress

### Creational — *how objects are created*

| Pattern | Status |
|---|---|
| Singleton | ✅ done |
| Factory Method | ✅ done |
| Builder | ✅ done |
| Abstract Factory | ✅ done |
| Prototype | ⬜ |

### Structural — *how classes are composed*

| Pattern | Status |
|---|---|
| Adapter | ✅ done |
| Bridge | ✅ done |
| Composite | ✅ done |
| Decorator | ⬜ |
| Facade | ⬜ |
| Flyweight | ⬜ |
| Proxy | ⬜ |

### Behavioral — *how objects interact*

| Pattern | Status |
|---|---|
| Chain of Responsibility | ⬜ |
| Command | ⬜ |
| Interpreter | ⬜ |
| Iterator | ⬜ |
| Mediator | ⬜ |
| Memento | ⬜ |
| Observer | ⬜ |
| State | ⬜ |
| Strategy | ⬜ |
| Template Method | ⬜ |
| Visitor | ⬜ |

## Project layout

```
src/main/java/com/naviroq/patterns/
├── creational/
│   └── singleton/        # 6 variants + demo + README explaining the trade-offs
├── structural/
└── behavioral/
src/test/java/com/example/patterns/...   # one test class per pattern
```

Each pattern lives in its own package and contains:

- the pattern classes (often several variants, with the trade-offs explained in the Javadoc),
- a `*Demo` class with a `main()` you can run,
- a `README.md` covering intent, pitfalls, and real-world uses,
- JUnit tests proving the pattern actually behaves the way it promises.

## Roadmap

1. **Creational:** Singleton → Factory Method → Builder → Abstract Factory → Prototype
2. **Behavioral:** Strategy → Observer → Template Method → Command → Iterator → State → ...
3. **Structural:** Adapter → Decorator → Facade → Proxy → Composite → ...
