class StoneGameIV_1510 {
    public boolean winnerSquareGame(int n) {
        // Boolean[]dp = new Boolean[n+1];
        boolean[] dp = new boolean[n + 1];
        // int[] dp = new int[n+1];
        // Arrays.fill(dp,-1);
        // -1 : not evaluated yet, 0: false, 1: true
        // return (winnerSquareGame(n,dp) ==1) ? true : false;
        return winnerSquareGame(n, dp);
    }

    private boolean winnerSquareGame(int n, Boolean[] dp) {
        if (dp[n] != null)
            return dp[n];
        if (n == 0)
            return dp[0] = false;
        if (n == 1)
            return dp[1] = true;

        // find the max possible square we can remove
        int maxsqrt = (int) Math.sqrt(n);

        // try every possible squares to find the optimal one
        for (int i = 1; i <= maxsqrt; i++) {
            int newN = n - i * i;
            // remember next call is for the opponents turn : if he loses then we win
            boolean recAns = winnerSquareGame(newN, dp);
            if (recAns == false) // opponent loses
                return dp[n] = true; // we win
        }
        // we come out of the loop that mean opponent is not losing in any of the above
        // square numbers
        return dp[n] = false;

    }

    // mem using int[]
    private int winnerSquareGameMem(int n, int[] dp) {
        if (dp[n] != -1)
            return dp[n];
        if (n == 0)
            return dp[0] = 0;
        if (n == 1)
            return dp[1] = 1;

        // find the max possible square we can remove
        int maxsqrt = (int) Math.sqrt(n);

        // try every possible squares to find the optimal one
        for (int i = 1; i <= maxsqrt; i++) {
            int newN = n - i * i;
            // remember next call is for the opponents turn : if he loses then we win
            int recAns = winnerSquareGameMem(newN, dp);
            if (recAns == 0) // opponent loses
                return dp[n] = 1; // we win
        }
        // we come out of the loop that mean opponent is not losing in any of the above
        // square numbers
        return dp[n] = 0;

    }

    // tabulation
    public boolean winnerSquareGame(int N, boolean[] dp) {
        dp[0] = false;
        dp[1] = true;
        boolean found = false;

        for (int n = 2; n <= N; n++) {
            int maxsqrt = (int) Math.sqrt(n);
            // try every possible squares to find the optimal one
            for (int i = 1; i <= maxsqrt; i++) {
                int newN = n - i * i;
                // remember next call is for the opponents turn : if he loses then we win
                if (dp[newN] == false) {
                    dp[n] = true; // we win
                    found = true;
                    break; // found a way to choose a square no need to chek for others
                    // continue;
                } // opponent loses

            }
            // we come out of the loop that mean opponent is not losing in any of the above
            // square numbers
            if (!found)
                dp[n] = false;
        }

        return dp[N];
    }
}