public class Questions {

    public static void display(int[] dp) {
        System.out.println(Arrays.toString(dp));
    }

    public static void display2d(int[][] dp) {
        for (int[] ar : dp)
            display(ar);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;

        return mazePathTab(dp, 0, 0, m - 1, n - 1, obstacleGrid, dir);

    }

    // Leetcode 63
    private static int mazePathTab(int[][] dp, int SR, int SC, int ER, int EC, int[][] obstacleGrid, int[][] dir) {

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
    public static int climbStairs(int n) {
        return fibo_opti(n);
    }

    private static int fibo_opti(int n) {
        int a = 1, b = 1; // here fibo start from 1 => 1,1,2,3 (REASON : NO OF STEPS CAN'T BE ZEROS HERE)

        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return b;

    }

    // Leetcode 746

    // Leetcode 91 Decode Ways I --------
    // 0 ms
    static int numDecodingsMemo(String str, int idx, int[] dp) {
        if (idx == str.length())
            return dp[idx] = 1;

        if (dp[idx] != -1)
            return dp[idx];

        char ch = str.charAt(idx);
        if (ch == '0')
            return dp[idx] = 0;

        int count = 0;

        count += numDecodingsMemo(str, idx + 1, dp);

        // at least 2 chars are available
        if (idx < str.length() - 1) {
            char ch2 = str.charAt(idx + 1);
            int num = (ch - '0') * 10 + (ch2 - '0');
            if (num <= 26)
                count += numDecodingsMemo(str, idx + 2, dp);

        }

        return dp[idx] = count;

    }

    // tab => slower 3 ms
    static int numDecodingsTab(String str, int IDX, int[] dp) {
        for (int idx = str.length(); idx >= 0; idx--) {
            if (idx == str.length()) {
                dp[idx] = 1;
                continue;
            }

            char ch = str.charAt(idx);
            if (ch == '0') {
                dp[idx] = 0;
                continue;
            }

            int count = 0;
            count += dp[idx + 1]; // numDecodings(str, idx + 1, dp);

            // adding the second onedp[idx+2] is dependent on condition

            // at least 2 chars are available
            if (idx < str.length() - 1) {
                char ch2 = str.charAt(idx + 1);
                int num = (ch - '0') * 10 + (ch2 - '0');
                if (num <= 26)
                    count += dp[idx + 2]; // numDecodings(str, idx + 2, dp);

            }

            dp[idx] = count;

        }
        return dp[IDX];
    }

    // depends on previous 2 states
    static int numDecodings_opti(String s) {
        int a = 1, b = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {
            int sum = 0;
            char ch = s.charAt(idx);
            if (ch != '0')
                sum += a;

                //since we can't return directly from here on seeing zero(iteratind from last)
            if (ch!='0' && idx < s.length() - 1) {
                char ch2 = s.charAt(idx + 1);
                int num = (ch - '0') * 10 + (ch2 - '0');
                if (num <= 26)
                    sum += b;
            }

            b = a;
            a = sum;
        }

        return a;

    }

    // -------

    // Leetcode 639. Decode Ways II

}
