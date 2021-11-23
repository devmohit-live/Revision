public class Tribonacci {
    // 1137. N-th Tribonacci Number
    // DP practise
    public int tribonacci(int n) {
        int[] dp = new int[n + 1];
        return triboOpti(n);
    }

    private int triboMem(int n, int[] dp) {
        if (n == 0)
            return dp[n] = 0;
        if (n == 1 || n == 2)
            return dp[n] = 1;

        if (dp[n] != 0)
            return dp[n];

        return dp[n] = triboMem(n - 1, dp) + triboMem(n - 2, dp) + triboMem(n - 3, dp);

    }

    private int triboTab(int N, int[] dp) {
        // l->r
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 0;
                continue;
            }
            if (n == 1 || n == 2) {
                dp[n] = 1;
                continue;
            }
            // dp[n] = tribo(n-1,dp)+tribo(n-2,dp)+tribo(n-3,dp);
            dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3];
        }
        return dp[N];
    }

    private int triboOpti(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        int a = 0, b = 1, c = 1, d = 0;
        n -= 2;
        while (n-- > 0) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;

        }
        return c;
    }

}
