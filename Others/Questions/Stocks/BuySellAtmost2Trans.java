public class BuySellAtmost2Trans {
    final int CAP = 2;

    public int maxProfit(int[] prices) {
        // similar to but and sell 2 just we have a constraint of 2 transaction
        // previously we had infinte transaction
        int n = prices.length;
        // int[][][] dp = new int[n+1][2][3];
        // for(int[][] matrix : dp) for(int[] row : matrix) Arrays.fill(row, -1);

        // return recursion(prices, 0, 1, CAP,dp);
        // return tabulation(prices);
        return spaceOptimzation(prices);
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
}
