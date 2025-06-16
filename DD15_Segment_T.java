package ds;

import java.util.NavigableMap;
import java.util.TreeMap;

public class DD15_Segment_T {

    // Manual Segment Tree for Range Sum
    private static class SegmentTree {
        private int[] tree;
        private int n;

        public SegmentTree(int[] arr) {
            this.n = arr.length;
            // allocate size 4*n
            tree = new int[4 * n];
            build(arr, 1, 0, n - 1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, 2 * node, start, mid);
                build(arr, 2 * node + 1, mid + 1, end);
                tree[node] = tree[2 * node] + tree[2 * node + 1];
            }
        }

        // update index i to value val
        public void update(int idx, int val) {
            updateRec(1, 0, n - 1, idx, val);
        }
        private void updateRec(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid) updateRec(2 * node, start, mid, idx, val);
                else updateRec(2 * node + 1, mid + 1, end, idx, val);
                tree[node] = tree[2 * node] + tree[2 * node + 1];
            }
        }

        // query sum range [l, r]
        public int query(int l, int r) {
            return queryRec(1, 0, n - 1, l, r);
        }
        private int queryRec(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;           // no overlap
            if (l <= start && end <= r) return tree[node]; // total overlap
            int mid = (start + end) / 2;
            int p1 = queryRec(2 * node, start, mid, l, r);
            int p2 = queryRec(2 * node + 1, mid + 1, end, l, r);
            return p1 + p2;
        }
    }

    public static void main(String[] args) {
        // --- Manual Segment Tree ---
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(arr);
        System.out.println("Sum of range [1, 4]: " + st.query(1, 4));  // 3+5+7+9 = 24
        st.update(3, 10); // arr[3] = 10
        System.out.println("After update, sum [1, 4]: " + st.query(1, 4)); // 3+5+10+9 = 27

        // --- Built-in via TreeMap (inefficient) ---
        // Use NavigableMap to store index->value and sum over subMap
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) map.put(i, arr[i]);
        // query sum [1,4]
        int sum = map.subMap(1, true, 4, true)
                     .values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("TreeMap range sum [1, 4]: " + sum);
        // update index 3
        map.put(3, 10);
        int sum2 = map.subMap(1, true, 4, true)
                      .values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("After update, TreeMap sum [1, 4]: " + sum2);
    }
}
