import java.util.Arrays;
import java.util.TreeMap;

// https://leetcode.com/problems/plates-between-candles/discuss/2057259/Java-3-Solutions-or-Prefix-Sum-or-Binary-Search-or-TreeMap
public class StarsAndBars {
    // Using TreeMap : Binary search :
    // time : (Qlog n), space : (N + Q)
    public static int[] starsBWBars(String s, int[] start, int[] end) {
        // to apply bs in candles(bars) index(previous and nextbar)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int idx = 0, n = s.length(), q = start.length;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '|')
                map.put(i, idx++); // ith index contains kth bar
        }

        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            // get indexes of the bars

            Integer idx1 = map.ceilingKey(start[i] - 1); // was 1 bases index
            Integer idx2 = map.floorKey(end[i] - 1);
            if (idx1 == null || idx2 == null || idx1 > idx2)
                continue;

            // get the no of the bar at that index it which bar number is at indexes

            int val1 = map.get(idx1), val2 = map.get(idx2);
            // no of starts = length(idx2-idx1 +1 )(contains both | & *) - no of bars (val2
            // -va1 +1)

            ans[i] = idx2 - idx1 - (val2 - val1);
        }

        return ans;
    }

    // Using prefix sum, left and right array : having bars till now, leftbarindex,
    // rightBardIndex respectively

    public static void main(String[] args) {
        String inp1 = "*|*|";
        int[] s1 = { 1 }, e1 = { 3 }; // 0
        String inp2 = "|**|*|*";
        int[] s2 = { 1, 1 }; // 1-based index
        int[] e2 = { 5, 6 };

        System.out.println(Arrays.toString(platesBetweenCandles(inp1, s1, e1)));
        System.out.println(Arrays.toString(platesBetweenCandles(inp2, s2, e2)));

    }
}
