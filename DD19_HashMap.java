package ds;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

public class DD19_HashMap {

	// Manual HashMap using separate chaining
	private static class MyHashMap {
		private static class Entry {
			int key;
			int value;
			Entry next;

			Entry(int key, int value) {
				this.key = key;
				this.value = value;
			}
		}

		private Entry[] buckets;
		private int capacity;
		private int size;

		public MyHashMap(int capacity) {
			this.capacity = capacity;
			buckets = new Entry[capacity];
			size = 0;
		}

		private int hash(int key) {
			return Math.abs(key) % capacity;
		}

		public void put(int key, int value) {
			int idx = hash(key);
			Entry head = buckets[idx];
			for (Entry e = head; e != null; e = e.next) {
				if (e.key == key) {
					e.value = value;
					return;
				}
			}
			Entry newEntry = new Entry(key, value);
			newEntry.next = head;
			buckets[idx] = newEntry;
			size++;
		}

		public Integer get(int key) {
			int idx = hash(key);
			for (Entry e = buckets[idx]; e != null; e = e.next) {
				if (e.key == key)
					return e.value;
			}
			return null;
		}

		public void remove(int key) {
			int idx = hash(key);
			Entry curr = buckets[idx];
			Entry prev = null;
			while (curr != null) {
				if (curr.key == key) {
					if (prev == null)
						buckets[idx] = curr.next;
					else
						prev.next = curr.next;
					size--;
					return;
				}
				prev = curr;
				curr = curr.next;
			}
		}

		public int size() {
			return size;
		}
	}

	public static void main(String[] args) {
		// --- Manual MyHashMap ---
		MyHashMap manualMap = new MyHashMap(10);
		manualMap.put(1, 100);
		manualMap.put(2, 200);
		manualMap.put(3, 300);
		System.out.println("Manual size: " + manualMap.size());
		System.out.println("Get key 2: " + manualMap.get(2));
		manualMap.put(2, 250);
		System.out.println("Updated key 2: " + manualMap.get(2));
		manualMap.remove(3);
		System.out.println("After remove key 3, get 3: " + manualMap.get(3));
		System.out.println("Manual size: " + manualMap.size());

		// --- Built-in HashMap ---
		Map<Integer, Integer> hashMap = new HashMap<>();
		hashMap.put(1, 100);
		hashMap.put(2, 200);
		hashMap.put(3, 300);
		System.out.println("\nHashMap: " + hashMap);
		System.out.println("Contains key 2? " + hashMap.containsKey(2));
		hashMap.put(2, 250);
		System.out.println("Updated HashMap: " + hashMap);
		hashMap.remove(3);
		System.out.println("After remove 3: " + hashMap);

		// --- Built-in LinkedHashMap (insertion-order) ---
		Map<Integer, Integer> linkedMap = new LinkedHashMap<>();
		linkedMap.put(1, 100);
		linkedMap.put(2, 200);
		linkedMap.put(3, 300);
		System.out.println("\nLinkedHashMap: " + linkedMap);

		// --- Built-in TreeMap (sorted by key) ---
		Map<Integer, Integer> treeMap = new TreeMap<>();
		treeMap.put(3, 300);
		treeMap.put(1, 100);
		treeMap.put(2, 200);
		System.out.println("\nTreeMap (sorted): " + treeMap);
		System.out.print("Iterate TreeMap entries: ");
		for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
			System.out.print("{" + entry.getKey() + ":" + entry.getValue() + "} ");
		}
		System.out.println();
	}
}
