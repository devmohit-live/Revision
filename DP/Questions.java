import java.util.*;

public class Questions {

    static int mod = (int) 1e9 + 7;

    public static void display(int[] dp) {
        System.out.println(Arrays.toString(dp));
    }

    public static void display2d(int[][] dp) {
        for (int[] ar : dp)
            display(ar);
    }

    public static void display(long[] dp) {
        System.out.println(Arrays.toString(dp));
    }

    public static void display2d(long[][] dp) {
        for (long[] ar : dp)
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
    class start_to_end {
        public int minCostClimbingStairs(int[] cost) {
            int[] dp = new int[cost.length + 2];
            Arrays.fill(dp, -1);
            solve(cost, dp, 0);
            return Math.min(dp[0], dp[1]);
        }

        int solve(int[] cost, int[] dp, int idx) {
            if (idx > cost.length)
                return 0;
            if (idx == cost.length)
                return dp[idx] = 0;

            if (dp[idx] != -1)
                return dp[idx];

            int a = cost[idx] + solve(cost, dp, idx + 1); // 1 jump
            int b = cost[idx] + solve(cost, dp, idx + 2); // 2 jump
            int ans = Math.min(a, b);

            return dp[idx] = ans;

        }
    }

    class end_to_start_and_tab {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n + 1];
            Arrays.fill(dp, -1);
            // solve(cost,dp,0);
            // return Math.min(dp[0],dp[1]);
            minJumpsTab(cost, dp, n);
            return Math.min(dp[n - 1], dp[n - 2]);
        }

