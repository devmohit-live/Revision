public class MinArrowtoBurstBallon_452 {
    // lc 452
    public int findMinArrowShots(int[][] points) {
        int n = points.length, count = n;
        Arrays.sort(points, (a, b) -> {
            // return a[1] -b[1]; // causes overflow in Integer extremes
            // case: [[-2147483646,-2147483645],[2147483646,2147483647]]
            return a[0] < b[0] ? -1 : 1;
        });

        int last[] = points[0];
        for (int i = 1; i < n; i++) {
            if (last[1] >= points[i][0]) {
                // merge
                last[0] = Math.max(last[0], points[i][0]); // start = max
                last[1] = Math.min(last[1], points[i][1]); // end = min
                count--;
            } else
                last = points[i];
        }

        return count;
    }
}
