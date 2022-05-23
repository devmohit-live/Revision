public class OnesAndZeros_474 {
    public int findMaxForm(String[] strs, int z, int o) {
        int n = strs.length;
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = countZeroAndOne(strs[i]);
        }

        // Arrays.stream(arr).forEach(ar->System.out.println(Arrays.toString(ar)));

        Integer[][][] dp = new Integer[n + 1][z + 1][o + 1];

        // return knapSack(arr,0,z,o,dp);
        // return knapSack_understandable(arr,0,0,0,z,o,dp);
        return targetSum(arr, 0, 0, 0, z, o, dp);
    }

    private int knapSack(int[][] arr, int idx, int z, int o, Integer[][][] dp) {

        if (idx == arr.length || (z <= 0 && o <= 0))
            return dp[idx][z][o] = 0;

        if (dp[idx][z][o] != null)
            return dp[idx][z][o];
        int include = 0, exclude = 0;

        if (z - arr[idx][0] >= 0 && o - arr[idx][1] >= 0)
            include = 1 + knapSack(arr, idx + 1, z - arr[idx][0], o - arr[idx][1], dp);

        exclude = Math.max(include, knapSack(arr, idx + 1, z, o, dp));

        return dp[idx][z][o] = Math.max(include, exclude);
    }

    private int knapSack_understandable(int[][] arr, int idx, int z, int o, int m, int n, Integer[][][] dp) {

        if (idx == arr.length && (z <= m && o <= n))
            return dp[idx][z][o] = 0; // identification mark

        if (dp[idx][z][o] != null)
            return dp[idx][z][o];
        int include = 0, exclude = 0;

        if (z + arr[idx][0] <= m && o + arr[idx][1] <= n)
            include = 1 + knapSack_understandable(arr, idx + 1, z + arr[idx][0], o + arr[idx][1], m, n, dp);

        exclude = Math.max(include, knapSack_understandable(arr, idx + 1, z, o, m, n, dp));

        return dp[idx][z][o] = Math.max(include, exclude);
    }

    private int[] countZeroAndOne(String s) {
        int z = 0;
        for (char ch : s.toCharArray())
            if (ch == '0')
                z++;

        return new int[] { z, s.length() - z };
    }
}
