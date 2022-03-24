package Others.Questions.AMZ.OA;
import java.util.Arrays;
import java.util.HashMap;

public class MinSwaps {

    class Pair {
        int val, idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int minSwaps(int[] arr) {
        int ans = 0, n = arr.length;
        boolean[] vis = new boolean[n]; // to detected cycle
        Pair[] sorted = new Pair[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = new Pair(arr[i], i);
        }
        //
        Arrays.sort(sorted, (a, b) -> {
            return a.val - b.val;
        });

        for (int i = 0; i < n; i++) {
            int sortedidx = sorted[i].idx;

            // already was in right position / already considered
            if (i == sortedidx || vis[i] == true)
                continue;

            int j = i, cycle = 0; // find cycllelength

            while (!vis[j]) {
                vis[j] = true; // mark
                cycle++;
                j = sorted[j].idx; // go to the lemets which is present at it's index

            }
            ans += (cycle - 1); // sortning n-1 ele makes nth ele in correct position
        }

        return ans;
    }

}
