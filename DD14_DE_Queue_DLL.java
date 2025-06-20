package ds;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayDeque;

public class DD14_DE_Queue_DLL {

	// Classical DLL-based Deque
	private static class DequeDLL {
		private static class Node {
			int data;
			Node prev;
			Node next;

			Node(int data) {
				this.data = data;
			}
		}

		private Node front; // Same as head
		private Node rear; // Same as tail
		private int size;

		public void insertFront(int data) {
			Node newNode = new Node(data);
			if (front == null) {
				front = rear = newNode;
			} else {
				newNode.next = front;
				front.prev = newNode;
				front = newNode;
			}
			size++;
		}

		public void insertRear(int data) {
			Node newNode = new Node(data);
			if (rear == null) {
				front = rear = newNode;
			} else {
				rear.next = newNode;
				newNode.prev = rear;
				rear = newNode;
			}
			size++;
		}

		public int deleteFront() {
			if (isEmpty())
				throw new IllegalStateException("Deque underflow");
			int val = front.data;
			if (front == rear) {
				front = rear = null;
			} else {
				front = front.next;
				front.prev = null;
			}
			size--;
			return val;
		}

		public int deleteRear() {
			if (isEmpty())
				throw new IllegalStateException("Deque underflow");
			int val = rear.data;
			if (front == rear) {
				front = rear = null;
			} else {
				rear = rear.prev;
				rear.next = null;
			}
			size--;
			return val;
		}

		public int getFront() {
			if (isEmpty())
				throw new IllegalStateException("Deque is empty");
			return front.data;
		}

		public int getRear() {
			if (isEmpty())
				throw new IllegalStateException("Deque is empty");
			return rear.data;
		}

		public boolean isEmpty() {
			return front == null;
		}

		public int size() {
			return size;
		}

		public void displayFrontToRear() {
			if (isEmpty()) {
				System.out.println("Deque is empty");
				return;
			}
			Node current = front;
			System.out.print("Deque Elements from front to rear: ");
			while (current != null) {
				System.out.print(current.data + " ");
				current = current.next;
			}
			System.out.println();
		}

		public void displayRearToFront() {
			if (isEmpty()) {
				System.out.println("Deque is empty");
				return;
			}
			Node current = rear;
			System.out.print("Deque Elements from rear to front: ");
			while (current != null) {
				System.out.print(current.data + " ");
				current = current.prev;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// --- Manual DequeDLL ---
		DequeDLL deque = new DequeDLL();
		deque.insertFront(10);
		deque.insertRear(20);
		deque.insertFront(5);
		deque.insertRear(30);
		deque.displayFrontToRear();
		deque.displayRearToFront();
		System.out.println("Manual DLL Deque size: " + deque.size());
		System.out.println("Front element: " + deque.getFront());
		System.out.println("Rear element: " + deque.getRear());
		System.out.print("Removing from front and rear: ");
		System.out.print(deque.deleteFront() + " "); // 5
		System.out.print(deque.deleteRear() + " "); // 30
		System.out.println("\nSize after removals: " + deque.size());

		// --- Built-in Deque via LinkedList ---
		Deque<Integer> linked = new LinkedList<>();
		linked.addFirst(100);
		linked.addLast(200);
		linked.addFirst(50);
		linked.addLast(300);
		System.out.println("\nLinkedList Deque: " + linked);
		System.out.println("LinkedList removeFirst: " + linked.removeFirst());
		System.out.println("LinkedList removeLast: " + linked.removeLast());
		System.out.println("LinkedList peekFirst: " + linked.peekFirst());
		System.out.println("LinkedList peekLast: " + linked.peekLast());
		System.out.println("LinkedList size: " + linked.size());

		// --- Built-in ArrayDeque (recommended) ---
		Deque<Integer> adq = new ArrayDeque<>();
		adq.addFirst(1000);
		adq.addLast(2000);
		adq.addFirst(500);
		adq.addLast(3000);
		System.out.println("\nArrayDeque: " + adq);
		System.out.println("ArrayDeque removeFirst: " + adq.removeFirst());
		System.out.println("ArrayDeque removeLast: " + adq.removeLast());
		System.out.println("ArrayDeque peekFirst: " + adq.peekFirst());
		System.out.println("ArrayDeque peekLast: " + adq.peekLast());
		System.out.println("ArrayDeque size: " + adq.size());
	}
}
