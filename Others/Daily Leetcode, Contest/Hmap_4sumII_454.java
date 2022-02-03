class Hmap_4sumII_454{
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        
      int count =0;
      HashMap<Integer,Integer> map=new HashMap<>();
      
      for(int a: A){
        for(int b: B){
          map.put(a+b, map.getOrDefault(a+b,0) + 1);
        }
      }
      
      int target=0;
      
      for(int a: C){
        for(int b: D){
         count+= map.getOrDefault( target- (a+b) ,0);
        }
      }
      
      return count;
    }
}