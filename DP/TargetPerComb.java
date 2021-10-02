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

    // -------------------- KnapSack -------------------------
    // Target sum : dp[li][tar] += dp[i][tar - arr[i]];
    // Knapsack : instead of + we just take max

    static int knapSack(int W, int wt[], int val[], int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = knapsack01(wt, val, W, n, dp);

        int tab = knapsack01Tab(wt, val, W, wt.length);
        System.out.println("Mem : " + ans + " Tab: " + tab);
        return ans;
    }

    // Subseq
    static int knapsack01(int[] wt, int[] val, int W, int n, int[][] dp) {

        if (n == 0 || W == 0) {
            return dp[n][W] = 0;
        }

        if (dp[n][W] != -1)
            return dp[n][W];

        int included = 0, excluded = 0;
        if (W - wt[n - 1] >= 0)
            included = val[n - 1] + knapsack01(wt, val, W - wt[n - 1], n - 1, dp);
        excluded = knapsack01(wt, val, W, n - 1, dp);

        return dp[n][W] = Math.max(included, excluded);

    }

    static int knapsack01Tab(int[] wt, int[] val, int W, int N) {
        int[][] dp = new int[N + 1][W + 1];
        for (int n = 0; n <= N; n++) {
            for (int w = 0; w <= W; w++) {
                if (n == 0 || w == 0) {
                    dp[n][w] = 0;
                    continue;
                }

                int inc = 0, excl = 0;
                if (w - wt[n - 1] >= 0) {
                    // look at previos index
                    inc = val[n - 1] + dp[n - 1][w - wt[n - 1]];
                }

                excl = dp[n - 1][w];
                dp[n][w] = Math.max(inc, excl);
            }
        }
        display2d(dp);
        backEngKnapsack(wt, val, dp, N, W, "");
        String ans = backEngKnapsack(wt, val, dp, N, W);
        System.out.println(ans);

        return dp[N][W];
    }

    // Unbounded Knapsack :Subseq
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

    // either use perm or comb result will be same

    // using subseq :
    static int knapSackRepAllowedTab(int[] wt, int[] val, int W, int N) {
        int size = wt.length;
        int[][] dp = new int[size + 1][W + 1];

        for (int cap = 0; cap <= W; cap++) {
            for (int item = 0; item <= N; item++) {
                if (cap == 0 || item == 0) {
                    dp[item][cap] = 0;
                    continue;
                }

                int inc = 0, excl = 0;
                if (cap - wt[item - 1] >= 0) {
                    inc = val[item - 1] + dp[item][cap - wt[item - 1]];
                }
                excl = dp[item - 1][cap];
                dp[item][cap] = Math.max(inc, excl);
            }
        }
        display2d(dp);
        return dp[N][W];
    }

    // Using target sum concepts > ncr mrthod
    // prem , comb doesn't matter, putting

    // PERM code here
    static int unboundedKnapSackTab2_1(int W, int[] wt, int[] val) {
        int n = val.length;
        int[] dp = new int[W + 1]; // w=tar

        // for every target we have choice for every coin
        // tar
        for (int w = 0; w <= W; w++) {
            // all coins are available
            for (int i = 0; i < n; i++) {
                if (w - wt[i] >= 0) {
                    dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
                }
            }
        }
        return dp[W];
    }

    // COMB code here
    static int unboundedKnapSackTab2_2(int W, int[] wt, int[] val) {
        int n = val.length;
        int[] dp = new int[W + 1]; // w=tar
        // using single coin at a time try to acheive target

        // effect of each coin: wt //
        for (int i = 0; i < n; i++) {
            // acheinving each target using a single coin
            for (int w = 0; w <= W; w++) {
                if (w - wt[i] >= 0) {
                    dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
                }
            }
        }
        return dp[W];
    }

    // ========= Print Path in KnapSack and Target Sum==========

    // same as mem just extra rule: Only make call to the profitable area(condition
    // before a call)

    // Target Sum : All possible coins (Path): Using suseq
    // rep allowed
    static int targetSumMem(int[] arr, int tar, int n, int[][] dp) {

        if (n == 0 || tar == 0) {
            if (tar == 0) {
                return dp[n][tar] = 1;
            }
            return dp[n][tar] = 0;
        }

        int count = 0;
        if (tar - arr[n - 1] >= 0)
            count += targetSumMem(arr, tar - arr[n - 1], n, dp);
        count += targetSumMem(arr, tar, n - 1, dp);
        return dp[n][tar] = count;
    }

    static int targetSumTab(int[] arr, int Tar, int N) {
        int size = arr.length;
        int[][] dp = new int[size + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    if (tar == 0)
                        dp[n][tar] = 1;
                    else
                        dp[n][tar] = 0;
                    continue;
                }

                int count = 0;

                if (tar - arr[n - 1] >= 0)
                    count += dp[n][tar - arr[n - 1]];

                count += dp[n - 1][tar];

                dp[n][tar] = count;

            }
        }

        display2d(dp);
        pathTargetSum(dp, arr, N, Tar, "");
        return dp[N][Tar];
    }

    static int pathTargetSum(int[][] dp, int[] arr, int n, int tar, String psf) {
        if (n == 0 || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        // make a call only if it's proftable (check for the location's profitablitiy
        // before going there)
        int count = 0;
        if (tar - arr[n - 1] >= 0 && dp[n][tar - arr[n - 1]] > 0) {
            count += pathTargetSum(dp, arr, n, tar - arr[n - 1], psf + arr[n - 1] + " ");
        }
        if (dp[n - 1][tar] > 0)
            count += pathTargetSum(dp, arr, n - 1, tar, psf);

        return count;

    }

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

    // TODO: KnapSack Printing

    static void backEngKnapsack(int[] wt, int[] val, int[][] dp, int n, int W, String psf) {
        if (n == 0 || W == 0) {
            if (W == 0) {
                System.out.println(psf);
            }
            return;
        }

        // inc = > dp[n][W-wt[n-1]], excl => dp[n-1][W] , ans => max of these two
        if (dp[n][W - wt[n - 1]] > dp[n - 1][W])
            backEngKnapsack(wt, val, dp, n, W - wt[n - 1], psf + "( " + wt[n - 1] + ", " + val[n - 1] + ")");
        else
            backEngKnapsack(wt, val, dp, n - 1, W, psf);
    }

    static String backEngKnapsack(int[] wt, int[] val, int[][] dp, int n, int W) {
        if (n == 0 || W == 0) {
            return "";
        }

        // inc = > dp[n][W-wt[n-1]], excl => dp[n-1][W] , ans => max of these two
        if (dp[n][W - wt[n - 1]] > dp[n][W])
            return "( " + wt[n - 1] + ", " + val[n - 1] + ")" + backEngKnapsack(wt, val, dp, n - 1, W - wt[n - 1]);
        else
            return "" + backEngKnapsack(wt, val, dp, n - 1, W);

    }

    // ========================== Questions =================================

    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    // : exactly no of infi combination
    static void linearEquationOfNVar(int[] coff, int rhs) {
        int[] dp = new int[rhs + 1];
        int ans = combinationTab1d(coff, rhs, dp);
        System.out.println(ans);
    }

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

    // Leetcode : 494 (involves origin shifting, and manipulating the target)
    public int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0)
            return 0;

        int sum = 0, n = nums.length;
        for (int el : nums)
            sum += el;

        // is target acheivable or not

        if (target < -sum || target > sum)
            return 0;

        // do origin shifting for negative target
        // since target is within the range of sum of max -ve it can be is : -sum

        int[][] dp = new int[n + 1][2 * sum + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        // sending the sum,and shifted target
        return findTargetSumWays(nums, n, sum, target + sum, dp);

    }

    // way1 : new target -> sum (actually tar-->0)
    // dp => target tending to 0(sum in shited space)
    private int findTargetSumWays0(int[] arr, int target, int sum, int n, int[][] dp) {

        if (n == 0) {
            // unshifted => tar == 0
            return dp[n][target] = ((target == sum) ? 1 : 0);
        }

        if (dp[n][target] != -1)
            return dp[n][target];

        int count = 0;
        // unshifted => tar - arr[n-1] >= -sum
        if (target - arr[n - 1] >= 0)
            count += findTargetSumWays(arr, target - arr[n - 1], sum, n - 1, dp);

        // unshifted => tar + arr[n-1] <= sum
        if (target + arr[n - 1] <= 2 * sum)
            count += findTargetSumWays(arr, target - (-arr[n - 1]), sum, n - 1, dp);

        return dp[n][target] = count;

    }

    // dp => sum -> target+sum => 0-> target in shifted space
    private int findTargetSumWays1(int[] arr, int n, int sum, int tar, int[][] dp) {
        if (n == 0) {
            return dp[n][tar] = (tar == sum) ? 1 : 0;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        int count = 0;
        // Apply for checks as we can't ensure the boundaries why tar goes far from sum
        // range
        // as base case cheks for only n==0 first then only tar ==sum
        if (tar - arr[n - 1] >= -sum)
            count += findTargetSumWays1(arr, n - 1, sum, tar - arr[n - 1], dp);

        if (tar - (-arr[n - 1]) <= 2 * sum)
            count += findTargetSumWays1(arr, n - 1, sum, tar - (-arr[n - 1]), dp);

        return dp[n][tar] = count;
    }

    // way2 : Reaching to Target from 0(which is now being shifted to sum)
    // orig : 0->tar (after shifting)==> sum->tar+sum // new targ =
    // tar+sum(shifting) as using this we can ensure the boundaries of sum
    // dp[n][sum] as sum is changing not target
    private int findTargetSumWays(int[] arr, int n, int sum, int tar, int[][] dp) {
        if (n == 0) {
            return dp[n][sum] = (tar == sum) ? 1 : 0;
        }

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int count = 0;
        // add once
        count += findTargetSumWays(arr, n - 1, sum + arr[n - 1], tar, dp);
        // subtract one
        count += findTargetSumWays(arr, n - 1, sum - arr[n - 1], tar, dp);

        return dp[n][sum] = count;
    }

    // Leetcode 698 : Partition to K Equal Sum Subsets

    // Doesn't involves dp
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length, max = 0;
        long sum = 0;
        for (int el : nums)
            sum += el;

        int tar = (int) (sum / k);

        if (sum % k != 0)
            return false;

        return canPartitionKSubsets(nums, k, 0, 0, tar, n, new boolean[n]);

    }

    private boolean canPartitionKSubsets(int[] arr, int k, int idx, int sumSF, int tar, int n, boolean[] vis) {
        if (k == 0)
            return true;
        if (sumSF > tar)
            return false;
        if (sumSF == tar || n == 0) {
            return sumSF == tar ? canPartitionKSubsets(arr, k - 1, 0, 0, tar, n, vis) : false;
        }

        boolean res = false;
        for (int i = idx; i < arr.length; i++) {
            if (vis[i])
                continue;
            vis[i] = true;
            res = res || canPartitionKSubsets(arr, k, i + 1, sumSF + arr[i], tar, n, vis);
            vis[i] = false;
        }

        return res;

    }

    // Leetcode 688 : Knight Probability in Chessboard
    // 3d dp
    public double knightProbability(int n, int k, int row, int column) {
        // all moves done and we are inside the valid r,c => in the board
        if (k == 0)
            return 1.0;

        // 2 things are changing r,c,k
        double dp[][][] = new double[k + 1][n + 1][n + 1];
        int dx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int dy[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

        return knightProbability(n, k, row, column, dp, dx, dy);

    }

    double knightProbability(int n, int k, int sr, int sc, double[][][] dp, int[] dx, int[] dy) {
        if (k == 0)
            return dp[k][sr][sc] = 1.0;

        if (dp[k][sr][sc] != 0)
            return dp[k][sr][sc];

        double count = 0;
        for (int d = 0; d < dx.length; d++) {
            int r = sr + dx[d];
            int c = sc + dy[d];

            if (r >= 0 && c >= 0 && r < n && c < n)
                count += knightProbability(n, k - 1, r, c, dp, dx, dy);

        }

        return dp[k][sr][sc] = count / 8.0; // all 8 directions probability

    }

    // Leetcode 198 : House Robber
    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n == 1 ? nums[0] : 0;
        }

        int[] dp1 = new int[n];
        // int[] dp2 = new int[n];
        Arrays.fill(dp1, -1);
        // Arrays.fill(dp2,-1);
        int startfrom0 = rob1(nums, dp1, 0);
        int startfrom1 = rob1(nums, dp1, 1);
        return Math.max(startfrom0, startfrom1);
    }

    private int rob1(int[] arr, int[] dp, int si) {
        int n = arr.length;

        if (si >= n) {
            return 0;
        }

        if (dp[si] != -1)
            return dp[si];

        int robbingCurrentHouse = arr[si] + rob1(arr, dp, si + 2);
        int notRobbingCurrentHouse = rob1(arr, dp, si + 1);

        return dp[si] = Math.max(robbingCurrentHouse, notRobbingCurrentHouse);

    }

    // Leetcode 213: House Robber II
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return n == 1 ? nums[0] : 0;
        }

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, -1);
        Arrays.fill(dp2, -1);
        int startfrom0 = rob(nums, dp1, 0, n - 2);
        int startfrom1 = rob(nums, dp2, 1, n - 1);
        return Math.max(startfrom0, startfrom1);

    }

    private int rob(int[] arr, int[] dp, int si, int ei) {

        // si==ei => can't make a statement here as to return arr[i] in this case we
        // have to be sure that si-1th house isn't robbed
        if (si > ei) { // si+2 case can lead to > and we are also not checking at si==ei => si++;
            return 0;
        }

        if (dp[si] != -1)
            return dp[si];

        int robbingCurrentHouse = arr[si] + rob(arr, dp, si + 2, ei);
        int notRobbingCurrentHouse = rob(arr, dp, si + 1, ei);

        return dp[si] = Math.max(robbingCurrentHouse, notRobbingCurrentHouse);

    }

    public static void main(String[] args) {
        // int[] arr = { 2, 3, 5, 7 };
        // int n = arr.length;
        // int tar = 10;

        // int[][] dp = new int[n + 1][tar + 1];
        // for (int[] d : dp)
        // Arrays.fill(d, -1);
        // int ans = targetSumTab(arr, tar, n);
        // System.out.println("Ans: " + ans);
        // int[][] dp2 = new int[n + 1][tar + 1];

        // int[] dp3 = new int[tar + 1];
        // Arrays.fill(dp3, -1); //don't fill with -1 is performing direct addition

        // int ans = combinationMem(arr, n, tar, dp); // 5
        // System.out.println("Combination Mem: " + ans);
        // display2d(dp);
        // int ans2 = combinationTab2d(arr, tar, n - 1, dp2);
        // System.out.println("Combination 2d : " + ans2);
        // display2d(dp2);
        // int ans3 = combinationTab1d(arr, tar, dp3);
        // System.out.println("Combination 1d : " + ans3);
        // System.out.println(Arrays.toString(dp3));

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

        // linearEquationOfNVar(new int[] { 1, 2 }, 5); // 3
        // linearEquationOfNVar(new int[] { 2, 2, 3 }, 4); // 3

        int W = 8;
        int val[] = { 10, 40, 50, 70 }; // op:110
        int wt[] = { 1, 3, 4, 5 };
        int n = val.length;
        System.out.println("Ans is : " + knapsack01Tab(wt, val, W, n));

        // knapSack(W, wt, val, n);

        // int dp[][] = new int[n + 1][W + 1];
        // for (int[] d : dp)
        // Arrays.fill(d, -1);
        // int mem = knapSackRepAllowed(wt, val, dp, W, n);
        // int dp2[][] = new int[n + 1][W + 1];
        // int tab = knapSackRepAllowedTab(wt, val, W, n);

        // int ncrtab1Perm = unboundedKnapSackTab2_1(W, wt, val);
        // int ncrtab2Comb = unboundedKnapSackTab2_1(W, wt, val);
        // System.out.println("Mem :" + mem + " " + tab);
        // System.out.println("NCR :" + "Perm " + ncrtab1Perm + " Comb :" +
        // ncrtab2Comb);
    }

}
