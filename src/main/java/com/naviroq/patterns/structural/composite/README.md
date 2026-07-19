# Composite Pattern

> **"Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly."**
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

Think about a computer's file system: a **folder** can contain **files**, but it can also contain **other folders**, which contain more files and folders, and so on — a tree of unknown depth.

If `File` and `Folder` are treated as completely different types, client code ends up littered with type-checking:

```java
public double getSize(Object item) {
    if (item instanceof File) {
        return ((File) item).getSize();
    } else if (item instanceof Folder) {
        double total = 0;
        for (Object child : ((Folder) item).getChildren()) {
            total += getSize(child); // Have to recurse manually, and re-check types
        }
        return total;
    }
    throw new IllegalArgumentException("Unknown type");
}
```

This is fragile — every new operation needs its own `instanceof` chain, and adding a new node type means touching every one of them.

---

## The Solution

Give `File` and `Folder` a **common interface** (`FileSystemItem`). A `Folder` doesn't just *contain* items — it *is* one too. This lets the client call the same method (`getSize()`, `print()`, etc.) on a single file or an entire nested folder tree, without ever knowing or caring which one it's holding.

---

## Structure

```text
composite/
├── common/
│   └── FileSystemItem.java   (The Component Interface)
├── leaf/
│   └── File.java              (The Leaf — has no children)
├── composite/
│   └── Folder.java            (The Composite — holds a list of FileSystemItem)
└── demo/
    └── Main.java               (The Client)
```

---

## Key Components

### 1. The Component Interface (`FileSystemItem`)

Both leaves and composites implement this same interface — this is what makes them interchangeable to the client.

```java
public interface FileSystemItem {
    double getSize();
    void print(String indent);
}
```

### 2. The Leaf (`File`)

A leaf has no children. It represents the "base case" of the tree — it just returns its own data.

```java
public class File implements FileSystemItem {
    private final String name;
    private final double sizeInKB;

    public File(String name, double sizeInKB) {
        this.name = name;
        this.sizeInKB = sizeInKB;
    }

    @Override
    public double getSize() {
        return sizeInKB;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "📄 " + name + " (" + sizeInKB + "KB)");
    }
}
```

### 3. The Composite (`Folder`)

A composite holds a collection of `FileSystemItem` — which can be a mix of `File`s and other `Folder`s. It implements the same interface, and its methods simply **delegate to, and combine the results of, its children**.

```java
public class Folder implements FileSystemItem {
    private final String name;
    private final List<FileSystemItem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemItem item) {
        children.add(item);
    }

    @Override
    public double getSize() {
        double total = 0;
        for (FileSystemItem item : children) {
            total += item.getSize(); // Works whether item is a File or another Folder
        }
        return total;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "📁 " + name);
        for (FileSystemItem item : children) {
            item.print(indent + "  ");
        }
    }
}
```

### 4. The Client (`Main`)

```java
Folder root = new Folder("root");
Folder documents = new Folder("documents");

documents.add(new File("resume.pdf", 120));
documents.add(new File("cover-letter.docx", 45));

root.add(documents);
root.add(new File("readme.txt", 2));

root.print("");
System.out.println("Total size: " + root.getSize() + "KB");
```

The client never checks whether it's holding a `File` or a `Folder` — it just calls `getSize()` and `print()`, and the tree handles the recursion internally.

---

## Running the Demo

```bash
cd src/main/java/com/naviroq/patterns/structural/composite/demo
javac Main.java && java Main
```

**Expected Output:**

```text
=== COMPOSITE PATTERN DEMO ===
📁 root
  📁 documents
    📄 resume.pdf (120.0KB)
    📄 cover-letter.docx (45.0KB)
  📄 readme.txt (2.0KB)
Total size: 167.0KB
```

---

## Trade-offs

| Pros | Cons |
| :--- | :--- |
| ✅ Client code is simplified — no `instanceof` checks, no manual recursion. | ⚠️ Can make the design overly general — sometimes you *want* to restrict what a Folder can contain. |
| ✅ Adding new leaf or composite types doesn't break existing client code. | ⚠️ Hard to enforce type-safety on children (e.g., stopping a `File` from having children isn't naturally enforced by the interface alone). |
| ✅ Naturally models recursive, tree-shaped data. | |

---

## When to Use This Pattern

- Your data naturally forms a **part-whole hierarchy** (file systems, UI component trees, org charts, menu structures).
- You want client code to treat a single object and a group of objects **identically**.
- You expect the tree's depth and shape to change over time without requiring changes to client code.