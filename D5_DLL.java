package ds;

import java.util.Iterator;
import java.util.LinkedList;

public class D5_DLL {

    // Manual Node for Doubly Linked List
    private static class Node {
        int data;
        Node prev;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // Insert at head
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Insert at tail
    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Delete at head
    public void deleteAtHead() {
        if (head == null) return;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    // Delete at tail
    public void deleteAtTail() {
        if (tail == null) return;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    // Display forward
    public void displayForward() {
        System.out.print("DLL Forward: ");
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " <-> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    // Display backward
    public void displayBackward() {
        System.out.print("DLL Backward: ");
        Node curr = tail;
        while (curr != null) {
            System.out.print(curr.data + " <-> ");
            curr = curr.prev;
        }
        System.out.println("null");
    }

    // Search
    public boolean search(int key) {
        Node curr = head;
        while (curr != null) {
            if (curr.data == key) return true;
            curr = curr.next;
        }
        return false;
    }

    // Get size
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        // --- Manual DLL ---
        D5_DLL manualList = new D5_DLL();
        manualList.insertAtHead(10);
        manualList.insertAtTail(20);
        manualList.insertAtHead(5);
        manualList.insertAtTail(25);
        manualList.displayForward();     // DLL Forward: 5 <-> 10 <-> 20 <-> 25 <-> null
        manualList.displayBackward();    // DLL Backward: 25 <-> 20 <-> 10 <-> 5 <-> null
        System.out.println("Manual size: " + manualList.size());
        System.out.println("Search 20? " + manualList.search(20));
        manualList.deleteAtHead();
        manualList.deleteAtTail();
        manualList.displayForward();     // DLL Forward: 10 <-> 20 <-> null

        // --- Built-in LinkedList ---
        LinkedList<Integer> builtList = new LinkedList<>();
        builtList.addFirst(10);          // [10]
        builtList.addLast(20);           // [10, 20]
        builtList.add(1, 15);            // [10, 15, 20]
        System.out.println("Built-in list: " + builtList);
        System.out.println("Contains 15? " + builtList.contains(15));
        System.out.println("Built-in size: " + builtList.size());

        // Remove elements
        int removedFirst = builtList.removeFirst();
        int removedLast = builtList.removeLast();
        System.out.println("Removed first: " + removedFirst + ", last: " + removedLast);
        System.out.println("Final built-in: " + builtList);

        // Iterate
        System.out.print("Iterate built-in: ");
        Iterator<Integer> it = builtList.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " -> ");
        }
        System.out.println("null");
    }
}
