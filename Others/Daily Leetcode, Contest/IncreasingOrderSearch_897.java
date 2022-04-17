public class IncreasingOrderSearch_897 {
    // BST
     public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root,list);
        TreeNode ans = new TreeNode(-1);
        TreeNode curr = ans;
        for(TreeNode node : list){
            node.left = node.right = null;
            curr.right = node;
            curr = curr.right;
        }
        
        return ans.right;
        
    }
    
    private void inorder(TreeNode root, List<TreeNode>ans){
        if(root == null) return;
        
        inorder(root.left,ans);
        ans.add(root);
        inorder(root.right,ans);
    }
}
