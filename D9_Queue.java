package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class D9_Queue {

	// Classical Manual Array-based Queue (non-circular)
	private static class ClassicalArrayQueue {
		private int[] arr;
		private int front, rear;
		private int size;

		public ClassicalArrayQueue(int size) {
			this.size = size;
			arr = new int[size];
			front = 0;
			rear = -1;
		}

		public void enqueue(int data) {
			if (rear == size - 1) {
				throw new IllegalStateException("Queue overflow");
			}
			arr[++rear] = data;
		}

		public int dequeue() {
			if (isEmpty()) {
				throw new IllegalStateException("Queue underflow");
			}
			int value = arr[front];
			front++;
			return value;
		}

		public int peek() {
			if (isEmpty())
				throw new IllegalStateException("Queue is empty");
			return arr[front];
		}

		public boolean isEmpty() {
			return front > rear;
		}

		public int size() {
			return isEmpty() ? 0 : (rear - front + 1);
		}

		public void display() {
			if (isEmpty()) {
				System.out.println("Queue is empty");
				return;
			}
			System.out.print("Classical Queue elements: \n");
			for (int i = front; i <= rear; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// --- Classical Manual Queue ---
		ClassicalArrayQueue classic = new ClassicalArrayQueue(5);
		classic.enqueue(10);
		classic.enqueue(20);
		classic.enqueue(30);
		classic.display();
		System.out.println("\nClassical Queue size: " + classic.size());
		System.out.println("Classical front: " + classic.peek());
		System.out.print("Classical dequeue sequence: ");
		while (!classic.isEmpty()) {
			System.out.print(classic.dequeue() + " ");
		}
		System.out.println("\nClassical isEmpty: " + classic.isEmpty());

		// --- Built-in Queue via LinkedList ---
		Queue<Integer> linkedQueue = new LinkedList<>();
		linkedQueue.offer(100);
		linkedQueue.offer(200);
		linkedQueue.offer(300);
		System.out.println("\nLinkedList Queue Elements: " + linkedQueue);
		System.out.println("\nLinkedList Queue poll: " + linkedQueue.poll());
		System.out.println("LinkedList Queue peek: " + linkedQueue.peek());
		System.out.println("LinkedList Queue size: " + linkedQueue.size());

		// --- Built-in ArrayDeque as Queue (recommended) ---
		Deque<Integer> dequeQueue = new ArrayDeque<>();
		dequeQueue.offer(1000);
		dequeQueue.offer(2000);
		dequeQueue.offer(3000);
		System.out.println("\nArrayDeque Queue Elements: " + dequeQueue);
		System.out.println("\nArrayDeque Queue poll: " + dequeQueue.poll());
		System.out.println("ArrayDeque Queue peek: " + dequeQueue.peek());
		System.out.println("ArrayDeque Queue size: " + dequeQueue.size());
	}
}
