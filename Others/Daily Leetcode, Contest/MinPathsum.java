public class MinPathsum {
    // 64
    int[][] dir = { { 0, 1 }, { 1, 0 } };

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int dp[][] = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return pathSum(grid, 0, 0, dp);

    }

    private int pathSum(int[][] mat, int sr, int sc, int[][] dp) {
        if (sr == mat.length - 1 && sc == mat[0].length - 1) {
            return dp[sr][sc] = mat[sr][sc];
        }
        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length) {
                int recans = pathSum(mat, r, c, dp);
                if (recans < min) {
                    min = recans;
                }
            }

        }
        return dp[sr][sc] = min + mat[sr][sc];
    }
}
