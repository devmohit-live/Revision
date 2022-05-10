import java.util.Arrays;
class CountSortedVowelString_1641{
    // similar to raget sum where repetion is allowed
    char[] s = {'a','e','i','o','u'};
    
    public int countVowelStrings(int n) {
        int[][] dp = new int[s.length+1][n+1];
        for(int[] d: dp) Arrays.fill(d,-1);
        return combinations(s,0,n,dp);
    }
    private int combinations(char[] arr, int idx, int k, int[][] dp){
               
        if(idx == arr.length || k == 0){
           if(k==0){
               
               return dp[idx][k] = 1;
           }
            return dp[idx][k] = 0;
        }
        
        if(dp[idx][k] != -1) return dp[idx][k];
        
        
        int count = 0;
        for(int i=idx;i<arr.length;i++){
            count += combinations(arr,i,k-1,dp);
        }
        
        return dp[idx][k] = count;
    }


    //Using Maths 
    // https://leetcode.com/problems/count-sorted-vowel-strings/discuss/2027881/Vowels-count-simple-math-faster
    public int countVowelStrings2(int n) {
        int startrsWithA = 1, startrsWithE = 1, startrsWithI = 1, startrsWithO = 1, startrsWithU = 1;
        if (n == 0)
            return 0;
        // FIrst way
        while (--n > 0) {
            startrsWithO += startrsWithU;
            startrsWithI += startrsWithO;
            startrsWithE += startrsWithI;
            startrsWithA += startrsWithE;
        }

        return startrsWithA + startrsWithE + startrsWithI + startrsWithO + startrsWithU;
        /*
         * { //Second way create an array int[] arr = new int[5]; //aeiou
         * Arrays.fill(arr,1); while(--n>0){ for(int i=3;i>=0;i--){ arr[i] += arr[i+1];
         * } }
         * 
         * int sum =0; for( int el: arr) sum+=el; return sum; }
         * 
         */

    }

}