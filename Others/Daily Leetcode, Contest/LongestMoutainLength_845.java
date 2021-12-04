package Daily Leetcode, Contest;

public class LongestMoutainLength_845 {
     public int longestMountain(int[] arr) {
       int n = arr.length, left = -1, right = -1, max = 0;
        
        if(n < 3) return max;
        
        for(int i=0;i<n;i++){
            int leftval = i-1>=0 ? arr[i-1] : Integer.MAX_VALUE;
            int rightval = i+1 < n ? arr[i+1] : Integer.MAX_VALUE;
            
            if(leftval< arr[i] && arr[i]> rightval){
                // we have found a peak
                left = i-1;
                right = i+1;
                
                // length of the mountins from both the ends
                while(left > 0 &&  arr[left] > arr[left-1] ) left--;
                while(right +1 < n &&  arr[right] > arr[right+1] ) right++;
                
                // there can be other peaks in the array
                max = Math.max(max, right - left +1);                
                
            }
            
        }
        
        return max;        
    }
}
