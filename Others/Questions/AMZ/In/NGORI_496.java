class NGORI_496{
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        //find ngor of nums2 array first
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for(int i=0;i<nums2.length;i++){
            while(st.peek()!=-1 && nums2[st.peek()] < nums2[i]){
                map.put(nums2[st.pop()], i); // nmber: index of it's ngor
            }
            st.push(i);
        }
        
        //find nums1 el in map and we have their ngor already
        // System.out.println(map); //map only contains the el whose ngor exists
        int[] ans = new int[nums1.length];
     
        for(int i=0;i<nums1.length;i++){
            int el = nums1[i];
            if(map.containsKey(el)){
                int idxOfngor = map.get(el);
                int ngor = nums2[idxOfngor];
                ans[i] = ngor;
            }else{
                ans[i] = -1;
            }
          
        }
        
        return ans;
    }
}