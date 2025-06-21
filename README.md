# DataStructures

Welcome to the **DataStructures** repository, a comprehensive collection of Java implementations covering fundamental data structures and their core operations. This repository not only focuses on manual implementations to deeply understand the core logic behind each structure but also showcases the use of built-in methods, classes, and interfaces for quick, efficient coding—making it ideal for cracking coding tests, interviews, and placements. Whether you’re brushing up on basics, preparing for interviews, or building a foundation for complex algorithms, this repo has you covered.

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

> 🎯 **At a Glance:** Browse implementations in sequential order (D1 → DD25) with detailed, beginner-friendly descriptions.

---

🔢 **D1\_Arrays.java**
Covers the basics of array manipulation including creation, traversal, insertion, deletion and differnt techniques of swapping. Great for getting started with static data structures.

---

🗒️ **D2\_String.java**
Implements common string operations like reversing, checking for palindromes, substring searches, and character frequency. Reinforces concepts like immutability and indexing.

---

📄 **D3\_SLL.java**
Singly Linked List implementation with support for inserting at head/tail, deleting nodes, and traversing the list. Ideal for understanding dynamic memory and pointers.

---

🔄 **D4\_C\_SLL.java**
Circular Singly Linked List where the last node connects back to the first, enabling continuous traversal. Useful in queue-based problems and round-robin scheduling.

---

↔️ **D5\_DLL.java**
Doubly Linked List with forward and backward traversal. Operations include insertion, deletion at any position, and node reversal.

---

🔄 **D6\_C\_DLL.java**
Circular Doubly Linked List extends DLL with circular connections for bidirectional continuous loops. Useful in media players and buffers.

---

📚 **D7\_Stack.java**
Classic stack using arrays, implementing `push`, `pop`, `peek`, and size tracking. Demonstrates LIFO behavior and error handling (overflow/underflow).

---

📚 **D8\_Stack\_SLL.java**
Stack built using singly linked list nodes for dynamic growth, removing fixed size constraints. Includes all standard stack operations.

---

🎒 **D9\_Queue.java**
Queue using an array with `enqueue`, `dequeue`, and overflow/underflow handling. Introduces FIFO mechanics.

---

🎒 **DD10\_Queue\_SLL.java**
Linked List-based queue supporting dynamic memory allocation. Useful when queue size isn't known in advance.

---

🔄 **DD11\_C\_Queue.java**
Circular Queue using arrays to efficiently reuse space. Avoids shifting on deletion, perfect for memory-constrained systems.

---

🔄 **DD12\_C\_Queue\_SLL.java**
Circular Queue implemented using a linked list to maintain flexibility and circular structure.

---

↕️ **DD13\_DE\_Queue.java**
Double-ended Queue using arrays, supports insertion/deletion from both ends. Applies in task scheduling and palindrome checking.

---

↕️ **DD14\_DE\_Queue\_DLL.java**
Deque implemented using doubly linked list for flexible insertion/removal at both ends. Handles large dynamic data efficiently.

---

🌳 **DD15\_BST.java**
Binary Search Tree with insertion, deletion, and traversal methods like in-order, pre-order, and post-order. Reinforces recursion and tree balancing basics.

---

⚖️ **DD16\_AVL.java**
Self-balancing BST (AVL Tree) with automatic height balancing via rotations (LL, RR, LR, RL). Ensures O(log n) operations.

---

📐 **DD17\_Segment\_T.java**
Segment Tree structure for fast range sum queries and point updates. Very useful for range query-based problems in competitive programming.

---

🗝️ **DD18\_HashSet.java**
Custom HashSet with separate chaining to handle hash collisions. Demonstrates hashing, buckets, and uniqueness.

---

📇 **DD19\_HashMap.java**
Implements a custom HashMap supporting insertion, retrieval, deletion, and resizing. Key-value store with internal hash function logic.

---

⬆️ **DD20\_MinHeap.java**
Binary Min-Heap ensuring the smallest element is always at the top. Used in priority queues and scheduling.

---

⬇️ **DD21\_MaxHeap.java**
Max-Heap variant where the largest element is maintained at the root. Useful for leaderboard and max-priority tasks.

---

🌐 **DD22\_BFS.java**
Breadth-First Search implementation for graphs using adjacency lists. Illustrates shortest path traversal layer-by-layer.

---

🌐 **DD23\_DFS.java**
Depth-First Search using recursion and explicit stack. Useful in maze solving, topological sort, and cycle detection.

---

🔍 **DD24\_Trie.java**
Trie or prefix tree that enables fast prefix-based string matching and autocomplete systems. Covers insert and search operations.

---

🛠️ **DD25\_Union\_Find\_DSU.java**
Disjoint Set Union with union by rank and path compression. Essential for graph components, Kruskal’s MST, and dynamic connectivity.

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
