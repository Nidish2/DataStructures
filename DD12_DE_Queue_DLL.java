package ds;

import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayDeque;

public class DD12_DE_Queue_DLL {

	// Manual DLL-based Deque
	private static class DLLDeque {
		private static class Node {
			int data;
			Node prev;
			Node next;

			Node(int data) {
				this.data = data;
			}
		}

		private Node head; // front
		private Node tail; // rear
		private int size;

		public void addFirst(int data) {
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

		public void addLast(int data) {
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

		public int removeFirst() {
			if (isEmpty())
				throw new IllegalStateException("Deque underflow");
			int val = head.data;
			if (head == tail) {
				head = tail = null;
			} else {
				head = head.next;
				head.prev = null;
			}
			size--;
			return val;
		}

		public int removeLast() {
			if (isEmpty())
				throw new IllegalStateException("Deque underflow");
			int val = tail.data;
			if (head == tail) {
				head = tail = null;
			} else {
				tail = tail.prev;
				tail.next = null;
			}
			size--;
			return val;
		}

		public int peekFirst() {
			if (isEmpty())
				throw new IllegalStateException("Deque is empty");
			return head.data;
		}

		public int peekLast() {
			if (isEmpty())
				throw new IllegalStateException("Deque is empty");
			return tail.data;
		}

		public boolean isEmpty() {
			return head == null;
		}

		public int size() {
			return size;
		}
	}

	public static void main(String[] args) {
		// --- Manual DLLDeque ---
		DLLDeque manual = new DLLDeque();
		manual.addFirst(10);
		manual.addLast(20);
		manual.addFirst(5);
		manual.addLast(30);
		System.out.println("Manual DLLDeque size: " + manual.size());
		System.out.println("Manual peekFirst: " + manual.peekFirst());
		System.out.println("Manual peekLast: " + manual.peekLast());
		System.out.print("Manual removeFirst/removeLast: ");
		System.out.print(manual.removeFirst() + " "); // 5
		System.out.print(manual.removeLast() + " "); // 30
		System.out.println("\nManual size after removals: " + manual.size());

		// --- Built-in Deque via LinkedList ---
		Deque<Integer> linked = new LinkedList<>();
		linked.addFirst(100);
		linked.addLast(200);
		linked.addFirst(50);
		System.out.println("\nLinkedList Deque: " + linked);
		System.out.println("LinkedList removeFirst: " + linked.removeFirst());
		System.out.println("LinkedList removeLast: " + linked.removeLast());
		System.out.println("LinkedList peekFirst: " + linked.peekFirst());
		System.out.println("LinkedList size: " + linked.size());

		// --- Built-in ArrayDeque (recommended) ---
		Deque<Integer> adq = new ArrayDeque<>();
		adq.addFirst(1000);
		adq.addLast(2000);
		adq.addFirst(500);
		System.out.println("\nArrayDeque: " + adq);
		System.out.println("ArrayDeque removeFirst: " + adq.removeFirst());
		System.out.println("ArrayDeque removeLast: " + adq.removeLast());
		System.out.println("ArrayDeque peekLast: " + adq.peekLast());
		System.out.println("ArrayDeque size: " + adq.size());
	}
}
