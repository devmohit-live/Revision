class LargestNumber_179 {
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) return "";
        int n = nums.length;
        StringBuilder sb = new StringBuilder();
        //can;t apply custom sort on primitive types
        String[] arr = new String[n];
        for(int i=0;i<n;i++) arr[i] = ""+nums[i];
        
        Arrays.sort(arr, (a,b)->{
            String s1 = a+b; //wrt a 
            String s2 = b+a; //wrt b
            //have to return greater val 
            return s2.compareTo(s1);
        });
        // if all are 0's then the first char will be zero only
        if(arr[0].equals("0") ) return "0";
        
        for(String el : arr) sb.append(el);
        
        return sb.toString();
    }
}