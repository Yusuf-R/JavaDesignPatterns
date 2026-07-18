# Abstract Factory

> **"Provide an interface for creating families of related or dependent objects without specifying their concrete classes."**
> — *Gang of Four*

---

## Intent

**Enforce family consistency.** The Abstract Factory pattern provides an interface for creating a **set** of related products (a family) without specifying their concrete classes. It guarantees that all products from a single factory are compatible with each other.

---

## The Problem It Solves

| Anti-Pattern (Multiple Factories) | Abstract Factory (Single Family Factory) |
| :--- | :--- |
| `Compute c = ComputeFactory.create("aws");` | `CloudProviderFactory factory = new AWSFactory();` |
| `Database d = DatabaseFactory.create("azure");` (WRONG MIX!) | `Compute c = factory.createCompute();` |
| The developer can accidentally mix AWS Compute with Azure Database. | `Database d = factory.createDatabase();` |
| This leads to runtime errors and inconsistent deployments. | All products come from the SAME factory—consistency is **guaranteed at compile-time**. |

---

## Structure (Our Example)

We built a **Cloud Infrastructure Factory** that provisions complete stacks for different cloud providers (AWS and Azure).

```text
abstractfactory/cloud/
├── common/
│   ├── Compute.java
│   ├── Database.java
│   └── Storage.java              (Product Interfaces)
├── aws/
│   ├── EC2Compute.java
│   ├── RDSDatabase.java
│   └── S3Storage.java            (AWS Products)
├── azure/
│   ├── AzureVMCompute.java
│   ├── CosmosDatabase.java
│   └── BlobStorage.java          (Azure Products)
├── factory/
│   ├── CloudProviderFactory.java (Abstract Factory Interface)
│   ├── AWSFactory.java           (Concrete Factory 1)
│   ├── AzureFactory.java         (Concrete Factory 2)
│   ├── ServiceInputs.java        (ENUM: aws, azure)
│   └── CreateFactory.java        (Provider / Factory Selector)
├── orchestrator/
│   └── CloudProvisioner.java     (The Business Logic)
└── demo/
    └── Main.java                 (Interactive CLI Client)
```

---

## Key Components

### 1. The Abstract Factory Interface (`CloudProviderFactory`)

```java
public interface CloudProviderFactory {
    Compute createCompute();
    Database createDatabase();
    Storage createStorage();
}
```

### 2. Concrete Factories (One per Family)

**AWS Factory:**

```java
public class AWSFactory implements CloudProviderFactory {
    public Compute createCompute() { return new EC2Compute(); }
    public Database createDatabase() { return new RDSDatabase(); }
    public Storage createStorage() { return new S3Storage(); }
}
```

**Azure Factory:**

```java
public class AzureFactory implements CloudProviderFactory {
    public Compute createCompute() { return new AzureVMCompute(); }
    public Database createDatabase() { return new CosmosDatabase(); }
    public Storage createStorage() { return new BlobStorage(); }
}
```

### 3. The Product Interfaces (`Compute`, `Database`, `Storage`)

Each defines the contract for a specific product type.

```java
public interface Compute {
    void startInstance(String instanceId);
    void stopInstance(String instanceId);
    String getComputeType();
}
```

### 4. The Orchestrator (`CloudProvisioner`)

The factory creates objects. The orchestrator uses them.

```java
public class CloudProvisioner {
    private final Compute compute;
    private final Database database;
    private final Storage storage;

    public CloudProvisioner(CloudProviderFactory factory) {
        this.compute = factory.createCompute();
        this.database = factory.createDatabase();
        this.storage = factory.createStorage();
    }

    public void deployInfrastructure() {
        compute.startInstance("i-123");
        database.connect();
        storage.upload("file.txt", data);
        printSummary();
    }
}
```

Why separate them? This respects the **Single Responsibility Principle**:

- The **Factory** handles *what* to create.
- The **Orchestrator** handles *how* to use what was created.

### 5. The Client (`Main.java`)

