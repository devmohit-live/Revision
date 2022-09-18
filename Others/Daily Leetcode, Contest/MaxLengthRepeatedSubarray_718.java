public class MaxLengthRepeatedSubarray_718 {
    int[][] dp;

    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        dp = new int[n + 1][m + 1];
        return lcsSubstring(nums1, nums2, n, m);
    }

    private int lcsSubstring(int[] A, int[] B, int n, int m) {
        int max = 0;
        // dp[0][x], dp[x][0] == 0 ; //already

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 0;
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
