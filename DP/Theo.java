import java.util.Arrays;

public class Theo {
    static void display(int[][] dp) {
        Arrays.stream(dp).forEach(d -> System.out.println(Arrays.toString(d)));
    }

    // FriendPairing GFG
    int mod = (int) 1e9 + 7;

    // Leetcode 516
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 0 || n == 1)
            return n;

        int i = 0, j = n - 1;
        int[][] dp = new int[n][n]; // 1 to n

        for (int[] d : dp)
            Arrays.fill(d, -1);

        return lps(s, i, j, dp);
    }

    int lps(String s, int i, int j, int[][] dp) {
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

    int lps_Tab(String s, int I, int J, int[][] dp) {
        // gap wala satergey
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            // move in a diagonal
            for (int i = -0, j = gap; i < n && j < n; i++, j++) {
                if (i > j) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }

                char a = s.charAt(i);
                char b = s.charAt(j);

                int ans = 0;

                if (a == b) {
                    ans = 2 + dp[i + 1][j - 1];// 2 + lps(s,i+1,j-1,dp);
                } else {
                    ans = Math.max(dp[i + 1][j], dp[i][j - 1]); // Math.max( lps(s,i+1,j,dp) , lps(s,i,j-1,dp));
                }

                dp[i][j] = ans;
            }

        }

        return dp[I][J];

    }

    // Leetcode 1143
    // lcs from src -> des
    public static void longestCommonSubsequences_src_to_des(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = lcs_src_des(text1, text2, 0, 0, dp);
        System.out.println("mem: " + ans);
        display(dp);

        int[][] dp2 = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans2 = lcs_Tab_src_des(text1, text2, dp2);
        System.out.println("Tab: " + ans2);
        display(dp2);
    }

    static int lcs_src_des(String s1, String s2, int i, int j, int[][] dp) {
        if (i == s1.length() || j == s2.length())
            return dp[i][j] = 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        char a = s1.charAt(i);
        char b = s2.charAt(j);

        int ans = 0;

        if (a == b) {
            ans = 1 + lcs_src_des(s1, s2, i + 1, j + 1, dp);
        } else {
            ans = Math.max(lcs_src_des(s1, s2, i + 1, j, dp), lcs_src_des(s1, s2, i, j + 1, dp));
        }
        return dp[i][j] = ans;
    }

    static int lcs_Tab_src_des(String s1, String s2, int[][] dp) {
        int N = s1.length(), M = s2.length();
        for (int i = N; i >= 0; i--) {
            for (int j = M; j >= 0; j--) {
                if (i == N || j == M) {
                    dp[i][j] = 0;
                    continue;
                }

                char a = s1.charAt(i);
                char b = s2.charAt(j);

                int ans = 0;

                if (a == b) {
                    ans = 1 + dp[i + 1][j + 1];// lcs(s1, s2, i + 1, j + 1, dp);
                } else {
                    ans = Math.max(dp[i + 1][j], dp[i][j + 1]);// Math.max(lcs(s1, s2, i + 1, j, dp), lcs(s1, s2, i, j +
                                                               // 1, dp));
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][0];
    }

    // lcs -> des -> src , n-- => recommended
    static void lcs(String s1, String s2) {
        System.out.println("Src to des: ");
        longestCommonSubsequences_src_to_des(s1, s2);
        System.out.println("\n\n Recommended: ");
        longestCommonSubsequences_recom(s1, s2);
    }

    public static void longestCommonSubsequences_recom(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = lcs_Mem(text1, text2, n, m, dp);
        System.out.println("mem: " + ans);
        display(dp);

        int[][] dp2 = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans2 = lcs_Tab(text1, text2, n, m, dp2);
        System.out.println("Tab: " + ans2);
        display(dp2);
    }

    static int lcs_Mem(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = 0;
        if (dp[n][m] != -1)
            return dp[n][m];

        int ans = 0;
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            ans = 1 + lcs_Mem(s1, s2, n - 1, m - 1, dp);
        } else {
            ans = Math.max(lcs_Mem(s1, s2, n - 1, m, dp), lcs_Mem(s1, s2, n, m - 1, dp));
        }

        return dp[n][m] = ans;
    }

    static int lcs_Tab(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }
                int ans = 0;
                if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
                    ans = 1 + dp[n - 1][m - 1];// 1 + lcs_Mem(s1, s2, n - 1, m - 1);
                } else {
                    // ans = Math.max(lcs_Mem(s1, s2, n - 1, m), lcs_Mem(s1, s2, n, m - 1));
                    ans = Math.max(dp[n - 1][m], dp[n][m - 1]);
                }

                dp[n][m] = ans;
            }
        }
        return dp[N][M];
    }

    // Leetcode 115
    static int mindistinctSubsMem(String s1, String s2, int n, int m, int[][] dp) {
        if (m == 0)
            return dp[n][m] = 1;

        if (n < m)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        char a = s1.charAt(n - 1);
        char b = s2.charAt(m - 1);
        int ans = 0;
        if (a == b) {
            // 2calls
            ans = mindistinctSubsMem(s1, s2, n - 1, m - 1, dp) + mindistinctSubsMem(s1, s2, n - 1, m, dp);
        } else {
            // 1 call

            ans = mindistinctSubsMem(s1, s2, n - 1, m, dp);

        }

        return dp[n][m] = ans;
    }

    static int mindistinctSubsTab(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }

                if (n < m) {
                    dp[n][m] = 0;
                    continue;
                }

                char a = s1.charAt(n - 1);
                char b = s2.charAt(m - 1);
                int ans = 0;
                if (a == b) {
                    // 2calls
                    ans = dp[n - 1][m - 1] + dp[n - 1][m];
                } else {
                    // 1 call

                    ans = dp[n - 1][m];

                }

                dp[n][m] = ans;
            }
        }
        return dp[N][M];
    }

    // Leetcode 72 : Edit Distance
    public static int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return minDistance(word1, word2, n, m, dp);
    }

    public static int minDistance(String s1, String s2, int n, int m, int[][] dp) {

        if (n == 0 && m == 0)
            return dp[n][m] = 0; // no operations needed
        if (n == 0)
            return dp[n][m] = m; // m insert operations
        if (m == 0)
            return dp[n][m] = n; // n delete operations

        if (dp[n][m] != -1)
            return dp[n][m];
        char a = s1.charAt(n - 1);
        char b = s2.charAt(m - 1);

        int replace = minDistance(s1, s2, n - 1, m - 1, dp);
        int insert = minDistance(s1, s2, n, m - 1, dp);
        int delete = minDistance(s1, s2, n - 1, m, dp);

        int ans = 0;
        if (a == b) {
            // move forward no operation is done-> similar to replace
            ans = replace;
        } else {
            // we have performed some operation => + cost
            ans = 1 + Math.min(replace, Math.min(insert, delete));
        }

        return dp[n][m] = ans;
    }

    public int minDistanceTab(String s1, String s2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 && m == 0) {
                    dp[n][m] = 0; // no operations needed
                    continue;
                }

                if (n == 0) {
                    dp[n][m] = m; // m insert operations
                    continue;
                }

                if (m == 0) {
                    dp[n][m] = n; // n delete operations
                    continue;
                }

                int replace = dp[n - 1][m - 1];
                int insert = dp[n][m - 1];
                int delete = dp[n - 1][m];

                char a = s1.charAt(n - 1);
                char b = s2.charAt(m - 1);
                int ans = 0;
                if (a == b) {
                    // move forward no operation is done-> similar to replace
                    ans = replace;
                } else {
                    // we have performed some operation => + cost
                    ans = 1 + Math.min(replace, Math.min(insert, delete));
                }

                dp[n][m] = ans;
            }
        }

        return dp[N][M];
    }

    public static void main(String[] args) {
        // String s1 = "geek", s2 = "acef";
        String s = "geeksforgeeks", t = "gks";
        // lcs(s1, s2);
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(mindistinctSubsTab(s, t, n, m, dp));
        display(dp);
    }
}
