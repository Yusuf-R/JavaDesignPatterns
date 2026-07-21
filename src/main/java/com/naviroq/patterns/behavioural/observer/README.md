# Observer Pattern

> **"Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically."**
> — *Gang of Four*

---

## Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Structure](#structure)
4. [Key Components](#key-components)
5. [Subscribing and Unsubscribing at Runtime](#subscribing-and-unsubscribing-at-runtime)
6. [Running the Demo](#running-the-demo)
7. [Trade-offs](#trade-offs)
8. [When to Use This Pattern](#when-to-use-this-pattern)

---

## The Problem

A weather station takes new readings and needs to push updates out to several independent displays: a phone app, a web dashboard, an alerting system, and a logger. If the `WeatherStation` had to know about each of these concretely, it would look like this:

```java
public void setMeasurements(float temp, float humidity, float pressure) {
    phoneDisplay.update(temp, humidity, pressure);
    webDashboard.update(temp, humidity, pressure);
    alertSystem.update(temp, humidity, pressure);
    logger.update(temp, humidity, pressure);
    // Adding a new display means editing WeatherStation directly
}
```

This tightly couples the weather station to every single display it happens to know about today. Adding a new display type — or temporarily disabling one — means changing the station's own code.

---

## The Solution

Give every display a common `Listeners` interface, and let the `WeatherStation` hold a **list** of them, without knowing their concrete types. When new measurements come in, the station simply loops through its list and notifies whoever is currently subscribed. Displays can be added or removed at runtime without the station's code ever changing.

---

## Structure

```text
weatherManager/
├── system/
│   ├── WeatherSystem.java         (The Subject Interface)
│   └── WeatherStation.java        (The Concrete Subject — publisher)
├── listeners/
│   ├── Listeners.java             (The Observer Interface)
│   ├── PhoneListeners.java
│   ├── WebDashboardListeners.java
│   ├── AlertSystemListeners.java
│   └── LoggersListeners.java      (Concrete Observers — subscribers)
└── demo/
    └── Main.java                   (The Client)
```

---

## Key Components

### 1. The Observer Interface (`Listeners`)

Every subscriber implements this — it's how the subject can notify any of them without knowing their concrete type.

```java
public interface Listeners {
    void update(float temperature, float humidity, float pressure);
}
```

### 2. The Subject Interface (`WeatherSystem`)

Defines the contract for anything that can be subscribed to.

```java
public interface WeatherSystem {
    void registerObserver(Listeners o);
    void removeObserver(Listeners o);
    void notifyObservers();
}
```

### 3. The Concrete Subject (`WeatherStation`)

Holds the list of subscribers and the current weather state. It only knows about the `Listeners` interface — never about `PhoneListeners` or `AlertSystemListeners` directly.

```java
public class WeatherStation implements WeatherSystem {
    private final List<Listeners> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Listeners o) {
        observers.add(o);
        System.out.println("[WeatherStation] Observer registered: " + o.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(Listeners o) {
        observers.remove(o);
        System.out.println("[WeatherStation] Observer removed: " + o.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (Listeners o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }

    // Called when the weather station gets new readings
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        System.out.println("\n[WeatherStation] NewState measurements: " + temperature + "°C, "
            + humidity + "%, " + pressure + " hPa");
        notifyObservers();
    }
}
```

### 4. Concrete Observers

Each one reacts to the same update differently — this is where behavior diverges.

```java
public class PhoneListeners implements Listeners {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("[PhoneListeners] 📱 Current: " + temperature + "°C, " + humidity + "% humidity");
    }
}
```

```java
public class AlertSystemListeners implements Listeners {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        if (temperature > 35) {
            System.out.println("[AlertSystemListeners] 🚨 HEAT WARNING: " + temperature + "°C exceeds safe threshold!");
        }
        if (pressure < 980) {
            System.out.println("[AlertSystemListeners] ⛈️ STORM ALERT: Low pressure " + pressure + " hPa");
        }
    }
}
```

Notice `AlertSystemListeners` doesn't just display data — it applies its own conditional logic on the same update. Each observer is free to interpret the notification however it needs to.

### 5. The Client (`Main`)

```java
WeatherStation weatherStation = new WeatherStation();

PhoneListeners phone = new PhoneListeners();
WebDashboardListeners web = new WebDashboardListeners();
AlertSystemListeners alerts = new AlertSystemListeners();
LoggersListeners logger = new LoggersListeners();

weatherStation.registerObserver(phone);
weatherStation.registerObserver(web);
weatherStation.registerObserver(alerts);
weatherStation.registerObserver(logger);

weatherStation.setMeasurements(22.5f, 65, 1013);
```

The client wires everything together, but from that point on, `WeatherStation` drives all the notifications on its own.

---

## Subscribing and Unsubscribing at Runtime

Because observers are just entries in a list, they can come and go freely without touching `WeatherStation`'s code:

```java
weatherStation.removeObserver(phone);
weatherStation.setMeasurements(20.0f, 70, 985); // phone does NOT receive this update

weatherStation.registerObserver(phone);
weatherStation.setMeasurements(18.5f, 75, 990); // phone receives this one
```

---

## Running the Demo

```bash
cd src/main/java/com/naviroq/patterns/behavioural/observer/weatherManager/demo
javac Main.java && java Main
```

**Expected Output:**

```text
[WeatherStation] Observer registered: PhoneListeners
[WeatherStation] Observer registered: WebDashboardListeners
[WeatherStation] Observer registered: AlertSystemListeners
[WeatherStation] Observer registered: LoggersListeners

=== MORNING ===

[WeatherStation] New measurements: 22.5°C, 65.0%, 1013.0 hPa
[PhoneListeners] 📱 Current: 22.5°C, 65.0% humidity
[WebDashboardListeners] 🌐 Weather: 22.5°C, Pressure: 1013.0 hPa
[LoggersListeners] 📝 ... | Temp: 22.5°C, Humidity: 65.0%, Pressure: 1013.0 hPa

=== AFTERNOON (getting hot) ===

[WeatherStation] New measurements: 36.0°C, 45.0%, 1008.0 hPa
[AlertSystemListeners] 🚨 HEAT WARNING: 36.0°C exceeds safe threshold!
...

--- Removing phone display ---
[WeatherStation] Observer removed: PhoneListeners

=== NIGHT (phone unsubscribed) ===
[WeatherStation] New measurements: 20.0°C, 70.0%, 985.0 hPa
(PhoneListeners does not appear in the output)
```

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Subject and observers are loosely coupled — the subject only knows the `Listeners` interface. | ⚠️ Notification order isn't guaranteed unless you enforce it explicitly. |
| ✅ New observer types can be added without modifying `WeatherStation`. | ⚠️ Forgetting to unsubscribe an observer can cause memory leaks in long-running applications. |
| ✅ Observers can subscribe/unsubscribe dynamically at runtime. | ⚠️ Debugging can be harder — one state change can trigger a cascade of updates across many objects. |
| ✅ Naturally models "broadcast" style updates (one change, many reactions). | |

---

## When to Use This Pattern

- One object's state change needs to be reflected across multiple, independent parts of the system (UI updates, logging, alerting).
- You don't know in advance how many observers there will be, or want that number to change at runtime.
- You want to decouple the object that owns the data from the objects that react to it.