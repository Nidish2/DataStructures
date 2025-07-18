package ds;

import java.util.*;

public class DD19_HashMap {

	// Manual CustomHashMap Implementation (Separate Chaining)

	/*
	 * 💡 What is Separate Chaining? 👉 It’s a collision resolution strategy used in
	 * hash tables. When two keys hash to the same index, separate chaining handles
	 * this by storing multiple key-value pairs at the same index using a linked
	 * list (or other structure like a tree or list).
	 */

	private static class CustomHashMap<K, V> {
		private static class Entry<K, V> {
			K key;
			V value;
			Entry<K, V> next;

			Entry(K key, V value) {
				this.key = key;
				this.value = value;
			}
		}

		private Entry<K, V>[] table;
		private int capacity; // Initial capacity of the hash table
		private int currentSize; // Current number of key-value pairs in the hash table

		@SuppressWarnings("unchecked")
		public CustomHashMap(int capacity) {
			this.capacity = capacity;
			this.table = new Entry[capacity];
			this.currentSize = 0;
		}

		private int getBucketIndex(K key) {
			return Math.abs(key.hashCode()) % capacity;
		}

		public void put(K key, V value) {
			int index = getBucketIndex(key);
			Entry<K, V> head = table[index];

			for (Entry<K, V> e = head; e != null; e = e.next) {
				if (e.key.equals(key)) {
					System.out.println("Key already exists, updating value.");
					e.value = value;
					return;
				}
			}

			Entry<K, V> newEntry = new Entry<>(key, value);
			newEntry.next = head;
			table[index] = newEntry;
			currentSize++;
			if (currentSize / capacity > 2) {
				rehash();
			}
		}

		@SuppressWarnings("unchecked")
		private void rehash() {
			Entry<K, V>[] oldTable = table;
			capacity *= 2; // Double the capacity
			table = new Entry[capacity];
			currentSize = 0; // Reset size for re-insertion

			for (Entry<K, V> head : oldTable) {
				while (head != null) {
					put(head.key, head.value); // Re-insert into new table
					head = head.next;
				}
			}
		}

		public V get(K key) {
			int index = getBucketIndex(key);
			Entry<K, V> current = table[index];
			while (current != null) {
				if (current.key.equals(key))
					return current.value;
				current = current.next;
			}
			return null;
		}

		public void remove(K key) {
			int index = getBucketIndex(key);
			Entry<K, V> current = table[index], prev = null;

			while (current != null) {
				if (current.key.equals(key)) {
					if (prev == null)
						table[index] = current.next;
					else
						prev.next = current.next;
					currentSize--;
					return;
				}
				prev = current;
				current = current.next;
			}
		}

		public boolean containsKey(K key) {
			return get(key) != null;
		}

		public boolean isEmpty() {
			return currentSize == 0;
		}

		public void clear() {
			Arrays.fill(table, null);
			currentSize = 0;
		}

		public int size() {
			return currentSize;
		}

		public Set<K> keySet() {
			Set<K> keys = new HashSet<>();
			for (Entry<K, V> e : table) {
				while (e != null) {
					keys.add(e.key);
					e = e.next;
				}
			}
			return keys;
		}

		public List<V> values() {
			List<V> vals = new ArrayList<>();
			for (Entry<K, V> e : table) {
				while (e != null) {
					vals.add(e.value);
					e = e.next;
				}
			}
			return vals;
		}

		public Map<K, V> toMap() {
			Map<K, V> map = new HashMap<>();
			for (Entry<K, V> e : table) {
				while (e != null) {
					map.put(e.key, e.value);
					e = e.next;
				}
			}
			return map;
		}

		public boolean equals(CustomHashMap<K, V> other) {
			return this.toMap().equals(other.toMap());
		}

		public CustomHashMap<K, V> cloneMap() {
			CustomHashMap<K, V> copy = new CustomHashMap<>(capacity);
			for (Entry<K, V> e : table) {
				while (e != null) {
					copy.put(e.key, e.value);
					e = e.next;
				}
			}
			return copy;
		}

