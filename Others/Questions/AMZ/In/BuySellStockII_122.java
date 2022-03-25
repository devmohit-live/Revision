public class BuySellStockII_122 {
     //Approach: Check for the next increasing value to sell : Draw a price/time graph/chart
    
    // brough today and will sell on next increased price
    public int maxProfit(int[] prices) {
        int ans = 0, n = arr.length;
        
        for(int i=0;i<n-1;i++){
            int buy = arr[i];
            int sell = arr[i+1];
            if(sell - buy > 0) ans += sell - buy;
        }
        return ans;
    }
   
}
