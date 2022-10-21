
import java.util.*;

public class StrangeArray {
    public static void main(String[] args) {
        solve(3, 1, new int[] { 3, 1, 3 });
        solve(3, 1, new int[] { 1, 2, 2 });
    }

    private static void solve(int N, int K, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (K != 0) {
                int key = arr[i] % (2 * K);
                map.put(key, map.getOrDefault(key, 0) + 1);
            } else
                map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        int max = 0;
        for (int val : map.values())
            max = Math.max(max, val);
        System.out.println(max);
    }
}   
