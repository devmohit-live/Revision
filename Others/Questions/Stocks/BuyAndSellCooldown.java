public class BuyAndSellCooldown {
    final int COOLDOWN = 1;

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2)
            return 0;
        // int[][]dp = new int[n][2];
        // for(int[] row: dp) Arrays.fill(row,-1);

        // return recursion(prices, 0, 1,dp);
        // return tabulation(prices);
        // return tabulation_improved(prices);
        return spaceOptimzation(prices);
    }

    // left to right
    private int recursion(int[] prices, int idx, int buying, int[][] dp) {
        if (idx >= prices.length)
            return 0;
        if (dp[idx][buying] != -1)
            return dp[idx][buying];
        int profit = 0;
        if (buying == 1) {
            profit = Math.max(-prices[idx] + recursion(prices, idx + 1, 0, dp), 0 + recursion(prices, idx + 1, 1, dp));
        } else {
            profit = Math.max(prices[idx] + recursion(prices, idx + 1 + COOLDOWN, 1, dp),
                    0 + recursion(prices, idx + 1, 0, dp));
        }

        return dp[idx][buying] = profit;
    }

    // right to left
    private int tabulation(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2]; // to save n, n+1 idx states (idx+2 is also being called in rec)

        // base
        // dp[n][0] = dp[n][1] = dp[n+1][0] = dp[n][1] = 0; by defautl is 0;
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buying = 0; buying <= 1; buying++) {
                if (buying == 1) {
                    dp[idx][buying] = Math.max(-prices[idx] + dp[idx + 1][0], 0 + dp[idx + 1][1]);
                } else {
                    dp[idx][buying] = Math.max(prices[idx] + dp[idx + 1 + COOLDOWN][1], 0 + dp[idx + 1][0]);
                }
            }
        }

        return dp[0][1];
    }

    private int tabulation_improved(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2]; // to save n, n+1 idx states (idx+2 is also being called in rec)

        // base
        // dp[n][0] = dp[n][1] = dp[n+1][0] = dp[n][1] = 0; by defautl is 0;
        for (int idx = n - 1; idx >= 0; idx--) {
            dp[idx][1] = Math.max(-prices[idx] + dp[idx + 1][0], 0 + dp[idx + 1][1]);

            dp[idx][0] = Math.max(prices[idx] + dp[idx + 1 + COOLDOWN][1], 0 + dp[idx + 1][0]);
        }

        return dp[0][1];
    }

    private int spaceOptimzation(int[] prices) {
        int n = prices.length;

        // Now here will have 3 states instead of two => curr: idx, plus1 = idx+1, plus2
        // = idx+2
        // we are making this assumbption that cooldown will always be 1, it ist is k
        // then we can't make this optimzattion of space
        int[] curr = new int[2], plus1 = new int[2], plus2 = new int[2];

        for (int idx = n - 1; idx >= 0; idx--) {

            curr[1] = Math.max(-prices[idx] + plus1[0], 0 + plus1[1]);

            curr[0] = Math.max(prices[idx] + plus2[1], 0 + plus1[0]);

            // after itration :
            // plus2 = plus1;
            // plus1 = curr;
            // all arrays are pointing to same object to avaoid this we can use
            // Arrays.copyofRange

            // plus2 = Arrays.copyOfRange(plus1,0,2);
            // plus1 = Arrays.copyOfRange(curr,0,2);

            // or we can manually do it;
            plus2[0] = plus1[0];
            plus2[1] = plus1[1];
            plus1[0] = curr[0];
            plus1[1] = curr[1];

        }

        return plus1[1];
    }
}
