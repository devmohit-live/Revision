public class IntervalListIntersections {
    // LC 998
    // found all the intersections points between 2 lists of intervals
    public int[][] intervalIntersection(int[][] first, int[][] second) {
        int i = 0, j = 0, n = first.length, m = second.length, smax, emin;
        if (n == 0 || m == 0)
            return new int[0][0];
        List<int[]> res = new ArrayList<>();
        while (i < n && j < m) {
            smax = Math.max(first[i][0], second[j][0]);
            emin = Math.min(first[i][1], second[j][1]);

            // condition for overlap
            if (emin >= smax) {
                res.add(new int[] { smax, emin });
            }

            // check which pointer to move
            // the shorter interval is submerged(the interval whose ending was near(lower)
            // is ended now and the leftover emax of another interval can overlap with
            // another interval)
            if (emin == first[i][1])
                i++;
            else
                j++;

        }
        return res.toArray(new int[res.size()][2]);
    }
}
