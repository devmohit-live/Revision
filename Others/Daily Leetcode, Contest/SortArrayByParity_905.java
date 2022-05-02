public class SortArrayByParity_905 {
     public int[] sortArrayByParity(int[] nums) {
        // return usingLinkedList(nums);
        // return usingComparator(nums);
        // return usingTwoPass(nums);
        return usingPartition(nums);
    }
    
    //nlogn
    private int[] usingComparator(int[] A){
        Integer[] arr = new Integer[A.length];
        for(int i=0;i<A.length;i++){
            arr[i] = A[i];
        }
        
        // Arrays.sort(arr,(a,b)->Integer.compare(a%2,b%2));
        //or
        Arrays.sort(arr,(a,b)->{
            int x = a%2, y = b%2;
            //even gives 0 ,odd gives 1
            return x-y;
        });
        for(int i=0;i<A.length;i++){
            A[i] = arr[i];
        }
        return A;
    }
    
    //on
    private int[] usingLinkedList(int[] nums){
      class Node{
          int val;
          Node next;
          Node(int val){
              this.val = val;
          }
      }
        Node even = new Node(-1), e = even;
        Node odd = new Node(-1), o = odd;
        
        for(int el: nums){
            if(el%2 == 0) {
                e.next = new Node(el);
                e = e.next;
            }
            else{
                o.next = new Node(el);
                o = o.next;
            }
        }
        even = even.next;
        odd = odd.next;
        
       int[] ans = new int[nums.length];
        int idx = 0;
        while(even != null){
            ans[idx++] = even.val;
            even = even.next;
        }
        while(odd != null){
            ans[idx++] = odd.val;
            odd = odd.next;
        }
        return ans;
    }
    
    //O(n) 
    private int[] usingTwoPass(int[] A){
        int idx = 0, n = A.length;
        int[] ans = new int[n];
        for(int el: A){
            if(el%2==0) ans[idx++] = el;
        }
        for(int el: A){
            if(el%2!=0) ans[idx++] = el;
        }
        
        return ans;
    }
    
    //O(n): Using quiclsort partiton technique
    private int[] usingPartition(int[] A){
        int a=-1,b=0, n= A.length;
        while(b<n){
            if(A[b]%2==0){
                swap(A,++a,b);
            }
            b++;
        }
        return A;
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    
}
