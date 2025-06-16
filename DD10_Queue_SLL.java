package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DD10_Queue_SLL {

    // Manual SLL-based Queue
    private static class SLLQueue {
        private static class Node {
            int data;
            Node next;
            Node(int data) { this.data = data; }
        }

        private Node head;  // points to front
        private Node tail;  // points to rear
        private int size;

        public void enqueue(int data) {
            Node newNode = new Node(data);
            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        public int dequeue() {
            if (isEmpty()) throw new IllegalStateException("Queue underflow");
            int value = head.data;
            head = head.next;
            if (head == null) tail = null;
            size--;
            return value;
        }

        public int peek() {
            if (isEmpty()) throw new IllegalStateException("Queue is empty");
            return head.data;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        // --- Manual SLLQueue ---
        SLLQueue manual = new SLLQueue();
        manual.enqueue(10);
        manual.enqueue(20);
        manual.enqueue(30);
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
        
        // --- Built-in LinkedList as Queue ---
        LinkedList<Integer> builtQueue = new LinkedList<>();
        builtQueue.offer(10000);
        builtQueue.offer(20000);
        builtQueue.offer(30000);
        System.out.println("\nLinkedList Queue poll: " + builtQueue.poll());
        System.out.println("LinkedList Queue peek: " + builtQueue.peek());
        System.out.println("LinkedList Queue size: " + builtQueue.size());
        
    }
}
