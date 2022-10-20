public class MergeIntervals {
    // Ninja :
    public static List<Interval> mergeIntervals(Interval[] intervals) {
        List<Interval> ans = new ArrayList<>();
        final int n = intervals.length;
        if (n == 0)
            return ans;

        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        int i = 1;
        Interval last = intervals[0];
        while (i < n) {
            if (last.finish < intervals[i].start) {
                ans.add(last);
                last = intervals[i];
            } else {
                last.finish = Math.max(last.finish, intervals[i].finish);
            }
            i++;
        }
        ans.add(last); // asdding the last merged interval
        return ans;
    }
    // lc

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n == 0)
            return intervals;

        List<int[]> L = new ArrayList();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] arr = intervals[0];
        int i = 1;
        while (i < intervals.length) {
            if (intervals[i][0] > arr[1]) {
                L.add(arr);
                arr = intervals[i];
            } else {
                arr[1] = Math.max(intervals[i][1], arr[1]);
            }
            i++;
        }
        L.add(arr);

        return L.toArray(new int[L.size()][]);
    }

}
