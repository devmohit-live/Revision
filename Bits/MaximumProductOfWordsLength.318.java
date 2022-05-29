class MaximumProductOfWordsLength.318{
     Set<Character> set;
    public int maxProduct(String[] words) {
        // return brute(words); 
        return approach1_bits(words);
    }
    
        
        //time: (n*k) : mask + n*n : calculaation of max : , totoal n*n
        //space : O(n)
    private int approach1_bits(String[] words){
        int n = words.length, max = 0;
        
        int[] strings_masks = new int[n];
        //max xhar val = 25(z) => 1<<25 = 33554432 which is very less than Integer.max(32bit) hence can use bitsmask of int
        
        
        //calculate the masks
        for(int i=0;i<n;i++)
            for(char ch : words[i].toCharArray()) strings_masks[i] |= ( 1<< (ch-'a') );
        
        // check for valid string : s1&s2 == 0 : no common words
        for(int i=0;i<n;i++) for(int j=i+1;j<n;j++){
            if( (strings_masks[i] & strings_masks[j]) == 0 ) max = Math.max(max, words[i].length() * words[j].length() );
        }
        
        
        return max;
      
    }
    
    
    // O(n*n)(lopp) *O(2*maxlen)(len odf max string : for checking validity) =>O(n*n*k)
    private int brute(String[] words){
        int ans = 0, n = words.length;
        
        for(int i=0;i<n;i++) for(int j=i+1;j<n;j++){
            int l1 = words[i].length(), l2 = words[j].length();
            if( areStringsValid(words[i],words[j]) ){
                ans = Math.max(ans,(l1*l2) );
            }
        }
        
        return ans;
    }
    
    private boolean areStringsValid(String a, String b){
        set = new HashSet<>();
        
        for(char ch : a.toCharArray()) set.add(ch);
        
        for(char ch : b.toCharArray()) if(set.contains(ch)) return false;
        
        return true;
    }
}