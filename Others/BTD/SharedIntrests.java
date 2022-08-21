import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedIntrests {
    public static void main(String[] args) {
        int n = 4, e = 5;
        int[] from = { 1, 1, 2, 2, 2 }, to = { 2, 2, 3, 3, 4 }, wt = { 2, 3, 1, 3, 4 };
        System.out.println(maxShared(n, e, from, to, wt));

    }

    private static long maxShared(int n, int e, int[] from, int[] to, int[] weight) {
        long ans = 0;
        Map<Integer, List<Long>> wtToPair = new HashMap<>();
        // Map<Long, List<> >
        int maxLen = -1, key = -1;
        for (int i = 0; i < e; i++) {
            int src = from[i], des = to[i], wt = weight[i];
            long pairNo = src * n + des;
            wtToPair.putIfAbsent(wt, new ArrayList<Long>());
            List<Long> corrsPairs = wtToPair.get(wt);
            corrsPairs.add(pairNo);

            if (corrsPairs.size() > maxLen) {
                maxLen = corrsPairs.size();
                key = wt;
            }

        }

        for (long pairNo : wtToPair.get(key)) {
            long src = pairNo / n;
            long des = pairNo % n;
            ans = Math.max(ans, src * des);
        }

        return (int) ans;
    }

}
