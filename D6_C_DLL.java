package ds;

import java.util.Iterator;
import java.util.LinkedList;

public class D6_C_DLL {

	// Manual Node for Circular Doubly Linked List
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
			head.next = head;
			head.prev = head;
		} else {
			newNode.next = head;
			newNode.prev = tail;
			tail.next = newNode;
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
			head.next = head;
			head.prev = head;
		} else {
			newNode.prev = tail;
			newNode.next = head;
			tail.next = newNode;
			head.prev = newNode;
			tail = newNode;
		}
		size++;
	}

	// Insert at tail optimized
	public void insertAtTail_op(int data) {
		insertAtHead(data);
		head = head.next; // move head back, so new node is tail
		tail = head.prev;
	}

	// Insert at position
	public void insertAtPosition(int data, int position) {
		if (position < 0 || position > size) {
			System.out.println("Invalid position");
			return;
		}
		if (position == 0) {
			insertAtHead(data);
			return;
		}
		if (position == size) {
			insertAtTail(data);
			return;
		}

		Node newNode = new Node(data);
		Node curr = head;
		for (int i = 0; i < position - 1; i++) {
			curr = curr.next;
		}
		newNode.prev = curr;
		newNode.next = curr.next;
		curr.next.prev = newNode;
		curr.next = newNode;
		size++;
	}

	// Delete at head
	public void deleteAtHead() {
		if (head == null)
			return;
		if (head == tail) {
			head = tail = null;
		} else {
			tail.next = head.next;
			head.next.prev = tail;
			head = head.next;
		}
		size--;
	}

	// Delete at tail
	public void deleteAtTail() {
		if (tail == null)
			return;
		if (head == tail) {
			head = tail = null;
		} else {
			tail.prev.next = head;
			head.prev = tail.prev;
			tail = tail.prev;
		}
		size--;
	}

	// Delete at position
	public void deleteAtPosition(int position) {
		if (position < 0 || position >= size) {
			System.out.println("Invalid position");
			return;
		}
		if (position == 0) {
			deleteAtHead();
			return;
		}
		if (position == size - 1) {
			deleteAtTail();
			return;
		}

		Node curr = head;
		for (int i = 0; i < position - 1; i++) {
			curr = curr.next;
		}
		Node toDelete = curr.next;
		curr.next = toDelete.next;
		toDelete.next.prev = curr;

		size--;
	}

	// Display forward
	public void displayForward() {
		if (head == null) {
			System.out.println("CDLL is empty");
			return;
		}
		System.out.print("CDLL Forward: ");
		Node curr = head;

		while (curr != tail) {
			System.out.print(curr.data + " <-> ");
			curr = curr.next;
		}
		System.out.print(tail.data + " <-> (back to head)\n");
	}

	// Display backward
	public void displayBackward() {
		if (tail == null) {
			System.out.println("CDLL is empty");
			return;
		}
		System.out.print("CDLL Backward: ");
		Node curr = tail;

		while (curr != head) {
			System.out.print(curr.data + " <-> ");
			curr = curr.prev;
		}
		System.out.print(head.data + " <-> (back to tail)\n");
	}

	// Search
	public boolean search(int key) {
		if (head == null)
			return false;
		Node curr = head;
		while (curr != tail) {
			if (curr.data == key)
				return true;
			curr = curr.next;
		}
		return false;
	}

	// Get size
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		// --- Manual CDLL ---
		D6_C_DLL manual = new D6_C_DLL();
		manual.insertAtHead(1); // 1
//        manual.insertAtTail(2);
//        manual.insertAtTail(3);
		manual.insertAtTail_op(2); // 1 <-> 2
		manual.insertAtTail_op(3); // 1 <-> 2 <-> 3
		manual.insertAtHead(0); // 0 <-> 1 <-> 2 <-> 3
		manual.insertAtPosition(11, 2); // 0 <-> 1 <-> 11 <-> 2 <-> 3

		manual.displayForward(); // CDLL Forward: 0 <-> 1 <-> 2 <-> 3 <-> (back to head)
		manual.displayBackward(); // CDLL Backward: 3 <-> 2 <-> 1 <-> 0 <-> (back to tail)
		System.out.println("Manual contains 2? " + manual.search(2));
		manual.deleteAtHead();
		manual.deleteAtTail();
		manual.deleteAtPosition(1); // Deletes 2
		manual.displayForward(); // CDLL Forward: 1 <-> 2 <-> (back to head)

		// --- Built-in LinkedList Simulation ---
		LinkedList<Integer> built = new LinkedList<>();
		built.add(1);
		built.add(2);
		built.add(3);
		built.add(4);
		System.out.println("Built list: " + built);

		// Circular traverse 6 steps
		System.out.print("Circular traverse: ");
		Iterator<Integer> it = built.iterator();
		for (int i = 0; i < 6; i++) {
			if (!it.hasNext())
				it = built.iterator();
			System.out.print(it.next() + " -> ");
		}
		System.out.println("(...)");

		// Remove and add at both ends
		built.removeFirst(); // removes 1
		built.removeLast(); // removes 4
		built.addFirst(0);
		built.addLast(5);
		System.out.println("Modified built: " + built);
		System.out.println("Built contains 3? " + built.contains(3));
		System.out.println("Built size: " + built.size());
	}
}
