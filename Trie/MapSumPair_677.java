//Leetcode 677

class MapSum {
    private class Trie{
        Trie[] children;
        int prefixSum; // path sum till node
        boolean isEnd;// redundant not needed
        Trie(){
            this.children = new Trie[26];
            this.prefixSum = 0;
            this.isEnd = false;
        }
    }
    private HashMap<String , Integer> map; // to store string: val mapping
    private Trie root;
    
    public MapSum() {
        this.root = new Trie();
        this.map = new HashMap<>();
    }
    
    private void add(String word, int val){
        Trie ptr = this.root;
        for(int i= 0;i<word.length();i++){
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if(ptr.children[idx] == null) ptr.children[idx] = new Trie();
            
            ptr.prefixSum += val; // the the net value to the path
            
            ptr = ptr.children[idx];
        }
        
        ptr.prefixSum += val; // add the net value to the end node too
        ptr.isEnd = true;
    }
    
    public void insert(String key, int val) {
        int ov = this.map.getOrDefault(key,0);
        // key may be inserted again with new value so we just have to add the difference in the trie path(as previously that path have some value)
        // and that value is not just with this string only mayeb another string with same prefix have also contributed in prefix sun so we can't just directly update teh prefix path value to a constant
        
        int impact = val - ov; 
        this.map.put(key,val);
        add(key,impact);
    }
    
    public int sum(String prefix) {
        return startsWith(prefix);
    }
    
    private int startsWith(String word){
        Trie ptr = this.root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if(ptr.children[idx] == null) return 0;
            
            ptr = ptr.children[idx];
        }
        
        return ptr.prefixSum;
    }
}