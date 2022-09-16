# LinkedList

An implementation of a double-linked list in Java. See source for docs.

Tested on Java 18 <br>
java 18.0.1.1 2022-04-22 <br>
Java(TM) SE Runtime Environment (build 18.0.1.1+2-6) <br>
Java HotSpot(TM) 64-Bit Server VM (build 18.0.1.1+2-6, mixed mode, sharing)

## Reference

This is a reference for the process of inserting new nodes into the list:

Either a new `LinkedList.Node` object is created or it's provided by the caller.

```mermaid
flowchart LR
    Z[New Node]
    A[Item 1] <--> B[Item 2]
    B <--> C[Item 3]
```

The new node is linked to the next node in the list.
The next node's previous reference is updated to the new node.

```mermaid
flowchart LR
    A[Item 1] <--> B[Item 2]
    Z[New Node] <--> C[Item 3]
```

The next field of the pevious node is updated with a reference to the new node.
The previous reference of the new node is updated with a reference to the previous node.

```mermaid
flowchart LR
    A[Item 1] <--> B[Item 2]
    B <--> C[New Node]
    C <--> D[Item 3]
```

## Usage

Import the `LinkedList.java` class to use it. Run the `Tester.java` class to run tests.
