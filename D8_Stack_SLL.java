package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class D8_Stack_SLL {

    // Manual LinkedList-based Stack (using SLL)
    private static class SLLStack {
        private static class Node {
            int data;
            Node next;
            Node(int data) { this.data = data; }
        }

        private Node top;
        private int size;

        public void push(int data) {
            Node newNode = new Node(data);
            newNode.next = top;
            top = newNode;
            size++;
        }

        public int pop() {
            if (isEmpty()) throw new IllegalStateException("Stack underflow");
            int value = top.data;
            top = top.next;
            size--;
            return value;
        }

        public int peek() {
            if (isEmpty()) throw new IllegalStateException("Stack is empty");
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        // --- Manual SLLStack ---
        SLLStack manual = new SLLStack();
        manual.push(10);
        manual.push(20);
        manual.push(30);
        System.out.println("Manual SLLStack size: " + manual.size());
        System.out.println("Manual peek: " + manual.peek());
        System.out.print("Manual pop sequence: ");
        while (!manual.isEmpty()) {
            System.out.print(manual.pop() + " ");
        }
        System.out.println("\nManual isEmpty: " + manual.isEmpty());

        // --- Built-in java.util.Stack ---
        Stack<Integer> builtStack = new Stack<>();
        builtStack.push(100);
        builtStack.push(200);
        builtStack.push(300);
        System.out.println("\nBuilt-in Stack pop: " + builtStack.pop());
        System.out.println("Built-in Stack peek: " + builtStack.peek());
        System.out.println("Built-in Stack size: " + builtStack.size());

        // --- Built-in ArrayDeque as Stack --- (recommended)
        Deque<Integer> dequeStack = new ArrayDeque<>();
        dequeStack.push(1000);
        dequeStack.push(2000);
        dequeStack.push(3000);
        System.out.println("\nArrayDeque pop: " + dequeStack.pop());
        System.out.println("ArrayDeque peek: " + dequeStack.peek());
        System.out.println("ArrayDeque size: " + dequeStack.size());
        
        // --- Built-in LinkedList as Stack ---
        LinkedList<Integer> linkedListStack = new LinkedList<>();
        linkedListStack.push(10000);
        linkedListStack.push(20000);
        linkedListStack.push(30000);
        System.out.println("\nLinkedList Stack pop: " + linkedListStack.pop());
        System.out.println("LinkedList Stack peek: " + linkedListStack.peek());
        System.out.println("LinkedList Stack size: " + linkedListStack.size());
        // Note: LinkedList in Java is not a stack, but can be used as one
    }
}
