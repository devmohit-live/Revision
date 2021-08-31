public class Questions {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;

        return mazePathTab(dp, 0, 0, m - 1, n - 1, obstacleGrid, dir);

    }

    // Leetcode 63
    private int mazePathTab(int[][] dp, int SR, int SC, int ER, int EC, int[][] obstacleGrid, int[][] dir) {

        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {

                if (sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }

                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= SR && r <= ER && c >= SC && c <= EC && obstacleGrid[r][c] != 1)
                        dp[sr][sc] += dp[r][c];

                }
            }
        }

        return dp[SR][SC];
    }

    // Leetcode 70 : Climbing Stair : Fibonacci
    public int climbStairs(int n) {
        return fibo_opti(n);
    }

    private int fibo_opti(int n) {
        int a = 1, b = 1; // here fibo start from 1 => 1,1,2,3 (REASON : NO OF STEPS CAN'T BE ZEROS HERE)

        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return b;

    }

}
