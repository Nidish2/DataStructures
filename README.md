# DataStructures

Welcome to the **DataStructures** repository, a comprehensive collection of Java implementations covering fundamental data structures and their core operations. Whether you’re brushing up on basics, preparing for interviews, or building a foundation for complex algorithms, this repo has you covered.
This repository covers manual implementations to help understand core data structure logic, along with the usage of built-in methods, classes, and interfaces for quick application in coding interviews and technical placement tests. 

---

## 🚀 Table of Contents

1. [Getting Started](#-getting-started)
2. [Prerequisites](#-prerequisites)
3. [Project Structure](#-project-structure)
4. [Program Descriptions](#-program-descriptions)
5. [Usage](#-usage)
6. [Contributing](#-contributing)
7. [License](#-license)

---

## 🔧 Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/Nidish2/DataStructures.git
   cd DataStructures
   ```
2. **Compile** all Java files (from project root):

   ```bash
   javac -d out src/*.java
   ```
3. **Run** individual programs:

   ```bash
   java -cp out D1_Arrays
   ```

---

## 📦 Prerequisites

* Java JDK 8 or above installed.
* A terminal or IDE (IntelliJ, Eclipse, VS Code, etc.)

---

## 🗂️ Project Structure

**Directory Layout Overview:**

* `src/`: All Java source files, each demonstrating a specific data structure in incremental complexity.
* `out/`: Compiled `.class` files generated upon build.
* `package-info.java`: Central package-level documentation.
* `README.md`: Project overview, instructions, and usage examples.
* `LICENSE`: Licensing terms for the project.

```
DataStructures/
├── src/
│   ├── D0_Test.java          # Basic test harness
│   ├── D1_Arrays.java        # Array operations demo
│   ├── D2_String.java        # String manipulation utilities
│   ├── D3_SLL.java           # Singly Linked List implementation
│   ├── D4_C_SLL.java         # Circular Singly Linked List
│   ├── D5_DLL.java           # Doubly Linked List
│   ├── D6_C_DLL.java         # Circular Doubly Linked List
│   ├── D7_Stack.java         # Stack using array
│   ├── D8_Stack_SLL.java     # Stack using linked list
│   ├── D9_Queue.java         # Queue using array
│   ├── DD10_Queue_SLL.java   # Queue using singly linked list
│   ├── DD11_C_Queue.java     # Circular Queue (array)
│   ├── DD12_C_Queue_SLL.java # Circular Queue (linked list)
│   ├── DD13_DE_Queue.java    # Double-Ended Queue (array)
│   ├── DD14_DE_Queue_DLL.java# Double-Ended Queue (DLL)
│   ├── DD15_BST.java         # Binary Search Tree
│   ├── DD16_AVL.java         # AVL Tree (self-balancing BST)
│   ├── DD17_Segment_T.java   # Segment Tree (range queries)
│   ├── DD18_HashSet.java     # HashSet implementation
│   ├── DD19_HashMap.java     # HashMap implementation
│   ├── DD20_MinHeap.java     # Min-Heap
│   ├── DD21_MaxHeap.java     # Max-Heap
│   ├── DD22_BFS.java         # Breadth-First Search on graphs/trees
│   ├── DD23_DFS.java         # Depth-First Search on graphs/trees
│   ├── DD24_Trie.java        # Trie (prefix tree)
│   └── DD25_Union_Find_DSU.java # Disjoint Set Union (Union-Find)
├── package-info.java         # Package documentation
└── README.md                 # This file
```

---

## 📖 Program Descriptions

> 🎯 **At a Glance:** Browse implementations in sequential order (D1 → DD25) with concise descriptions.

* 🔢 **D1\_Arrays.java**: Array operations – traversal, insertion, deletion, searching, and sorting examples.
* 🗒️ **D2\_String.java**: String utilities – reversal, palindrome checks, substring search, and common manipulations.
* 📄 **D3\_SLL.java**: Singly Linked List with `insert()`, `delete()`, and traversal using a head pointer.
* 🔄 **D4\_C\_SLL.java**: Circular Singly Linked List where the last node loops back to head.
* ↔️ **D5\_DLL.java**: Doubly Linked List supporting bidirectional traversal and updates.
* 🔄 **D6\_C\_DLL.java**: Circular Doubly Linked List combining DLL features with circular linking.
* 📚 **D7\_Stack.java**: Stack implemented with an underlying array; covers `push`, `pop`, `peek`, overflow/underflow.
* 📚 **D8\_Stack\_SLL.java**: Stack built on a Singly Linked List, demonstrating dynamic sizing.
* 🎒 **D9\_Queue.java**: FIFO Queue implementation using a fixed-size array.
* 🎒 **DD10\_Queue\_SLL.java**: Queue constructed via singly linked list pointers for dynamic enqueue/dequeue.
* 🔄 **DD11\_C\_Queue.java**: Array-based Circular Queue optimizing space reuse.
* 🔄 **DD12\_C\_Queue\_SLL.java**: Circular Queue using linked list nodes in a loop.
* ↕️ **DD13\_DE\_Queue.java**: Double-Ended Queue (Deque) array version for operations at both ends.
* ↕️ **DD14\_DE\_Queue\_DLL.java**: Deque implemented with a Doubly Linked List for dynamic resizing.
* 🌳 **DD15\_BST.java**: Binary Search Tree with `insert()`, `search()`, `delete()`, and traversal methods.
* ⚖️ **DD16\_AVL.java**: AVL Tree – a self-balancing BST showcasing left/right and LR/RL rotations.
* 📐 **DD17\_Segment\_T.java**: Segment Tree supporting efficient range sum queries and point updates.
* 🗝️ **DD18\_HashSet.java**: Simplified HashSet with separate chaining to handle collisions.
* 📇 **DD19\_HashMap.java**: Custom HashMap featuring key-value storage, resizing, and collision resolution.
* ⬆️ **DD20\_MinHeap.java**: Min-Heap implementation for priority queue operations.
* ⬇️ **DD21\_MaxHeap.java**: Max-Heap variant prioritizing larger elements at the top.
* 🌐 **DD22\_BFS.java**: Breadth-First Search traversal on graph structures using adjacency lists.
* 🌐 **DD23\_DFS.java**: Depth-First Search traversal on graphs/trees with recursive and stack-based approaches.
* 🔍 **DD24\_Trie.java**: Trie (prefix tree) with insert, search, and prefix query operations.
* 🛠️ **DD25\_Union\_Find\_DSU.java**: Disjoint Set Union (Union-Find) with path compression and union by rank.

---

## 💻 Usage Examples

**1. Arrays Demo**

```bash
# Compile
javac -d out src/D1_Arrays.java
# Run
java -cp out D1_Arrays
```

**2. AVL Tree**

```bash
javac -d out src/DD16_AVL.java
java -cp out DD16_AVL
```

*Repeat similarly for any `*.java` file by replacing the filename.*

---

## 🤝 Contributing

Contributions are always welcome! Feel free to:

* Open an issue to report bugs or suggest improvements.
* Submit a pull request with enhancements or new data structures.

Please follow the standard GitHub workflow:

1. Fork the repo
2. Create a feature branch (`git checkout -b feature/NewStructure`)
3. Commit your changes (`git commit -m 'Add XYZ structure'`)
4. Push to the branch (`git push origin feature/NewStructure`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
