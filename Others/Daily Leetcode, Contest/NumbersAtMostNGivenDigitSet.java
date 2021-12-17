class NumbersAtMostNGivenDigitSet{
     public int atMostNGivenDigitSet(String[] digits, int num) {
        // count the number which can be obtained without any restriction : x, xx, xxx, xxxx, xxx...n-1 time
        // 1 digit, 2 digit , n-1 digit numbers from digits <= n
        String number = ""+num;
        int count = 0, len = digits.length, n = number.length();
        for(int i=1;i<number.length();i++) count += Math.pow(len,i);
        
        // count the numbers which are <=n now we have some number in a pattern like 
//         5xxxx, 51xxx,53xxx, 531xx,533xx , 5331x,53331,53332,53333,53334,5335
//         when digit n is 5335
        int i =0; // going to each of the digits in number n
        while(i<n){
            int j = 0;
            
            while(j<len && Integer.valueOf(digits[j]) < number.charAt(i)-'0' ){
                count += Math.pow(len, n - i - 1);
                j++;
            }
            
            if(j == len || Integer.valueOf(digits[j]) > number.charAt(i)-'0' ) break;
            
            i++;
            
        }
        
        if(i == n)
            count++;
        
        
        return count;
     
    }
}
}