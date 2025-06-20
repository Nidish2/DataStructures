package ds;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class DD18_HashSet {

	// Manual Implementation of HashSet using Separate Chaining
	private static class CustomHashSet {
		private static class Node {
			int key;
			Node next;

			Node(int key) {
				this.key = key;
			}
		}

		private Node[] bucketArray;
		private int capacity;
		private int currentSize;

		public CustomHashSet(int capacity) {
			this.capacity = capacity;
			this.bucketArray = new Node[capacity];
			this.currentSize = 0;
		}

		private int getBucketIndex(int key) {
			return Math.abs(key) % capacity;
		}

		public void add(int key) {
			int idx = getBucketIndex(key);
			Node head = bucketArray[idx];

			while (head != null) {
				if (head.key == key)
					return; // key already exists
				head = head.next;
			}

			Node newNode = new Node(key);
			newNode.next = bucketArray[idx];
			bucketArray[idx] = newNode;
			currentSize++;
		}

		public boolean contains(int key) {
			int idx = getBucketIndex(key);
			Node current = bucketArray[idx];

			while (current != null) {
				if (current.key == key)
					return true;
				current = current.next;
			}

			return false;
		}

		public void remove(int key) {
			int idx = getBucketIndex(key);
			Node current = bucketArray[idx], prev = null;

			while (current != null) {
				if (current.key == key) {
					if (prev == null) {
						bucketArray[idx] = current.next;
					} else {
						prev.next = current.next;
					}
					currentSize--;
					return;
				}
				prev = current;
				current = current.next;
			}
		}

		public int size() {
			return currentSize;
		}
	}

	public static void main(String[] args) {
		// ----------------- Manual Custom HashSet -----------------
		CustomHashSet customSet = new CustomHashSet(10);
		customSet.add(10);
		customSet.add(20);
		customSet.add(30);
		System.out.println("Custom Set Size: " + customSet.size());
		System.out.println("Contains 20? " + customSet.contains(20));
		customSet.remove(20);
		System.out.println("After removal, contains 20? " + customSet.contains(20));
		System.out.println("Custom Set Size: " + customSet.size());

		// ----------------- Built-in HashSet -----------------
		HashSet<Integer> hashSet = new HashSet<>();
		hashSet.add(10);
		hashSet.add(20);
		hashSet.add(30);
		System.out.println("\nHashSet: " + hashSet);
		System.out.println("HashSet contains 30? " + hashSet.contains(30));
		hashSet.remove(20);
		System.out.println("HashSet after removing 20: " + hashSet);

		// ----------------- Built-in LinkedHashSet -----------------
		LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();
		linkedSet.add(10);
		linkedSet.add(20);
		linkedSet.add(30);
		System.out.println("\nLinkedHashSet (insertion order): " + linkedSet);
		System.out.print("LinkedHashSet Iteration: ");
		for (int val : linkedSet) {
			System.out.print(val + " ");
		}

		// ----------------- Built-in TreeSet -----------------
		TreeSet<Integer> treeSet = new TreeSet<>();
		treeSet.add(30);
		treeSet.add(10);
		treeSet.add(20);
		System.out.println("\nTreeSet (sorted): " + treeSet);
		System.out.print("TreeSet Iteration: ");
		for (int val : treeSet) {
			System.out.print(val + " ");
		}
		System.out.println();
	}
}
