class KDistantAway_863{
     public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        List<TreeNode> path = new ArrayList<>();
        n2rp(root,target,k,ans); // aPPROACH 2
        
        // //Approach 1 :
        // n2rp(root,target,path);
        // TreeNode prev = null;
        // for(int i=0;i<path.size();i++){
        //     int level = k - i;
        //     kDown(path.get(i),prev,level,ans);
        //     prev = path.get(i);
        // }
        return ans;
    }
    
    //Approach 1 : use n2rp and accumulate the nodes in the path
//     use that list to find down on each node with previos node as blocked node
     // TIme: O(h) +O(n))traversing * O(k)time => O(n*k)
    //Space: O(n) : store the treende for k down + recursive space O(h)
    
    private boolean n2rp(TreeNode root, TreeNode node,List<TreeNode>ans){
        if(root == null) return false;
        
        boolean res = root == node || n2rp(root.left,node,ans) || n2rp(root.right,node,ans);
        if(res) ans.add(root);
        return res;
    }
    
    //Approach 2 : Space Optimised : O(1) => with recursive space O(H)
    private int n2rp(TreeNode root , TreeNode tar, int k,List<Integer>ans){
        if(root == null) return -1;
        
        if(root == tar){
            kDown(root,null,k - 0,ans);
            return 1;
        }
        int left = n2rp(root.left,tar,k,ans);
        if(left!=-1){
            kDown(root, root.left, k-left,ans);
            return left+1;
        }
         int right = n2rp(root.right,tar,k,ans);
        if(right!=-1){
            kDown(root, root.right, k-right,ans);
            return right+1;
        }
        
        return -1;
    }
    
    private void kDown(TreeNode root,TreeNode blocked, int k,List<Integer>ans){
        if(root == null || k<0 || root == blocked) return ;
        if(k==0){
            ans.add(root.val);
            return ;
        }
            
        
        kDown(root.left,blocked,k-1,ans);
        kDown(root.right,blocked,k-1,ans);
    }
}