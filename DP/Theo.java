import java.util.Arrays;

public class Theo {
    static void display(int[][] dp) {
        Arrays.stream(dp).forEach(d -> System.out.println(Arrays.toString(d)));
    }

    // FriendPairing GFG
    int mod = (int) 1e9 + 7;

    public long countFriendsPairings(int n) {
        if (n == 0)
            return 0;
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        return pair_tab(n, dp);

    }

    long pair_Mem(int n, long[] dp) {
        if (n == 0)
            return dp[n] = 1;

        if (dp[n] != -1)
            return dp[n];

        long single = pair_Mem(n - 1, dp);
        long paired = (n - 2) >= 0 ? pair_Mem(n - 2, dp) * (n - 1) : 0;
        long ans = (single + paired) % mod;
        return dp[n] = ans;

    }

    long pair_tab(int N, long[] dp) {
        for (int n = 0; n <= N; n++) {

            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            long single = dp[n - 1];
            long paired = (n - 2) >= 0 ? dp[n - 2] * (n - 1) : 0;
            long ans = (single + paired) % mod;
            dp[n] = ans;
        }

        return dp[N];
    }

    long pair_opti(int N, long[] dp) {
        int a = 1, b = 1, sum = 0;
        if (N < 2)
            return b;

        for (int i = 2; i <= N; i++) {
            // long single = a;
            // long pair =((i-1) * b )%mod;

            sum = (a) + ((i - 1) * b) % mod;
            a = b;
            b = sum;
        }
        return b;
    }

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
    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = lcs(text1, text2, 0, 0, dp);
        display(dp);
        return ans;
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
            ans = 1 + lcs(s1, s2, i + 1, j + 1, dp);
        } else {
            ans = Math.max(lcs(s1, s2, i + 1, j, dp), lcs(s1, s2, i, j + 1, dp));
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
        display(dp);
        return dp[0][0];
    }

    // lcs -> des -> src , n-- => recommended
    static void lcs(String s1, String s2) {

    }

    static int lcs_Mem(String s1, String s2, int n, int m) {
        if (n == 0 || m == 0)
            return dp[n][m] = 0;
        if (dp[n][m] != -1)
            return dp[n][m];

        int ans = 0;
        if (s1.charAt(n) == s2.charAt(m)) {
            ans = 1 + lcs_Mem(s1, s2, n - 1, m - 1);
        } else {
            ans = Math.max(lcs_Mem(s1, s2, n - 1, m), lcs_Mem(s1, s2, n, m - 1));
        }

        return dp[n][m] = ans;
    }

    static int lcs_Tab(String s1, String s2, int N, int M) {
        for(){
            for(){
                if(n==0 || m==0){
                   dp[n][m]=0;
                   continue; 
                } 
                int ans=0;
                if(s1.charAt(n) == s2.charAt(m)){
                    ans = 1 + lcs_Mem(s1, s2, n-1, m-1);
                }else{
                    ans = Math.max(lcs_Mem(s1, s2, n-1, m), lcs_Mem(s1, s2, n, m-1));
                }

                dp[n][m]=ans;
            }
        }
        return dp[N][M];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcdefgh", "acef") + "\n");
        String s1 = "abcdefgh", s2 = "acef";
        int N = s1.length(), M = s2.length();
        int[][] dp = new int[N + 1][M + 1];
        Arrays.stream(dp).forEach(d -> Arrays.fill(d, -1));
        System.out.println(lcs_Tab(s1, s2, dp));
    }
}
