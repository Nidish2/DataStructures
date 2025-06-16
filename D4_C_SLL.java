package ds;

import java.util.*;

public class D4_C_SLL {

    // Node inner class
    private static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node tail;  // we keep tail to make head access via tail.next
    private int size;

    // Insert at head (front)
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    // Insert at tail (end)
    public void insertAtTail(int data) {
        insertAtHead(data);  // new node becomes new head
        tail = tail.next;    // move tail pointer to new node
    }

    // Insert at position (0‑based)
    public void insertAtPosition(int data, int pos) {
        if (pos < 0 || pos > size) throw new IndexOutOfBoundsException();
        if (pos == 0) {
            insertAtHead(data);
        } else if (pos == size) {
            insertAtTail(data);
        } else {
            Node curr = tail.next;  // head
            for (int i = 1; i < pos; i++) curr = curr.next;
            Node newNode = new Node(data);
            newNode.next = curr.next;
            curr.next = newNode;
            size++;
        }
    }

    // Delete head
    public void deleteAtHead() {
        if (tail == null) return;
        if (tail.next == tail) {
            tail = null;
        } else {
            tail.next = tail.next.next;
        }
        size--;
    }

    // Delete tail
    public void deleteAtTail() {
        if (tail == null) return;
        if (tail.next == tail) {
            tail = null;
        } else {
            Node curr = tail.next;  // head
            while (curr.next != tail) curr = curr.next;
            curr.next = tail.next;  // skip old tail
            tail = curr;            // update tail
        }
        size--;
    }

    // Delete at position
    public void deleteAtPosition(int pos) {
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        if (pos == 0) {
            deleteAtHead();
        } else if (pos == size - 1) {
            deleteAtTail();
        } else {
            Node curr = tail.next;  // head
            for (int i = 1; i < pos; i++) curr = curr.next;
            curr.next = curr.next.next;
            size--;
        }
    }

    // Search for a value
    public boolean search(int key) {
        if (tail == null) return false;
        Node curr = tail.next;  // head
        do {
            if (curr.data == key) return true;
            curr = curr.next;
        } while (curr != tail.next);
        return false;
    }

    // Display list
    public void display() {
        if (tail == null) {
            System.out.println("CSLL is empty");
            return;
        }
        System.out.print("CSLL: ");
        Node curr = tail.next;  // head
        do {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        } while (curr != tail.next);
        System.out.println("(back to head)");
    }

    // Get size
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        D4_C_SLL list = new D4_C_SLL();

        // Real‑world vibe: a round‑robin playlist that loops forever
        list.insertAtHead(100);
        list.insertAtTail(200);
        list.insertAtTail(300);
        list.insertAtPosition(250, 2); // [100 → 200 → 250 → 300 → ...]

        list.display();               // CSLL: 100 -> 200 -> 250 -> 300 -> (back to head)
        System.out.println("Size: " + list.size()); // 4

        System.out.println("Search 250? " + list.search(250)); // true
        System.out.println("Search 999? " + list.search(999)); // false

        list.deleteAtPosition(2);     // remove 250
        list.deleteAtHead();          // remove 100
        list.deleteAtTail();          // remove 300

        list.display();               // CSLL: 200 -> (back to head)
        System.out.println("Size: " + list.size()); // 1
        
        LinkedList<Integer> csll = new LinkedList<>();

        // ➕ Add elements (tail inserts)
        csll.add(100);
        csll.add(200);
        csll.add(300);
        System.out.println("Initial list: " + csll);  
        // [100, 200, 300]

        // 🔄 Circular traversal demo: loop 7 steps
        System.out.print("Circular iterate 7 items: ");
        Iterator<Integer> it = csll.iterator();
        for (int i = 0; i < 7; i++) {
            if (!it.hasNext()) it = csll.iterator();      // wrap around
            System.out.print(it.next() + " → ");
        }
        System.out.println("(…)");
        
        csll.addLast(400);                                // add 400 at tail
        System.out.println("After removeFirst & addLast: " + csll);
        // [200, 300, 400]

        // 🔍 Search & size
        System.out.println("Contains 300? " + csll.contains(300)); 
        System.out.println("Size now: " + csll.size());

        // 🗑️ Remove at index (pos 1)
        int removedAt1 = csll.remove(1);                  // removes 300
        System.out.println("Removed at index 1: " + removedAt1);
        System.out.println("Final list: " + csll);        // [200, 400]
    }
}
