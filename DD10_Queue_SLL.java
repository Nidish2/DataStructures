package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DD10_Queue_SLL {

	// === Manual SLL-based Queue ===
	private static class SLLQueue {
		private static class Node {
			int data;
			Node next;

			Node(int data) {
				this.data = data;
			}
		}

		private Node front; // same as 'head'
		private Node rear; // same as 'tail'
		private int size;

		public void enqueue(int data) {
			Node newNode = new Node(data);
			if (rear == null) {
				front = rear = newNode;
			} else {
				rear.next = newNode;
				rear = newNode;
			}
			size++;
		}

		public int dequeue() {
			if (isEmpty())
				throw new IllegalStateException("Queue underflow");
			int value = front.data;
			front = front.next;
			if (front == null)
				rear = null;
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
				System.out.println("SLL Queue is empty");
				return;
			}
			System.out.print("SLL Queue elements: ");
			Node current = front;
			while (current != null) {
				System.out.print(current.data + " ");
				current = current.next;
			}
			System.out.println();
		}
	}

	// === Main Function ===
	public static void main(String[] args) {

		// --- Manual SLLQueue ---
		SLLQueue manual = new SLLQueue();
		manual.enqueue(10);
		manual.enqueue(20);
		manual.enqueue(30);
		manual.display();
		System.out.println("Manual SLLQueue size: " + manual.size());
		System.out.println("Manual front: " + manual.peek());

		System.out.print("Manual dequeue sequence: ");
		while (!manual.isEmpty()) {
			System.out.print(manual.dequeue() + " ");
		}
		System.out.println("\nManual isEmpty: " + manual.isEmpty());

		// --- Built-in Queue via LinkedList ---
		Queue<Integer> linkedQueue = new LinkedList<>();
		linkedQueue.offer(100);
		linkedQueue.offer(200);
		linkedQueue.offer(300);
		System.out.println("\nLinkedList Queue Elements: " + linkedQueue);
		System.out.println("LinkedList Queue poll: " + linkedQueue.poll());
		System.out.println("LinkedList Queue peek: " + linkedQueue.peek());
		System.out.println("LinkedList Queue size: " + linkedQueue.size());

		// --- Built-in Queue via ArrayDeque ---
		Deque<Integer> dequeQueue = new ArrayDeque<>();
		dequeQueue.offer(1000);
		dequeQueue.offer(2000);
		dequeQueue.offer(3000);
		System.out.println("\nArrayDeque Queue Elements: " + dequeQueue);
		System.out.println("ArrayDeque Queue poll: " + dequeQueue.poll());
		System.out.println("ArrayDeque Queue peek: " + dequeQueue.peek());
		System.out.println("ArrayDeque Queue size: " + dequeQueue.size());

		// --- LinkedList as Queue (again for clarity) ---
		LinkedList<Integer> builtQueue = new LinkedList<>();
		builtQueue.offer(10000);
		builtQueue.offer(20000);
		builtQueue.offer(30000);
		System.out.println("\nLinkedList (again) Queue Elements: " + builtQueue);
		System.out.println("LinkedList poll: " + builtQueue.poll());
		System.out.println("LinkedList peek: " + builtQueue.peek());
		System.out.println("LinkedList size: " + builtQueue.size());
	}
}
