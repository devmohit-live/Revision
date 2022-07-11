public class JumpII {
    int n = nums.length;
    // int[] dp = new int[n+1];
    // Arrays.fill(dp,n+1);
    // return minJumps(nums,0,dp);
    return

    minJumps_Tab(nums);
    }

    // O(n) : Greedy

    // Tabulation : k*n
    private int minJumps_Tab(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // Tabulation technique
        dp[n - 1] = 0; // last index --> no jump;

        /*
         * storing minimum no of moves at each index of my dp array to reach nth index
         * each index of nums[] mention max steps to jump. You are always making jump
         */
        for (int i = n - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE; // reset min

            // iterate on ith value of nums arr, and i+j < n saves for arrayIdxoutofbond exp
            // in dp arr. i.e you are always looking for elements inside dp[]
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                // very crutial check for test case [2,3,0,1,4]
                if (dp[i + j] == 0 && i + j != n - 1)
                    continue;
                min = Math.min(1 + dp[i + j], min);
                dp[i] = min;

            }
        }
        return dp[0];
    }

    // O(k*n)
    private int minJumps(int[] arr, int idx, int[] dp) {
        if (idx == arr.length - 1)
            return dp[idx] = 0;
        if (dp[idx] != arr.length + 1)
            return dp[idx];

        for (int j = 1; j <= arr[idx]; j++) {
            int finalpoint = Math.min(idx + j, arr.length - 1);
            dp[idx] = Math.min(dp[idx], minJumps(arr, finalpoint, dp) + 1);
        }

        return dp[idx];
    }

}
