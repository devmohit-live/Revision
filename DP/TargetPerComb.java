import java.util.Arrays;

public class TargetPerComb {

    static void display2d(int[][] dp) {
        for (int[] d : dp)
            System.out.println(Arrays.toString(d));
    }

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

    static int combinationMem(int[] arr, int n, int tar, int[][] dp) {
        if (tar == 0)
            return dp[n][tar] = 1;
        if (dp[n][tar] != -1)
            return dp[n][tar];
        int count = 0;
        for (int i = n; i > 0; i--) {
            if (tar - arr[i - 1] >= 0)
                count += combinationMem(arr, i, tar - arr[i - 1], dp);
        }

        return dp[n][tar] = count;
    }

    // TODO: not working
    public static int combinationTab2d(int[] arr, int Tar, int LI, int[][] dp) {

        for (int li = 0; li <= LI; li++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar == 0) {
                    dp[li][tar] = 1;
                    continue;
                }

                for (int i = li; i >= 0; i--)
                    if (tar - arr[i] >= 0) {
                        dp[li][tar] += dp[i][tar - arr[i]];
                    }
            }
        }

        return dp[LI][Tar];
    }

    static int combinationTab1d(int[] arr, int Tar, int[] dp) {
        // bascially in 2d tab we were dependent of previous coin's data for our ans
        // we just have to make those changes being reflected in array for each coin we
        // get different targets

        for (int el : arr) {
            dp[0] = 1; // identificatin mark
            for (int tar = 1; tar <= Tar; tar++) {
                if (tar - el >= 0) {
                    dp[tar] += dp[tar - el];
                }
            }
        }
        return dp[Tar];
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int n = arr.length;
        int tar = 10;

        int[][] dp = new int[n + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int[][] dp2 = new int[n + 1][tar + 1];

        int[] dp3 = new int[tar + 1];
        // Arrays.fill(dp3, -1); //don't fill with -1 is performing direct addition

        int ans = combinationMem(arr, n, tar, dp); // 5
        System.out.println("Combination Mem:  " + ans);
        display2d(dp);
        int ans2 = combinationTab2d(arr, tar, n - 1, dp2);
        System.out.println("Combination 2d :  " + ans2);
        display2d(dp2);
        int ans3 = combinationTab1d(arr, tar, dp3);
        System.out.println("Combination 1d :  " + ans3);
        System.out.println(Arrays.toString(dp3));

        // int[] dpper = new int[tar + 1];
        // Arrays.fill(dpper, -1);
        // int per = infipermutationMem(arr, 10, dpper);
        // System.out.println("Permutatiton : " + per);
        // System.out.println(Arrays.toString(dpper));

        // System.out.println("Combination 1 D");
        // int[] dpcomb = new int[tar + 1];
        // int comb = combinationTab1d(arr, tar, dpcomb);
        // System.out.println(comb);
        // System.out.println(Arrays.toString(dpcomb));

    }

    // -------------------- KnapSack -------------------------

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

    // void knapsackPath()

    // Target Sum : All possible coins (Path): Using knapsack

    static boolean targetSumDP(int[] arr, int N, int Tar, boolean[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = tar == 0;
                    continue;
                }

                boolean res = false;

                if (tar - arr[n - 1] >= 0)
                    res = res || dp[n - 1][tar - arr[n - 1]];

                res = res || dp[n - 1][tar];

                dp[n][Tar] = res;

            }
        }
        return dp[N][Tar];
    }

    // backEngTarget(int[] arr, boolean[][]dp, int N , int Tar,String psf){

    // }

    // ========================== Questions =================================

    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    // : exactly no of combinations

    // Leetcode 377 : Combination Sum IV

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // Arrays.fill(dp,-1); //only for mem identity, not for tab as we have to do
        // dp[i]+=something
        // return infiPerm(nums,target,dp);
        return infiPermTab(nums, target, dp);

    }

    private int infiPermMem(int[] arr, int tar, int[] dp) {

        if (tar == 0)
            return dp[tar] = 1;

        if (dp[tar] != -1)
            return dp[tar];

        int count = 0;
        for (int el : arr) {
            if (tar - el >= 0)
                count += infiPermMem(arr, tar - el, dp);
        }
        return dp[tar] = count;

    }

    // Leetcode 322: Coin Change (Mi no. of coins req )
    // can be done with combination or permutation
    public int coinChange(int[] coins, int amount) {
        int ans = MinCoins(coins, amount, new int[amount + 1]);
        return ans == (int) 1e9 ? -1 : ans;
    }

    // it is basically infiPermTab code with minor changes
    private int MinCoins(int[] arr, int Tar, int[] dp) {
        dp[0] = 0; // identification mark : 0 coins are used to make tar 0
        for (int tar = 1; tar <= Tar; tar++) {
            int coins = (int) 1e9;
            for (int el : arr) {
                if (tar - el >= 0)
                    coins = Math.min(coins, 1 + dp[tar - el]);
            }

            dp[tar] = coins;
        }

        return dp[Tar];
    }

    // Subset SUM (GFG):
    // https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/?category[]=Dynamic%20Programming&category[]=Dynamic%20Programming&page=4&query=category[]Dynamic%20Programmingpage4category[]Dynamic%20Programming#
    static Boolean isSubsetSum(int N, int arr[], int tar) {
        int[][] dp = new int[N + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        // int ans = comb(arr,N,0,tar,dp);
        // if(ans ==-1 || ans == 0) return false;
        // return true;
        return targetSum(arr, N, tar, dp) == 1;
    }

    // first way : -1: unexplored, 0: false, 1:true
    static int comb(int[] arr, int n, int idx, int tar, int[][] dp) {
        if (idx == n || tar == 0) {
            if (tar == 0)
                return dp[idx][tar] = 1;
            else
                return dp[idx][tar] = 0;
        }

        if (dp[idx][tar] != -1)
            return dp[idx][tar];
        boolean res = false;
        for (int i = idx; i < n; i++) {
            if (tar - arr[i] >= 0)
                res = res || comb(arr, n, i + 1, tar - arr[i], dp) == 1;
        }
        return dp[idx][tar] = res ? 1 : 0;

    }
    // second way : same as above just check wheter ans > 0(we are storing count as
    // we normally do)

    // third waY: using sunbseq method with boolean res
    public static int targetSum(int[] arr, int n, int tar, int[][] dp) {
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0 ? 1 : 0);
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        boolean res = false;
        if (tar - arr[n - 1] >= 0)
            res = res || targetSum(arr, n - 1, tar - arr[n - 1], dp) == 1;
        res = res || targetSum(arr, n - 1, tar, dp) == 1;

        return dp[n][tar] = res ? 1 : 0;
    }

    // https://practice.geeksforgeeks.org/problems/minimum-cost-to-fill-given-weight-in-a-bag1956/1#

    public int minimumCost(int cost[], int N, int W) {
        // unbounded knapsack with min cost
        int[][] dp2 = new int[N + 1][W + 1];
        for (int[] d : dp2)
            Arrays.fill(d, -1);
        int ans = KnapSack(cost, W, dp2, N);
        if (W == 0)
            return 0;
        return (ans >= (int) 1e8 || ans == 0) ? -1 : ans;

        // target sum coin
        // int[] dp = new int[W + 1];
        // Arrays.fill(dp,-1);
        // targetSum(cost,N,W);

        // Arrays.fill(dp,(int)1e9);
        // return minCost(cost,N,W,dp);

    }

    // First way
    // 1 indexed based also ith index means i kg wt
    int KnapSack(int[] cost, int W, int[][] dp, int n) {

        if (n == 0 || W == 0) {
            if (W == 0)
                return dp[n][W] = 0; // already 0
            else
                return dp[n][W] = (int) 1e9; // not possible

        }

        if (dp[n][W] != -1)
            return dp[n][W];

        int inc = (int) 1e8, exc = (int) 1e8;

        if (cost[n - 1] > -1 && W - n >= 0)
            inc = cost[n - 1] + KnapSack(cost, W - n, dp, n);
        exc = KnapSack(cost, W, dp, n - 1);

        return dp[n][W] = Math.min(inc, exc);
    }

}
