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

		private void swap(int i, int j) {
			int tmp = heap[i];
			heap[i] = heap[j];
			heap[j] = tmp;
		}

		public void add(int val) {
			if (size == heap.length)
				throw new IllegalStateException("Heap overflow");
			heap[size] = val;
			int current = size++;
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

		// ✅ 1. Heapify an arbitrary array into Max Heap
		public void heapifyArray(int[] arr) {
			if (arr.length > heap.length)
				throw new IllegalArgumentException("Array too large");
			System.arraycopy(arr, 0, heap, 0, arr.length);
			size = arr.length;
			for (int i = (size / 2) - 1; i >= 0; i--) {
				heapify(i);
			}
		}

		// ✅ 2. Increase key at index
		public void increaseKey(int index, int newVal) {
			if (index >= size || newVal < heap[index])
				throw new IllegalArgumentException("Invalid increase");
			heap[index] = newVal;
			while (index > 0 && heap[index] > heap[parent(index)]) {
				swap(index, parent(index));
				index = parent(index);
			}
		}

		// ✅ 3. Decrease key at index
		public void decreaseKey(int index, int newVal) {
			if (index >= size || newVal > heap[index])
				throw new IllegalArgumentException("Invalid decrease");
			heap[index] = newVal;
			heapify(index); // restore heap property
		}

		// ✅ 3. Delete a node at index
		public void delete(int index) {
			increaseKey(index, Integer.MAX_VALUE);
			poll(); // now root has the target, just remove
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		@Override
		public String toString() {
			return Arrays.toString(Arrays.copyOf(heap, size));
		}
	}

	public static void main(String[] args) {
		// --- Manual MaxHeap ---
		MaxHeap manual = new MaxHeap(15);
		manual.add(20);
		manual.add(5);
		manual.add(15);
		manual.add(22);
		manual.add(3);
		manual.add(10);
		manual.add(30);
		manual.add(25);
		System.out.println("Manual MaxHeap elements: " + manual);
		System.out.println("Peek (max): " + manual.peek());

		// ✅ Increase key
		manual.increaseKey(3, 50); // 22 -> 50
		System.out.println("After increaseKey at index 3 (22) to 50: " + manual);

		// ✅ Decrease key
		manual.decreaseKey(1, 2); // 5 -> 2
		System.out.println("After decreaseKey at index 1 (5) to 2: " + manual);

		// ✅ Delete element at index 1
		manual.delete(1);
		System.out.println("After deleting index 1: " + manual);

		// ✅ Heapify array
		int[] arr = { 40, 10, 30, 50, 20 };
		System.out.println("Heapify array: " + Arrays.toString(arr));
		manual.heapifyArray(arr);
		System.out.println("After heapifyArray([40, 10, 30, 50, 20]): " + manual);

		System.out.println("Manual MaxHeap size: " + manual.size());

		// ✅ Poll all
		System.out.print("Poll sequence: ");
		while (!manual.isEmpty()) {
			System.out.print(manual.poll() + " ");
		}

		// --- Built-in PriorityQueue as Max-Heap ---
		PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
		maxPQ.offer(20);
		maxPQ.offer(5);
		maxPQ.offer(15);
		maxPQ.offer(22);
		maxPQ.offer(3);
		System.out.println("\n\nPriorityQueue Max-Heap elements (unordered): " + maxPQ);
		System.out.println("PQ peek (max): " + maxPQ.peek());
		System.out.print("PQ poll sequence: ");
		while (!maxPQ.isEmpty()) {
			System.out.print(maxPQ.poll() + " ");
		}
		System.out.println("\nPQ size after polls: " + maxPQ.size());
	}
}
