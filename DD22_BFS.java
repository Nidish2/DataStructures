package ds;

import java.util.*;

public class DD22_BFS {

	// ---------------- Manual Circular Queue ----------------
	private static class ArrayQueue {
		private int[] arr;
		private int head = 0, tail = 0, size = 0;

		public ArrayQueue(int capacity) {
			arr = new int[capacity];
		}

		public void enqueue(int x) {
			if (size == arr.length)
				throw new IllegalStateException("Queue overflow");
			arr[tail] = x;
			tail = (tail + 1) % arr.length;
			size++;
		}

		public int dequeue() {
			if (size == 0)
				throw new IllegalStateException("Queue underflow");
			int val = arr[head];
			head = (head + 1) % arr.length;
			size--;
			return val;
		}

		public boolean isEmpty() {
			return size == 0;
		}
	}

	// ---------------- Manual Graph & BFS ----------------
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

		public void bfs(int start) {
			boolean[] visited = new boolean[V];
			ArrayQueue queue = new ArrayQueue(V); // doubled capacity for safety
			visited[start] = true;
			queue.enqueue(start);

			System.out.print("Manual BFS: ");
			while (!queue.isEmpty()) {
				int u = queue.dequeue();
				System.out.print(u + " ");
				for (int nbr : adj[u]) {
					if (!visited[nbr]) {
						visited[nbr] = true;
						queue.enqueue(nbr);
					}
				}
			}
			System.out.println("\n");
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

	// ---------------- Built-in Graph & BFS ----------------
	private static class GraphBuiltIn {
		private Map<Integer, List<Integer>> adj;

		public GraphBuiltIn(int V) {
			adj = new HashMap<>();
			for (int i = 0; i < V; i++)
				adj.put(i, new ArrayList<>());
		}

		public void addEdge(int u, int v) {
			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		public void bfs(int start) {
			boolean[] visited = new boolean[adj.size()];
			Deque<Integer> queue = new LinkedList<>();
			visited[start] = true;
			queue.offer(start);

			System.out.print("Built-in BFS: ");
			while (!queue.isEmpty()) {
				int u = queue.poll();
				System.out.print(u + " ");
				for (int nbr : adj.get(u)) {
					if (!visited[nbr]) {
						visited[nbr] = true;
						queue.offer(nbr);
					}
				}
			}
			System.out.println();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("Graph (Built-in):\n");
			for (var entry : adj.entrySet()) {
				sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
			}
			return sb.toString();
		}
	}

	// ---------------- Main Method ----------------
	public static void main(String[] args) {
		// === Manual BFS ===
		GraphManual manualGraph = new GraphManual(6);
		manualGraph.addEdge(0, 1);
		manualGraph.addEdge(0, 2);
		manualGraph.addEdge(1, 3);
		manualGraph.addEdge(1, 4);
		manualGraph.addEdge(2, 5);
		System.out.println(manualGraph);
		manualGraph.bfs(0); // Expected: 0 1 2 3 4 5

		// === Built-in BFS ===
		GraphBuiltIn builtInGraph = new GraphBuiltIn(6);
		builtInGraph.addEdge(0, 1);
		builtInGraph.addEdge(0, 2);
		builtInGraph.addEdge(1, 3);
		builtInGraph.addEdge(1, 4);
		builtInGraph.addEdge(2, 5);
		System.out.println(builtInGraph);
		builtInGraph.bfs(0); // Expected: 0 1 2 3 4 5
	}
}
