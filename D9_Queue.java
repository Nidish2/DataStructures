package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class D9_Queue {

    // Manual Array-based Queue
    private static class ArrayQueue {
        private int[] arr;
        private int head = 0, tail = 0, size = 0;

        public ArrayQueue(int capacity) {
            arr = new int[capacity];
        }

        public void enqueue(int data) {
            if (size == arr.length) throw new IllegalStateException("Queue overflow");
            arr[tail] = data;
            tail = (tail + 1) % arr.length;
            size++;
        }

        public int dequeue() {
            if (isEmpty()) throw new IllegalStateException("Queue underflow");
            int value = arr[head];
            head = (head + 1) % arr.length;
            size--;
            return value;
        }

        public int peek() {
            if (isEmpty()) throw new IllegalStateException("Queue is empty");
            return arr[head];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        // --- Manual ArrayQueue ---
        ArrayQueue manual = new ArrayQueue(5);
        manual.enqueue(10);
        manual.enqueue(20);
        manual.enqueue(30);
        System.out.println("Manual Queue size: " + manual.size());
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
        System.out.println("\nLinkedList Queue poll: " + linkedQueue.poll());
        System.out.println("LinkedList Queue peek: " + linkedQueue.peek());
        System.out.println("LinkedList Queue size: " + linkedQueue.size());

        // --- Built-in ArrayDeque as Queue (recommended) ---
        Deque<Integer> dequeQueue = new ArrayDeque<>();
        dequeQueue.offer(1000);
        dequeQueue.offer(2000);
        dequeQueue.offer(3000);
        System.out.println("\nArrayDeque Queue poll: " + dequeQueue.poll());
        System.out.println("ArrayDeque Queue peek: " + dequeQueue.peek());
        System.out.println("ArrayDeque Queue size: " + dequeQueue.size());
    }
}
