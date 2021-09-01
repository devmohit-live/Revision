import java.util.Arrays;

import jdk.nashorn.internal.IntDeque;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;

public class TwoPointers {
    public static void display(int[] dp) {
        System.out.println(Arrays.toString(dp));
    }

    public static void display2d(int[][] dp) {
        for (int[] ar : dp)
            display(ar);
    }

    public static int fib_mem(int n, int[] dp) {
        if (n <= 0) {
            return dp[n] = 0;
        }
        if (n == 1) {
            return dp[n] = 1;
        }

        // lookup
        if (dp[n] != 0)
            return dp[n];

        int ans = fib_mem(n - 1, dp) + fib_mem(n - 2, dp);
        return dp[n] = ans;

    }

    public static int fib_tab(int N, int[] dp) {
        // direction from mem dp filling
        for (int n = 0; n <= N; n++) {
            if (n <= 0) {
                dp[n] = 0;
                continue;
            }
            if (n == 1) {
                dp[n] = 1;
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2]; // relation
        }
        return dp[N]; // ans, var
    }

    public static int fib_opti(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return b;
    }

    static void fibo_run() {
        for (int n = 0; n < 15; n++) {
            int[] dp = new int[n + 1];
            fib_mem(n, dp);
            display(dp);
            int[] dp2 = new int[n + 1];
            fib_tab(n, dp2);
            display(dp2);
            System.out.println(fib_opti(n));
        }
    }

    static int mazePathMem(int[][] dp, int sr, int sc, int dr, int dc, int[][] dir) {
        if (sr == dr && sc == dc)
            return dp[sr][sc] = 1;

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            if (r >= 0 && c >= 0 && r <= dr && c <= dc) {
                count += mazePathMem(dp, r, c, dr, dc, dir);
            }
        }
        return dp[sr][sc] = count;

    }

    public static int mazePathTab(int[][] dp, int SR, int SC, int DR, int DC, int[][] dir) {

        // pattern of filling the dp in mem
        for (int sr = DR; sr >= SR; sr--) {
            for (int sc = DC; sc >= SC; sc--) {
                if (sr == DR && sc == DC) {
                    dp[sr][sc] = 1; // base case
                    continue;
                }

                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    if (r >= SR && c >= SC && r <= DR && c <= DC)
                        dp[sr][sc] += dp[r][c];

                    // OR as we are setting 1 only at specific cond = sr,sc = er,ec
                    // if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length)
                    // dp[sr][sc] += dp[r][c];
                }
            }
        }

        return dp[SR][SC]; // ans , variable names
    }

    public static int mazePathJumpsMem(int[][] dp, int sr, int sc, int er, int ec, int[][] dir) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;

        for (int[] d : dir) {

            // for (int rad = 1; rad <= Math.max(er, ec); rad++) {
            // int r = sr + rad * d[0];
            // int c = sc + rad * d[1];
            // if (r >= 0 && c >= 0 && r <= er && c <= ec) {
            // count += mazePathJumps(dp, r, c, er, ec, dir);
            // }
            // }

            // OR
            int r = sr + d[0];
            int c = sc + d[1];
            while (r >= 0 && r <= er && c >= 0 && c <= ec) {
                count += mazePathJumpsMem(dp, r, c, er, ec, dir);
                r += d[0];
                c += d[1];
            }

        }

        return dp[sr][sc] = count;
    }

    public static int mazePathJumpsTab(int[][] dp, int SR, int SC, int ER, int EC, int[][] dir) {
        // direction mem dp filling
        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (sr == ER && sc == EC) {
                    dp[sr][sc] = 1;
                    continue;
                }

                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    while (r >= SR && r <= ER && c >= SC && c <= EC) {
                        dp[sr][sc] += dp[r][c];
                        r += d[0];
                        c += d[1];
                    }

                }
            }
        }

        return dp[SR][SC];
    }

    public static int boardPathMem(int n, int[] dp) {
        if (n == 0)
            return dp[n] = 1;

        if (dp[n] != 0)
            return dp[n];

        int count = 0;
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += boardPathMem(n - dice, dp);
        }
        return dp[n] = count;
    }

    public static int boardPathTab(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            // if dp[n] is dependent on last 6 states if exists ie dp[n] = summation
            // (dp[n-x]) if n-x exists x=>[1,6]
            for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
                dp[n] += dp[n - dice]; //
            }

        }
        return dp[N];
    }

    public static int boardPathJumpsMem(int n, int[] dp, int[] jumps) {
        if (n == 0)
            return dp[n] = 1;

        if (dp[n] != 0)
            return dp[n];

        int count = 0;
        for (int jump : jumps) {
            if (jump != 0 && n - jump >= 0)
                count += boardPathJumpsMem(n - jump, dp, jumps);
        }
        return dp[n] = count;
    }

    public static int boardPathJumpsTab(int N, int[] dp, int[] jumps) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            for (int jump : jumps) {
                if (jump != 0 && n - jump >= 0)
                    dp[n] += dp[n - jump]; // dependent on previos jump value
            }

        }
        return dp[N];
    }

    static void maze_run() {
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        // String[] dirS = { "H", "V", "D" };
        int m = 5, n = 5;
        int[][] dp = new int[m + 1][n + 1];
        System.out.println(mazePathMem(dp, 0, 0, m, n, dir));
        display2d(dp);
        System.out.println(mazePathTab(dp, 0, 0, m, n, dir));
        int[][] dp2 = new int[m + 1][n + 1];
        display2d(dp2);
    }

    static void mazeJump_run() {
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        // int m = 1, n = 1;
        int m = 3, n = 3;
        int[][] dp = new int[m + 1][n + 1];
        System.out.println(mazePathJumpsMem(dp, 0, 0, m, n, dir));
        display2d(dp);
        System.out.println();
        int[][] dp2 = new int[m + 1][n + 1];
        System.out.println(mazePathJumpsTab(dp2, 0, 0, m, n, dir));
        display2d(dp2);
    }

    static void board_run() {
        int n = 10;
        int[] dp = new int[n + 1];
        System.out.println(boardPathMem(n, dp));
        display(dp);

        int[] dp2 = new int[n + 1];
        System.out.println(boardPathTab(n, dp2));
        display(dp2);
    }

    static void boardPathVar_run() {
        int n = 10;
        int[] allPossibleJumps = { 0, 1, 2, 3, 8, 9, 6, 10 }; // 0 jmp pe infinite call lg skti h

        int[] dp = new int[n + 1];
        System.out.println(boardPathJumpsMem(n, dp, allPossibleJumps));
        display(dp);
        int[] dp2 = new int[n + 1];
        System.out.println(boardPathJumpsTab(n, dp2, allPossibleJumps));
        display(dp2);
    }

    public static void main(String[] args) {
        // fibo_run();

        // maze_run();
        // mazeJump_run();
        // board_run();
        boardPathVar_run();

    }

}
