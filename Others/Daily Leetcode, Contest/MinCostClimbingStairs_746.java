class MinCostClimbingStairs_746 {
    final int MAX = Integer.MAX_VALUE, default_val = MAX -1;
    int[] dp;
    public int minCostClimbingStairs(int[] cost) {
        //start from last index and try reaching 0 or 1st idex get min from them
        // int n = cost.length;
        // dp = new int[n+1];
        // Arrays.fill(dp,MAX);
        // return solve(cost,n);
        // return tab(cost);
        return optimization(cost);
    }
    
    
    //mem
    private int solve(int[] arr, int idx){
        if(idx < 0) return default_val;
        if(dp[idx] != MAX) return dp[idx];
        
        if(idx == 0 || idx ==1) return dp[idx] = arr[idx];
        
        // int ans = default_val;
        int selfCost = idx<arr.length ? arr[idx] : 0;
        int oneStep = solve(arr, idx-1);
        int twoStep = solve(arr,idx-2);
        int ans = Math.min(oneStep,twoStep);
        // if(oneStep != default_val) ans = Math.min(ans,selfCost + oneStep);
        // if(twoStep != default_val) ans = Math.min(ans, selfCost + twoStep);
        return dp[idx] = ans + selfCost;
    }
    
    private int tab(int[] arr){
        int n = arr.length;
        dp = new int[n];
        // if(idx == 0 || idx ==1)  dp[idx] = arr[idx];
        dp[0] = arr[0];
        dp[1] = arr[1];
        
        for(int i=2;i<n;i++){
            //ltor
           dp[i] = Math.min(dp[i-1],dp[i-2]) + arr[i];
        }
       return Math.min(dp[n-1],dp[n-2]);
    }
    
    private int optimization(int[] arr){
        int minus1 = arr[1], minus2 = arr[0], n = arr.length;
        for(int i=2;i<n;i++){
            int min = Math.min(minus1,minus2) + arr[i];
            //forward the values
            // minus1 = minus2; // dp[i-1] = dpi[i-2];
            // minus2 = min;// dp[i-2] = dp[i]
            minus2 = minus1;
            minus1 = min;
        }
        
        return Math.min(minus1, minus2);
    }
}