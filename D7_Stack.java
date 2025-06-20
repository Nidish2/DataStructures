package ds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class D7_Stack {

	// Manual Array-based Stack
	private static class ArrayStack {
		private int[] arr;
		private int top = -1;

		public ArrayStack(int capacity) {
			arr = new int[capacity];
		}

		public void push(int data) {
			if (top == arr.length - 1)
				throw new IllegalStateException("Stack overflow");
			arr[++top] = data;
		}

		public int pop() {
			if (isEmpty())
				throw new IllegalStateException("Stack underflow");
			return arr[top--];
		}

		public int peek() {
			if (isEmpty())
				throw new IllegalStateException("Stack is empty");
			return arr[top];
		}

		public boolean isEmpty() {
			return top == -1;
		}

		public int size() {
			return top + 1;
		}

		public void display() {
			if (isEmpty()) {
				System.out.println("Stack is empty");
				return;
			}
			System.out.print("Stack elements: \n");
			for (int i = top; i >= 0; i--) {
				System.out.println(arr[i]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// --- Manual ArrayStack ---
		ArrayStack manual = new ArrayStack(5);
		manual.push(10);
		manual.push(20);
		manual.push(30);
		manual.display();
		System.out.println("Manual Stack size: " + manual.size());
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
		System.out.println("Built-in Stack Elements: " + builtStack);
		System.out.println("\nBuilt-in Stack pop: " + builtStack.pop());
		System.out.println("Built-in Stack peek: " + builtStack.peek());
		System.out.println("Built-in Stack size: " + builtStack.size());

		// --- Built-in ArrayDeque as Stack --- (recommended)
		Deque<Integer> dequeStack = new ArrayDeque<>();
		dequeStack.push(1000);
		dequeStack.push(2000);
		dequeStack.push(3000);
		System.out.println("\nArrayDeque Stack Elements: " + dequeStack);
		System.out.println("\nArrayDeque pop: " + dequeStack.pop());
		System.out.println("ArrayDeque peek: " + dequeStack.peek());
		System.out.println("ArrayDeque size: " + dequeStack.size());
	}
}
