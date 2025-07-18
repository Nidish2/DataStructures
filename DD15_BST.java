package ds;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class DD15_BST {

	// Classical BST Node
	private static class TreeNode {
		int key;
		TreeNode left, right;

		TreeNode(int key) {
			this.key = key;
		}
	}

	private TreeNode root;

	// Insert a key
	public void insert(int key) {
		root = insert(root, key);
	}

	private TreeNode insert(TreeNode root, int key) {
		if (root == null)
			return new TreeNode(key);
		if (key < root.key)
			root.left = insert(root.left, key);
		else if (key > root.key)
			root.right = insert(root.right, key);
		return root;
	}

	// Search a key
	public boolean search(int key) {
		return search(root, key);
	}

	private boolean search(TreeNode node, int key) {
		if (node == null)
			return false;
		if (key == node.key)
			return true;
		return key < node.key ? search(node.left, key) : search(node.right, key);
	}

	// Delete a key using inorder successor
	public void deleteSuccessor(int key) {
		root = deleteSuccessor(root, key);
	}

	private TreeNode deleteSuccessor(TreeNode node, int key) {
		if (node == null)
			return null;
		if (key < node.key)
			node.left = deleteSuccessor(node.left, key);
		else if (key > node.key)
			node.right = deleteSuccessor(node.right, key);
		else {
			// One or no child
			if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;

			// Two children: replace with inorder successor
			node.key = findMinValue(node.right);
			node.right = deleteSuccessor(node.right, node.key);
		}
		return node;
	}

	// Delete a key using inorder predecessor
	public void deletePredecessor(int key) {
		root = deletePredecessor(root, key);
	}

	private TreeNode deletePredecessor(TreeNode node, int key) {
		if (node == null)
			return null;
		if (key < node.key)
			node.left = deleteSuccessor(node.left, key);
		else if (key > node.key)
			node.right = deleteSuccessor(node.right, key);
		else {
			// One or no child
			if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;

			// Two children: delete the inorder predecessor
			node.key = findMaxValue(node.left);
			node.left = deletePredecessor(node.left, node.key);
		}
		return node;
	}

	public int findMinValue() {
		if (root == null)
			throw new IllegalStateException("Tree is empty");
		return findMinValue(root);
	}

	private int findMinValue(TreeNode node) {
		while (node.left != null)
			node = node.left;
		return node.key;
	}

	public int findMaxValue() {
		if (root == null)
			throw new IllegalStateException("Tree is empty");
		return findMaxValue(root);
	}

	private int findMaxValue(TreeNode node) {
		while (node.right != null)
			node = node.right;
		return node.key;
	}

	// In-order traversal
	public void inOrder() {
		inOrder(root);
		System.out.println();
	}

	private void inOrder(TreeNode node) {
		if (node == null)
			return;
		inOrder(node.left);
		System.out.print(node.key + " ");
		inOrder(node.right);
	}

	// Pre-order is also known as DFS (Depth First Search)
	public void preOrder() {
		preOrder(root);
		System.out.println();
	}

	private void preOrder(TreeNode node) {
		if (node == null)
			return;
		System.out.print(node.key + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public void postOrder() {
		postOrder(root);
		System.out.println();
	}

	private void postOrder(TreeNode node) {
		if (node == null)
			return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.key + " ");
	}

	// Level-order traversal (BFS)
	public void bfs() {
		if (root == null)
			return;
		Deque<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			System.out.print(node.key + " ");
			if (node.left != null)
				q.add(node.left);
			if (node.right != null)
				q.add(node.right);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// --- Manual Classical BST ---
		DD15_BST bst = new DD15_BST();
		int[] keys = { 50, 30, 70, 20, 40, 60, 80 };
		for (int key : keys)
			bst.insert(key);

		System.out.print("In-order traversal: ");
		bst.inOrder(); // 20 30 40 50 60 70 80
		System.out.print("Pre-order / DFS traversal: ");
		bst.preOrder(); // 50 30 20 40 70 60 80
		System.out.print("Post-order traversal: ");
		bst.postOrder(); // 20 40 30 60 80 70 50
		System.out.print("Level-order traversal (BFS): ");
		bst.bfs(); // 50 30 70 20 40 60 80

		System.out.println("Search 40? " + bst.search(40)); // true
		System.out.println("Search 90? " + bst.search(90)); // false

		bst.deleteSuccessor(20);
		bst.deletePredecessor(30);
		System.out.println("In-order after deletions: ");
		bst.inOrder(); // 40 60 70 80
		System.out.println("Pre-order (DFS) after deletions: ");
		bst.preOrder(); // 60 40 70 80
		System.out.println("Post-order after deletions: ");
		bst.postOrder(); // 40 80 70 60
		System.out.println("Level-order (BFS) after deletions: ");
		bst.bfs(); // 60 40 70 80
		System.out.println("Min value: " + bst.findMinValue()); // 40
		System.out.println("Max value: " + bst.findMaxValue()); // 80

		// --- Built-in TreeSet (Balanced BST using Red-Black Tree) ---
		TreeSet<Integer> treeSet = new TreeSet<>();
		for (int key : keys)
			treeSet.add(key);

		System.out.println("\nTreeSet: " + treeSet); // Prints the elements in In Order (sorted order)
		System.out.println("Contains 60? " + treeSet.contains(60));
		treeSet.remove(70);
		System.out.println("After removing 70: " + treeSet);

		System.out.print("TreeSet iteration: ");
		Iterator<Integer> it = treeSet.iterator();
		while (it.hasNext())
			System.out.print(it.next() + " ");
		System.out.println();
	}
}