        int minJumpsTab(int[] cost, int[] dp, int n) {
            for (int idx = 0; idx <= n; idx++) {

                if (idx == 0 || idx == 1) {
                    dp[idx] = cost[idx];
                    continue;
                }

                int singleJump = dp[idx - 1];
                int doubleJump = dp[idx - 2];
                int selfCost = idx < cost.length ? cost[idx] : 0;
                int minCost = Math.min(singleJump, doubleJump) + selfCost;
                dp[idx] = minCost;
            }
            return dp[n];
        }
    }

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

            // since we can't return directly from here on seeing zero(iteratind from last)
            if (ch != '0' && idx < s.length() - 1) {
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
    // if there is a mod operation alaways take dp,storage as long

    static long decode2(String s, int idx, long[] dp) {
        int n = s.length();

        if (idx == n) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        char ch1 = s.charAt(idx);
        if (ch1 == '0')
            return dp[idx] = 0;

        long count = 0;
        if (ch1 == '*') {
            // single character call
            count = (count + (9 * decode2(s, idx + 1, dp))) % mod;

            // double character call
            if (idx < n - 1) {
                char ch2 = s.charAt(idx + 1);
                if (ch2 >= '0' && ch2 <= '6') {
                    count = (count + (2 * decode2(s, idx + 2, dp))) % mod;
                } else if (ch2 >= '7' && ch2 <= '9') {
                    count = (count + (1 * decode2(s, idx + 2, dp))) % mod;
                } else {
                    // 2nd charcater is also a star
                    count = (count + (15 * decode2(s, idx + 2, dp))) % mod;
                }
            }

        } else {
            // first character is a character

            // single call
            count = (count + (1 * decode2(s, idx + 1, dp))) % mod;

            // double character call
            if (idx < n - 1) {
                char ch2 = s.charAt(idx + 1);
                if (ch1 == '1' && ch2 == '*') {
                    // second character id a star
                    count = (count + (9 * decode2(s, idx + 2, dp))) % mod;

                } else if (ch1 == '2' && ch2 == '*') {
                    // second character is a star
                    count = (count + (6 * decode2(s, idx + 2, dp))) % mod;

                } else if (ch2 != '*') {
                    // compulsory to check ch2!='*' else ch2-'0' will give some lesser number so
                    // num<=26 and unnceaary wrong call will bw made
                    // normal character
                    int num = (ch1 - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count = (count + (1 * decode2(s, idx + 2, dp))) % mod;
                }

            }

        }

        return dp[idx] = count;
    }

    // GFG MaxGold:
    static int[][] dir1 = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

    static int maxGold(int n, int m, int M[][]) {
        int gold = 0;
        int gold2 = 0;

        int[][] dp = new int[n][m];
        int[][] dp2 = new int[n][m];

        for (int r = 0; r < n; r++) {
            maxGold_Mem(r, 0, M, dp);
            gold = Math.max(gold, dp[r][0]);
            maxGold_Tab(r, 0, M, dp2);
            gold2 = Math.max(gold, dp2[r][0]);
        }

        display2d(dp);
        System.out.println(gold2);
        display2d(dp2);
        return gold;
    }

    static int maxGold_Mem(int sr, int sc, int[][] mat, int[][] dp) {
        if (sc + 1 == mat[0].length)
            return dp[sr][sc] = mat[sr][sc];

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int maxGold = 0;
        for (int[] d : dir1) {
            int r = sr + d[0];
            int c = sc + d[1];
            if (r >= 0 && r < mat.length && c >= 0 && c < mat[0].length) {
                maxGold = Math.max(maxGold, maxGold_Mem(r, c, mat, dp));
            }
        }

        return dp[sr][sc] = mat[sr][sc] + maxGold;
    }

    static int maxGold_Tab(int SR, int SC, int[][] mat, int[][] dp) {
        int n = mat.length, m = mat[0].length;
        for (int sr = n - 1; sr >= SR; sr--) {
            for (int sc = m - 1; sc >= SC; sc--) {
                if (sc + 1 == mat[0].length) {
                    dp[sr][sc] = mat[sr][sc];
                    continue;
                }
                int maxGold = 0;
                for (int[] d : dir1) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    if (r >= 0 && r < mat.length && c >= 0 && c < mat[0].length) {

                        maxGold = Math.max(maxGold, dp[r][c]); // Math.max(maxGold, maxGold_Mem(r, c, mat, dp));
                    }
                }

                dp[sr][sc] = mat[sr][sc] + maxGold;
            }
        }
        return dp[SR][SC];
    }

    // MIN cost GFG : exactly max gold
    static int[][] dir = { { 1, 0 }, { 1, -1 }, { 1, 1 } };

    static int maximumPath(int N, int Matrix[][]) {

        int n = Matrix.length, m = Matrix[0].length;

        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        // starting from any col in mat
        int max = 0;

        for (int c = 0; c < m; c++) {
            maxsum_Mem(0, c, Matrix, dp);
            max = Math.max(max, dp[0][c]);
        }

        // Arrays.stream(dp).forEach(d->System.out.println(Arrays.toString(d)));

        return max;
    }

    static int maxsum_Mem(int sr, int sc, int[][] mat, int[][] dp) {
        if (sr + 1 == mat.length)
            return dp[sr][sc] = mat[sr][sc];

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int maxPath = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && r < mat.length && c >= 0 && c < mat[0].length) {
                maxPath = Math.max(maxPath, maxsum_Mem(r, c, mat, dp));
            }
        }

        return dp[sr][sc] = maxPath + mat[sr][sc];
    }

    // = Friends Pairing GFG =>

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

    // We have done this as we just need no of ways , so we used the concept that n
    // similar calls will give same result so net = n*x
    // we can't

    // https://
    // www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
    static long divideInKSubsetsMem(int n, int k, long[][] dp) {
        if (n == 0 || k == 0)
            return dp[n][k] = 0;
        // n==k n people n groups => 1 in each group, k==1 all people in 1 group
        if (n == k || k == 1)
            return dp[n][k] = 1;

        if (dp[n][k] != -1)
            return dp[n][k];

        // we made 1 group of self(group-1, peopel-1);
        long selfGroup = divideInKSubsetsMem(n - 1, k - 1, dp);

        // we will pair with other groups formed ny rest of memebers, no we can choose
        // any of those k groups formed by n-1 members
        long pairWithOtherFormedGroups = divideInKSubsetsMem(n - 1, k, dp) * k;
        long ans = selfGroup + pairWithOtherFormedGroups;
        return dp[n][k] = ans;
    }

    // TODO: TO ask why tab and mem gives different answers

    static long divideInKSubsetsTab(int N, int K, long[][] dp) {
        // dependent on previous row's data , we have handled the n=k=0 in main already
        // and also in memoization there is no concpet of n=k=0

        for (int n = 0; n <= N; n++) {
            for (int k = 0; k <= K; k++) {
                if (n == 0 || k == 0) {
                    dp[n][k] = 0;
                    continue;
                }
                if (n == k || k == 1) {
                    dp[n][k] = 1;
                    continue;
                }

                long selfGroup = dp[n - 1][k - 1]; // divideInKSubsetsMem(n - 1, k - 1, dp);
                long pairWithOtherFormedGroups = dp[n - 1][k] * k; // divideInKSubsetsMem(n - 1, k, dp) * k;
                long ans = selfGroup + pairWithOtherFormedGroups;
                dp[n][k] = ans;
            }
        }
        return dp[N][K];
    }

    static long divideInKGroups(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        Arrays.stream(dp).forEach(d -> Arrays.fill(d, -1));
        long ans = divideInKSubsetsMem(n, k, dp);
        display2d(dp);
        System.out.println(ans);

        long[][] dp2 = new long[n + 1][k + 1];
        Arrays.stream(dp2).forEach(d -> Arrays.fill(d, -1));
        long ans2 = divideInKSubsetsTab(n, k, dp2);
        display2d(dp2);
        return ans2;
    }

    // Leetcode 538 : Based on lcs
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        // A + B - 2* (A intersection B) => no of words making s1,s2 uncommon
        int res = n + m - (2 * lcs_Mem(word1, word2, n, m, new int[n + 1][m + 1]));
        return res;
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

    public static void main(String[] args) {
        // String s[] = { "3*", "212311", "*", "1*", "2*", "*3", "**", "*3525**56*" };
        // for (String st : s) {
        // long[] dp = new long[st.length() + 1];
        // Arrays.fill(dp, -1);
        // System.out.println(decode2(st, 0, dp));
        // System.out.println(Arrays.toString(dp));
        // }

        // TODO: to ask

        System.out.println(divideInKGroups(5, 3));
        System.out.println();

        // int n = 3, m = 3;
        // int[][] M = { { 1, 3, 3 }, { 2, 1, 4 }, { 0, 6, 4 } };
        // System.out.println(maxGold(n, m, M));
    }

}
