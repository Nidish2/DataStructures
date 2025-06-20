package ds;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;

public class DD12_C_Queue_SLL {

	// Manual Circular Queue using SLL
	private static class CircularQueueSLL {
		private static class Node {
			int data;
			Node next;

			Node(int data) {
				this.data = data;
			}
		}

		private Node front = null, rear = null;
		private int size = 0;

		// Enqueue operation
		public void enqueue(int data) {
			Node newNode = new Node(data);
			if (rear == null) {
				front = rear = newNode;
				rear.next = front; // circular link
			} else {
				rear.next = newNode;
				rear = newNode;
				rear.next = front;
			}
			size++;
		}

		// Dequeue operation
		public int dequeue() {
			if (isEmpty())
				throw new IllegalStateException("Queue underflow");
			int value = front.data;
			if (front == rear) { // only one element
				front = rear = null;
			} else {
				front = front.next;
				rear.next = front;
			}
			size--;
			return value;
		}

		public int peek() {
			if (isEmpty())
				throw new IllegalStateException("Queue is empty");
			return front.data;
		}

		public boolean isEmpty() {
			return front == null;
		}

		public int size() {
			return size;
		}

		public void display() {
			if (isEmpty()) {
				System.out.println("Circular SLL Queue is empty");
				return;
			}
			System.out.print("Circular SLL Queue: ");
			Node curr = front;

			while (true) {
				System.out.print(curr.data + " -> ");
				curr = curr.next;
				if (curr == front)
					break; // looped back to front
			}
			System.out.println("(back to front)");
		}
	}

	public static void main(String[] args) {

		// ✅ Manual Circular Queue using SLL
		CircularQueueSLL manual = new CircularQueueSLL();
		manual.enqueue(10);
		manual.enqueue(20);
		manual.enqueue(30);
		manual.display(); // 10 -> 20 -> 30 -> (back to front)
		System.out.println("Size: " + manual.size());
		System.out.println("Dequeued: " + manual.dequeue());
		System.out.println("Front: " + manual.peek());
		manual.display();
		System.out.println("Dequeuing all elements:");
		while (!manual.isEmpty()) {
			System.out.println("Dequeued: " + manual.dequeue());
		}
		System.out.println();

		// ✅ Built-in Circular Queue Simulation using LinkedList
		Queue<Integer> linkedQueue = new LinkedList<>();
		linkedQueue.offer(100);
		linkedQueue.offer(200);
		linkedQueue.offer(300);
		System.out.println("\nLinkedList Queue: " + linkedQueue);
		System.out.println("LinkedList Queue size: " + linkedQueue.size());
		System.out.println("\nLinkedList Queue poll: " + linkedQueue.poll());
		System.out.println("LinkedList Queue peek: " + linkedQueue.peek());

		// ✅ Built-in Circular Queue Simulation using ArrayDeque
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(1000);
		deque.offer(2000);
		deque.offer(3000);
		System.out.println("\nArrayDeque Circular Queue: " + deque);
		System.out.println("ArrayDeque size: " + deque.size());
		System.out.println("\nArrayDeque poll: " + deque.poll());
		System.out.println("ArrayDeque peek: " + deque.peek());

		// Simulate circular loop manually
		System.out.print("Circular-like iteration: ");
		int limit = 6, count = 0;
		while (count < limit) {
			if (deque.isEmpty())
				break;
			int val = deque.poll();
			System.out.print(val + " → ");
			deque.offer(val); // circular effect
			count++;
		}
		System.out.println("(...)");
	}
}
