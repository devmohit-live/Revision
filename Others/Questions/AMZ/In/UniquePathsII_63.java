package Others.Questions.AMZ.In;

public class UniquePathsII_63 {
    int[][] dir = { { 0, 1 }, { 1, 0 } };

    public int uniquePathsWithObstacles(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int n = grid.length, m = grid[0].length;
        Integer[][] dp = new Integer[n + 1][m + 1];

        if (grid[n - 1][m - 1] == 1 || grid[0][0] == 1)
            return 0;

        return solve(grid, 0, 0, dp);
    }

    // not considering vis will also act as marka nd unmark

    private Integer solve(int[][] grid, int sr, int sc, Integer[][] dp) {
        if (sr == grid.length - 1 && sc == grid[0].length - 1)
            return dp[sr][sc] = 1;
        if (dp[sr][sc] != null)
            return dp[sr][sc];
        int count = 0;
        for (int[] d : dir) {
            int r = sr + d[0], c = sc + d[1];
            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] != 1) {
                count += solve(grid, r, c, dp);
            }
        }

        return dp[sr][sc] = count;
    }
}
