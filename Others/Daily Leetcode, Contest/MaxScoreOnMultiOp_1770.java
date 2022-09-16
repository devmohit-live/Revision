public class MaxScoreOnMultiOp_1770 {
    // int[][][] dp;
    int[][] dp;
    public int maximumScore(int[] arr, int[] multi) {
        int n = arr.length, m = multi.length;
        if(n == 0 || m==0) return 0;
        // dp[][][] = new int[n+1][n+1][m+1];
        // for(int[][] dd: dp) for(int[] d: dp) Arrays.fill(d,-1);
       // return maximumScore(arr,multi,0,n-1,0);
       
        
        //Optimized
        // dp = new int[n+1][m+1];
        // for(int[] d: dp) Arrays.fill(d,-1);
        // return maximumScore2(arr,multi,0,0);
        
        return iterative(arr,multi);
    }
    
    
//     TLE
    private int maximumScore(int[] arr, int[] multi, int i, int j, int idx){
        if(i>j) return 0;
        if(idx  == multi.length) return 0;
        
        int left = arr[i]*multi[idx] + maximumScore(arr,multi,i+1,j,idx+1);
        int right = arr[j]*multi[idx] + maximumScore(arr,multi,i,j-1,idx+1);
        return Math.max(left,right);
    }
    
    //TLE
    //j can be obtained as n - 1 -i
     private int maximumScore2(int[] arr, int[] multi, int i, int idx){
        int j = (arr.length-1) -(idx - i);
        if(i>j) return 0;
        if(idx  == multi.length) return 0;
         
        if(dp[i][idx]!= -1) return dp[i][idx];
        int left = arr[i]*multi[idx] + maximumScore2(arr,multi,i+1,idx+1);
         //approapriate j
        // not n-i-1 : as it may happen we only choose from start so j is still pointing to last : the correct index is obtaines (n-1) -(idx -i)
        int right = arr[j]*multi[idx] + maximumScore2(arr,multi,i,idx+1);
        return dp[i][idx] = Math.max(left,right);
    }
    
    //Iterative
    private int iterative(int[] arr, int[] multi){
         // For Right Pointer
        int n = arr.length;
        // Number of Operations
        int m = multi.length;
        int[][] dp = new int[m + 1][m + 1]; //both are m
        
        for (int i = 0; i <= m; i++)
            Arrays.fill(dp[i], 0);
        
        for (int op = m - 1; op >= 0; op--) {
            for (int left = op; left >= 0; left--) {
                int l = multi[op] * arr[left] + dp[op + 1][left + 1];
                int r = multi[op] * arr[n - 1 - (op - left)] + dp[op + 1][left];
                dp[op][left] = Math.max(l,r);
            }
        }
        
        return dp[0][0];
    }
}
