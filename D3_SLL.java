package ds;

import java.util.Arrays;
import java.util.LinkedList;

public class D3_SLL {

	// Node inner class
	private static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}

	private Node head;
	private int size;

	// Insert at the front (head)
	public void insertAtHead(int data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;
		size++;
	}

	// Insert at the end (tail)
	public void insertAtTail(int data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
		} else {
			Node curr = head;
			while (curr.next != null)
				curr = curr.next;
			curr.next = newNode;
		}
		size++;
	}

	// Insert at a specific position (0‑based)
	public void insertAtPosition(int data, int pos) {
		if (pos < 0 || pos > size)
			throw new IndexOutOfBoundsException();
		if (pos == 0) {
			insertAtHead(data);
		} else {
			Node newNode = new Node(data);
			Node curr = head;
			for (int i = 1; i < pos; i++)
				curr = curr.next;
			newNode.next = curr.next;
			curr.next = newNode;
			size++;
		}
	}

	// Delete from head
	public void deleteAtHead() {
		if (head == null)
			return;
		head = head.next;
		size--;
	}

	// Delete from tail
	public void deleteAtTail() {
		if (head == null)
			return;
		if (head.next == null) {
			head = null;
		} else {
			Node curr = head;
			while (curr.next.next != null)
				curr = curr.next;
			curr.next = null;
		}
		size--;
	}

	// Delete from specific position
	public void deleteAtPosition(int pos) {
		if (pos < 0 || pos >= size)
			throw new IndexOutOfBoundsException();
		if (pos == 0) {
			deleteAtHead();
		} else {
			Node curr = head;
			for (int i = 1; i < pos; i++)
				curr = curr.next;
			curr.next = curr.next.next;
			size--;
		}
	}

	// Search for a value
	public boolean search(int key) {
		Node curr = head;
		while (curr != null) {
			if (curr.data == key)
				return true;
			curr = curr.next;
		}
		return false;
	}

	// Display all nodes
	public void display() {
		Node curr = head;
		System.out.print("SLL: ");
		while (curr != null) {
			System.out.print(curr.data + " -> ");
			curr = curr.next;
		}
		System.out.println("null");
	}

	// Get current size
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		D3_SLL list = new D3_SLL();

		// 🚀 Real‑world analogy: Imagine a music playlist where each song points to the
		// next.
		list.insertAtHead(10);
		list.insertAtTail(20);
		list.insertAtTail(30);
		list.insertAtPosition(25, 2); // [10 -> 20 -> 25 -> 30]

		list.display(); // SLL: 10 -> 20 -> 25 -> 30 -> null
		System.out.println("Size: " + list.size()); // Size: 4

		System.out.println("Search 25? " + list.search(25)); // true
		System.out.println("Search 99? " + list.search(99)); // false

		list.deleteAtPosition(2); // remove 25
		list.deleteAtHead(); // remove 10
		list.deleteAtTail(); // remove 30

		list.display(); // SLL: 20 -> null
		System.out.println("Size: " + list.size()); // Size: 1
		
		// 🎯 Instantiate the built‑in LinkedList
	    LinkedList<Integer> list1 = new LinkedList<>();

	    // ➕ Add elements (head, tail, and at index)
	    list1.addFirst(10);              // [10]
	    list1.addLast(20);               // [10, 20]
	    list1.add(1, 15);                // insert at index 1 → [10, 15, 20]
	    System.out.println("After adds: " + list1);

	    // 🔍 Search / contains
	    System.out.println("Contains 15? " + list1.contains(15)); // true
	    System.out.println("Index of 20: " + list1.indexOf(20));  // 2

	    // 📏 Size
	    System.out.println("Size: " + list1.size());

	    // 🗑️ Remove elements (head, tail, and at index)
	    int removedHead = list1.removeFirst();    // removes 10
	    int removedTail = list1.removeLast();     // removes 20
	    int removedAt1   = list1.remove(0);       // now only one left at idx 0
	    System.out.printf("Removed: head=%d, tail=%d, at0=%d%n",
	                      removedHead, removedTail, removedAt1);

	    // ✅ Rebuild and iterate
	    list1.addAll(Arrays.asList(5, 25, 35, 45));   // [5,25,35,45]
	    System.out.print("Iterating: ");
	    for (Integer x : list1) {
	        System.out.print(x + " -> ");
	    }
	    System.out.println("null");
	}
}
