public class MinStepsToMakePalindrome {
    public int minInsertions(String s) {
        StringBuilder sb = new StringBuilder(s);
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int c = lcs(s, sb.reverse().toString(), dp, n, n);

        return s.length() - c;
    }

    private int lcs(String a, String b, int[][] dp, int n, int m) {
        if (n == 0 || m == 0)
            return dp[n][m] = 0;
        if (dp[n][m] != -1)
            return dp[n][m];

        char x = a.charAt(n - 1), y = b.charAt(m - 1);
        if (x == y)
            return dp[n][m] = 1 + lcs(a, b, dp, n - 1, m - 1);
        return dp[n][m] = Math.max(lcs(a, b, dp, n - 1, m), lcs(a, b, dp, n, m - 1));

    }

    // Min chars required to maek string palindrome : InterviewBit: KMP

    public int solve(String A) {
        int len = A.length();
        String reverse = new StringBuilder(A).reverse().toString();
        A = A + "$" + reverse;
        return len - KMP(A);
    }

    public static int KMP(String s) {
        int m = s.length();
        int lps[] = new int[m];
        int i = 1, len = 0;
        lps[0] = 0;
        while (i < m) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps[m - 1];
    }
}
