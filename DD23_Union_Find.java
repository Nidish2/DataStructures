package ds;

import java.util.*;

public class DD23_Union_Find {

	// Manual Union-Find with Path Compression + Union by Rank
	static class UnionFind {
		int[] parent, rank;

		public UnionFind(int n) {
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++)
				parent[i] = i;
		}

		public int find(int x) {
			if (parent[x] != x)
				parent[x] = find(parent[x]); // Path compression
			return parent[x];
		}

		public void union(int x, int y) {
			int px = find(x);
			int py = find(y);
			if (px == py)
				return;

			if (rank[px] < rank[py]) {
				parent[px] = py;
			} else if (rank[px] > rank[py]) {
				parent[py] = px;
			} else {
				parent[py] = px;
				rank[px]++;
			}
		}

		public boolean connected(int x, int y) {
			return find(x) == find(y);
		}
	}

	public static void main(String[] args) {
		// --- Manual Union-Find ---
		UnionFind uf = new UnionFind(10);
		uf.union(1, 2);
		uf.union(2, 3);
		uf.union(4, 5);
		System.out.println("1 connected to 3: " + uf.connected(1, 3)); // true
		System.out.println("1 connected to 5: " + uf.connected(1, 5)); // false
		uf.union(3, 5);
		System.out.println("1 connected to 5 after union: " + uf.connected(1, 5)); // true

		// --- Simulated Inbuilt (Java has no direct DSU class) ---
		// Simulate using Map if working with strings or general objects
		Map<String, String> parent = new HashMap<>();

		String[] items = { "a", "b", "c", "d" };
		for (String item : items)
			parent.put(item, item);

		// Simple find
		java.util.function.Function<String, String> find = new java.util.function.Function<>() {
			public String apply(String x) {
				if (!parent.get(x).equals(x))
					parent.put(x, apply(parent.get(x))); // path compression
				return parent.get(x);
			}
		};

		// Union
		java.util.function.BiConsumer<String, String> union = (x, y) -> {
			String rootX = find.apply(x);
			String rootY = find.apply(y);
			if (!rootX.equals(rootY))
				parent.put(rootX, rootY);
		};

		union.accept("a", "b");
		union.accept("c", "d");
		System.out.println("'a' connected to 'b': " + find.apply("a").equals(find.apply("b")));
		System.out.println("'a' connected to 'd': " + find.apply("a").equals(find.apply("d")));
		union.accept("b", "d");
		System.out.println("'a' connected to 'd' after union: " + find.apply("a").equals(find.apply("d")));
	}
}
