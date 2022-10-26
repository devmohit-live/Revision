class ReversePairs_493 {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0 , nums.length-1);
    }
    
    //nlong : Merge Sort : reverse path : merging on the way : similar to count inversion
    private int mergeSort(int[] arr, int lo, int hi){
        if(lo>=hi) return 0;
        
        int mid = lo + ((hi-lo)>>1);
        int count = 0;
        
        count+= mergeSort(arr, lo, mid);
        count+= mergeSort(arr, mid+1, hi);
        count+= merge(arr, lo, mid, hi);
        
        return count;
    }
    
    
    private int merge(int[] arr, int lo, int mid, int hi){
        int n = hi-lo+1, idx = 0, i = lo, j = mid+1, count = 0;
        int[] tmp = new int[n];
    
         //count pairs
        while(i<=mid){
            while(j<=hi && arr[i] > 2l * arr[j]) j++;
            count+=(j-(mid+1)); //mid+1 => start point from j
            i++;
        }
        
        i= lo;
        j = mid+1;
       
        //merge
        while(i<=mid && j<=hi){
            if(arr[i] <= arr[j]) tmp[idx++] = arr[i++];
            else tmp[idx++] = arr[j++];
        }
        
        while(i<=mid) tmp[idx++] = arr[i++];
        while(j<=hi) tmp[idx++] = arr[j++];
        
        // for(int itr = lo; itr <= hi; itr++) arr[itr] = tmp[itr-lo];
        // System.arraycopy(source_arr, sourcePos, dest_arr,destPos, len);
        System.arraycopy(tmp, 0, arr, lo, hi-lo+1);
        return count;
    }
    
    //brute : O(n*n)
    
    private int brute(int[] arr){
        int count = 0;
        final int N = arr.length;
        if(N == 0) return count;
        
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if((long)arr[i] > 2L * arr[j]) count++;        
            }
        }
        
        return count;
    }
    
}