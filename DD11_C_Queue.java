package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DD11_C_Queue {

	// Circular Queue using Array
	static class CircularQueue {
		private int[] arr;
		private int front;
		private int rear;
		private int size;
		private int capacity;

		CircularQueue(int capacity) {
			this.capacity = capacity;
			arr = new int[capacity];
			front = -1;
			rear = -1;
			size = 0;
		}

		// Enqueue
		public void enqueue(int data) {
			if (isFull()) {
				System.out.println("Circular Queue is full");
				return;
			}
			if (isEmpty()) {
				front = rear = 0;
			} else {
				rear = (rear + 1) % capacity;
			}
			arr[rear] = data;
			size++;
		}

		// Dequeue
		public int dequeue() {
			if (isEmpty()) {
				throw new IllegalStateException("Circular Queue is empty");
			}
			int value = arr[front];
			if (front == rear) { // only one element
				front = rear = -1;
			} else {
				front = (front + 1) % capacity;
			}
			size--;
			return value;
		}

		// Peek front
		public int peek() {
			if (isEmpty()) {
				throw new IllegalStateException("Circular Queue is empty");
			}
			return arr[front];
		}

		// Check if empty
		public boolean isEmpty() {
			return size == 0;
		}

		// Check if full
		public boolean isFull() {
			return size == capacity;
		}

		// Display queue
		public void display() {
			if (isEmpty()) {
				System.out.println("Circular Queue is empty");
				return;
			}
			System.out.print("Circualr Queue: ");
			for (int i = 0; i < size; i++) {
				System.out.print(arr[(front + i) % capacity] + " ");
			}
			System.out.println();
		}

		// Get size
		public int getSize() {
			return size;
		}
	}

	public static void main(String[] args) {
		CircularQueue cq = new CircularQueue(5);

		cq.enqueue(10);
		cq.enqueue(20);
		cq.enqueue(30);
		cq.enqueue(40);
		cq.enqueue(50);

		cq.display(); // 10 20 30 40 50
		System.out.println("Dequeued: " + cq.dequeue()); // 10
		cq.enqueue(60); // adds to front due to circularity
		cq.display(); // 20 30 40 50 60
		System.out.println("Dequeued: " + cq.dequeue()); // removes 20
		cq.display(); // 30 40 50 60

		System.out.println("Front: " + cq.peek()); // 20
		System.out.println("Size: " + cq.getSize());

		// Using Queue with LinkedList (FIFO)
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(10);
		queue.offer(20);
		queue.offer(30);
		System.out.println("In Built Circular Queue: \n" + queue);
		System.out.println("In Built Circular Queue size: " + queue.size()); // 3
		System.out.println("In Built Circular Queue poll: " + queue.poll()); // 10
		System.out.println("In Built Circular Queue peek: " + queue.peek()); // 20

		// Using ArrayDeque as Circular Queue
		Deque<Integer> circularQueue = new ArrayDeque<>();
		circularQueue.offer(100);
		circularQueue.offer(200);
		circularQueue.offer(300);
		System.out.println("ArrayDeque Circular Queue: " + circularQueue);
		System.out.println("ArrayDeque size: " + circularQueue.size()); // 3
		System.out.println("ArrayDeque poll: " + circularQueue.poll()); // 100
		System.out.println("ArrayDeque peek: " + circularQueue.peek()); // 200

		// Simulate circular behavior (wrap manually if needed)
		circularQueue.offer(400);
		circularQueue.offer(500);
		circularQueue.offer(600); // Add more if needed
		System.out.println("Final Circular Queue: " + circularQueue);
	}
}
