import java.util.Arrays;

public class MCM_CutProblems {
    public static void display2d(int[][] dp) {
        for (int[] d : dp)
            System.out.println(Arrays.toString(d));
    }

    static int matrixMultiplication(int N, int arr[]) {
        int[][] dp = new int[N][N];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = mcm_Mem(arr, 0, N - 1, dp);
        display2d(dp);
        int[][] dp2 = new int[N][N];
        int ans2 = mcm_Tab(arr, 0, N - 1, dp2);
        return ans;
    }

    static int mcm_Mem(int arr[], int si, int ei, int[][] dp) {

        // single matrix
        if (ei - si == 1) {
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != -1)
            return dp[si][ei];

        // make valid cuts
        int ans = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {
            int leftSideMultiplication = mcm_Mem(arr, si, cut, dp);
            int rightSideMultiplication = mcm_Mem(arr, cut, ei, dp);
            int leftAndRighMultiplication = arr[si] * arr[cut] * arr[ei];
            int net = leftSideMultiplication + rightSideMultiplication + leftAndRighMultiplication;
            ans = Math.min(ans, net);
        }

        return dp[si][ei] = ans;

    }

    static int mcm_Tab(int[] arr, int SI, int EI, int[][] dp) {
        // gap=0 represents invalid 2d matrix => 1d
        int n = arr.length;
        for (int gap = 1; gap < n; gap++) {
            for (int si = SI, ei = gap; si < n && ei <= EI; si++, ei++) {
                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    continue;
                }

                // make valid cuts
                int ans = (int) 1e9;
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftSideMultiplication = dp[si][cut]; // mcm_Mem(arr, si, cut, dp);
                    int rightSideMultiplication = dp[cut][ei];// mcm_Mem(arr, cut, ei, dp);
                    int leftAndRighMultiplication = arr[si] * arr[cut] * arr[ei];
                    int net = leftSideMultiplication + rightSideMultiplication + leftAndRighMultiplication;
                    ans = Math.min(ans, net);
                }

                dp[si][ei] = ans;

            }
        }
        System.out.println("DP of Tabulation: ");
        display2d(dp);
        return dp[SI][EI];
    }

    // GFG:
    // https://practice.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication1024/1#
    static String matrixChainOrder(int p[], int n) {
        int[][] dp = new int[n][n];
        String[][] sdp = new String[n][n];
        String ans = mcm_Tab_with_Path(p, 0, n - 1, dp, sdp);
        return ans;
    }

    static String mcm_Tab_with_Path(int[] arr, int SI, int EI, int[][] dp, String[][] sdp) {
        // gap=0 represents invalid 2d matrix => 1d
        int n = arr.length;
        for (int gap = 1; gap < n; gap++) {
            for (int si = SI, ei = gap; si < n && ei <= EI; si++, ei++) {
                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                // make valid cuts
                int ans = (int) 1e9;
                String sans = "";

                for (int cut = si + 1; cut < ei; cut++) {
                    int leftSideMultiplication = dp[si][cut]; // mcm_Mem(arr, si, cut, dp);
                    int rightSideMultiplication = dp[cut][ei];// mcm_Mem(arr, cut, ei, dp);
                    int leftAndRighMultiplication = arr[si] * arr[cut] * arr[ei];
                    int net = leftSideMultiplication + rightSideMultiplication + leftAndRighMultiplication;
                    if (ans > net) {
                        ans = net;
                        sans = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }

                }

                dp[si][ei] = ans;
                sdp[si][ei] = sans;

            }
        }
        // System.out.println("DP of Tabulation: ");
        // display2d(dp);
        return sdp[SI][EI];
    }

    // ============================== Min Max =================================

    // https://www.geeksforgeeks.org/minimum-maximum-values-expression/
    static class Pair {
        int min = (int) 1e9;
        int max = -(int) 1e9;

        Pair(int val) {
            this.min = this.max = val;

        }

        Pair() {

        }

    }

    static Pair evaluate(Pair left, Pair right, char op) {
        Pair ans = new Pair();

        // all values are +ve
        if (op == '*') {
            ans.min = left.min * right.min;
            ans.max = left.max * right.max;

        } else if (op == '+') {
            ans.min = left.min + right.min;
            ans.max = left.max + right.max;
        }

        return ans;
    }

    // make a cut at every operator
    static Pair minmax(String exp, int si, int ei, Pair[][] dp) {
        // only sinlge character is left +ve base case
        if (si == ei) {
            int val = exp.charAt(si) - '0';
            return dp[si][ei] = new Pair(val);
        }
        // lookup
        if (dp[si][ei] != null)
            return dp[si][ei];

        Pair res = new Pair();
        // make valid cuts : opeartior is at odd pos
        for (int cut = si + 1; cut < ei; cut += 2) {
            char operaotr = exp.charAt(cut);
            // left and right evalutaed string on current operaotr str[cut]
            Pair leftSideEvaluation = minmax(exp, si, cut - 1, dp);
            Pair rightSideEvaluation = minmax(exp, cut + 1, ei, dp);

            // rec ans
            Pair eval = evaluate(leftSideEvaluation, rightSideEvaluation, operaotr);

            res.min = Math.min(res.min, eval.min);
            res.max = Math.max(res.max, eval.max);
        }

        return dp[si][ei] = res;

    }

    // Follow UP : If operator - is also included

    public static void min_max(Strings exp) {

        int n = exp.length();
        Pair[][] dp = new Pair[n][n];
        Pair res = minmax(exp, 0, n - 1, dp);
        System.out.println("Min val is : " + res.min);
        System.out.println("Max val is : " + res.max);
    }

    public static void main(String[] args) {
        // int N = 5;
        // int[] arr = { 40, 20, 30, 10, 30 };
        // // Output: 26000
        // matrixMultiplication(N, arr);
        min_max("1+2*3+4*5");
    }
}
