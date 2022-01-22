class StoneGameIV_1510 {
    public boolean winnerSquareGame(int n) {
        Boolean[]dp = new Boolean[n+1];
        return winnerSquareGame(n,dp);
    }
    
    private boolean winnerSquareGame(int n,Boolean[] dp) {
        if(dp[n]!=null) return dp[n];
        if(n==0) return dp[0] = false;
        if(n==1) return dp[1] = true;
          
        // find the max possible square we can remove
        int maxsqrt = (int)Math.sqrt(n);
        
        //try every possible squares to find the optimal one
        for(int i=1;i<=maxsqrt;i++){
            int newN =  n - i*i;
            // remember next call is for the opponents turn : if he loses then we win
            boolean recAns = winnerSquareGame(newN, dp);
            if(recAns == false) // opponent loses
                return dp[n] = true; // we win
        }
        // we come out of the loop that mean opponent is not losing in any of the above square numbers
        return dp[n] = false;
        
    }
}