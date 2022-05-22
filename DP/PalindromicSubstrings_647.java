/**
 * PalindromicSubstrings_647
 */
public class PalindromicSubstrings_647 {

    public int countSubstrings(String s) {
        // return brute(s);
        // return brute_improved(s);
        // return optimal_dp(s);
        return optimal(s);
    }

    // Brute: create all substrings and check for palindrome : O(n^3)
    private int brute(String s) {
        int count = 0, n = s.length();
        // generate all substrings and check for palindorme

        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                count += isPalindrome(sub) ? 1 : 0;
            }

        return count;
    }

    private int brute_improved(String s) {
        int count = 0, n = s.length();
        // generate all substrings and check for palindorme

        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++) {
                count += isPalindrome(s, i, j) ? 1 : 0;
            }

        return count;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1, n = s.length();
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    // Better O(n*n / 2) : DP
    // Approach: use find longestpalindromic substring approach : it's dp contains
    // all palindromic substrings

    private int optimal_dp(String s) {
        int count = 0, n = s.length();
        boolean[][] dp = new boolean[n][n];

        // tabulations : as we need all intermediate states
        // using gap startergy

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                // 1char
                if (gap == 0)
                    dp[i][j] = true;
                // 2 chars
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                // rest of the gaps : check for digonal left downwards : i+1,j-1
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);

                if (dp[i][j])
                    count++;
            }
        }
        // Arrays.stream(dp).forEach(ar -> System.out.println(Arrays.toString(ar)));
        return count;

    }

    private Boolean lps_memo(String s, int i, int j, Boolean[][] dp) {
        // if(i>=s.length() || j<0 ) return false;

        if (dp[i][j] != null)
            return dp[i][j];
        if (i >= j)
            return dp[i][j] = true;

        dp[i][j] = s.charAt(i) == s.charAt(j) && lps_memo(s, i + 1, j - 1, dp);

        return dp[i][j];

    }

    // Optimal: use two pointers treat every character as middle of palindromic
    // substring ans try to increase the left and right boundaries while maintainng
    // it's palindromic characteristics
    // Do it for odd length(palindromic string and even lenegth palindromic string)
    // O(n^2) + O(n^2) => O(n^2)

    // Time: O(n)(for each l,r) * O(n)(for each palidromic check) = >O(n*n)
    private int optimal(String s) {
        int n = s.length(), count = 0;
        int l = 0, r = 0;

        for (int i = 0; i < n; i++) {

            l = i;
            r = i; // odd length (1 common character in between)
            count += palindromic(s, l, r);

            l = i;
            r = i + 1; // even length (two same characters)
            count += palindromic(s, l, r);

        }

        return count;
    }

    // (n/2)+(n/2) = > O(n)
    private int palindromic(String s, int l, int r) {
        int count = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            count++;
            l--;
            r++;
        }

        return count;
    }

}