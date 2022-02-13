import java.util.Arrays;

public class Contest280 {
    // Question 1: 2169. Count Operations to Obtain Zero
    // Approach 1 : Better O(1) space
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while(num1>0 && num2>0){
            if(num1 == num2) return ans+1;
            if(num1>num2) num1-=num2;
            else num2-=num1;
            ans++;
        }
        
        return ans;
    }

    // Approach 2 : Recursion :
    public int countOperations2(int num1, int num2) {
        if (num1 == 0 || num2 == 0)
            return 0;
        if (num1 < num2)
            return countOperations(num2, num1);
        return 1 + countOperations(num1 - num2, num2);
    }

    //Question 2: 2170. Minimum Operations to Make the Array Alternating
     public int minimumOperations(int[] nums) {
        
        int[] freqOdd = new int[(int)1e5 +1];
        int[] freqEven = new int[(int)1e5 +1];
        int n = nums.length, maxel = 0;
        for(int i=0;i<n;i++){
            maxel = Math.max(maxel,nums[i]);
            if((i&1)!=0){
                //odd
                freqOdd[nums[i]]++;
            }else{
                 freqEven[nums[i]]++;
            }
        }
    
        int firstMaxEven = 0, secondMaxEven = 0;
        int firstmaxevenFreq = 0, secondmaxevenFreq = 0;
        int firstMaxOdd = 0, secondMaxOdd = 0;
        int firstmaxoddFreq = 0, secondmaxoddFreq = 0;
           
        for(int i = 1 ;i<=maxel;i++){
            
            //even
            if(freqEven[i] > firstmaxevenFreq){
                 secondMaxEven = firstMaxEven;
                secondmaxevenFreq = firstmaxevenFreq; 
                firstMaxEven = i;
                firstmaxevenFreq = freqEven[i];;
            }else if(freqEven[i] > secondmaxevenFreq){
                secondMaxEven = i;
                secondmaxevenFreq = freqEven[i]; 
            }
            
             //odd
             if(freqOdd[i] > firstmaxoddFreq){
                secondMaxOdd = firstMaxOdd;
                secondmaxoddFreq = firstmaxoddFreq; 
                firstMaxOdd = i;
                firstmaxoddFreq = freqOdd[i];;
            }else if(freqOdd[i] > secondmaxoddFreq){
                secondMaxOdd = i;
                secondmaxoddFreq = freqOdd[i]; 
            }
            
        }
        
        
        if(firstMaxEven!=firstMaxOdd){
            return n - (freqEven[firstMaxEven] + freqOdd[firstMaxOdd]);
        }
       
        
        int choice1 = n - (freqEven[firstMaxEven] + freqOdd[secondMaxOdd]);
        int choice2 = n - (freqEven[secondMaxEven] + freqOdd[firstMaxOdd]);
        return Math.min(choice1,choice2);
        
    }

    // Question 3 : 2171. Removing Minimum Number of Magic Beans
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        int n = beans.length; // taking int causes a problem
        
        long ans = Long.MAX_VALUE,sum = 0;
        for(int el: beans) sum+=el;
        
        for(int i=0;i<n;i++){
            long changes = sum - ((n-i*1l)*beans[i]); // all number are onverted to b=beasn[i];
            
            ans = Math.min(ans,changes);
        }
        
        return ans;
    }

}
