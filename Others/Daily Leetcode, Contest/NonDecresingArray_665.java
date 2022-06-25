public class NonDecresingArray_665 {
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        if (n == 1 || n == 0)
            return true;

        // int[] dp = new int[n+1];
        // int lis = lis(nums,dp,n);

        // return n-lis<=1;

        return optimal(nums, n);
    }

    private boolean optimal(int[] nums, int n) {
        int count = 1, last = nums[0];
        // check from start
        for (int i = 1; i < n; i++) {
            if (last <= nums[i]) {
                last = nums[i];
                count++;
            }
        }

        if (n - count <= 1)
            return true;

        count = 1;
        last = nums[n - 1];
        // check from behind
        for (int i = n - 2; i >= 0; i--) {
            if (last >= nums[i]) {
                last = nums[i];
                count++;
            }
        }

        if (n - count <= 1)
            return true;

        return false;
    }

    // O(n*n)
    private int lis(int[] arr, int[] dp, int n) {
        int max = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }
}