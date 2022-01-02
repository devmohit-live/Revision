class MusicPairs{
     public int numPairsDivisibleBy60(int[] time) {
        // complemetnt , count of complement
      HashMap<Integer,Integer> map = new HashMap<>();
        int ans = 0, n = time.length;
        if(n<=1) return 0;
        // whatever bigger numbber it is compliment i < 60 as we are doing mod
        // my current element after comsideration can pair with someone else as their are no restrictions so it can also act as someone's else compliment
        
        // % prop: (a+b)%m => (a%m + b%m)%m
        for(int i=0;i<n;i++){
            int mytime = time[i] % 60;
            int complement = (60 - mytime) % 60;
            ans += map.getOrDefault(complement, 0);
            
            map.put(mytime,map.getOrDefault(mytime,0)+1);
        }
        
        return ans;
    }
}