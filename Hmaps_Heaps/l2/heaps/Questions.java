import java.util.ArrayList;

public class Questions {
    // https://practice.geeksforgeeks.org/problems/kth-smallest-element5635/1#
    // kth smallest : N logk

    // Basicall making the pq of opposite nature tells that we are following the
    // elimination method =>
    /*
     * ( now getting the priority element isn't desired to us, removing/geeting the
     * unwanted and preserving the needed for future use is our statergy )
     */
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > k)
                pq.remove();
        }

        return pq.peek();
    }

    // n + klogn => using heap as we have the complete array
    public static int kthSmallestBetter(int[] arr, int l, int r, int k) {
        // create heap => O(n)
        for (int i = arr.length - 1; i >= 0; i--) {
            downHeapify(arr, i, arr.length, false);
        }

        int lidx = arr.length - 1;
        // remove code
        int K = k;
        while (K >= 0) {
            swap(arr, 0, lidx);
            downHeapify(arr, 0, lidx, isIncreasing);
            lidx--; // decreading the heaping boundary as last elements are sorted
        }
        return arr[n - k];

    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int compareTo(int t, int o, boolean isIncreaing) {
        int res = o - t;
        if (isIncreaing) {
            res *= -1;
        }
        return res;
    }

    // (log n)
    private static void downHeapify(int[] arr, int pi, int limit, boolean isIncreaing) {
        int mxidx = pi;
        int lic = 2 * pi + 1;
        int ric = 2 * pi + 2;

        if (lic < limit && compareTo(arr[lic], arr[mxidx], isIncreaing) > 0)
            mxidx = lic;
        if (ric < limit && compareTo(arr[ric], arr[mxidx], isIncreaing) > 0)
            mxidx = ric;

        if (pi != mxidx) {
            swap(arr, pi, mxidx);
            downHeapify(arr, mxidx, limit, isIncreaing);
        }
    }

    // Leetcode 973:
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            long d1 = points[a][0] * points[a][0] + points[a][1] * points[a][1];
            long d2 = points[b][0] * points[b][0] + points[b][1] * points[b][1];
            return (int) (d2 - d1); // max pq
        });

        int n = points.length;

        for (int i = 0; i < n; i++) {
            pq.add(i);

            if (pq.size() > k)
                pq.remove();
        }

        int[][] ans = new int[k][];
        int i = 0;
        while (pq.size() != 0) {
            int idx = pq.remove();
            ans[i++] = points[idx];
        }

        return ans;
    }

    private int[][] kClosestUsingClass(int[][] points, int k) {

        class Pair implements Comparable<Pair> {
            int x, y;
            long dis;

            Pair() {

            }

            Pair(int x, int y) {
                this.x = x;
                this.y = y;
                this.dis = (x * x + y * y);
            }

            // reverse behavior
            public int compareTo(Pair o) {
                return (int) (o.dis - this.dis);

            }

        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int[] point : points) {
            Pair p = new Pair(point[0], point[1]);
            pq.add(p);

            if (pq.size() > k)
                pq.remove();

        }

        int[][] ans = new int[k][2];
        int i = 0;

        while (i < k) {
            Pair rm = pq.remove();
            ans[i][0] = rm.x;
            ans[i][1] = rm.y;
            i++;
        }

        return ans;
    }

    // Leetcode 692 : Top K Frequent words
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b)) {
                return b.compareTo(a); // remove the lexo greater string first and preserver the lesser for last(ans)
            }

            return map.get(a) - map.get(b); // remove the less freq element first and preserve the maxfreq ele for last

        });

        for (String word : map.keySet()) {
            pq.add(word);
            if (pq.size() > k)
                pq.remove();

        }
        LinkedList<String> list = new LinkedList<>();

        while (k-- > 0) {
            list.addFirst(pq.remove());
        }

        return (List<String>) list;
    }
}
