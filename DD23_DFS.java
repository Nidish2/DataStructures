package ds;

import java.util.*;

public class DD23_DFS {

	// ---------------- Manual DFS (Recursive) ----------------
	private static class GraphManual {
		private int V;
		private List<Integer>[] adj;

		@SuppressWarnings("unchecked")
		public GraphManual(int V) {
			this.V = V;
			adj = new ArrayList[V];
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
			System.out.println("\n");
		}

		private void dfsUtil(int start, boolean[] visited) {
			visited[start] = true;
			System.out.print(start + " ");
			for (int nbr : adj[start]) {
				if (!visited[nbr]) {
					dfsUtil(nbr, visited);
				}
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Graph (Manual):\n");
			for (int i = 0; i < V; i++) {
				sb.append(i).append(" -> ").append(adj[i]).append("\n");
			}
			return sb.toString();
		}
	}

	// ---------------- Built-in DFS (Iterative using Stack) ----------------
	private static class GraphBuiltInIter {
		private Map<Integer, List<Integer>> adj;

		public GraphBuiltInIter(int V) {
			adj = new HashMap<>();
			for (int i = 0; i < V; i++) {
				adj.put(i, new ArrayList<>());
			}
		}

		public void addEdge(int u, int v) {
			adj.get(u).add(v);
			adj.get(v).add(u); // Undirected
		}

		public void dfs(int start) {
			boolean[] visited = new boolean[adj.size()];
			Deque<Integer> stack = new ArrayDeque<>();
			stack.push(start);

			System.out.print("Built-in DFS (stack): ");
			while (!stack.isEmpty()) {
				int u = stack.pop();
				if (!visited[u]) {
					visited[u] = true;
					System.out.print(u + " ");

					List<Integer> neighbors = new ArrayList<>(adj.get(u));
					Collections.reverse(neighbors); // Reverse for consistent DFS order
					for (int nbr : neighbors) {
						if (!visited[nbr]) {
							stack.push(nbr);
						}
					}
				}
			}
			System.out.println("\n");
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Graph (Built-in Iterative DFS):\n");
			for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
				sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
			}
			return sb.toString();
		}
	}

	// ---------------- Built-in DFS (Recursive) ----------------
	private static class GraphBuiltInRec {
		private Map<Integer, List<Integer>> adj;

		public GraphBuiltInRec(int V) {
			adj = new HashMap<>();
			for (int i = 0; i < V; i++) {
				adj.put(i, new ArrayList<>());
			}
		}

		public void addEdge(int u, int v) {
			adj.get(u).add(v);
			adj.get(v).add(u); // Undirected
		}

		public void dfs(int start) {
			Set<Integer> visited = new HashSet<>();
			System.out.print("Built-in DFS (rec): ");
			dfsUtil(start, visited);
			System.out.println();
		}

		private void dfsUtil(int node, Set<Integer> visited) {
			visited.add(node);
			System.out.print(node + " ");
			for (int nbr : adj.get(node)) {
				if (!visited.contains(nbr)) {
					dfsUtil(nbr, visited);
				}
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Graph (Built-in Recursive DFS):\n");
			for (int key : adj.keySet()) {
				sb.append(key).append(" -> ").append(adj.get(key)).append("\n");
			}
			return sb.toString();
		}
	}

	// ---------------- Main Method ----------------
	public static void main(String[] args) {
		// === Manual DFS ===
		GraphManual manualGraph = new GraphManual(6);
		manualGraph.addEdge(0, 1);
		manualGraph.addEdge(0, 2);
		manualGraph.addEdge(1, 3);
		manualGraph.addEdge(1, 4);
		manualGraph.addEdge(2, 5);
		System.out.println(manualGraph);
		manualGraph.dfs(0); // Possible DFS: 0 1 3 4 2 5

		// === Built-in DFS Iterative ===
		GraphBuiltInIter builtInGraph = new GraphBuiltInIter(6);
		builtInGraph.addEdge(0, 1);
		builtInGraph.addEdge(0, 2);
		builtInGraph.addEdge(1, 3);
		builtInGraph.addEdge(1, 4);
		builtInGraph.addEdge(2, 5);
		System.out.println(builtInGraph);
		builtInGraph.dfs(0); // Possible DFS: 0 1 3 4 2 5

		// === Built-in DFS Recursive ===
		GraphBuiltInRec graph = new GraphBuiltInRec(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 5);

		System.out.println(graph);
		graph.dfs(0); // Expected DFS: 0 1 3 4 2 5 or similar (depends on neighbors' order)
	}
}
