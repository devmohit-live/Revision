public class BuyAnsSellMultiple {
    // 122. Best Time to Buy and Sell Stock II
    // no consecutive buys and sells are allowed(BB, SS => not allowed)

    public int maxProfit(int[] prices) {
        // memo
        // int[][] dp = new int[prices.length][2];
        // for(int[] d: dp) Arrays.fill(d,-1);
        // return recursion(prices, 0 , 1,dp);

        // tabulation
        // return tabulation(prices);
        return spaceOptimzation(prices);
    }

    private int recursion(int[] prices, int idx, int canBuy, int[][] dp) {
        // base
        if (idx == prices.length)
            return 0;

        if (dp[idx][canBuy] != -1)
            return dp[idx][canBuy];

        int profit = 0;
        // if canbuy
        if (canBuy == 1) {
            // option : can buy today , or will not buy today
            profit = Math.max(-prices[idx] + recursion(prices, idx + 1, 0, dp), recursion(prices, idx + 1, 1, dp));
        } else {
            // 2 options : will sell today(and can buy next) or will not sell today
            profit = Math.max(prices[idx] + recursion(prices, idx + 1, 1, dp), 0 + recursion(prices, idx + 1, 0, dp));
        }
        return dp[idx][canBuy] = profit;
    }

    private int tabulation(int[] prices) { // base case invalid also have to be stored so +1 in size
        int n = prices.length;
        int[][] dp = new int[n + 1][2]; // buy/notbuy
        // dp[n] =0
        dp[n][0] = dp[n][1] = 0;

        // right to left
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                // if canbuy
                if (buy == 1)
                    // option : can buy today , or will not buy today
                    dp[idx][buy] = Math.max(-prices[idx] + dp[idx + 1][0], 0 + dp[idx + 1][1]);
                else
                    // 2 options : will sell today(and can buy next) or will not sell today
                    dp[idx][buy] = Math.max(prices[idx] + dp[idx + 1][1], 0 + dp[idx + 1][0]);
            }
        }

        return dp[0][1]; // starting point of memo

    }

    // see idx+1 is repeating every timee so instead of having 2d dp hve a 1 dp dp

    private int spaceOptimzation(int[] prices) {
        int n = prices.length;
        int[] next = new int[2], curr = new int[2];
        // next[n][0] = next[n][1] = curr[n][0] = curr[n][1] = 0;
        // idx+1 =>next , idx =>curr

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                // if canbuy
                if (buy == 1)
                    // option : can buy today , or will not buy today
                    curr[buy] = Math.max(-prices[idx] + next[0], 0 + next[1]);
                else
                    // 2 options : will sell today(and can buy next) or will not sell today
                    curr[buy] = Math.max(prices[idx] + next[1], 0 + next[0]);
            }

            // after very succcessfull iterartion

            next = curr;
        }

        // return dp[0][1];
        return next[1]; // ot curr[1] both will bw same

    }

    // Following Uptrend
    private int uptrend(int[] arr) {
        // trying to but and sell on each uptrend => buy on lowest and sell on
        // hight(0eak) of a single big trend
        // local maxima - localminima
        // and sum of these diff(maxima, minima) => ans

        // Cons: the number of transactions done will be maximum
        // x no of transcation done in uptrend can be done in 1 transction for local
        // maxima and minima

        int ans = 0;
        if (arr.length < 2)
            return 0;

        for (int i = 1; i < arr.length; i++) {
            int profit = arr[i] - arr[i - 1];
            ans += profit > 0 ? profit : 0; // adding th uptrens
        }

        return ans;
    }
}
