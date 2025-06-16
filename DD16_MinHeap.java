package ds;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DD16_MinHeap {

    // Manual Min-Heap implementation (array-based)
    private static class MinHeap {
        private int[] heap;
        private int size;

        public MinHeap(int capacity) {
            heap = new int[capacity];
            size = 0;
        }

        private int parent(int i) { return (i - 1) / 2; }
        private int leftChild(int i) { return 2 * i + 1; }
        private int rightChild(int i) { return 2 * i + 2; }

        public void add(int val) {
            if (size == heap.length) throw new IllegalStateException("Heap overflow");
            heap[size] = val;
            int current = size++;
            // bubble up
            while (current > 0 && heap[current] < heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
        }

        public int peek() {
            if (size == 0) throw new IllegalStateException("Heap is empty");
            return heap[0];
        }

        public int poll() {
            if (size == 0) throw new IllegalStateException("Heap underflow");
            int root = heap[0];
            heap[0] = heap[--size];
            heap[size] = 0;
            heapify(0);
            return root;
        }

        private void heapify(int i) {
            int smallest = i;
            int left = leftChild(i);
            int right = rightChild(i);
            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;
            if (smallest != i) {
                swap(i, smallest);
                heapify(smallest);
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
        // --- Manual MinHeap ---
        MinHeap manual = new MinHeap(10);
        manual.add(20);
        manual.add(5);
        manual.add(15);
        manual.add(22);
        manual.add(3);
        System.out.println("Manual MinHeap elements: " + manual);
        System.out.println("Manual peek (min): " + manual.peek());
        System.out.print("Manual poll sequence: ");
        while (manual.size() > 0) {
            System.out.print(manual.poll() + " ");
        }
        System.out.println("\nManual size after polls: " + manual.size());

        // --- Built-in PriorityQueue (Min-Heap) ---
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(20);
        pq.offer(5);
        pq.offer(15);
        pq.offer(22);
        pq.offer(3);
        System.out.println("\nPriorityQueue elements: " + pq);
        System.out.println("PQ peek (min): " + pq.peek());
        System.out.print("PQ poll sequence: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println("\nPQ size after polls: " + pq.size());
    }
}
