public class SumOfEvenNumberAfterQueries_985 {
     int esum = 0;
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length, qn = queries.length;
        if(n==0 || qn == 0) return new int[0];
        
        // return usingSet(nums, queries, n , qn);
        
        return contantSpace(nums, queries);
    
    }
    
    //Time : O(n), Space: O(1)
    private int[] contantSpace(int[] arr, int[][] queries){
        int esum = 0, i = 0;
        for(int el : arr) if((el&1)==0) esum+=el;
        
        int[] ans = new int[queries.length];
        for(int[] q: queries){
            int val = q[0], idx = q[1];
            int prev = arr[idx];
            if((prev&1)==0) esum-=prev;
            
            arr[idx]+=val;
            
            //now check if new values is odd or even
            if((arr[idx]&1)==0) esum+=arr[idx];
            
            ans[i++] = esum;
        }
        
        return ans;
    }
    
    
//     Time: O(n), Space: O(n)
    private int[] usingSet(int[] nums, int[][] queries, int n , int qn){
        int idx = 0;
        Set<Integer>evenIdx = new HashSet<>();
        for(int i=0;i<n;i++) {
            if((nums[i]&1) == 0) {
                evenIdx.add(i);
                esum+= nums[i];
            }
            // else osum += nums[i];
        }
        
        
        int[] ans = new int[qn];
        for(int[] q: queries){
             ans[idx++] = checkOperation(nums, evenIdx, q);
        }
        
        return ans;
    }
    
    private int checkOperation(int[] arr, Set<Integer> set, int[] q){
        int val = q[0], idx = q[1];
        int prev = arr[idx], changedVal = prev + val;
        
        boolean isEven = (changedVal&1) == 0;
        if(set.contains(idx)){ // previous val was even
            // System.out.println("Set contains idx : previously was even : "+ prev);
            if(isEven){ // still even
                // System.out.println("Now becomes even : "+ changedVal);
                //still even
                esum += val; // add the changes in previous val
            }else{
                // System.out.println("Now becomes odd : "+ changedVal);
                set.remove(idx);
                esum-=prev; // takes away his prev value;
                
            }
            // System.out.println("------------------");
        }else{ // previous values was odd
            // System.out.println("Set not contains idx : previously was odd : "+ prev);
            if(isEven){
                 // System.out.println("Now becomes even : "+ changedVal);
                set.add(idx);
                esum+=changedVal;
            }else{// still odd : no changes in even sum
                 // System.out.println("Now becomes odd : "+ changedVal);
            }
             // System.out.println("------------------");
        }
        // System.out.println(esum);
        arr[idx] = changedVal;
        return esum;
    }
}
