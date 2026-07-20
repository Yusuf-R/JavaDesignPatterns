# Proxy Pattern

> **"Provide a surrogate or placeholder for another object to control access to it."**
> — *Gang of Four*

---

## Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Structure](#structure)
4. [Key Components](#key-components)
5. [Types of Proxy](#types-of-proxy)
6. [Running the Demo](#running-the-demo)
7. [Trade-offs](#trade-offs)
8. [When to Use This Pattern](#when-to-use-this-pattern)

---

## The Problem

Spinning up an EC2 instance to run a computation is **expensive** — in our example, it takes 5 full seconds per call. If a client calls `compute()` with the same input multiple times, there's no reason to pay that cost again. But the `EC2Instance` class itself has no concept of caching — it just does the work, every single time, no questions asked.

We don't want to pollute `EC2Instance` with caching logic (that's not its job), and we don't want the client to have to manage a cache manually every time it calls the service.

---

## The Solution

Introduce a **Proxy** — `EC2ProxyInstance` — that implements the exact same interface as the real service (`EC2Services`). The client talks to the Proxy exactly as if it were talking to the real `EC2Instance`. Internally, the Proxy decides whether to:

- Return a cached result immediately, **or**
- Forward the call to the real `EC2Instance` (paying the 5-second cost), then cache the result for next time.

The client never knows the difference — it just calls `compute()`.

---

## Structure

```text
ec2Instance/
├── EC2Services.java            (The Subject Interface)
├── EC2Instance.java            (The Real Subject — expensive, does the actual work)
├── ec2proxy/
│   └── EC2ProxyInstance.java   (The Proxy — adds caching, controls access to EC2Instance)
└── demo/
    └── Main.java                (The Client)
```

---

## Key Components

### 1. The Subject Interface (`EC2Services`)

Both the real object and the Proxy implement this — this is what makes them interchangeable to the client.

```java
public interface EC2Services {
    String compute(String input);
}
```

### 2. The Real Subject (`EC2Instance`)

Does the actual, expensive work. It has no idea a Proxy even exists.

```java
public class EC2Instance implements EC2Services {
    @Override
    public String compute(String input) {
        System.out.println("[RealService] Starting EC2 instance: Computing (this takes 5 seconds)...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) { }
        return "Result for: " + input.toUpperCase();
    }
}
```

### 3. The Proxy (`EC2ProxyInstance`)

Implements the same interface, holds a reference to the real `EC2Instance`, and adds a caching layer in front of it.

```java
public class EC2ProxyInstance implements EC2Services {
    private final EC2Instance ec2Instance;
    private final Map<String, String> cache = new HashMap<>();

    public EC2ProxyInstance() {
        ec2Instance = new EC2Instance();
    }

    @Override
    public String compute(String input) {
        if (cache.containsKey(input)) {
            System.out.println("[EC2Proxy] ✅ CACHE HIT for: " + input);
            return cache.get(input);
        }

        System.out.println("[EC2Proxy] ❌ CACHE MISS for: " + input + " — starting EC2 instance...");
        String result = ec2Instance.compute(input);
        cache.put(input, result);
        System.out.println("[EC2Proxy] 💾 Cached result for: " + input);
        return result;
    }
}
```

Notice `EC2ProxyInstance` doesn't create the real `EC2Instance` lazily here — but the same class could easily be extended to delay creation until the first genuine cache miss, which is a common Proxy variant (see [Types of Proxy](#types-of-proxy) below).

### 4. The Client (`Main`)

```java
EC2Services service = new EC2ProxyInstance();

String r1 = service.compute("AtlantaServer-AS2"); // Cache MISS — waits 5 seconds
String r2 = service.compute("AtlantaServer-AS2"); // Cache HIT — instant

String r3 = service.compute("SouthAtlantic-SE2"); // Cache MISS — waits 5 seconds
String r4 = service.compute("SouthAtlantic-SE2"); // Cache HIT — instant
```

The client only ever holds a reference typed as `EC2Services` — it has no idea whether it's talking to the real instance or the proxy. That decision was made once, at construction time.

---

## Types of Proxy

`EC2ProxyInstance` here is a **Caching Proxy**, but the same structural idea shows up in a few common flavors:

| Type | What It Controls | Example |
| :--- | :--- | :--- |
| **Caching Proxy** *(this example)* | Avoids redundant expensive calls by storing prior results. | Our `EC2ProxyInstance`. |
| **Virtual Proxy** | Delays creation of an expensive object until it's actually needed. | Only creating `EC2Instance` on the first real cache miss, not in the constructor. |
| **Protection Proxy** | Restricts access based on permissions. | Checking a user's role before forwarding a call to the real object. |
| **Remote Proxy** | Represents an object that lives in a different address space (e.g., another server). | A local stub standing in for a service on a remote machine. |
| **Logging/Monitoring Proxy** | Records calls (timing, arguments, frequency) without changing behavior. | Wrapping a service to log every `compute()` call before forwarding it. |

---

## Running the Demo

```bash
cd src/main/java/com/naviroq/patterns/structural/proxy/ec2Instance/demo
javac Main.java && java Main
```

**Expected Output:**

```text
=== FIRST CALL ===
[EC2Proxy] ❌ CACHE MISS for: AtlantaServer-AS2 — starting EC2 instance...
[RealService] Starting EC2 instance: Computing (this takes 5 seconds)...
[EC2Proxy] 💾 Cached result for: AtlantaServer-AS2
Result for: ATLANTASERVER-AS2

=== SECOND CALL (same input) ===
[EC2Proxy] ✅ CACHE HIT for: AtlantaServer-AS2
Result for: ATLANTASERVER-AS2

=== THIRD CALL (new input) ===
[EC2Proxy] ❌ CACHE MISS for: SouthAtlantic-SE2 — starting EC2 instance...
[RealService] Starting EC2 instance: Computing (this takes 5 seconds)...
[EC2Proxy] 💾 Cached result for: SouthAtlantic-SE2
Result for: SOUTHATLANTIC-SE2

=== FOURTH CALL (cached input) ===
[EC2Proxy] ✅ CACHE HIT for: SouthAtlantic-SE2
Result for: SOUTHATLANTIC-SE2
```

Notice calls 1 and 3 each pay the 5-second cost, while calls 2 and 4 return instantly.

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Adds cross-cutting behavior (caching, access control, logging) without touching the real object. | ⚠️ Adds an extra layer of indirection. |
| ✅ Real object stays focused on its actual job (Single Responsibility Principle). | ⚠️ A poorly invalidated cache can silently serve stale data. |
| ✅ Client code is completely unaware anything is being intercepted. | ⚠️ Thread-safety of shared state (like the cache `HashMap`) needs to be considered under concurrent access. |

---

## When to Use This Pattern

- Creating or calling the real object is expensive, and results can safely be reused (Caching Proxy).
- You want to defer creation of a heavy object until it's genuinely needed (Virtual Proxy).
- You need to add access control, logging, or monitoring around an object without modifying its code (Protection/Logging Proxy).
- The real object lives elsewhere (a different process, server, or service) and you need a local stand-in (Remote Proxy).