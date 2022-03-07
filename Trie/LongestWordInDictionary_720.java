class LongestWordInDictionary_720{
      class Trie{
        Trie[] children;
        String word;
        Trie(){
            this.children = new Trie[26];
            this.word = null;
        }
    }
    
    public String longestWord(String[] words) {
        Trie root = new Trie();
        String[] ans = new String[1];
        ans[0] = ""; //intialize to avoid null.length()
        
        for(String word: words) insert(root,word);
        
        //try of every nodes
        for(int i=0;i<26;i++){
            Trie ptr = root.children[i];
            if(ptr!=null) dfs(ptr,ans);
        }
        return ans[0];
    }
    
    private void insert(Trie root, String word){
        Trie ptr = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if(ptr.children[idx] == null) ptr.children[idx] = new Trie();
            ptr = ptr.children[idx];
        }
        ptr.word = word;
    }
    
    private void dfs(Trie ptr,String[] ans){
        if(ptr.word ==null) return; // not an  ending (non continous characters)
        
        if(ans[0].length() <  ptr.word.length()){
            ans[0] = ptr.word;
            // System.out.println(ans);
        }
        
        for(int i=0;i<26;i++){  
            if(ptr.children[i]!=null) dfs(ptr.children[i],ans);
        }
    }
    
}