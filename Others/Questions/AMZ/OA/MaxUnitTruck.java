package Others.Questions.AMZ.OA;
import java.util.Arrays;

public class MaxUnitTruck {
    // Fractional Knapsack : Greedy : LC 1710
    public int maximumUnits(int[][] arr, int cap) {
        // box[i] = numberOfBoxesi, numberOfUnitsPerBoxi => wt, val
        // fractional knapsack
        int n = arr.length;
        Arrays.sort(arr, (a, b) -> {
            return b[1] - a[1]; // price per wait is already given

            // double vpw1 = a[1]*1.0 / a[0];
            // double vpw2 = b[1]*1.0 / b[0];
            // return Double.compare(vpw2,vpw1);
        });
        int ans = 0, i = 0;
        while (cap > 0 && i < n) {
            int currcap = arr[i][0], currval = arr[i][1];
            if (cap - currcap >= 0) {
                ans += (currcap * currval);
                cap -= currcap;
            } else {
                ans += (cap * currval);
                cap = 0;
            }
            i++;
        }

        return ans;
    }
}
