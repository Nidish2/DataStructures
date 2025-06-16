package ds;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.Iterator;

public class DD18_HashSet {

    // Manual HashSet using separate chaining
    private static class MyHashSet {
        private static class Node {
            int key;
            Node next;
            Node(int key) { this.key = key; }
        }

        private Node[] buckets;
        private int capacity;
        private int size;

        public MyHashSet(int capacity) {
            this.capacity = capacity;
            this.buckets = new Node[capacity];
            this.size = 0;
        }

        private int hash(int key) {
            return Math.abs(key) % capacity;
        }

        public void add(int key) {
            int idx = hash(key);
            Node curr = buckets[idx];
            while (curr != null) {
                if (curr.key == key) return; // already present
                curr = curr.next;
            }
            Node newNode = new Node(key);
            newNode.next = buckets[idx];
            buckets[idx] = newNode;
            size++;
        }

        public boolean contains(int key) {
            int idx = hash(key);
            Node curr = buckets[idx];
            while (curr != null) {
                if (curr.key == key) return true;
                curr = curr.next;
            }
            return false;
        }

        public void remove(int key) {
            int idx = hash(key);
            Node curr = buckets[idx], prev = null;
            while (curr != null) {
                if (curr.key == key) {
                    if (prev == null) buckets[idx] = curr.next;
                    else prev.next = curr.next;
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
        // --- Manual MyHashSet ---
        MyHashSet manual = new MyHashSet(10);
        manual.add(5);
        manual.add(15);
        manual.add(25);
        System.out.println("Manual set size: " + manual.size());
        System.out.println("Contains 15? " + manual.contains(15));
        System.out.println("Contains 99? " + manual.contains(99));
        manual.remove(15);
        System.out.println("After remove 15, contains 15? " + manual.contains(15));
        System.out.println("Manual set size: " + manual.size());

        // --- Built-in HashSet ---
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(5);
        hashSet.add(15);
        hashSet.add(25);
        System.out.println("\nHashSet: " + hashSet);
        System.out.println("HashSet contains 25? " + hashSet.contains(25));
        hashSet.remove(15);
        System.out.println("After remove 15: " + hashSet);

        // --- Built-in LinkedHashSet (insertion order) ---
        LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();
        linkedSet.add(5);
        linkedSet.add(15);
        linkedSet.add(25);
        System.out.println("\nLinkedHashSet: " + linkedSet);

        // --- Built-in TreeSet (sorted) ---
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(25);
        treeSet.add(5);
        treeSet.add(15);
        System.out.println("\nTreeSet (sorted): " + treeSet);
        System.out.print("Iterate TreeSet: ");
        Iterator<Integer> it = treeSet.iterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }
}
