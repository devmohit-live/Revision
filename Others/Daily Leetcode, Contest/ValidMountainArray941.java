class ValidMountainArray941 {

    //Easy LC:941
    public boolean validMountainArray(int[] arr) {
       // take two people climb them from the opposite ends of mountains if they reach the same place(peak) then it is a valid mountain
        
        // remeber peak can't be starting and ending point 
        int  n=arr.length, i=0, j = n-1;
        if(n<=2) return false;
        
        while(i+1<n && arr[i]<arr[i+1]) i++;
        while(j-1>0 && arr[j-1]>arr[j]) j--;
        
        return i>0 && j<n-1 && i==j;
        
    }
}