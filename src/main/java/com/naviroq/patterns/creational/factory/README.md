# Factory Method

> **"Define an interface for creating an object, but let subclasses decide which class to instantiate."**
> — *Gang of Four*

---

## Intent

**Encapsulate object creation.** The Factory Method pattern defines a method for creating objects, but lets subclasses (or a centralized factory class) decide which concrete class to instantiate.

Instead of scattering `new` keywords throughout your codebase, you delegate the creation responsibility to a single, controlled location.

---

## The Problem It Solves

| Anti-Pattern (Tight Coupling) | Factory Method (Decoupled) |
| :--- | :--- |
| `Car myCar = new Sedan();` | `Car myCar = CarFactory.create(SEDAN);` |
| If you add a new `ElectricCar`, you must find every `new` statement and modify it. | You just update the factory's `switch` statement. The rest of the code remains untouched. |
| Violates the **Open/Closed Principle** (open for extension, closed for modification). | Adheres to the Open/Closed Principle. |

---

## Structure (Our Example)

We built a **Car Factory** that produces different types of cars (`Sedan`, `SUV`, `Sport`, `Limousine`, `Hatchback`).

```text
factory/
├── common/
│   └── Car.java                  (The Product Interface)
├── types/
│   ├── Sedan.java
│   ├── Suv.java
│   ├── Sport.java
│   ├── Limousine.java
│   └── Hatchback.java            (Concrete Products)
├── factory/
│   ├── SelectionTypes.java       (ENUM: SEDAN, SUV, SPORT...)
│   └── CarFactory.java           (The Factory Method)
└── demo/
    └── Main.java                 (The Client)
```

---

## Key Components

### 1. The Product Interface (`Car`)

```java
public interface Car {
    void start();
    void stop();
    void steer();
    void brake();
}
```

### 2. Concrete Products (`Sedan`, `SUV`, etc.)

Each class implements the interface with its own behavior.

```java
public class Suv implements Car {
    @Override
    public void start() {
        System.out.println("  [SUV] Engine roars! 4x4 mode ACTIVATED.");
    }
}
```

### 3. The Selection Type (`SelectionTypes` Enum)

Using an Enum provides compile-time safety—typos are caught before runtime.

```java
public enum SelectionTypes {
    SEDAN, SUV, SPORT, LIMOUSINE, HATCHBACK
}
```

### 4. The Factory Method (`CarFactory`)

```java
public class CarFactory {
    public static Car createCar(SelectionTypes type) {
        return switch (type) {
            case SEDAN -> new Sedan();
            case SUV -> new Suv();
            case SPORT -> new Sport();
            case LIMOUSINE -> new Limousine();
            case HATCHBACK -> new Hatchback();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
```

### 5. The Client (`Main.java`)

```java
Car myCar = CarFactory.createCar(SelectionTypes.SUV);
myCar.start(); // Executes SUV's start() method.
```

The client does not know about `Sedan` or `SUV`—it only knows `Car`. This is polymorphism in action.

---

## Why Enum Over String?

| Feature | String-Based | Enum-Based |
| :--- | :--- | :--- |
| **Typos** | `"suuv"` compiles, but crashes at runtime. | `SUV` is checked by the compiler. |
| **IDE Support** | No autocomplete. | Autocomplete works perfectly. |
| **Refactoring** | Hard to rename `"SUV"` to `"SportsUtility"`. | Rename the enum value, and the IDE updates all usages. |

**Verdict:** For production code, prefer Enums over Strings for factory selection.

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Decouples client code from concrete classes. | ⚠️ The factory grows as you add new types (violates OCP slightly). |
| ✅ Centralizes creation logic in one place. | ⚠️ The client must know the Enum values. |
| ✅ Promotes polymorphism and testability. | |

---

## Running the Demo

Navigate to the demo package and run `Main.java`.

```bash
cd src/main/java/com/naviroq/patterns/creational/factory/car/demo
javac Main.java && java Main
```

---

## When to Use This Pattern

- You have a class that cannot anticipate the type of objects it must create.
- You want to encapsulate creation logic in a single, maintainable location.
- You want to promote polymorphism by programming to an interface, not an implementation.

---

## Next Steps

If you need to create families of related objects (e.g., Compute + Database + Storage that must be from the same cloud provider), move on to the **Abstract Factory** pattern.