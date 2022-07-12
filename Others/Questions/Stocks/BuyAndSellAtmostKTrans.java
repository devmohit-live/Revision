public class BuyAndSellAtmostKTrans {
    public int maxProfit(int k, int[] prices) {
        BuySellAtmost2Trans solver = new BuySellAtmost2Trans(k);
        return solver.maxProfit(prices);
    }
}
