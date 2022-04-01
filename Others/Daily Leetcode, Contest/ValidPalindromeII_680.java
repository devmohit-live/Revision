public class ValidPalindromeII_680 {
    public boolean validPalindrome(String s) {
        // return brute(s);
        // return minDeletionLps(s);
        return twoPointers(s);

    }

    // by creating new strings after removing each char
    private boolean brute(String s) {
        if (s == null || s.length() == 0)
            return false;

        if (s.length() == 1 || isPal(s))
            return true;

        StringBuilder sb;
        for (int i = 0; i < s.length(); i++) {
            sb = new StringBuilder(s);
            sb.deleteCharAt(i);
            if (isPal(sb.toString()))
                return true;
        }
        return false;
    }

    // tle and mle : using lps
    private boolean minDeletionLps(String s) {
        int n = s.length();

        int i = 0, j = n - 1;
        int[][] dp = new int[n][n]; // 1 to n

        for (int[] d : dp)
            Arrays.fill(d, -1);

        int lps = lps(s, i, j, dp); // gives mle

        // int lps = lps(s,rev,n,n,dp); // gives tle
        // System.out.println(lps);
        // find lps
        // if s.length - lps <=1 -> possible

        return n - lps <= 1;

    }

    private int lps(String s, int i, int j, int[][] dp) {
        if (i > j)
            return dp[i][j] = 0;

        if (i == j)
            return 1;

        if (dp[i][j] != -1)
            return dp[i][j];

        char a = s.charAt(i);
        char b = s.charAt(j);

        int ans = 0;

        if (a == b) {
            ans = 2 + lps(s, i + 1, j - 1, dp);
        } else {
            ans = Math.max(lps(s, i + 1, j, dp), lps(s, i, j - 1, dp));
        }

        return dp[i][j] = ans;
    }

    private int lps_av(String a, String b, int n, int m, Integer[][] dp) {

        if (n == 0 || m == 0)
            return dp[n][m] = 0;

        if (dp[n][m] != null)
            return dp[n][m];

        char x = a.charAt(n - 1), y = b.charAt(m - 1);
        if (x == y) {
            dp[n][m] = 2 + lps_av(a, b, n - 1, m - 1, dp);
        } else {
            dp[n][m] = Math.max(lps_av(a, b, n - 1, m, dp), lps_av(a, b, n, m - 1, dp));
        }
        return dp[n][m];
    }

    private boolean isPal(String s) {
        int i = 0, j = s.length() - 1;
        return isPal(s, i, j);
    }

    // optimal: using two pointers : on first mismath check if deleting anyone
    // character from wither end makes the rest palindomr or not

    private boolean twoPointers(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return (isPal(s, i + 1, j) || isPal(s, i, j - 1));
            }
            i++;
            j--;
        }

        return true; // complete string is pal
    }

    private boolean isPal(String s, int si, int ei) {

        while (si < ei)
            if (s.charAt(si++) != s.charAt(ei--))
                return false;

        return true;
    }
}
