public class KnightProbability_688 {
    int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
    int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

    // total possiblities at a time = 8
    // at every level we further have /8 possiblities that's why we divide /8 at
    // each level
    // at start we have 1/8 possiblities
    // at next level for each 8 possiblities we have futher 8 possiblities => 1/64 ,
    // similarly for next further move(k)
    // if we can't have any move the probability of being in a board in 1.

    public double knightProbability(int n, int k, int row, int col) {
        double[][][] dp = new double[k + 1][n + 1][n + 1];
        return dfs(n, k, row, col, dp);

    }

    private double dfs(int n, int k, int sr, int sc, double[][][] dp) {
        if (k == 0)
            return dp[k][sr][sc] = 1.0; // 0 moves

        if (dp[k][sr][sc] != 0.0)
            return dp[k][sr][sc];

        double count = 0;

        for (int i = 0; i < dx.length; i++) {
            int r = sr + dx[i], c = sc + dy[i];
            if (r >= 0 && c >= 0 && r < n && c < n)
                count += dfs(n, k - 1, r, c, dp);
        }
        return dp[k][sr][sc] = count / 8.0;
    }
}
