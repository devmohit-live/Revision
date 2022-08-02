class MakeZero_2357 {
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
     for(int el : nums){
         if(el == 0) continue;
         set.add(el);
     }
        
        return set.size();
        
    }
}