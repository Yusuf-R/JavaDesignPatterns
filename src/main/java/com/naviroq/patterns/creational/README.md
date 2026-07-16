# Creational -- Singleton

> Ensure a class has only **one instance**, and provide a **global point of access** to it.

## The problem

Some things should exist exactly once: a configuration holder, a connection pool, a registry, a hardware interface. "Just be careful not to call `new` twice" is not a design — it's a hope. 

The Singleton pattern ensures a class has exactly one instance and provides a global point of access to it.