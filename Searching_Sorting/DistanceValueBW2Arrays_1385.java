public class DistanceValueBW2Arrays_1385 {
     public int findTheDistanceValue(int[] A, int[] B, int d) {
        // return brute(A,B,d);
        return sortingAndBS(A,B,d);
        // return UsingTreeSetInsteadOfSorting(A,B,d);
    }
    //(m*n)
    private int brute(int[] arr1, int[] arr2, int d){
        int count = 0;
        for(int el: arr1){
            boolean isValid = true;
            for(int item : arr2){
                isValid &= Math.abs(el - item) > d ;
            }
            if(isValid) count++;
        }
        
        return count;
    }
    
    //Optimal(Without using an extra space)
    // mlogn => n is the size of second array
    //Approach Check if any element in B exists whic is <= d ditance from the current ele 
    private int sortingAndBS(int[] A , int[] B, int d){
        Arrays.sort(B);
        int count = 0;
        for(int el : A){
            boolean isValid = dDsitnaceCLoseElementBS(B,el,d);
            if(isValid) count++;
        }
        return count;
    }
    //bs
    private boolean dDsitnaceCLoseElementBS(int[] arr, int tar, int d){
        int n = arr.length, si = 0, ei = n-1;
        boolean found = false;
        
        while(si<=ei){
            int mid = si + (ei -si)/2;
            // if(tar == arr[mid] || Math.abs(arr[mid]) <= d ){ tar == arr[mid] is already defined by second condition
            
            if(Math.abs(arr[mid]-tar) <= d ){
                found = true;
                break;
            }else if(arr[mid] > tar) ei = mid - 1;
            else si = mid + 1;
        }
        
        return !found;
    }
    
    //Not that Optimal: worst : mnlognbcz at worst case tree.sunset is nlogn
    // Using java TreeSet : //add all of the numbers in treeSet => sorting order is maintained and searching in logn no need to do explicit bs
     public int UsingTreeSetInsteadOfSorting(int[] arr1, int[] arr2, int d) {
        TreeSet<Integer> tree = new TreeSet<>();
         //nlogn
         for(int num: arr2){
            tree.add(num); 
        }
         
        int distance = 0;
        for(int i = 0; i < arr1.length; i++){
           int left = arr1[i] - d;
           int right = arr1[i] + d;
            
// Tree.Subset(inclusive,exclusivr) => klogp => k is the size of redblack tree p is no of elements in retruned subset(that lies in rangel iclusiveNo to exclusiveNo)
            // worst case all number lies in here klogk
            // in this case k is n => nlogn
           Set<Integer> set = tree.subSet(left, right+1);
           if(set.isEmpty()){
             distance++;
           }
        }
      return distance;
    }
}
