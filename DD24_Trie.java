package ds;

import java.util.*;

public class DD24_Trie {

	// Manual Trie Implementation
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean isEndOfWord = false;
	}

	static class Trie {
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			TrieNode node = root;
			for (char ch : word.toCharArray()) {
				int idx = ch - 'a';
				if (node.children[idx] == null)
					node.children[idx] = new TrieNode();
				node = node.children[idx];
			}
			node.isEndOfWord = true;
		}

		public boolean search(String word) {
			TrieNode node = root;
			for (char ch : word.toCharArray()) {
				int idx = ch - 'a';
				if (node.children[idx] == null)
					return false;
				node = node.children[idx];
			}
			return node.isEndOfWord;
		}

		public boolean startsWith(String prefix) {
			TrieNode node = root;
			for (char ch : prefix.toCharArray()) {
				int idx = ch - 'a';
				if (node.children[idx] == null)
					return false;
				node = node.children[idx];
			}
			return true;
		}
	}

	public static void main(String[] args) {
		// --- Manual Trie ---
		Trie manualTrie = new Trie();
		manualTrie.insert("devil");
		manualTrie.insert("data");
		manualTrie.insert("structure");
		System.out.println("Manual search 'data': " + manualTrie.search("data"));
		System.out.println("Manual search 'dev': " + manualTrie.search("dev"));
		System.out.println("Manual startsWith 'str': " + manualTrie.startsWith("str"));

		// --- Inbuilt Simulation (via TreeMap) ---
		Map<String, String> pseudoTrie = new TreeMap<>(); // Lexicographically sorted
		pseudoTrie.put("apple", "fruit");
		pseudoTrie.put("app", "abbreviation");
		pseudoTrie.put("application", "software");

		System.out.println("\nInbuilt Trie-Like Simulation:");
		System.out.println("Full word 'apple' exists: " + pseudoTrie.containsKey("apple"));

		String prefix = "app";
		boolean prefixFound = pseudoTrie.keySet().stream().anyMatch(key -> key.startsWith(prefix));
		System.out.println("Prefix 'app' exists: " + prefixFound);
	}
}
