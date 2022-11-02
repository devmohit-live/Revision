public class LongestPalindromicSubstring_LPS {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int totalPal = 0, maxPalLength = 0;
        String maxPalString = "";

        longestPalindrome(s, s, dp);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j]) {
                    totalPal++;
                    int len = j - i + 1;
                    if (len > maxPalLength) {
                        maxPalLength = len;
                        maxPalString = s.substring(i, j + 1);
                    }
                }

            }
        }

        System.out.printf(" total pal %d , maxlength %d , maxPalString %s \n", totalPal, maxPalLength, maxPalString);
        return maxPalString;
    }

    private void longestPalindrome(String s1, String s2, boolean[][] dp) {
        int n = s1.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; i < n && j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;

                // 2 for match and 1 common in between
                else if (gap == 1 && s1.charAt(i) == s2.charAt(j))
                    dp[i][j] = true;

                else {

                    boolean a = s1.charAt(i) == s2.charAt(j); // extreme matches
                    boolean b = dp[i + 1][j - 1]; // mid substring should be a pal
                    dp[i][j] = a && b;
                }

            }
        }

    }
}
