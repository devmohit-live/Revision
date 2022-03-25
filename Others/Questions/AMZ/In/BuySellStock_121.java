package Others.Questions.AMZ.In;

/**
 * BuySellStock_121
 */
public class BuySellStock_121 {

    public int maxProfit(int[] prices) {
        // return brute(prices);
        return optimal(prices);
    }

    // brute : for each day we say we brought the share and for next subsequnt days
    // we try to sell ans calulate max profit

    public int brute(int[] arr) {
        int max = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            // brought today, can sell today too
            for (int j = i; j < n; j++) {
                max = Math.max(max, arr[j] - arr[i]);
            }
        }
        return max;
    }

    // maintain the min point so far while traversing and calulate the profit at
    // each instance
    // assumption: we try to sell each day and we have somehow brought the share at
    // mincost

    private int optimal(int[] arr) {
        int n = arr.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i] - min);
        }
        return max;

    }
}