		public void printMap() {
			System.out.println("Map: " + this.toMap());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < capacity; i++) {
				sb.append("Bucket ").append(i).append(": ");
				Entry<K, V> current = table[i];
				while (current != null) {
					sb.append("{").append(current.key).append(": ").append(current.value).append("} ");
					current = current.next;
				}
				sb.append("\n");
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		System.out.println("----------- Manual CustomHashMap -----------");
		CustomHashMap<Integer, String> classicalMap = new CustomHashMap<>(10);
		classicalMap.put(100, "One Hundred");
		classicalMap.put(2, "two");
		classicalMap.put(3, "Three");
		classicalMap.put(4, "Four");
		classicalMap.put(5, "Five");
		classicalMap.put(6, "Six");
		classicalMap.put(7, "Seven");
		classicalMap.put(8, "Eight");
		classicalMap.put(9, "Nine");
		classicalMap.put(10, "Ten");
		classicalMap.put(11, "Eleven");
		classicalMap.put(12, "Twelve");
		classicalMap.put(13, "Thirteen");
		classicalMap.put(14, "Fourteen");
		classicalMap.put(15, "Fifteen");

		System.out.println("Map: \n" + classicalMap);
		classicalMap.printMap();
		System.out.println("Size: " + classicalMap.size());
		System.out.println("Get key 2: " + classicalMap.get(2));
		classicalMap.put(22, "Twenty Two");
		classicalMap.put(2, "Two");
		System.out.println("Updated key 2: " + classicalMap.get(2));
		classicalMap.remove(3);
		System.out.println("KeySet: " + classicalMap.keySet());
		System.out.println("Values: " + classicalMap.values());
		System.out.println("Contains key 1? " + classicalMap.containsKey(1));

		classicalMap.printMap();

		CustomHashMap<Integer, String> copyMap = classicalMap.cloneMap();
		System.out.println("Cloned equals original? " + copyMap.equals(classicalMap));
		classicalMap.clear();
		System.out.println("After clear, isEmpty? " + classicalMap.isEmpty());

		System.out.println("\n----------- Built-in HashMap -----------");
		HashMap<String, String> techMap = new HashMap<>();
		techMap.put("Java", "Backend");
		techMap.put("Python", "AI/ML");
		techMap.put("JavaScript", "Frontend");

		System.out.println("Map: " + techMap);
		System.out.println("Contains Key 'Java'? " + techMap.containsKey("Java"));
		System.out.println("Contains Value 'AI/ML'? " + techMap.containsValue("AI/ML"));
		techMap.putIfAbsent("C++", "System");
		techMap.replace("Python", "Data Science");
		techMap.remove("JavaScript");

		System.out.println("Updated Map: " + techMap);
		System.out.println("Keys: " + techMap.keySet());
		System.out.println("Values: " + techMap.values());
		System.out.println("EntrySet: " + techMap.entrySet());

		@SuppressWarnings("unchecked")
		HashMap<String, String> cloned = (HashMap<String, String>) techMap.clone();
		System.out.println("Cloned Map: " + cloned);
		System.out.println("Equal? " + techMap.equals(cloned));
		techMap.clear();
		System.out.println("After Clear - isEmpty? " + techMap.isEmpty());

		System.out.println("\n----------- LinkedHashMap (Insertion Order) -----------");
		LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>();
		linkedMap.put(1, "One");
		linkedMap.put(2, "Two");
		linkedMap.put(3, "Three");
		System.out.println("LinkedHashMap: " + linkedMap);

		System.out.println("\n----------- TreeMap (Sorted by Key) -----------");
		TreeMap<Integer, String> sortedMap = new TreeMap<>();
		sortedMap.put(3, "Three");
		sortedMap.put(1, "One");
		sortedMap.put(2, "Two");

		for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
			System.out.print("{" + entry.getKey() + ":" + entry.getValue() + "} ");
		}
		System.out.println();
	}
}
