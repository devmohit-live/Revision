class Subset{
    //Subset Leetcode :78
      public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
       List<Integer> small = new ArrayList<>();
       int noOfSubsets =  createSubsets(nums,0,small,ans);
      return ans;
      
    }
  
  int createSubsets(int[] arr, int idx, List<Integer> small, List<List<Integer>> ans){
    ans.add(new ArrayList<Integer>(small));
    
    if(idx==arr.length){
      return 1;
    }
    
    int count = 0;
    for(int i=idx;i<arr.length;i++){
      small.add(arr[i]);
      count+= createSubsets(arr,i+1,small,ans);
      small.remove(small.size()-1);
    }
    
    
    return count;
  }
  
  
    
    
    //Subset2 Leetcode:90
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        int count = subsets(nums, 0, small, ans);

        return ans;
    }

    private int subsets(int[] arr, int idx, List<Integer> small, List<List<Integer>> ans) {
        List<Integer> base = new ArrayList<>(small);
        ans.add(base);

        if (idx == arr.length) {
            return 1;
        }

        int count = 0;
        int prev = -11;

        for (int i = idx; i < arr.length; i++) {
            if (prev != arr[i]) {
                small.add(arr[i]);
                count += subsets(arr, i + 1, small, ans);
                small.remove(small.size() - 1);
            }
            prev = arr[i];

        }
        return count;

    }

}