class CoinChange{
        public int coinChange(int[] arr, int Tar) {
       // return permutation(arr,Tar);
       return combination1d(arr,Tar);
    }
    
    private int permutaions(int[] arr, int Tar){
        int[] dp = new int[Tar+1];
        Arrays.fill(dp,(int)1e9);
        dp[0] = 0; //identification mark
        
        for(int tar = 1;tar<=Tar;tar++){
            //for every target use all the coins : creates permutaion
            for(int coin: arr){
                if(tar-coin >= 0)
                    dp[tar] = Math.min(dp[tar], dp[tar-coin]+1);
            }
        }
        
        return dp[Tar] == (int)1e9 ? -1 : dp[Tar];
    }
    
     private int combination1d(int[] arr, int Tar){
        int[] dp = new int[Tar+1];
        Arrays.fill(dp,(int)1e9);
        dp[0] = 0; //identification mark
        
         for(int coin: arr){
            //try to acheive all targets possible using a single coin : this data will be used by other subsequent coins
             // conbination
            for(int tar = 1;tar<=Tar;tar++){
          
                if(tar-coin >= 0)
                    dp[tar] = Math.min(dp[tar], dp[tar-coin]+1);
            }
        }
        
        return dp[Tar] == (int)1e9 ? -1 : dp[Tar];
    }
    
    
}