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

	private static class CustomHashMap {
		private static class Entry {
			int key;
			int value;
			Entry next;

			Entry(int key, int value) {
				this.key = key;
				this.value = value;
			}
		}

		private Entry[] table;
		private int capacity;
		private int currentSize;

		public CustomHashMap(int capacity) {
			this.capacity = capacity;
			this.table = new Entry[capacity];
			this.currentSize = 0;
		}

		private int getBucketIndex(int key) {
			return Math.abs(key) % capacity;
		}

		public void put(int key, int value) {
			int index = getBucketIndex(key);
			Entry head = table[index];

			for (Entry e = head; e != null; e = e.next) {
				if (e.key == key) {
					e.value = value;
					// Key already exists, update value
					return;
				}
			}

			Entry newEntry = new Entry(key, value);
			newEntry.next = table[index];
			table[index] = newEntry;
			currentSize++;
		}

		public Integer get(int key) {
			int index = getBucketIndex(key);
			Entry current = table[index];
			while (current != null) {
				if (current.key == key)
					return current.value;
				current = current.next;
			}
			return null;
		}

		public void remove(int key) {
			int index = getBucketIndex(key);
			Entry current = table[index], prev = null;

			while (current != null) {
				if (current.key == key) {
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

		public boolean containsKey(int key) {
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

		public Set<Integer> keySet() {
			Set<Integer> keys = new HashSet<>();
			for (Entry e : table) {
				while (e != null) {
					keys.add(e.key);
					e = e.next;
				}
			}
			return keys;
		}

		public List<Integer> values() {
			List<Integer> vals = new ArrayList<>();
			for (Entry e : table) {
				while (e != null) {
					vals.add(e.value);
					e = e.next;
				}
			}
			return vals;
		}

		public Map<Integer, Integer> toMap() {
			Map<Integer, Integer> map = new HashMap<>();
			for (Entry e : table) {
				while (e != null) {
					map.put(e.key, e.value);
					e = e.next;
				}
			}
			return map;
		}

		public boolean equals(CustomHashMap other) {
			return this.toMap().equals(other.toMap());
		}

		public CustomHashMap cloneMap() {
			CustomHashMap copy = new CustomHashMap(capacity);
			for (Entry e : table) {
				while (e != null) {
					copy.put(e.key, e.value);
					e = e.next;
				}
			}
			return copy;
		}

		public void printMap() {
			for (int i = 0; i < capacity; i++) {
				System.out.print("Bucket " + i + ": ");
				Entry current = table[i];
				while (current != null) {
					System.out.print("{" + current.key + ":" + current.value + "} ");
					current = current.next;
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("----------- Manual CustomHashMap -----------");
		CustomHashMap classicalMap = new CustomHashMap(10);
		classicalMap.put(1, 100);
		classicalMap.put(2, 200);
		classicalMap.put(3, 300);
		classicalMap.put(4, 400);
		classicalMap.put(5, 500);
		classicalMap.put(6, 600);
		classicalMap.put(7, 700);
		classicalMap.put(8, 800);
		classicalMap.put(9, 900);
		classicalMap.put(10, 1000);
		classicalMap.put(11, 1100); // This will cause a collision with key 1
		classicalMap.put(12, 1200); // This will cause a collision with key 2
		classicalMap.put(13, 1300); // This will cause a collision with key 3
		classicalMap.put(14, 1400); // This will cause a collision with key 4
		classicalMap.put(15, 1500); // This will cause a collision with key 5

		System.out.println("Size: " + classicalMap.size());
		System.out.println("Get key 2: " + classicalMap.get(2));
		classicalMap.put(22, 250);
		System.out.println("Updated key 2: " + classicalMap.get(2));
		classicalMap.remove(3);
		System.out.println("KeySet: " + classicalMap.keySet());
		System.out.println("Values: " + classicalMap.values());
		System.out.println("Contains key 1? " + classicalMap.containsKey(1));

		classicalMap.printMap();

		CustomHashMap copyMap = classicalMap.cloneMap();
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
