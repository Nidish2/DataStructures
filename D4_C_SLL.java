package ds;

import java.util.*;

public class D4_C_SLL {

	// Node inner class
	private static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			this.next = null; // next will be set in circular manner
		}
	}

	private Node head; // points to tail node
	private int size;

	// Insert at head (front)
	public void insertAtHead(int data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			head.next = head; // points to itself
		} else {
			Node curr = head;
			while (curr.next != head) {
				curr = curr.next; // reach the last node
			}
			newNode.next = head; // new node points to old head
			curr.next = newNode; // last node now points to new head
			head = newNode; // update head to new node
		}
		size++;
	}

	// Insert at tail (end)
	public void insertAtTail_op(int data) {
		insertAtHead(data);
		head = head.next; // move head back, so new node is tail
	}

	// Insert at tail (end)
	public void insertAtTail(int data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			head.next = head;
		} else {
			Node curr = head;
			while (curr.next != head) {
				curr = curr.next; // find the last node
			}
			curr.next = newNode;
			newNode.next = head; // close the loop
		}
		size++;
	}

	// Insert at position (0-based)
	public void insertAtPosition(int data, int pos) {
		if (pos < 0 || pos > size)
			throw new IndexOutOfBoundsException(pos + " is out of bounds for size " + size);
		if (pos == 0) {
			insertAtHead(data);
		} else if (pos == size) {
			insertAtTail(data);
		} else {
			Node curr = head; // ✅ start from actual head
			for (int i = 1; i < pos; i++) {
				curr = curr.next;
			}
			Node newNode = new Node(data);
			newNode.next = curr.next;
			curr.next = newNode;
			size++;
		}
	}

	// Delete head
	public void deleteAtHead() {
		if (head == null) {
			System.out.println("CSLL is empty, nothing to delete.");
			return;
		}
		if (head.next == head) {
			head = null;
		} else {
			Node curr = head;
			// Find the last node (tail)
			while (curr.next != head) {
				curr = curr.next;
			}
			curr.next = head.next; // last node points to new head
			head = head.next; // move head to next node
		}
		size--;
	}

	// Delete tail
	public void deleteAtTail() {
		if (head == null) {
			System.out.println("CSLL is empty, nothing to delete.");
			return;
		}
		if (head.next == head) {
			head = null;
		} else {
			Node curr = head;
			while (curr.next.next != head) {
				curr = curr.next;
			}
			// curr.next is tail
			curr.next = head; // remove tail
		}
		size--;
	}

	// Delete at position
	public void deleteAtPosition(int pos) {
		if (pos < 0 || pos >= size)
			throw new IndexOutOfBoundsException(pos + " is out of bounds for size " + size);
		if (pos == 0) {
			deleteAtHead();
		} else if (pos == size - 1) {
			deleteAtTail();
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
		if (head == null)
			return false;
		Node curr = head;
		do {
			if (curr.data == key)
				return true;
			curr = curr.next;
		} while (curr != head);
		return false;
	}

	// Display list
	public void display() {
		if (head == null) {
			System.out.println("CSLL is empty");
			return;
		}
		System.out.print("CSLL: ");
		Node curr = head;
		// Start from head and loop until we reach back to head
		while (curr.next != head) {
			System.out.print(curr.data + " -> ");
			curr = curr.next;
		}
		System.out.print(curr.data + " -> (back to head)\n");
	}

	// Get size
	public int size() {
		return size;
	}

	// Main method for testing
	public static void main(String[] args) {
		D4_C_SLL list = new D4_C_SLL();

		// Real‑world vibe: a round‑robin playlist that loops forever
		list.insertAtHead(100);
//		list.insertAtTail(200);
//		list.insertAtTail(300);
		list.insertAtTail_op(200); // insert at tail using head manipulation
		list.insertAtTail_op(300); // insert at tail using head manipulation
		list.insertAtPosition(250, 2); // [100 → 200 → 250 → 300 → ...]

		list.display(); // CSLL: 100 -> 200 -> 250 -> 300 -> (back to head)
		System.out.println("Size: " + list.size()); // 4

		System.out.println("Search 250? " + list.search(250)); // true
		System.out.println("Search 999? " + list.search(999)); // false

		list.deleteAtPosition(2); // remove 250
		list.deleteAtHead(); // remove 100
		list.deleteAtTail(); // remove 300

		list.display(); // CSLL: 200 -> (back to head)
		System.out.println("Size: " + list.size()); // 1

		// Demonstrating in built Java LinkedList as a circular linked list
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
			if (!it.hasNext())
				it = csll.iterator(); // wrap around
			System.out.print(it.next() + " → ");
		}
		System.out.println("(…)");

		csll.addLast(400); // add 400 at tail
		System.out.println("After removeFirst & addLast: " + csll);
		// [200, 300, 400]

		// 🔍 Search & size
		System.out.println("Contains 300? " + csll.contains(300));
		System.out.println("Size now: " + csll.size());

		// 🗑️ Remove at index (pos 1)
		int removedAt1 = csll.remove(1); // removes 300
		System.out.println("Removed at index 1: " + removedAt1);
		System.out.println("Final list: " + csll); // [200, 400]
	}
}
