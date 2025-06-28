package ds;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DD20_MinHeap {

	// Manual Min-Heap implementation (array-based)
	private static class MinHeap {
		private int[] heap;
		private int size;

		public MinHeap(int capacity) {
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
			while (current > 0 && heap[current] < heap[parent(current)]) {
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
			int smallest = i;
			int left = leftChild(i);
			int right = rightChild(i);
			if (left < size && heap[left] < heap[smallest])
				smallest = left;
			if (right < size && heap[right] < heap[smallest])
				smallest = right;
			if (smallest != i) {
				swap(i, smallest);
				heapify(smallest);
			}
		}

		// ✅ 1. Heapify an arbitrary array into Min Heap
		public void heapifyArray(int[] arr) {
			if (arr.length > heap.length)
				throw new IllegalArgumentException("Array too large");
			System.arraycopy(arr, 0, heap, 0, arr.length);
			size = arr.length;
			for (int i = (size / 2) - 1; i >= 0; i--) {
				heapify(i);
			}
		}

		// ✅ 2. Decrease key at index = bubble up
		public void decreaseKey(int index, int newVal) {
			if (index >= size || newVal > heap[index])
				throw new IllegalArgumentException("Invalid decrease");
			heap[index] = newVal;
			while (index > 0 && heap[index] < heap[parent(index)]) {
				swap(index, parent(index));
				index = parent(index);
			}
		}

		// For Min Heap — increaseKey = bubble down
		public void increaseKey(int index, int newVal) {
			if (index >= size || newVal < heap[index])
				throw new IllegalArgumentException("Invalid increase");
			heap[index] = newVal;
			heapify(index); // bubble down
		}

		// ✅ 3. Delete a node at index
		public void delete(int index) {
			decreaseKey(index, Integer.MIN_VALUE);
			poll(); // removes the new min (which was at index)
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
		// --- Manual MinHeap ---
		MinHeap manual = new MinHeap(15);
		manual.add(20);
		manual.add(5);
		manual.add(15);
		manual.add(22);
		manual.add(3);
		System.out.println("Manual MinHeap elements: " + manual);
		System.out.println("Peek (min): " + manual.peek());

		// ✅ Decrease key
		manual.decreaseKey(3, 1); // 22 -> 1
		System.out.println("After decreaseKey at index 3 to 1: " + manual);

		// ✅ Increase key
		manual.increaseKey(1, 10); // 5 -> 10
		System.out.println("After increaseKey at index 1 to 10: " + manual);

		// ✅ Delete element at index 1
		manual.delete(1);
		System.out.println("After deleting index 1: " + manual);

		// ✅ Heapify an array
		int[] arr = { 40, 10, 30, 50, 20 };
		manual.heapifyArray(arr);
		System.out.println("After heapifyArray([40, 10, 30, 50, 20]): " + manual);

		System.out.println("Manual MinHeap size: " + manual.size());

		System.out.print("Poll sequence: ");
		while (!manual.isEmpty()) {
			System.out.print(manual.poll() + " ");
		}

		// --- Built-in PriorityQueue (Min-Heap) ---
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(20);
		pq.offer(5);
		pq.offer(15);
		pq.offer(22);
		pq.offer(3);
		System.out.println("\n\nPriorityQueue elements: " + pq);
		System.out.println("PQ peek (min): " + pq.peek());
		System.out.print("PQ poll sequence: ");
		while (!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
	}
}
