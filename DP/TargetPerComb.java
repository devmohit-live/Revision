import java.util.Arrays;

public class TargetPerComb {

    // Infinite Supply

    public static int infipermutationMem(int[] arr, int tar, int[] dp) {
        // all possibilites will be considered so no use of idx

        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1)
            return dp[tar];
        int count = 0;
        for (int el : arr) {
            if (tar - el >= 0)
                count += infipermutationMem(arr, tar - el, dp);
        }
        return dp[tar] = count;
    }

    private int infiPermTab(int[] arr, int Tar, int[] dp) {
        dp[0] = 1; // identification mark

        for (int tar = 1; tar <= Tar; tar++) {
            // check all possi
            int count = 0;
            for (int el : arr) {
                if (tar - el >= 0)
                    count += dp[tar - el];
            }

            dp[tar] = count;
        }

        return dp[Tar];
    }

    static int combinationMem(int[] arr, int n, int idx, int tar, int[][] dp) {
        if (idx == n || tar == 0) {
            if (tar == 0)
                return dp[idx][tar] = 1;
            else
                return dp[idx][tar] = 0;
        }

        if (dp[idx][tar] != -1)
            return dp[idx][tar];

        int count = 0;
        for (int i = idx; i < n; i++) {
            if (tar - arr[i] >= 0)
                count += combinationMem(arr, n, i + 1, tar - arr[i], dp);
        }
        return dp[idx][tar] = count;

    }

    static int combinationTab1d(int[] arr, int Tar, int[] dp) {
        // bascially in 2d tab we were dependent of previous coin's data for our ans
        // we just have to make those changes being reflected in array for each coin we
        // get different targets

        for (int el : arr) {
            dp[0] = 1; // identificatin mark
            int count = 0;
            for (int tar = 1; tar <= Tar; tar++) {
                if (tar - el >= 0) {
                    dp[tar] += dp[tar - el];
                }
            }
        }
        return dp[Tar];
    }


    static int knapSack(int W, int wt[], int val[], int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return knapsack01(wt, val, W, n, dp);
    }

    static int knapsack01(int[] wt, int[] val, int W, int n, int[][] dp) {

        if (n == 0 || W == 0)
            return dp[n][W] = 0;

        if (dp[n][W] != -1)
            return dp[n][W];

        int included = 0, excluded = 0;
        if (W - wt[n - 1] >= 0)
            included = val[n - 1] + knapsack01(wt, val, W - wt[n - 1], n - 1, dp);
        excluded = knapsack01(wt, val, W, n - 1, dp);

        return dp[n][W] = Math.max(included, excluded);

    }

    // Unbounded Knapsack
    static int knapSackRepAllowed(int[] wt, int[] val, int[][] dp, int W, int n) {
        if (n == 0 || W == 0) {
            // either no elements left or no space left
            return dp[n][W] = 0;
        }
        if (dp[n][W] != -1)
            return dp[n][W];

        int a = 0, b = 0;

        if (W - wt[n - 1] >= 0) {
            // repetition is allowed
            a = val[n - 1] + knapSackRepAllowed(wt, val, dp, W - wt[n - 1], n);
        }
        b = knapSackRepAllowed(wt, val, dp, W, n - 1);

        return dp[n][W] = Math.max(a, b);
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int n = arr.length;
        int tar = 10;

        int[][] dp = new int[n + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int ans = combinationMem(arr, n - 1, 0, 10, dp); // 5
        System.out.println("Combination:  " + ans);
        System.out.println(Arrays.toString(dp));

        int[] dpper = new int[tar + 1];
        Arrays.fill(dpper, -1);
        int per = infipermutationMem(arr, 10, dpper);
        System.out.println("Permutatiton : " + per);
        System.out.println(Arrays.toString(dpper));

        System.out.println("Combination  1 D");
        int[] dpcomb = new int[tar + 1];
        int comb = combinationTab1d(arr, tar, dpcomb);
        System.out.println(comb);
        System.out.println(Arrays.toString(dpcomb));

    }
}
