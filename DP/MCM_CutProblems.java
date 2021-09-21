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





    

    public static void main(String[] args) {
        int N = 5;
        int[] arr = { 40, 20, 30, 10, 30 };
        // Output: 26000
        matrixMultiplication(N, arr);
    }
}
