package ds;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class DD19_HashMap {

	// Manual HashMap Implementation (Separate Chaining)
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

		public int size() {
			return currentSize;
		}
	}

	public static void main(String[] args) {
		// ---------------- Manual Custom HashMap ----------------
		CustomHashMap customMap = new CustomHashMap(10);
		customMap.put(1, 100);
		customMap.put(2, 200);
		customMap.put(3, 300);
		System.out.println("Custom Map Size: " + customMap.size());
		System.out.println("Get key 2: " + customMap.get(2));
		customMap.put(2, 250);
		System.out.println("Updated key 2: " + customMap.get(2));
		customMap.remove(3);
		System.out.println("After remove key 3, get 3: " + customMap.get(3));
		System.out.println("Custom Map Size: " + customMap.size());

		// ---------------- In-built HashMap ----------------
		Map<Integer, Integer> hashMap = new HashMap<>();
		hashMap.put(1, 100);
		hashMap.put(2, 200);
		hashMap.put(3, 300);
		System.out.println("\nHashMap: " + hashMap);
		System.out.println("HashMap contains key 2? " + hashMap.containsKey(2));
		hashMap.put(2, 250);
		System.out.println("Updated HashMap: " + hashMap);
		hashMap.remove(3);
		System.out.println("After removing key 3: " + hashMap);

		// ---------------- In-built LinkedHashMap ----------------
		Map<Integer, Integer> linkedMap = new LinkedHashMap<>();
		linkedMap.put(1, 100);
		linkedMap.put(2, 200);
		linkedMap.put(3, 300);
		System.out.println("\nLinkedHashMap (Insertion Order): " + linkedMap);

		// ---------------- In-built TreeMap ----------------
		Map<Integer, Integer> treeMap = new TreeMap<>();
		treeMap.put(3, 300);
		treeMap.put(1, 100);
		treeMap.put(2, 200);
		System.out.println("\nTreeMap (Sorted by Key): " + treeMap);
		System.out.print("TreeMap Iteration: ");
		for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
			System.out.print("{" + entry.getKey() + ":" + entry.getValue() + "} ");
		}
		System.out.println();
	}
}
