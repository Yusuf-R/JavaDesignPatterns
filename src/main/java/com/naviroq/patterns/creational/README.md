# Creational Design Pattern: Singleton

> **"Ensure a class has only one instance, and provide a global point of access to it."**
> — *Gang of Four*

---

## 📖 Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Implementations](#implementations)
4. [Which One Should You Use?](#which-one-should-you-use)
5. [Running the Demo](#running-the-demo)
6. [Key Takeaways](#key-takeaways)

---

## The Problem

In many applications, certain resources must exist exactly once:

- **Configuration Manager** – Loading settings from a file or database.
- **Database Connection Pool** – Managing a limited number of connections.
- **Logging Service** – Writing logs to a single, consistent file.
- **Hardware Interface** – Controlling a physical device (e.g., printer, camera).

"Just be careful not to call `new` twice" is **not a design** — it's a **hope**. The Singleton pattern provides a **guarantee**, not a suggestion.

### The Core Challenge

| Challenge | Why It Matters |
| :--- | :--- |
| **Single Instance** | Only one object should ever exist. |
| **Global Access** | All parts of the application need to reach it. |
| **Thread Safety** | Multiple threads must not create duplicate instances. |
| **Lazy vs. Eager** | When should the object be created? |

---

## The Solution

The Singleton pattern solves this through three key mechanisms:

1. **Private Constructor** – Prevents external instantiation (`new` is forbidden).
2. **Static Field** – Holds the single instance in a class-level variable.
3. **Static Accessor** – Provides a global method to retrieve the instance.

```java
public class Singleton {
    // 1. Private Constructor
    private Singleton() { }

    // 2. Static Field (the vault)
    private static Singleton instance;

    // 3. Static Accessor (the gatekeeper)
    public static Singleton getInstance() {
        // Creation logic goes here
        return instance;
    }
}
```

---

## Implementations

This repository demonstrates four distinct implementations, each with its own trade-offs.

### 1. Eager Singleton (`AppConfigEager`)

**The Pre-Heated Oven**

```java
private static final AppConfigEager INSTANCE = new AppConfigEager();
```

- **When it's created:** At class-load time (JVM guarantees thread-safety).
- **Thread-Safe:** ✅ Yes (JVM ClassLoader lock).
- **Performance:** Excellent after startup.
- **Best For:** Core dependencies that are always needed.

**Advantages:**
- Simplest implementation.
- No synchronization overhead.
- JVM guarantees absolute thread-safety.

**Drawbacks:**
- ⚠️ Startup Cost: Object is created even if never used.
- ⚠️ Resource Waste: Wasted memory/time if the object is heavy and rarely accessed.
- ⚠️ Exception Risk: Constructor errors crash the application at startup (can be mitigated with static blocks).

### 2. Lazy Singleton (`AppConfigLazy`)

**The Procrastinator (Intentionally Broken)**

```java
public static AppConfigLazy getInstance() {
    if (instance == null) {
        instance = new AppConfigLazy();
    }
    return instance;
}
```

- **When it's created:** On the first call to `getInstance()`.
- **Thread-Safe:** ❌ No. Multiple threads can create multiple instances.
- **Performance:** Fast when single-threaded, but broken in multi-thread.
- **Best For:** Nothing in production. This is a learning example.

**The Race Condition Breakdown:**
1. Thread A checks `if (instance == null)` → true.
2. Thread B checks `if (instance == null)` → true (before A finishes).
3. Both threads create separate objects.
4. Result: Two instances exist. Singleton is violated.

**Why Keep This Code?**
It serves as a visual proof of the race condition. Run the demo and watch it fail.

### 3. Synchronized Lazy Singleton (`AppConfigLazySync`)

**The Procrastinator with a Bouncer**

```java
public static synchronized AppConfigLazySync getInstance() {
    if (instance == null) {
        instance = new AppConfigLazySync();
    }
    return instance;
}
```

- **When it's created:** On the first call, but only one thread at a time.
- **Thread-Safe:** ✅ Yes (synchronized method).
- **Performance:** Poor after creation (all threads queue for the lock).
- **Best For:** Simple applications where performance is not critical.

**How It Works:**
- The `synchronized` keyword forces threads to line up single-file.
- Only one thread can enter the method at a time.
- After the object exists, threads still queue to check null.

**Drawback:**
- ⚠️ Performance Bottleneck: Even after creation, every call acquires a lock.

### 4. Double-Checked Locking (DCL) Singleton (`AppConfigLazyDCL`)

**The Optimized Procrastinator**

```java
private static volatile AppConfigLazyDCL instance = null;

public static AppConfigLazyDCL getInstance() {
    if (instance == null) {                 // 1st check (no lock)
        synchronized (AppConfigLazyDCL.class) {
            if (instance == null) {         // 2nd check (inside lock)
                instance = new AppConfigLazyDCL();
            }
        }
    }
    return instance;
}
```

- **When it's created:** On the first call, with minimal locking.
- **Thread-Safe:** ✅ Yes (volatile + synchronized block).
- **Performance:** Excellent (no locking after creation).
- **Best For:** High-performance applications where the object is accessed frequently.

**The Magic of `volatile`:**
- Prevents instruction reordering.
- Guarantees the object is fully constructed before becoming visible to other threads.
- Without `volatile`, Java's JVM might reorder constructor steps, causing threads to see a partially constructed object.

**Key Insight:**
Only the first thread pays the locking cost. All subsequent threads bypass the lock entirely.

---

## Which One Should You Use?

| Approach | Thread-Safe | Performance After Creation | Best For |
| :--- | :--- | :--- | :--- |
| **Eager** | ✅ (JVM) | ⚡ Excellent | Always-needed core dependencies |
| **Lazy (Broken)** | ❌ | ⚡ Excellent | Never use in production |
| **LazySync** | ✅ (method lock) | 🐢 Poor (all threads queue) | Simple, low-traffic apps |
| **LazyDCL** | ✅ (volatile + block lock) | ⚡ Excellent | High-performance, high-traffic apps |

**Senior Engineer's Rule of Thumb:**
- Start with **Eager**. It's simple, safe, and JVM-backed.
- Only use **LazyDCL** if you have measurable performance metrics that prove Eager is too slow.
- **Never** use broken Lazy. It's a teaching tool, not a production pattern.

---

## Running the Demo

The `Main.java` class runs a complete validation suite:

```bash
javac com/naviroq/patterns/creational/singleton/SingletonDemo.java
java com.naviroq.patterns.creational.singleton.demo.SingletonDemoletonDemo
```

Expected Output:

```text
=== TESTING EAGER SINGLETON ===
Eager: Configuration loaded at class-load time.
Eager1: com.naviroq...AppConfigEager@1b6d3586
Eager2: com.naviroq...AppConfigEager@1b6d3586
eager1 == eager2? -> true

=== TESTING LAZY SINGLETON (Multi-thread Attack) ===
LazyThread-0 entered Lazy getInstance()
LazyThread-0 saw instance is NULL. Creating...
LazyThread-1 entered Lazy getInstance()
LazyThread-1 saw instance is NULL. Creating...
   [Lazy Constructor] Starting to build object...
   [Lazy Constructor] Starting to build object...
   [Lazy Constructor] Finished building object!
   [Lazy Constructor] Finished building object!

--- AppConfigLazy Results ---
Thread-0 got: AppConfigLazy@4554617c
Thread-1 got: AppConfigLazy@1b6d3586   <--- DIFFERENT OBJECTS!
❌ LAZY SINGLETON IS BROKEN!

=== TESTING LAZY SYNC SINGLETON (The Cure) ===
   [Sync Constructor] Starting to build object...
   [Sync Constructor] Finished building object!

--- AppConfigLazySync Results ---
SyncThread-0 got: AppConfigLazySync@74a14482
SyncThread-1 got: AppConfigLazySync@74a14482
✅ SYNC LAZY SINGLETON SURVIVED!

=== TESTING LAZY DCL SINGLETON (Performance Optimized) ===
   [DCL Constructor] Starting to build object...
   [DCL Constructor] Finished building object!

--- AppConfigLazyDCL Results ---
DCLThread-0 got: AppConfigLazyDCL@1540e19d
DCLThread-1 got: AppConfigLazyDCL@1540e19d
✅ DCL LAZY SINGLETON SURVIVED!
   💡 Performance win: Only the first thread waited for the lock.
```

---

## Key Takeaways

**What You Learned:**

- **Private Constructor** → The lock that prevents external `new` calls.
- **Static Field** → The vault that holds the single instance.
- **Eager** → Simple, safe, but pre-heats even when unused.
- **Lazy** → Fast, but broken in multi-threaded environments.
- **Synchronized** → Safe, but creates a performance bottleneck.
- **Double-Checked Locking** → The best of both worlds: safe + fast.
- **Volatile** → Prevents instruction reordering, ensuring full object construction visibility.

**The Deeper Lesson:**

The Singleton pattern teaches us that "design is about trade-offs":

- Eager trades startup time for simplicity.
- Lazy trades safety for speed.
- Sync trades speed for safety.
- DCL trades code complexity for optimal performance.

---

## Next Steps

Explore other Creational Patterns in this repository:

- **Builder** – Construct complex objects step-by-step.
- **Factory Method** – Delegate object creation to subclasses.
- **Abstract Factory** – Create families of related objects.

Or revisit this pattern and challenge yourself:

- Can you implement a `ThreadLocal` Singleton?
- How would you handle serialization in a Singleton?
- What about reflection attacks on the private constructor?

## 🛡️ Vulnerabilities & Defenses

Singletons are not inherently secure. They can be broken by:

| Attack | How it breaks | Defense |
| :--- | :--- | :--- |
| **Reflection** | Bypasses `private` constructor via `setAccessible(true)`. | Throw `RuntimeException` in constructor if instance exists. |
| **Serialization** | Creates a new object during deserialization. | Implement `readResolve()` to return the existing instance. |
| **Cloning** | Creates a field-by-field copy. | **Don't implement `Cloneable`.** Override and throw exception. |


## Defense Mechanisms: Bulletproofing the Singleton

Even with a private constructor and a solid `getInstance()` implementation, the JVM has several backdoors that bypass your Singleton logic entirely. A determined (or careless) piece of code can still create a second instance — unless you explicitly defend against it.


Even with a private constructor and a solid `getInstance()` implementation, the JVM has several backdoors that bypass your Singleton logic entirely. A determined (or careless) piece of code can still create a second instance — unless you explicitly defend against it.

| Attack | Bypasses `getInstance()`? | Affects Eager? | Affects Lazy? |
| :--- | :--- | :--- | :--- |
| **Reflection** | ✅ Yes | ✅ Yes | ✅ Yes |
| **Serialization/Deserialization** | ✅ Yes | ✅ Yes | ✅ Yes |
| **Cloning** | ✅ Yes | ✅ Yes | ✅ Yes |

### 1. Reflection Attack (The Master Key)

Reflection ignores the `private` keyword entirely. It calls `new AppConfigEager()` or `new AppConfigLazy()` directly through hidden JVM APIs, completely skipping `getInstance()`.

**The Fix — guard inside the constructor:**

```java
private AppConfigEager() {
    if (INSTANCE != null) {
        throw new RuntimeException("Reflection attack blocked!");
    }
    // ... actual initialization
}
```

### 2. Serialization / Deserialization (The Teleporter)

Serialization never touches your constructor at all. It uses a JVM backdoor to rebuild an object straight from bytes — producing a brand-new copy regardless of whether the original was Eager or Lazy.

**The Fix — `readResolve()`:**

```java
protected Object readResolve() {
    return getInstance(); // Overwrites the deserialized copy with the real one.
}
```

The JVM calls this method automatically right after rebuilding the object from bytes. We discard that byte-copy and hand back the real, existing instance instead.

### 3. Cloning (The Copy Machine)

`clone()` performs a field-by-field copy of an object and has no awareness of Singleton rules.

**The Fix — override and block:**

```java
@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Cannot clone a Singleton!");
}
```

Or simpler still: **don't implement `Cloneable`** in the first place. Since `Object.clone()` is `protected`, no outside code can call it unless you explicitly expose it via `Cloneable` — so leaving it unimplemented blocks the attack by default.

### Key Insight

None of these three attacks route through `getInstance()` — that's exactly what makes them dangerous. They exploit JVM-level backdoors (reflection APIs, the deserialization pipeline, `Object.clone()`) that exist *outside* your carefully designed accessor logic. A truly bulletproof Singleton has to defend at every entry point an object can be created from, not just the one you intended.

---

## License

This project is for educational purposes. Use it freely to learn, experiment, and grow as a developer.

Happy Coding! 🚀