package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DD11_DE_Queue {

    // Manual array-based Deque
    private static class ArrayDequeManual {
        private int[] arr;
        private int front;
        private int rear;
        private int size;

        public ArrayDequeManual(int capacity) {
            arr = new int[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        public void addFirst(int data) {
            if (size == arr.length) throw new IllegalStateException("Deque overflow");
            front = (front - 1 + arr.length) % arr.length;
            arr[front] = data;
            size++;
        }

        public void addLast(int data) {
            if (size == arr.length) throw new IllegalStateException("Deque overflow");
            rear = (rear + 1) % arr.length;
            arr[rear] = data;
            size++;
        }

        public int removeFirst() {
            if (isEmpty()) throw new IllegalStateException("Deque underflow");
            int value = arr[front];
            front = (front + 1) % arr.length;
            size--;
            return value;
        }

        public int removeLast() {
            if (isEmpty()) throw new IllegalStateException("Deque underflow");
            int value = arr[rear];
            rear = (rear - 1 + arr.length) % arr.length;
            size--;
            return value;
        }

        public int peekFirst() {
            if (isEmpty()) throw new IllegalStateException("Deque is empty");
            return arr[front];
        }

        public int peekLast() {
            if (isEmpty()) throw new IllegalStateException("Deque is empty");
            return arr[rear];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        // --- Manual ArrayDequeManual ---
        ArrayDequeManual manual = new ArrayDequeManual(5);
        manual.addLast(10);
        manual.addLast(20);
        manual.addFirst(5);
        manual.addLast(30);
        System.out.println("Manual Deque size: " + manual.size());
        System.out.println("Manual peekFirst: " + manual.peekFirst());
        System.out.println("Manual peekLast: " + manual.peekLast());
        System.out.print("Manual removeFirst/removeLast: ");
        System.out.print(manual.removeFirst() + " "); // 5
        System.out.print(manual.removeLast() + " ");  // 30
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
