package Questions.AMZ;

import java.util.PriorityQueue;

public class KClosestPoint {
    public int[][] kClosest(int[][] points, int k) {
        // smallest => max pq for elimination

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int x1 = a[0], y1 = a[1];
            int x2 = b[0], y2 = b[1];

            long d1 = x1 * x1 + y1 * y1;
            long d2 = x2 * x2 + y2 * y2;

            return (int) (d2 - d1);

        });

        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > k)
                pq.remove();
        }

        if (pq.size() < k) // not possible to find k closests point
            return new int[0][]; // -1

        int[][] ans = new int[k][];
        int idx = k;
        while (idx-- > 0) {
            ans[idx] = pq.remove();
        }

        return ans;

    }
}
