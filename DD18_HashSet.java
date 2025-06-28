package ds;

import java.util.*;

public class DD18_HashSet {

	// Manual Implementation of HashSet using Separate Chaining

	/*
	 * 💡 What is Separate Chaining? 👉 It’s a collision resolution strategy used in
	 * hash tables. When two keys hash to the same index, separate chaining handles
	 * this by storing multiple key-value pairs at the same index using a linked
	 * list (or other structure like a tree or list).
	 */

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
			Node cur = bucketArray[idx];

			while (cur != null) {
				if (cur.key == key)
					return; // duplicate
				cur = cur.next;
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

		public void clear() {
			Arrays.fill(bucketArray, null);
			currentSize = 0;
		}

		public boolean isEmpty() {
			return currentSize == 0;
		}

		public int size() {
			return currentSize;
		}

		public List<Integer> toList() {
			List<Integer> result = new ArrayList<>();
			for (Node bucket : bucketArray) {
				Node current = bucket;
				while (current != null) {
					result.add(current.key);
					current = current.next;
				}
			}
			return result;
		}

		public CustomHashSet cloneSet() {
			CustomHashSet cloned = new CustomHashSet(capacity);
			for (int val : this.toList()) {
				cloned.add(val);
			}
			return cloned;
		}

		public boolean equals(CustomHashSet other) {
			if (this.size() != other.size())
				return false;
			for (int val : this.toList()) {
				if (!other.contains(val))
					return false;
			}
			return true;
		}

		public CustomHashSet union(CustomHashSet other) {
			CustomHashSet result = this.cloneSet();
			for (int val : other.toList()) {
				result.add(val);
			}
			return result;
		}

		public CustomHashSet intersection(CustomHashSet other) {
			CustomHashSet result = new CustomHashSet(capacity);
			for (int val : this.toList()) {
				if (other.contains(val))
					result.add(val);
			}
			return result;
		}

		public void printSet() {
			System.out.println("CustomHashSet: " + this.toList());
		}
	}

	public static void main(String[] args) {
		System.out.println("----------- Manual CustomHashSet -----------");
		CustomHashSet classicalSet = new CustomHashSet(10);
		classicalSet.add(10);
		classicalSet.add(20);
		classicalSet.add(30);

		classicalSet.printSet();
		System.out.println("Custom Set Size: " + classicalSet.size());
		System.out.println("Contains 20? " + classicalSet.contains(20));
		classicalSet.remove(20);
		System.out.println("After removal, contains 20? " + classicalSet.contains(20));
		System.out.println("Custom Set Elements: " + classicalSet.toList());

		CustomHashSet anotherSet = new CustomHashSet(10);
		anotherSet.add(30);
		anotherSet.add(40);
		anotherSet.add(10);

		System.out.println("Union: " + classicalSet.union(anotherSet).toList());
		System.out.println("Intersection: " + classicalSet.intersection(anotherSet).toList());
		System.out.println("Sets Equal? " + classicalSet.equals(anotherSet));

		classicalSet.clear();
		System.out.println("After clear, isEmpty? " + classicalSet.isEmpty());

		System.out.println("\n----------- Built-in HashSet -----------");
		HashSet<String> techSet = new HashSet<>();
		techSet.add("Java");
		techSet.add("Python");
		techSet.add("C++");

		System.out.println("HashSet: " + techSet);
		System.out.println("Contains Python? " + techSet.contains("Python"));
		techSet.remove("Python");
		System.out.println("After remove: " + techSet);
		System.out.println("Size: " + techSet.size());
		System.out.println("Is Empty? " + techSet.isEmpty());

		@SuppressWarnings("unchecked")
		HashSet<String> clonedSet = (HashSet<String>) techSet.clone();
		System.out.println("Cloned HashSet: " + clonedSet);

		HashSet<String> backendSet = new HashSet<>();
		backendSet.add("Java");
		backendSet.add("Node");

		HashSet<String> union = new HashSet<>(techSet);
		union.addAll(backendSet);

		HashSet<String> intersection = new HashSet<>(techSet);
		intersection.retainAll(backendSet);

		System.out.println("Union: " + union);
		System.out.println("Intersection: " + intersection);
		System.out.println("Equals backendSet? " + techSet.equals(backendSet));

		System.out.println("\n----------- LinkedHashSet (Order Preserved) -----------");
		LinkedHashSet<Integer> orderedSet = new LinkedHashSet<>();
		orderedSet.add(3);
		orderedSet.add(1);
		orderedSet.add(2);
		System.out.println("LinkedHashSet: " + orderedSet);

		System.out.println("\n----------- TreeSet (Sorted Order) -----------");
		TreeSet<Integer> sortedSet = new TreeSet<>();
		sortedSet.add(5);
		sortedSet.add(1);
		sortedSet.add(3);
		System.out.println("TreeSet: " + sortedSet);
	}
}
