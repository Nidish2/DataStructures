package ds;

import java.util.Iterator;
import java.util.TreeSet;

public class DD13_BST {

    // Manual BST Node
    private static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    private Node root;

    // Insert a value
    public void insert(int data) {
        root = insertRec(root, data);
    }
    private Node insertRec(Node node, int data) {
        if (node == null) return new Node(data);
        if (data < node.data) node.left = insertRec(node.left, data);
        else if (data > node.data) node.right = insertRec(node.right, data);
        return node;
    }

    // Search a value
    public boolean search(int key) {
        return searchRec(root, key);
    }
    private boolean searchRec(Node node, int key) {
        if (node == null) return false;
        if (key == node.data) return true;
        return key < node.data ? searchRec(node.left, key)
                               : searchRec(node.right, key);
    }

    // In-order traversal
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }
    private void inorderRec(Node node) {
        if (node == null) return;
        inorderRec(node.left);
        System.out.print(node.data + " ");
        inorderRec(node.right);
    }

    // Delete a value
    public void delete(int key) {
        root = deleteRec(root, key);
    }
    private Node deleteRec(Node node, int key) {
        if (node == null) return null;
        if (key < node.data) node.left = deleteRec(node.left, key);
        else if (key > node.data) node.right = deleteRec(node.right, key);
        else {
            // node with one or no child
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            // node with two children: get inorder successor (min in right)
            node.data = minValue(node.right);
            node.right = deleteRec(node.right, node.data);
        }
        return node;
    }
    private int minValue(Node node) {
        int minv = node.data;
        while (node.left != null) {
            node = node.left;
            minv = node.data;
        }
        return minv;
    }

    public static void main(String[] args) {
        // --- Manual BST ---
        DD13_BST tree = new DD13_BST();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) tree.insert(v);
        System.out.print("Manual BST inorder: ");
        tree.inorder();  // 20 30 40 50 60 70 80

        System.out.println("Search 40? " + tree.search(40)); // true
        System.out.println("Search 90? " + tree.search(90)); // false

        tree.delete(20);
        tree.delete(30);
        tree.delete(50);
        System.out.print("After deletions inorder: ");
        tree.inorder();  // 40 60 70 80

        // --- Built-in TreeSet (balanced BST) ---
        TreeSet<Integer> bstSet = new TreeSet<>();
        for (int v : values) bstSet.add(v);
        System.out.println("\nBuilt-in TreeSet: " + bstSet);
        System.out.println("Contains 60? " + bstSet.contains(60));
        bstSet.remove(70);
        System.out.println("After remove 70: " + bstSet);

        System.out.print("Iterate TreeSet: ");
        Iterator<Integer> it = bstSet.iterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }
}
