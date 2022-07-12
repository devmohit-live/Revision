public class BuySellAtmost2Trans {
    final int CAP;

    BuySellAtmost2Trans(int capapcity) {
        this.CAP = capapcity;
    }

    BuySellAtmost2Trans() {
        this(2);
    }

    public int maxProfit(int[] prices) {
        // similar to but and sell 2 just we have a constraint of 2 transaction
        // previously we had infinte transaction
        int n = prices.length;
        // int[][][] dp = new int[n+1][2][3];
        // for(int[][] matrix : dp) for(int[] row : matrix) Arrays.fill(row, -1);

        // return recursion(prices, 0, 1, CAP,dp);
        // return tabulation(prices);
        // return spaceOptimzation(prices);
        return approach2(prices);
    }

    private int approach2(int[] prices) {
        int n = prices.length;
        // int[][] dp = new int[n][2*CAP];
        // for(int[] row : dp) Arrays.fill(row, -1);

        // return recursionApproach2(prices,0,0,dp);
        // return tabulationApproach2(prices);
        return spaceOptimizationApproach2(prices);
    }

    // memoization is giving tle
    // left to right
    private int recursion(int[] prices, int idx, int buying, int cap, int[][][] dp) {
        int n = prices.length;
        if (idx == n || cap == 0)
            return dp[idx][buying][cap] = 0;

        if (dp[idx][buying][cap] != -1)
            return dp[idx][buying][cap];

        int profit = 0;
        if (buying == 1) {
            // can buy today or other day
            profit = Math.max(-prices[idx] + recursion(prices, idx + 1, 0, cap, dp),
                    0 + recursion(prices, idx + 1, 1, cap, dp));
        } else {
            // can sell today or other day : on selling 1 transcation is completed
            profit = Math.max(prices[idx] + recursion(prices, idx + 1, 1, cap - 1, dp),
                    0 + recursion(prices, idx + 1, 0, cap, dp));
        }

        return profit;
    }

    // right to left
    private int tabulation(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];

        // by defaul it is 0 only
        // for(int buy = 0;buy<=1;buy+++) for(int cap =0;cap<=2;cap++) dp[n][buy][cap] =
        // 0;
        // for(int buy = 0;buy<=1;buy+++) for(int idx =0;idx<=n-1;idx++) dp[n][buy][0] =
        // 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buying = 0; buying <= 1; buying++) {
                for (int cap = 1; cap <= CAP; cap++) {
                    if (buying == 1) {
                        // can buy today or other day
                        dp[idx][buying][cap] = Math.max(-prices[idx] + dp[idx + 1][0][cap], 0 + dp[idx + 1][1][cap]);
                    } else {
                        // can sell today or other day : on selling 1 transcation is completed
                        dp[idx][buying][cap] = Math.max(prices[idx] + dp[idx + 1][1][cap - 1], 0 + dp[idx + 1][0][cap]);
                    }
                }
            }
        }

        return dp[0][1][2]; // memo called from
    }

    private int spaceOptimzation(int[] prices) {
        int n = prices.length;
        // buyinh and capapcity are the dimesions

        int[][] next = new int[2][3], curr = new int[2][3];

        // by defaul it is 0 only for next and curr

        // for(int buy = 0;buy<=1;buy+++) [buy][0] = 0, curr[buy][0] = 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buying = 0; buying <= 1; buying++) {
                for (int cap = 1; cap <= CAP; cap++) {
                    if (buying == 1) {
                        // can buy today or other day
                        curr[buying][cap] = Math.max(-prices[idx] + next[0][cap], 0 + next[1][cap]);
                    } else {
                        // can sell today or other day : on selling 1 transcation is completed
                        curr[buying][cap] = Math.max(prices[idx] + next[1][cap - 1], 0 + next[0][cap]);
                    }
                }
            }

            next = curr;
        }

        return next[1][2]; // memo called from
    }

    // Different techbique of using 2d dp instead of 1d dp
    // take BS as 1 unit ans as seperate tranction ie atmost two trascation means bs
    // , bs ie we have total of 4 tranasction(2b and 2 sell)

    // buy B will always occur first(even indexes)(0,2,4,6, ...k/2)
    // selling will ocurr at odd indexes
    // so instead of having dp[idx][buying][cap] we can have dp[index][cap*2]

    // works : left to right

    private int recursionApproach2(int[] prices, int idx, int transNo, int[][] dp) {
        int n = prices.length;
        if (idx == n || transNo == 2 * CAP)
            return 0;

        if (dp[idx][transNo] != -1)
            return dp[idx][transNo];
        int profit = 0;
        if ((transNo & 1) == 0) {
            // even = B => can buy today or other day
            profit = Math.max(-prices[idx] + recursionApproach2(prices, idx + 1, transNo + 1, dp),
                    0 + recursionApproach2(prices, idx + 1, transNo, dp));

        } else {
            // sell

            profit = Math.max(prices[idx] + recursionApproach2(prices, idx + 1, transNo + 1, dp),
                    0 + recursionApproach2(prices, idx + 1, transNo, dp));
        }

        return dp[idx][transNo] = profit;
    }

    private int tabulationApproach2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2 * CAP + 1];

        // base // by default is zero
        // for(int idx = 0; idx<=n;idx++) dp[idx][2*CAP] = 0;
        // for(int cap = 0;cap<=2*CAp;cap++) dp[n][cap] = 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int transNo = 2 * CAP - 1; transNo >= 0; transNo--) {
                int profit = 0;
                if ((transNo & 1) == 0) {
                    // even = B => can buy today or other day
                    dp[idx][transNo] = Math.max(-prices[idx] + dp[idx + 1][transNo + 1], 0 + dp[idx + 1][transNo]);

                } else {
                    // sell
                    dp[idx][transNo] = Math.max(prices[idx] + dp[idx + 1][transNo + 1], 0 + dp[idx + 1][transNo]);
                }
            }
        }

        return dp[0][0];
    }

    private int spaceOptimizationApproach2(int[] prices) {
        int n = prices.length;
        // dp[idx+1] : next , dp[idx] : curr
        int[] next = new int[2 * CAP + 1], curr = new int[2 * CAP + 1];

        // base cases
        // prev : // for(int idx = 0; idx<=n;idx++) dp[idx][2*CAP] = 0; ->
        // next[2*CAP] = curr[2*CAP] = 0 => curr state (base)

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int transNo = 2 * CAP - 1; transNo >= 0; transNo--) {
                int profit = 0;
                if ((transNo & 1) == 0) {
                    // even = B => can buy today or other day
                    curr[transNo] = Math.max(-prices[idx] + next[transNo + 1], 0 + next[transNo]);

                } else {
                    // sell
                    curr[transNo] = Math.max(prices[idx] + next[transNo + 1], 0 + next[transNo]);
                }
            }

            // after every iteration pass the prev state
            next = curr;
        }

        return next[0];
    }

}
