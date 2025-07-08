package ds;

import java.util.NavigableMap;
import java.util.TreeMap;

public class DD17_Segment_T {

	// Classical Segment Tree with Lazy Propagation for Range Sum(or Min, Max, Avg)
	// Queries
	private static class SegmentTree {
		private int[] tree, lazy;
		private int size;

		public SegmentTree(int[] inputArray) {
			this.size = inputArray.length;
			tree = new int[4 * size];
			lazy = new int[4 * size];
			build(inputArray, 1, 0, size - 1);
		}

		private void build(int[] input, int node, int start, int end) {
			if (start == end) {
				tree[node] = input[start];
			} else {
				int mid = start + (end - start) / 2;
				build(input, 2 * node, start, mid);
				build(input, 2 * node + 1, mid + 1, end);
				tree[node] = tree[2 * node] + tree[2 * node + 1];
			}
		}

		// Lazy propagation to push updates down the tree
		private void propagate(int node, int start, int end) {
			if (lazy[node] != 0) {
				tree[node] += (end - start + 1) * lazy[node]; // apply pending update
				if (start != end) { // if not a leaf node
					lazy[2 * node] += lazy[node];
					lazy[2 * node + 1] += lazy[node];
				}
				lazy[node] = 0;
			}
		}

		// Range update with delta using lazy propagation
		public void rangeUpdate(int l, int r, int delta) {
			rangeUpdate(1, 0, size - 1, l, r, delta);
		}

		private void rangeUpdate(int node, int start, int end, int l, int r, int delta) {
			propagate(node, start, end); // push any previous lazy updates

			if (r < start || end < l)
				return; // no overlap

			if (l <= start && end <= r) {
				lazy[node] += delta;
				propagate(node, start, end);
				return;
			}

			int mid = (start + end) / 2;
			rangeUpdate(2 * node, start, mid, l, r, delta);
			rangeUpdate(2 * node + 1, mid + 1, end, l, r, delta);
			tree[node] = tree[2 * node] + tree[2 * node + 1];
		}

		// Single index point update
		public void pointUpdate(int index, int newValue) {
			pointUpdate(1, 0, size - 1, index, newValue);
		}

		private void pointUpdate(int node, int start, int end, int idx, int value) {
			propagate(node, start, end);
			if (start == end) {
				tree[node] = value;
			} else {
				int mid = (start + end) / 2;
				if (idx <= mid)
					pointUpdate(2 * node, start, mid, idx, value);
				else
					pointUpdate(2 * node + 1, mid + 1, end, idx, value);
				tree[node] = tree[2 * node] + tree[2 * node + 1];
			}
		}

		// Range sum query
		public int getRangeSum(int left, int right) {
			return query(1, 0, size - 1, left, right);
		}

		private int query(int node, int start, int end, int l, int r) {
			propagate(node, start, end);

			if (r < start || end < l)
				return 0; // no overlap

			if (l <= start && end <= r)
				return tree[node]; // complete overlap

			int mid = start + (end - start) / 2;
			int leftSum = query(2 * node, start, mid, l, r);
			int rightSum = query(2 * node + 1, mid + 1, end, l, r);
			return leftSum + rightSum;
		}
	}

	public static void main(String[] args) {
		// ------------ Manual Segment Tree With Lazy Propagation ------------
		System.out.println("Manual Implementation of Segment Tree with Lazy Propagation for Range Sum Queries");
		int[] arr = { 1, 3, 5, 7, 9, 11 };
		SegmentTree sT = new SegmentTree(arr);

		System.out.println("Range Sum [1, 4]: " + sT.getRangeSum(1, 4)); // 24

		sT.pointUpdate(3, 10); // Update index 3 with value 10
		System.out.println("After point update, Range Sum [1, 4]: " + sT.getRangeSum(1, 4)); // 27

		sT.rangeUpdate(2, 5, 2); // Add +2 to range [2, 5]
		System.out.println("After range update (+2), Range Sum [1, 4]: " + sT.getRangeSum(1, 4)); // 35

		// ------------ Built-in TreeMap Simulation for Comparison ------------
		System.out.println("\nUsing Inbuilt TreeMap for Range Sum Queries");
		NavigableMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(i, arr[i]);
		}

		int builtinSum = map.subMap(1, true, 4, true).values().stream().mapToInt(Integer::intValue).sum();
		System.out.println("TreeMap Range Sum [1, 4]: " + builtinSum);

		map.put(3, 10); // simulate update
		int builtinSumUpdated = map.subMap(1, true, 4, true).values().stream().mapToInt(Integer::intValue).sum();
		System.out.println("After update, TreeMap Range Sum [1, 4]: " + builtinSumUpdated);
	}
}
// This code implements a Segment Tree with Lazy Propagation for range sum queries and point updates.