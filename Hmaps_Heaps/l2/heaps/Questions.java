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

    // Leetcode 778: Swim in water
    public int swimInWater(int[][] grid) {
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        // max of min : bfs on min of pq : last elemet that leads to end => max

        int n = grid.length, m = grid[0].length, maxtime = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;
            return grid[i1][j1] - grid[i2][j2];
        }); // min time

        // maxtime = maxvalue of mins

        boolean[][] vis = new boolean[n][m];

        if (n <= 1)
            return 0;

        pq.add(0);
        vis[0][0] = true;

        while (pq.size() != 0) {
            int rm = pq.remove();

            int i = rm / m;
            int j = rm % m;

            vis[i][j] = true;
            int minHeightSoFar = grid[i][j];
            maxtime = Math.max(maxtime, minHeightSoFar);

            if (i == n - 1 && j == m - 1)
                break;

            for (int[] d : dir) {
                int r = i + d[0];
                int c = j + d[1];
                if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                    vis[r][c] = true;
                    pq.add(r * m + c);
                }
            }

        }

        return maxtime;

    }

    // Leetcode 1642 :Furthest Building You Can Reach

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // of size l gaurantees me that these no of elements can be
                                                           // acheived by ladder(size of pq == no of ladders)

        // elimination statergy => min nikalte jao or bricks se cover krte jao ,max walo
        // ko preserve kro or usko ladder se aceive krenege

        int n = heights.length;
        for (int i = 1; i < n; i++) {
            int curr = heights[i], prev = heights[i - 1], diff = curr - prev;

            if (diff > 0)
                pq.add(diff);
            // we are sure that upto pq.size() elemets we acn definity cover with ladder

            // if we got more then that we will try to cover smallest differences using
            // bricks(that's why min pq)
            if (pq.size() > ladders)
                bricks -= pq.remove();

            if (bricks < 0)
                return i - 1;

        }

        // reached at end

        return n - 1;

    }
}