The client selects a family once and then uses the factory to build everything.

```java
ServiceInputs selected = ServiceInputs.aws;
CloudProviderFactory factory = new CreateFactory().getService(selected);

CloudProvisioner provisioner = new CloudProvisioner(factory);
provisioner.deployInfrastructure();
```

The compiler guarantees that `Compute`, `Database`, and `Storage` all come from the same family (AWS).

---

## The Magic of Compile-Time Consistency

```java
// Picking the family ONCE
CloudProviderFactory factory = new AWSFactory();

// Guaranteed to be all AWS components!
Compute c = factory.createCompute();   // → EC2Compute
Database d = factory.createDatabase(); // → RDSDatabase
Storage s = factory.createStorage();   // → S3Storage
```

The Client cannot mix an AWS Compute with an Azure Database because they all come from the same factory instance. This prevents runtime errors and logic bugs.

---

## The Orchestrator (The Missing Piece)

A common misconception is that the Abstract Factory deploys the services. It does not. The Factory only creates the objects.

| Misconception | Reality |
| :--- | :--- |
| "The Factory should deploy the services." | ❌ No. The Factory only creates the objects. |
| "The Products should call each other." | ❌ No. Products are independent. |
| "The Client should handle the deployment logic." | ❌ No. The Client becomes bloated. |
| **Correct:** | ✅ The Orchestrator handles the workflow. |

---

## Interactive Demo

Our `Main` class simulates a real-world CLI tool:

1. Displays a menu of available clouds (`aws`, `azure`).
2. Reads user input and validates it against the `ServiceInputs` Enum.
3. Handles typos gracefully (loops until valid).
4. Passes the selected Enum to the `CreateFactory`.
5. Hands the resulting factory to the `CloudProvisioner`.
6. Executes `deployInfrastructure()` and displays a final confirmation.

**Sample Output:**

```text
👉 Please enter your preferred cloud provider (aws/azure): azure

=====================================================================
  🚀 DEPLOYING INFRASTRUCTURE ON AZURE
=====================================================================
--- [1] Provisioning Compute ---
  [Azure VM] Starting instance: i-1734567890456...
...
✅ Successfully deployed azure infrastructure. All services are ready!
```

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Enforces family consistency (no mixing). | ⚠️ Adding a new provider (e.g., GCP) requires adding a new Factory, new Products, and modifying the Enum/Provider. |
| ✅ Decouples client code from concrete classes. | ⚠️ More complex than a simple Factory. |
| ✅ Makes swapping entire families (AWS ↔ Azure) trivial. | |
| ✅ Excellent for cross-platform/framework support. | |

---

## Factory Method vs Abstract Factory (Quick Recap)

| | Factory Method | Abstract Factory |
| :--- | :--- | :--- |
| **Creates** | A single product (e.g., `Car`). | A family of related products (e.g., Compute + Database + Storage). |
| **Intent** | Let subclasses decide *which class* to instantiate. | Let subclasses decide *which family* to instantiate. |
| **Analogy** | A vending machine for Cars. | A "Theme Store" that sells matching chair, table, and sofa. |
| **Our Example** | `CarFactory.createCar(SEDAN)` | `CloudProvisioner(new AWSFactory())` |

---

## Running the Demo

Navigate to the demo package and run `Main.java`. Interact with the CLI to provision either AWS or Azure infrastructure.

```bash
cd src/main/java/com/naviroq/patterns/creational/abstractfactory/cloud/demo
javac Main.java && java Main
```

---

## When to Use This Pattern

- You need to ensure that a set of related objects are used together consistently.
- You want to swap out entire families of objects (e.g., change from AWS to Azure) with minimal code changes.
- You want to enforce design-time consistency (compile-time safety) rather than relying on runtime checks.

---

## Conclusion

The Abstract Factory pattern is the go-to solution for building modular, extensible systems that need to support multiple families of products. Combined with an Orchestrator, it creates a clean, decoupled, and testable architecture.