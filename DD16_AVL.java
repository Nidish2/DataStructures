package ds;

import java.util.TreeSet;

public class DD16_AVL {

	// Classical AVL Node
	private static class TreeNode {
		int key, height;
		TreeNode left, right;

		TreeNode(int key) {
			this.key = key;
			this.height = 1;
		}
	}

	private TreeNode root;

	// Height of node
	private int height(TreeNode node) {
		return node == null ? 0 : node.height;
	}

	// Balance Factor
	private int getBalance(TreeNode node) {
		return node == null ? 0 : height(node.left) - height(node.right);
	}

	// Right Rotate
	private TreeNode rightRotate(TreeNode y) {
		TreeNode x = y.left;
		TreeNode T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		return x;
	}

	// Left Rotate
	private TreeNode leftRotate(TreeNode x) {
		TreeNode y = x.right;
		TreeNode T2 = y.left;

		// Perform rotation
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		return y;
	}

	// Balance the tree
	private TreeNode doBalance(TreeNode node) {
		if (node == null)
			return null;

		// Update height
		node.height = 1 + Math.max(height(node.left), height(node.right));

		int balance = getBalance(node);

		// 4 Cases
		if (balance > 1 && getBalance(node.left) >= 0) // LL
			return rightRotate(node);

		if (balance > 1 && getBalance(node.left) < 0) { // LR
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balance < -1 && getBalance(node.right) <= 0) // RR
			return leftRotate(node);

		if (balance < -1 && getBalance(node.right) > 0) { // RL
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node; // already balanced
	}

	// Insert into AVL Tree
	public void insert(int key) {
		root = insert(root, key);
	}

	// Recursive insert function
	private TreeNode insert(TreeNode node, int key) {
		if (node == null)
			return new TreeNode(key);

		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);
		else
			return node; // Duplicate not allowed

		// Update height
		node.height = 1 + Math.max(height(node.left), height(node.right));

		// Balance the node
		return doBalance(node);
	}

	// In-order Traversal (sorted order)
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

	// Search in AVL Tree
	public boolean search(int key) {
		return search(root, key);
	}

	private boolean search(TreeNode node, int key) {
		if (node == null)
			return false;
		if (key < node.key)
			return search(node.left, key);
		else if (key > node.key)
			return search(node.right, key);
		else
			return true; // Key found
	}

	// Delete from AVL Tree
	public void deleteSuccessor(int key) {
		root = deleteSuccessor(root, key);
	}

	private TreeNode deleteSuccessor(TreeNode node, int key) {
		if (node == null)
			return node;

		if (key < node.key)
			node.left = deleteSuccessor(node.left, key);
		else if (key > node.key)
			node.right = deleteSuccessor(node.right, key);
		else {
			// Node with one child or no child
			if (node.left == null || node.right == null) {
				TreeNode temp = (node.left != null) ? node.left : node.right;
				if (temp == null) {
					return null; // No child case
				} else {
					return temp; // One child case
				}
			} else {
				// Node with two children: Get the inorder successor
				TreeNode temp = minValueNode(node.right);
				node.key = temp.key; // Copy the inorder successor's content to this node
				node.right = deleteSuccessor(node.right, temp.key); // Delete the inorder successor
			}
		}

		// Update height and balance the tree
		node.height = 1 + Math.max(height(node.left), height(node.right));
		return doBalance(node);
	}

	// Delete a node from AVL Tree
	public void deletePredeccessor(int key) {
		root = deletePredeccessor(root, key);
	}

	private TreeNode deletePredeccessor(TreeNode node, int key) {
		if (node == null)
			return node;

		if (key < node.key)
			node.left = deletePredeccessor(node.left, key);
		else if (key > node.key)
			node.right = deletePredeccessor(node.right, key);
		else {
			// Node with one child or no child
			if (node.left == null || node.right == null) {
				TreeNode temp = (node.left != null) ? node.left : node.right;
				if (temp == null) {
					return null; // No child case
				} else {
					return temp; // One child case
				}
			} else {
				// Node with two children: Get the inorder predecessor
				TreeNode temp = maxValueNode(node.left);
				node.key = temp.key; // Copy the inorder predecessor's content to this node
				node.left = deletePredeccessor(node.left, temp.key); // Delete the inorder predecessor
			}
		}

		// Update height and balance the tree
		node.height = 1 + Math.max(height(node.left), height(node.right));
		return doBalance(node);
	}

	// Helper function to find the node with maximum value in a subtree
	private TreeNode maxValueNode(TreeNode node) {
		TreeNode current = node;
		while (current.right != null) {
			current = current.right;
		}
		return current;
	}

	// Helper function to find the node with minimum value in a subtree
	private TreeNode minValueNode(TreeNode node) {
		TreeNode current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	public int getMin() {
		if (root == null) {
			throw new IllegalStateException("Tree is empty");
		}
		return minValueNode(root).key;
	}

	public int getMax() {
		if (root == null) {
			throw new IllegalStateException("Tree is empty");
		}
		return maxValueNode(root).key;
	}

	// Main method to demonstrate AVL Tree operations
	public static void main(String[] args) {
		// ----------- Manual AVL ----------
		DD16_AVL avlTree = new DD16_AVL();
		int[] keys = { 10, 20, 30, 40, 50, 25 };
		for (int key : keys)
			avlTree.insert(key);

		System.out.print("Manual AVL Tree Inorder (Sorted): ");
		avlTree.inOrder(); // 10 20 25 30 40 50
		System.out.print("Manual AVL Tree Preorder: ");
		avlTree.preOrder(); // 30 20 10 25 40 50
		System.out.print("Manual AVL Tree Postorder: ");
		avlTree.postOrder(); // 10 25 20 50 40 30
		System.out.println("Manual Search for 25: " + avlTree.search(25)); // true
		System.out.println("Manual Search for 100: " + avlTree.search(100)); // false
		System.out.println("Min: " + avlTree.getMin()); // 10
		System.out.println("Max: " + avlTree.getMax()); // 50
		avlTree.deleteSuccessor(30); // Deletes 30, successor is 40
		System.out.print("After deleting successor of 30: ");
		avlTree.inOrder(); // 10 20 25 40 50

		avlTree.deletePredeccessor(30); // Deletes 30, predecessor is 20
		avlTree.inOrder(); // 10 25 40 50

		// Note: Java has no built-in AVL, but TreeSet uses a balanced tree (Red-Black
		// Tree).
		TreeSet<Integer> set = new TreeSet<>();
		for (int key : keys)
			set.add(key);

		System.out.println("Inbuilt TreeSet (RB Tree) Inorder: " + set);
		System.out.println("Contains 25? " + set.contains(25));

		set.remove(30); // Removes 30, successor is 40}
	}
}
