public class PseudoPalindromicPathsInBT_1457 {
     private int[] freq;
    private int ans;
    public int pseudoPalindromicPaths (TreeNode root) {
        ans = 0;
        freq = new int[10];
        // solve(root, new StringBuilder());
        // System.out.println("*************************");
        // System.out.println(checkPalindrome("111"));
        // approach2(root, freq);
        approach3(root, 0);
        return ans;    
    }
    
    
    //Time : O(n), Space: O(10) : constant
    
    //Use freq array in itself to avoid extra work : check in postorder
    private void approach2(TreeNode root,int[] freq){
        if(root == null) return;
        freq[root.val]++;
        
        if(root.left!=null) approach2(root.left, freq);
        if(root.right!=null) approach2(root.right, freq);
        
        //postorder work
        
        if(root.left == null && root.right == null){
            int oddFreqElementsCount = 0; //atmost 1
            for(int i=0;i<10;i++){
                if((freq[i]&1)==1) oddFreqElementsCount++;
            }
            
            if(oddFreqElementsCount <= 1) ans++;
        }
        freq[root.val]--;
    }
    
    //Using int as bitmask for presence of odd freq of elemets
    private void approach3(TreeNode root, int freq){
        if(root==null) return;
        
        int currPos = (1<<root.val-1);
        //do xor : will unset if freq is even
        freq ^= currPos;
        
        approach3(root.left, freq);
        approach3(root.right, freq);

        if(root.left == null && root.right == null){
            //check if only 1 element have odd freq : only 1 bit is set
            if( (freq&(freq-1)) == 0) ans++;
            return;
        }
        
        freq ^= currPos; //undo changes
        
    }
    
    
    
    //O(n*n) : TLE
    private void solve(TreeNode root,StringBuilder sb){
        if(root == null){
            // boolean flag = checkPalindrome(sb.toString());
            // ans += flag ? 1 : 0;
            // System.out.println(sb.toString()+" "+flag+" "+ans);
            return;
        }
        
        if(root.left == null && root.right == null){
            sb.append(root.val);
            boolean flag = checkPalindrome(sb.toString());
            ans += flag ? 1 : 0;
            // System.out.println(sb.toString()+" "+flag+" "+ans);
            sb.deleteCharAt(sb.length()-1); //always remember to undo the changes
        }
        
        sb.append(root.val);
        
        solve(root.left,sb);
        solve(root.right,sb);
        
        sb.deleteCharAt(sb.length()-1);
    }
    
    //O(n)
    private boolean checkPalindrome(String s){
        char[] arr = s.toCharArray();
        freq = new int[10];
        for(char c : arr){
            freq[c-'0']++;
        }
        int n = arr.length;
        
        boolean midAlreadyPresent = false;
        
        for(int i=0;i<10;i++){
            if(freq[i]>0 && (freq[i]&1)==1 ){
                if(midAlreadyPresent) return false;
                midAlreadyPresent = true;
            }
        }
        
         if((n&1)==1){
             return midAlreadyPresent;
         }else{
             //n is even
             return !midAlreadyPresent;
         }
    }
}
