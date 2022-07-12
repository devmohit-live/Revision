public class BuyAndSellFee {
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            int n = prices.length;
            if (n < 2)
                return 0;

            // int[][] dp = new int[n][2];
            // for(int[] row : dp) Arrays.fill(row,-1);

            // return recursion(prices, 0, 1, fee,dp);
            // return tabulation(prices,fee);
            // return spaceOptimization(prices,fee);
            return spaceOptimization2(prices, fee);
        }

        // l to r
        private int recursion(int[] prices, int idx, int buying, int fee, int[][] dp) {

            if (idx == prices.length)
                return 0;

            if (dp[idx][buying] != -1)
                return dp[idx][buying];

            int profit = 0;
            if (buying == 1) {
                profit = Math.max(-prices[idx] + recursion(prices, idx + 1, 0, fee, dp),
                        0 + recursion(prices, idx + 1, 1, fee, dp));
            } else {
                profit = Math.max(prices[idx] - fee + recursion(prices, idx + 1, 1, fee, dp),
                        0 + recursion(prices, idx + 1, 0, fee, dp));
            }

            return dp[idx][buying] = profit;
        }

        // r to l
        private int tabulation(int[] prices, int fee) {
            int n = prices.length;
            int[][] dp = new int[n + 1][2];
            // base dp[n][0] = dp[n][1] = 0; //defualt value is also 0
            for (int idx = n - 1; idx >= 0; idx--) {
                // 1 : buying
                dp[idx][1] = Math.max(-prices[idx] + dp[idx + 1][0], 0 + dp[idx + 1][1]);
                dp[idx][0] = Math.max(prices[idx] - fee + dp[idx + 1][1], 0 + dp[idx + 1][0]);
            }

            return dp[0][1];
        }

        private int spaceOptimization(int[] prices, int fee) {
            int n = prices.length;
            int[] next = new int[2], curr = new int[2];

            for (int idx = n - 1; idx >= 0; idx--) {
                // 1 : buying
                curr[1] = Math.max(-prices[idx] + next[0], 0 + next[1]);
                curr[0] = Math.max(prices[idx] - fee + next[1], 0 + next[0]);

                next = curr;
            }

            return next[1];
        }

        private int spaceOptimization2(int[] prices, int fee) {
            int n = prices.length;
            int nextBuying = 0, nextNotBuying = 0, currBuying = 0, currNotBuying = 0;

            for (int idx = n - 1; idx >= 0; idx--) {
                // 1 : buying
                currBuying = Math.max(-prices[idx] + nextNotBuying, 0 + nextBuying);
                currNotBuying = Math.max(prices[idx] - fee + nextBuying, 0 + nextNotBuying);

                // next = curr;
                nextBuying = currBuying;
                nextNotBuying = currNotBuying;
            }

            return currBuying;
        }

    }
}
