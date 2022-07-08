public class PaintHousesIII {
    private int[][][] dp;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        dp = new int[m + 1][n + 1][target + 1];
        for (int[][] ddp : dp)
            for (int[] d : ddp)
                Arrays.fill(d, -1);
        int ans = solve(houses, cost, m, n, target, 0, 0, 0);
        return ans == MAX ? -1 : ans;
    }

    private int solve(int[] houses, int[][] cost, int nHouses, int nColors, int maxTarget, int idx, int prevColor,
            int currTar) {
        if (currTar > maxTarget)
            return MAX;
        if (dp[idx][prevColor][currTar] != -1)
            return dp[idx][prevColor][currTar];
        if (idx == nHouses) {
            return dp[idx][prevColor][currTar] = currTar == maxTarget ? 0 : MAX;
        }

        int ans = MAX;
        // house is not painted
        if (houses[idx] == 0) {
            // try painting with all possible colors
            for (int col = 0; col < nColors; col++) {
                int target = prevColor != col + 1 ? currTar + 1 : currTar; // we are painting with diff col : increases
                                                                           // nbrset count
                int rec = solve(houses, cost, nHouses, nColors, maxTarget, idx + 1, col + 1, target);
                if (rec != MAX) {
                    ans = Math.min(ans, cost[idx][col] + rec);
                }
            }
        } else {
            // houses is already painted
            int target = prevColor != houses[idx] ? currTar + 1 : currTar;
            ans = Math.min(ans, solve(houses, cost, nHouses, nColors, maxTarget, idx + 1, houses[idx], target));
        }

        return dp[idx][prevColor][currTar] = ans;
    }
}
