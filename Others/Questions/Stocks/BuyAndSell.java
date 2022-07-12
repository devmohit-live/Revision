public class BuyAndSell {
    public int maxProfit(int[] prices) {
        // try to sell today and check for profit
        final int N = prices.length;
        int maxProfit = 0, currProfit = 0, min = prices[0];
        for (int i = 1; i < N; i++) {
            currProfit = prices[i] - min;
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(maxProfit, currProfit);
        }

        return maxProfit;
    }
}
