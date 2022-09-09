public class IsSubseq_392 {
    int[][] dp;

    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        // dp = new int[n+1][m+1];
        // for(int[] d: dp) Arrays.fill(d,-2);
        // int ans = lcs(s,t,n,m);
        // // System.out.println(ans);
        // return ans == n;

        return optimized(s, t, n, m);

    }

    private boolean optimized(String s, String t, int n, int m) {
        int i = 0, j = 0, count = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                count++;
            } else {
                j++; // move the t'th string char to mathc with curr char of s
            }
        }

        return count == n;

    }

    private int lcs(String s, String t, int n, int m) {

        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -2)
            return dp[n][m];

        char a = s.charAt(n - 1), b = t.charAt(m - 1);

        int ans = 0;
        if (a == b) {
            ans = 1 + lcs(s, t, n - 1, m - 1);
        } else {
            ans = Math.max(lcs(s, t, n - 1, m), lcs(s, t, n, m - 1));
        }

        return dp[n][m] = ans;
    }
}
