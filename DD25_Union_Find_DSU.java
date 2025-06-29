package ds;

import java.util.*;
import java.util.function.*;

public class DD25_Union_Find_DSU {

	// Manual Union-Find with both Union by Rank & Size + Path Compression + Count
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

		public int find(int x) {
			if (parent[x] != x)
				parent[x] = find(parent[x]); // path compression
			return parent[x];
		}

		// Union by Rank
		public void unionByRank(int x, int y) {
			int rX = find(x), rY = find(y);
			if (rX == rY)
				return;

			if (rank[rX] < rank[rY]) {
				parent[rX] = rY;
				size[rY] += size[rX];
			} else if (rank[rX] > rank[rY]) {
				parent[rY] = rX;
				size[rX] += size[rY];
			} else {
				parent[rY] = rX;
				size[rX] += size[rY];
				rank[rX]++;
			}
			count--;
		}

		// Union by Size
		public void unionBySize(int x, int y) {
			int rX = find(x), rY = find(y);
			if (rX == rY)
				return;

			if (size[rX] < size[rY]) {
				parent[rX] = rY;
				size[rY] += size[rX];
			} else {
				parent[rY] = rX;
				size[rX] += size[rY];
			}
			count--;
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
		System.out.println("🛠️ Manual Union-Find (DSU) with Rank & Size:\n");

		UnionFind uf = new UnionFind(11); // ➕ Increased to 11 to handle index 10

		// Union By Rank
		System.out.println("🔗 Union By Rank:");
		uf.unionByRank(1, 2);
		uf.unionByRank(2, 3);
		uf.unionByRank(4, 5);

		System.out.println("✅ 1 connected to 3: " + uf.connected(1, 3));
		System.out.println("❌ 1 connected to 5: " + uf.connected(1, 5));

		uf.unionByRank(3, 5);
		System.out.println("✅ 1 connected to 5 after unionByRank: " + uf.connected(1, 5));
		System.out.println("📏 Size of set containing 1: " + uf.getSize(1));
		System.out.println("🔢 Disjoint set count: " + uf.countSets());

		// Union By Size
		System.out.println("\n📦 Union By Size:");
		uf.unionBySize(6, 7);
		uf.unionBySize(7, 8);
		uf.unionBySize(9, 10);

		System.out.println("✅ 6 connected to 8: " + uf.connected(6, 8));
		System.out.println("❌ 6 connected to 10: " + uf.connected(6, 10));

		uf.unionBySize(8, 10);

		System.out.println("✅ 6 connected to 10 after unionBySize: " + uf.connected(6, 10));
		System.out.println("📏 Size of set containing 6: " + uf.getSize(6));
		System.out.println("🔢 Disjoint set count after unions: " + uf.countSets());

		// -------------------------------------------------------------------
		System.out.println("⚙️ Inbuilt-style DSU (Union by Rank):\n");

		Map<String, String> parentRank = new HashMap<>();
		Map<String, Integer> rank = new HashMap<>();

		String[] rankItems = { "x", "y", "z", "w" };
		for (String item : rankItems) {
			parentRank.put(item, item);
			rank.put(item, 0);
		}

		// Find with path compression (Rank)
		Function<String, String> findRank = new Function<>() {
			public String apply(String x) {
				if (!parentRank.get(x).equals(x))
					parentRank.put(x, apply(parentRank.get(x)));
				return parentRank.get(x);
			}
		};

		// Union by rank
		BiConsumer<String, String> unionByRank = (x, y) -> {
			String rootX = findRank.apply(x);
			String rootY = findRank.apply(y);
			if (!rootX.equals(rootY)) {
				if (rank.get(rootX) < rank.get(rootY)) {
					parentRank.put(rootX, rootY);
				} else if (rank.get(rootX) > rank.get(rootY)) {
					parentRank.put(rootY, rootX);
				} else {
					parentRank.put(rootY, rootX);
					rank.put(rootX, rank.get(rootX) + 1);
				}
			}
		};

		// Test Rank DSU
		unionByRank.accept("x", "y");
		unionByRank.accept("z", "w");

		System.out.println("'x' connected to 'y': " + findRank.apply("x").equals(findRank.apply("y")));
		System.out.println("'x' connected to 'z': " + findRank.apply("x").equals(findRank.apply("z")));

		unionByRank.accept("y", "w");
		System.out.println("'x' connected to 'z' after union: " + findRank.apply("x").equals(findRank.apply("z")));

		System.out.println("\n📌 Final Parent Mapping (Rank):");
		for (String item : rankItems)
			System.out.println(item + " -> " + findRank.apply(item));

		System.out.println("\n🎯 Final Ranks:");
		for (String item : rankItems)
			System.out.println(item + " = " + rank.get(findRank.apply(item)));

		// -------------------------------------------------------------------
		System.out.println("\n⚙️ Inbuilt-style DSU (Union by Size):\n");

		Map<String, String> parentSize = new HashMap<>();
		Map<String, Integer> size = new HashMap<>();

		String[] sizeItems = { "a", "b", "c", "d", "e" };
		for (String item : sizeItems) {
			parentSize.put(item, item);
			size.put(item, 1);
		}

		// Find with path compression (Size)
		Function<String, String> findSize = new Function<>() {
			public String apply(String x) {
				if (!parentSize.get(x).equals(x))
					parentSize.put(x, apply(parentSize.get(x)));
				return parentSize.get(x);
			}
		};

		// Union by size
		BiConsumer<String, String> unionBySize = (x, y) -> {
			String rootX = findSize.apply(x);
			String rootY = findSize.apply(y);
			if (!rootX.equals(rootY)) {
				if (size.get(rootX) < size.get(rootY)) {
					parentSize.put(rootX, rootY);
					size.put(rootY, size.get(rootX) + size.get(rootY));
				} else {
					parentSize.put(rootY, rootX);
					size.put(rootX, size.get(rootX) + size.get(rootY));
				}
			}
		};

		// Test Size DSU
		unionBySize.accept("a", "b");
		unionBySize.accept("c", "d");

		System.out.println("'a' connected to 'b': " + findSize.apply("a").equals(findSize.apply("b")));
		System.out.println("'a' connected to 'd': " + findSize.apply("a").equals(findSize.apply("d")));

		unionBySize.accept("b", "d");
		System.out.println("'a' connected to 'd' after union: " + findSize.apply("a").equals(findSize.apply("d")));
		System.out.println("Size of set containing 'a': " + size.get(findSize.apply("a")));

		System.out.println("\n📌 Final Parent Mapping (Size):");
		for (String item : sizeItems)
			System.out.println(item + " -> " + findSize.apply(item));

		System.out.println("\n📏 Final Sizes:");
		for (String item : sizeItems)
			System.out.println(item + " = " + size.get(findSize.apply(item)));
	}
}
