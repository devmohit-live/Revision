class FindKClosestElement_658{ 
      public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        // return usingPQ(arr,k,x);
         return usingBinarySearch(arr,k,x);
    }


    private List<Integer> usingPQ(int[] arr, int k , int x){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            int d1 = Math.abs(a-x);
            int d2 = Math.abs(b-x);
            if(d1 == d2) return b-a; //remove b(greater)
            return d2 - d1;
        });
        
        
        for(int el : arr){
            pq.add(el);
            if(pq.size()>k) pq.remove();
        }
        
        List<Integer> ans = new ArrayList<>(k);
        while(!pq.isEmpty()) ans.add(pq.remove());
        Collections.sort(ans); // ans should be in sorted order in terms of values
        return ans;
        
    }
        private List<Integer> usingBinarySearch(int[] A, int k, int x){
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }
}