package ds;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class DD21_MaxHeap {

	// Manual Max-Heap implementation (array-based)
	private static class MaxHeap {
		private int[] heap;
		private int size;

		public MaxHeap(int capacity) {
			heap = new int[capacity];
			size = 0;
		}

		private int parent(int i) {
			return (i - 1) / 2;
		}

		private int leftChild(int i) {
			return 2 * i + 1;
		}

		private int rightChild(int i) {
			return 2 * i + 2;
		}

		public void add(int val) {
			if (size == heap.length)
				throw new IllegalStateException("Heap overflow");
			heap[size] = val;
			int current = size++;
			// bubble up
			while (current > 0 && heap[current] > heap[parent(current)]) {
				swap(current, parent(current));
				current = parent(current);
			}
		}

		public int peek() {
			if (size == 0)
				throw new IllegalStateException("Heap is empty");
			return heap[0];
		}

		public int poll() {
			if (size == 0)
				throw new IllegalStateException("Heap underflow");
			int root = heap[0];
			heap[0] = heap[--size];
			heap[size] = 0;
			heapify(0);
			return root;
		}

		private void heapify(int i) {
			int largest = i;
			int left = leftChild(i);
			int right = rightChild(i);
			if (left < size && heap[left] > heap[largest])
				largest = left;
			if (right < size && heap[right] > heap[largest])
				largest = right;
			if (largest != i) {
				swap(i, largest);
				heapify(largest);
			}
		}

		private void swap(int i, int j) {
			int tmp = heap[i];
			heap[i] = heap[j];
			heap[j] = tmp;
		}

		public int size() {
			return size;
		}

		@Override
		public String toString() {
			return Arrays.toString(Arrays.copyOf(heap, size));
		}
	}

	public static void main(String[] args) {
		// --- Manual MaxHeap ---
		MaxHeap manual = new MaxHeap(10);
		manual.add(20);
		manual.add(5);
		manual.add(15);
		manual.add(22);
		manual.add(3);
		System.out.println("Manual MaxHeap elements: " + manual);
		System.out.println("Manual peek (max): " + manual.peek());
		System.out.print("Manual poll sequence: ");
		while (manual.size() > 0) {
			System.out.print(manual.poll() + " ");
		}
		System.out.println("\nManual size after polls: " + manual.size());

		// --- Built-in PriorityQueue as Max-Heap ---
		PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
		maxPQ.offer(20);
		maxPQ.offer(5);
		maxPQ.offer(15);
		maxPQ.offer(22);
		maxPQ.offer(3);
		System.out.println("\nPriorityQueue Max-Heap elements (unordered): " + maxPQ);
		System.out.println("PQ peek (max): " + maxPQ.peek());
		System.out.print("PQ poll sequence: ");
		while (!maxPQ.isEmpty()) {
			System.out.print(maxPQ.poll() + " ");
		}
		System.out.println("\nPQ size after polls: " + maxPQ.size());
	}
}
