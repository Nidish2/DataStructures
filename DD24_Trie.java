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

		private TrieNode traverseToNode(String str) {
			TrieNode node = root;
			for (char ch : str.toCharArray()) {
				int idx = ch - 'a';
				if (node.children[idx] == null)
					return null;
				node = node.children[idx];
			}
			return node;
		}

		public boolean search(String word) {
			TrieNode node = traverseToNode(word);
			return node != null && node.isEndOfWord;
		}

		public boolean startsWith(String prefix) {
			TrieNode node = traverseToNode(prefix);
			return node != null;
		}

		public void delete(String word) {
			delete(root, word, 0);
		}

		private boolean delete(TrieNode node, String word, int depth) {
			if (node == null)
				return false;

			if (depth == word.length()) {
				if (!node.isEndOfWord)
					return false;
				node.isEndOfWord = false;
				return isEmpty(node);
			}

			int idx = word.charAt(depth) - 'a';
			if (delete(node.children[idx], word, depth + 1)) {
				node.children[idx] = null;
				return !node.isEndOfWord && isEmpty(node);
			}
			return false;
		}

		private boolean isEmpty(TrieNode node) {
			for (TrieNode child : node.children)
				if (child != null)
					return false;
			return true;
		}

		public List<String> getSuggestions(String prefix) {
			List<String> result = new ArrayList<>();
			TrieNode node = traverseToNode(prefix);
			if (node == null)
				return result; // Avoid NPE
			collectSuggestions(node, new StringBuilder(prefix), result);
			return result;
		}

		private void collectSuggestions(TrieNode node, StringBuilder sb, List<String> result) {
			if (node.isEndOfWord)
				result.add(sb.toString());
			for (char c = 'a'; c <= 'z'; c++) {
				int idx = c - 'a';
				if (node.children[idx] != null) {
					sb.append(c);
					collectSuggestions(node.children[idx], sb, result);
					sb.deleteCharAt(sb.length() - 1);
				}
			}
		}

		public int countWordsWithPrefix(String prefix) {
			TrieNode node = traverseToNode(prefix);
			return node == null ? 0 : countWords(node);
		}

		private int countWords(TrieNode node) {
			if (node == null)
				return 0;
			int count = node.isEndOfWord ? 1 : 0;
			for (TrieNode child : node.children)
				count += countWords(child);
			return count;
		}

		public String longestPrefixMatch(String query) {
			TrieNode node = root;
			StringBuilder sb = new StringBuilder();
			for (char ch : query.toCharArray()) {
				int idx = ch - 'a';
				if (node.children[idx] != null) {
					sb.append(ch);
					node = node.children[idx];
				} else {
					break;
				}
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		// --- Manual Trie ---
		Trie manualTrie = new Trie();
		manualTrie.insert("devil");
		manualTrie.insert("data");
		manualTrie.insert("structure");
		manualTrie.insert("dev");
		manualTrie.insert("develop");
		System.out.println("Manual search 'data': " + manualTrie.search("data"));
		System.out.println("Manual search 'dev': " + manualTrie.search("dev"));
		System.out.println("Manual startsWith 'str': " + manualTrie.startsWith("str"));
		System.out.println("Manual suggestions for 'dev': " + manualTrie.getSuggestions("dev"));
		System.out.println("Manual count with prefix 'de': " + manualTrie.countWordsWithPrefix("de"));
		System.out.println("Longest prefix match for 'developer': " + manualTrie.longestPrefixMatch("developer"));
		manualTrie.delete("data");
		System.out.println("After delete 'data', search 'data': " + manualTrie.search("data"));

		// --- Inbuilt Simulation (via TreeMap) ---
		Map<String, String> pseudoTrie = new TreeMap<>(); // Lexicographically sorted
		pseudoTrie.put("apple", "fruit");
		pseudoTrie.put("app", "abbreviation");
		pseudoTrie.put("application", "software");

		System.out.println("\n🌟 Inbuilt Trie-Like Simulation 🌟\n");
		System.out.println("Full word 'apple' exists: " + pseudoTrie.containsKey("apple"));

		String prefix = "app";
		boolean prefixFound = pseudoTrie.keySet().stream().anyMatch(key -> key.startsWith(prefix));
		System.out.println("Prefix 'app' exists: " + prefixFound);
		System.out.println("Suggestions for 'app': ");
		pseudoTrie.keySet().stream().filter(key -> key.startsWith(prefix)).forEach(System.out::println);
		System.out.println("Longest prefix match for 'applify': " + pseudoTrie.keySet().stream()
				.filter("applify"::startsWith).max(Comparator.comparingInt(String::length)).orElse(""));
	}
}
