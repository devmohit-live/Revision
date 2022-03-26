class BinaryTreeMaxPathSum_124 {
    class Pair{
        int rtn = 0; // straight path from root to node: leading to another straight path;
        int ntn = Integer.MIN_VALUE; // node to node path (start ans ends at any stage: store the max observed till now in the subtreee)
        
    }

    //Approach : pah : startigh line from node(any other node in it's subtree) to a root(current node)
    
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        
        return maxPathSum_(root).ntn; 
    }
    
    private Pair maxPathSum_(TreeNode root){
        if(root == null) return new Pair();
        Pair left = maxPathSum_(root.left);
        Pair right = maxPathSum_(root.right);
        //staright path
        int rtn = Math.max(left.rtn, right.rtn) + root.val;
        int upath = left.rtn + right.rtn + root.val;
        int ntn = getMaximum(rtn,upath,root.val, left.ntn , right.ntn);
        Pair myans = new Pair();
        myans.rtn = Math.max(rtn,root.val); // maybe for rtn only rootval is positive or greater than it's both subtree
        myans.ntn = ntn;
        
        return myans;
    }
    
    private int getMaximum(int... ar){
        int max = ar[0];
        for(int el : ar) max = Math.max(max,el);
        
        return max;
    }
    
}