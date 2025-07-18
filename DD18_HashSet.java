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

	private static class CustomHashSet<K> {

		private static class Node<K> {
			K key;
			Node<K> next;

			Node(K key) {
				this.key = key;
			}
		}

		private Node<K>[] bucket;
		private int capacity; // Initial capacity of the hash set
		private int currentSize; // Current number of elements in the hash set

		@SuppressWarnings("unchecked")
		public CustomHashSet(int capacity) {
			this.capacity = capacity;
			this.bucket = new Node[capacity]; // 👈 Generic array
			this.currentSize = 0;
		}

		private int getBucketIndex(K key) {
			return Math.abs(key.hashCode()) % capacity;
		}

		public void add(K key) {
			int idx = getBucketIndex(key);
			Node<K> current = bucket[idx];

			while (current != null) {
				if (current.key.equals(key)) {
					System.out.println("Key " + key + " already exists in the set.");
					return; // duplicate
				}
				current = current.next;
			}

			Node<K> newNode = new Node<>(key);
			newNode.next = bucket[idx];
			bucket[idx] = newNode;
			currentSize++;
			if (currentSize / capacity > 2) {
				rehash();
			}
		}

		@SuppressWarnings("unchecked")
		private void rehash() {
			Node<K>[] oldBucket = bucket;
			capacity *= 2; // Double the capacity
			bucket = new Node[capacity];
			currentSize = 0; // Reset current size to 0 for re-insertion

			for (Node<K> node : oldBucket) {
				Node<K> current = node;
				while (current != null) {
					add(current.key); // Re-inserts into the new resized table
					current = current.next;
				}
			}
		}

		public boolean contains(K key) {
			int idx = getBucketIndex(key);
			Node<K> current = bucket[idx];

			while (current != null) {
				if (current.key.equals(key))
					return true;
				current = current.next;
			}
			return false;
		}

		public void remove(K key) {
			int idx = getBucketIndex(key);
			Node<K> current = bucket[idx], prev = null;

			while (current != null) {
				if (current.key.equals(key)) {
					if (prev == null) {
						bucket[idx] = current.next;
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
			Arrays.fill(bucket, null);
			currentSize = 0;
		}

		public boolean isEmpty() {
			return currentSize == 0;
		}

		public int size() {
			return currentSize;
		}

		public List<K> toList() {
			List<K> result = new ArrayList<>();
			for (Node<K> node : bucket) {
				Node<K> current = node;
				while (current != null) {
					result.add(current.key);
					current = current.next;
				}
			}
			return result;
		}

		public CustomHashSet<K> cloneSet() {
			CustomHashSet<K> cloned = new CustomHashSet<>(capacity);
			for (K val : this.toList()) {
				cloned.add(val);
			}
			return cloned;
		}

		public boolean equals(CustomHashSet<K> other) {
			if (this.size() != other.size())
				return false;
			for (K val : this.toList()) {
				if (!other.contains(val))
					return false;
			}
			return true;
		}

		public CustomHashSet<K> union(CustomHashSet<K> other) {
			CustomHashSet<K> result = this.cloneSet();
			for (K val : other.toList()) {
				result.add(val);
			}
			return result;
		}

		public CustomHashSet<K> intersection(CustomHashSet<K> other) {
			CustomHashSet<K> result = new CustomHashSet<>(capacity);
			for (K val : this.toList()) {
				if (other.contains(val))
					result.add(val);
			}
			return result;
		}

		public void printSet() {
			System.out.println("CustomHashSet: " + this.toList());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < capacity; i++) {
				sb.append("Bucket ").append(i).append(": ");
				Node<K> current = bucket[i];
				while (current != null) {
					sb.append("{").append(current.key).append("} ");
					current = current.next;
				}
				sb.append("\n");
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		System.out.println("----------- Generic Manual CustomHashSet -----------");

		CustomHashSet<Integer> set = new CustomHashSet<>(10);
		set.add(10);
		set.add(20);
		set.add(30);
		set.add(20); // Duplicate, should not be added again
		set.add(40);
		set.add(50);
		set.add(61);
		set.add(72);
		set.add(103); // Should trigger rehashing if needed
		set.add(84);
		set.add(95);
		set.add(106);
		set.add(117); // Should trigger rehashing if needed
		set.add(125); // Should trigger rehashing if needed
		set.add(138); // Should trigger rehashing if needed
		set.add(149); // Should trigger rehashing if needed

		System.out.println("Custom Set Elements: \n" + set);

		set.printSet();
		System.out.println("Custom Set Size: " + set.size());
		System.out.println("Contains 20? " + set.contains(20));
		set.remove(20);
		System.out.println("After removal, contains 20? " + set.contains(20));
		System.out.println("Set Size after removal: " + set.size());

		System.out.println("Custom Set Elements: \n" + set);
		System.out.println("Custom Set Elements: " + set.toList());

		CustomHashSet<Integer> anotherSet = new CustomHashSet<>(10);
		anotherSet.add(30);
		anotherSet.add(40);
		anotherSet.add(10);

		System.out.println("Union: " + set.union(anotherSet).toList());
		System.out.println("After Union: \n" + set);
		System.out.println("Intersection: " + set.intersection(anotherSet).toList());
		System.out.println("Sets Equal? " + set.equals(anotherSet));

		set.clear();
		System.out.println("After clear, isEmpty? " + set.isEmpty());

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
