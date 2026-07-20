# Strategy Pattern

> **"Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from the clients that use it."**
> — *Gang of Four*

---

## Table of Contents

1. [The Problem](#the-problem)
2. [The Solution](#the-solution)
3. [Structure](#structure)
4. [Key Components](#key-components)
5. [Running the Demo](#running-the-demo)
6. [Trade-offs](#trade-offs)
7. [When to Use This Pattern](#when-to-use-this-pattern)

---

## The Problem

A checkout flow needs to support multiple payment methods — credit card, PayPal, crypto — and more will likely be added later. A naive approach crams all the logic into one class with a giant conditional:

```java
public void checkout(String method, double amount) {
    if (method.equals("credit_card")) {
        // credit card logic
    } else if (method.equals("paypal")) {
        // paypal logic
    } else if (method.equals("crypto")) {
        // crypto logic
    }
    // Every new payment method means editing this method again
}
```

This violates the **Open/Closed Principle** — every new payment option means modifying existing, already-tested code, and the checkout class ends up knowing far more than it should about how each payment method actually works.

---

## The Solution

Extract each payment method into its own class, all implementing a common `PaymentStrategy` interface. The checkout flow (`CheckoutService`) doesn't know or care *how* a strategy pays — it just calls `pay()` on whichever one it was handed. New payment gateways can be added by creating a new class, with zero changes to `CheckoutService`.

---

## Structure

```text
paymentStrategy/
├── PaymentStrategy.java          (The Strategy Interface)
├── gateway/
│   ├── creditCard/
│   │   └── CreditCardStrategy.java
│   ├── crypto/
│   │   └── CryptoStrategy.java
│   └── paypal/
│       └── PayPalStrategy.java   (Concrete Strategies)
├── services/
│   └── CheckoutService.java      (The Context — uses a Strategy)
└── demo/
    └── Main.java                  (The Client)
```

---

## Key Components

### 1. The Strategy Interface (`PaymentStrategy`)

Every payment gateway implements this one contract.

```java
public interface PaymentStrategy {
    void pay(double amount);
}
```

### 2. Concrete Strategies (`CreditCardStrategy`, `PayPalStrategy`, `CryptoStrategy`)

Each gateway encapsulates its own logic and required details, completely independent of the others.

```java
public class CreditCardStrategy implements PaymentStrategy {
    private final String cardNumber;
    private final String expiryDate;
    private final String cvv;

    public CreditCardStrategy(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("💳 Paid " + amount + " using Credit Card ending in "
            + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("   ✅ Transaction approved!");
    }
}
```

```java
public class PayPalStrategy implements PaymentStrategy {
    private final String email;

    public PayPalStrategy(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("💰 Paid " + amount + " using PayPal account: " + email);
        System.out.println("   ✅ Payment successful!");
    }
}
```

```java
public class CryptoStrategy implements PaymentStrategy {
    private final String walletAddress;

    public CryptoStrategy(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println("🪙 Paid " + amount + " using Crypto Wallet: " + walletAddress);
        System.out.println("   ✅ Transaction submitted!");
    }
}
```

### 3. The Context (`CheckoutService`)

Holds a reference to whichever `PaymentStrategy` it's given and delegates the actual payment work to it. It never knows which concrete strategy it's holding.

```java
public class CheckoutService {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double total) {
        if (paymentStrategy == null) {
            System.out.println("❌ No payment method selected!");
            return;
        }
        System.out.println("\n🛒 Checkout total: $" + total);
        paymentStrategy.pay(total);
    }
}
```

### 4. The Client (`Main`)

The client picks a strategy at runtime and hands it to the context — swapping strategies requires no changes to `CheckoutService` at all.

```java
CheckoutService checkout = new CheckoutService();

checkout.setPaymentStrategy(new CreditCardStrategy("4111111111111234", "09/28", "123"));
checkout.checkout(150.00);

checkout.setPaymentStrategy(new PayPalStrategy("user@example.com"));
checkout.checkout(75.00);

checkout.setPaymentStrategy(new CryptoStrategy("bc1qxyz..."));
checkout.checkout(220.00);
```

---

## Running the Demo

```bash
cd src/main/java/com/naviroq/patterns/behavioural/strategy/paymentStrategy/demo
javac Main.java && java Main
```

**Expected Output (interactive CLI):**

```text
💳 ====== PAYMENT SYSTEM (Strategy Pattern) ======

--- Select Payment Method ---
1. Credit Card
2. PayPal
3. Cryptocurrency
4. Exit
Choose: 1
Enter total amount: $150
Card number: 4111111111111234
Expiry (MM/YY): 09/28
CVV: 123

🛒 Checkout total: $150.0
💳 Paid 150.0 using Credit Card ending in 1234
   ✅ Transaction approved!
```

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ New algorithms/strategies can be added without touching existing code (Open/Closed Principle). | ⚠️ Increases the number of classes — one per strategy. |
| ✅ Eliminates large conditional blocks (`if/else` or `switch` chains). | ⚠️ The client must know which concrete strategy to instantiate. |
| ✅ Strategies can be swapped at runtime, even mid-flow. | |
| ✅ Each algorithm is independently testable in isolation. | |

---

## When to Use This Pattern

- You have multiple ways of doing the same conceptual task (paying, sorting, validating, compressing) and want to switch between them at runtime.
- You want to avoid a large conditional statement that picks behavior based on a type or flag.
- You expect new variations of the algorithm to be added over time.