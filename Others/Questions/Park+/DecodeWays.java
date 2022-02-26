public class DecodeWays {
    //lc 91 
    public int numDecodings(String s) {
        char[] arr = s.toCharArray();

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);

        return solve(arr, 0, dp);
    }

    private int solve(char[] arr, int idx, int[] dp) {
        if (idx == arr.length) {
            return 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        int count = 0;
        char a = arr[idx];

        if (a != '0') {
            count += solve(arr, idx + 1, dp);
        }

        if (idx + 1 < arr.length && a != '0') {
            char b = arr[idx + 1];
            int num = ((a - '0') * 10) + (b - '0');
            if (num <= 26)
                count += solve(arr, idx + 2, dp);
        }

        return dp[idx] = count;

    }
}
