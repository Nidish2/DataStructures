package ds;

import java.util.*;

public class DD25_Union_Find_DSU {

	// Manual Union-Find with Path Compression + Union by Rank + Size + Count
	static class UnionFind {
		int[] parent, rank, size;
		int count; // No. of disjoint sets

		public UnionFind(int n) {
			parent = new int[n];
			rank = new int[n];
			size = new int[n];
			count = n;
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		// Find with path compression
		public int find(int x) {
			if (parent[x] != x)
				parent[x] = find(parent[x]);
			return parent[x];
		}

		// Union by rank
		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);

			if (rootX == rootY)
				return;

			if (rank[rootX] < rank[rootY]) {
				parent[rootX] = rootY;
				size[rootY] += size[rootX];
			} else if (rank[rootX] > rank[rootY]) {
				parent[rootY] = rootX;
				size[rootX] += size[rootY];
			} else {
				parent[rootY] = rootX;
				size[rootX] += size[rootY];
				rank[rootX]++;
			}
			count--; // One less disjoint set now
		}

		public boolean connected(int x, int y) {
			return find(x) == find(y);
		}

		public int getSize(int x) {
			return size[find(x)];
		}

		public int countSets() {
			return count;
		}
	}

	public static void main(String[] args) {
		// --- Manual DSU Usage ---
		System.out.println("🛠️ Manual Union-Find (DSU) Example:\n");

		UnionFind uf = new UnionFind(10);
		uf.union(1, 2);
		uf.union(2, 3);
		uf.union(4, 5);

		System.out.println("1 connected to 3: " + uf.connected(1, 3)); // true
		System.out.println("1 connected to 5: " + uf.connected(1, 5)); // false

		uf.union(3, 5);
		System.out.println("1 connected to 5 after union: " + uf.connected(1, 5)); // true
		System.out.println("Size of set containing 1: " + uf.getSize(1)); // 5 elements merged
		System.out.println("Disjoint set count: " + uf.countSets());

		// --- Simulated Inbuilt Style (Generic, String Example) ---
		System.out.println("\n⚙️ Inbuilt-style Union-Find with Map<String, String>:\n");

		Map<String, String> parent = new HashMap<>();
		Map<String, Integer> size = new HashMap<>();

		String[] items = { "a", "b", "c", "d", "e" };
		for (String item : items) {
			parent.put(item, item);
			size.put(item, 1);
		}

		// Find with path compression
		java.util.function.Function<String, String> find = new java.util.function.Function<>() {
			public String apply(String x) {
				if (!parent.get(x).equals(x))
					parent.put(x, apply(parent.get(x)));
				return parent.get(x);
			}
		};

		// Union with size map
		java.util.function.BiConsumer<String, String> union = (x, y) -> {
			String rootX = find.apply(x);
			String rootY = find.apply(y);
			if (!rootX.equals(rootY)) {
				if (size.get(rootX) < size.get(rootY)) {
					parent.put(rootX, rootY);
					size.put(rootY, size.get(rootX) + size.get(rootY));
				} else {
					parent.put(rootY, rootX);
					size.put(rootX, size.get(rootX) + size.get(rootY));
				}
			}
		};

		// Simulate operations
		union.accept("a", "b");
		union.accept("c", "d");

		System.out.println("'a' connected to 'b': " + find.apply("a").equals(find.apply("b")));
		System.out.println("'a' connected to 'd': " + find.apply("a").equals(find.apply("d")));

		union.accept("b", "d");

		System.out.println("'a' connected to 'd' after union: " + find.apply("a").equals(find.apply("d")));
		System.out.println("Size of set containing 'a': " + size.get(find.apply("a")));
	}
}
