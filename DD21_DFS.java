package ds;

import java.util.*;

public class DD21_DFS {

	// Manual Graph + DFS using recursion
	private static class GraphManual {
		private int V;
		private List<Integer>[] adj;

		@SuppressWarnings("unchecked")
		public GraphManual(int V) {
			this.V = V;
			adj = new List[V];
			for (int i = 0; i < V; i++)
				adj[i] = new ArrayList<>();
		}

		public void addEdge(int u, int v) {
			adj[u].add(v);
			adj[v].add(u);
		}

		public void dfs(int start) {
			boolean[] visited = new boolean[V];
			System.out.print("Manual DFS (rec): ");
			dfsUtil(start, visited);
			System.out.println();
		}

		private void dfsUtil(int node, boolean[] visited) {
			visited[node] = true;
			System.out.print(node + " ");
			for (int nbr : adj[node]) {
				if (!visited[nbr])
					dfsUtil(nbr, visited);
			}
		}
	}

	public static void main(String[] args) {
		// --- Manual DFS ---
		GraphManual gm = new GraphManual(6);
		gm.addEdge(0, 1);
		gm.addEdge(0, 2);
		gm.addEdge(1, 3);
		gm.addEdge(1, 4);
		gm.addEdge(2, 5);
		gm.dfs(0); // expected: 0 1 3 4 2 5 (or similar DFS order)

		// --- Built-in DFS using Stack ---
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for (int i = 0; i < 6; i++)
			adj.put(i, new ArrayList<>());
		adj.get(0).addAll(Arrays.asList(1, 2));
		adj.get(1).addAll(Arrays.asList(0, 3, 4));
		adj.get(2).addAll(Arrays.asList(0, 5));
		adj.get(3).add(1);
		adj.get(4).add(1);
		adj.get(5).add(2);

		boolean[] visited = new boolean[6];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		System.out.print("Built-in DFS (stack): ");
		while (!stack.isEmpty()) {
			int u = stack.pop();
			if (!visited[u]) {
				visited[u] = true;
				System.out.print(u + " ");
				List<Integer> neighbors = adj.get(u);
				// reverse for consistent order (optional)
				Collections.reverse(neighbors);
				for (int nbr : neighbors) {
					if (!visited[nbr])
						stack.push(nbr);
				}
			}
		}
		System.out.println();
	}
}
