package ds;

import java.util.*;

public class DD22_BFS {

	// Manual Queue using simple array-based implementation
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

	// Manual Graph with BFS using ArrayQueue
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

		public void bfs(int start) {
			boolean[] visited = new boolean[V];
			ArrayQueue q = new ArrayQueue(V);
			visited[start] = true;
			q.enqueue(start);
			System.out.print("Manual BFS: ");
			while (!q.isEmpty()) {
				int u = q.dequeue();
				System.out.print(u + " ");
				for (int nbr : adj[u]) {
					if (!visited[nbr]) {
						visited[nbr] = true;
						q.enqueue(nbr);
					}
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// --- Manual BFS ---
		GraphManual gm = new GraphManual(6);
		gm.addEdge(0, 1);
		gm.addEdge(0, 2);
		gm.addEdge(1, 3);
		gm.addEdge(1, 4);
		gm.addEdge(2, 5);
		gm.bfs(0); // expected: 0 1 2 3 4 5

		// --- Built-in BFS using java.util.Queue ---
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
		Queue<Integer> queue = new LinkedList<>();
		visited[0] = true;
		queue.offer(0);
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
}
