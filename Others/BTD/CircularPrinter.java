public class CircularPrinter {
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {

        String str[] = { "BZA", "AZGB" }; // {4,13}
        for (String s : str)
            System.out.println(circularPrinter(s));

    }

    public static int circularPrinter(String s) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, MAX);
        return solve(s, 0, 'A', dp);
    }

    private static int solve(String s, int idx, char currPtr, int[] dp) {
        if (idx == s.length())
            return 0;
        if (dp[idx] != MAX)
            return dp[idx];

        int ans = MAX;
        char ch = s.charAt(idx);
        int clockWise = Math.abs(ch - currPtr);
        int anti = 26 - clockWise;
        int time = Math.min(clockWise, anti);
        ans = Math.min(ans, time + solve(s, idx + 1, ch, dp));
        return dp[idx] = ans;
    }
}
