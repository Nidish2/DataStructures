package ds;

import java.util.TreeSet;

public class DD14_AVL {

    // Manual AVL Tree Node
    private static class Node {
        int data;
        Node left, right;
        int height;
        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    // Utility to get height
    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    // Right rotate
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        // rotation
        x.right = y;
        y.left = T2;
        // update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    // Left rotate
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        // rotation
        y.left = x;
        x.right = T2;
        // update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    // Get balance factor
    private int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    // Insert value
    public void insert(int key) {
        root = insertRec(root, key);
    }
    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.data) node.left = insertRec(node.left, key);
        else if (key > node.data) node.right = insertRec(node.right, key);
        else return node; // duplicates not allowed

        // update height
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // LL Case
        if (balance > 1 && key < node.left.data)
            return rightRotate(node);
        // RR Case
        if (balance < -1 && key > node.right.data)
            return leftRotate(node);
        // LR Case
        if (balance > 1 && key > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL Case
        if (balance < -1 && key < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
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

    public static void main(String[] args) {
        // --- Manual AVL ---
        DD14_AVL avl = new DD14_AVL();
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int v : values) avl.insert(v);
        System.out.print("Manual AVL inorder: ");
        avl.inorder(); // balanced order

        // Note: Java has no built-in AVL, but TreeSet uses a balanced tree (Red-Black
        TreeSet<Integer> tree = new TreeSet<>();
        for (int v : values) tree.add(v);
        System.out.println("Built-in TreeSet (RB-tree) inorder: " + tree);
    }
}
