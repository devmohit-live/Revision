public class SumRoottoLeafBinary1022{
    public int sumRootToLeaf(TreeNode root) {
        if(root == null) return 0;
        return dfs(root,0);
        
    }
    private int dfs(TreeNode root, int ans){
        if(root == null) return 0;
        if(root.left == null && root.right==null){
            int val =  ((ans<<1) | root.val);
            return val;
        }
        int sum = 0;
        ans = ((ans<<1) | root.val);
        sum+=dfs(root.left,ans);
        sum+=dfs(root.right,ans);
        return sum;
    }
}