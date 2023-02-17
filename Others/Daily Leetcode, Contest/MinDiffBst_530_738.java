public class MinDiffBst_530_738 {
//    https://leetcode.com/problems/minimum-distance-between-bst-nodes/
//    https://leetcode.com/problems/minimum-absolute-difference-in-bst/

    public int minDiffInBST(TreeNode root) {
        return getMinimumDifference1(root);
    }
    // 1.in-order dfs

    public int getMinimumDifference1(TreeNode root) {
        if(root==null)return 0;
        int[] min=new int[1];
        min[0]=Integer.MAX_VALUE;
        dfs(root,null,min);
        return min[0];
    }

    public TreeNode dfs(TreeNode root,TreeNode pre,int[] min) {
        if(root==null)return pre;
        pre=dfs(root.left,pre,min);
        if(pre!=null){
            min[0]=Math.min(min[0],root.val-pre.val);
        }
        pre=root;
        pre=dfs(root.right,pre,min);
        return pre;
    }

    // 2.in-order iterator

    public int getMinimumDifference2(TreeNode root) {
        if(root==null)return 0;
        Stack<TreeNode> stack=new Stack<>();
        int min=Integer.MAX_VALUE;
        TreeNode pre=null;
        while(root!=null||!stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(pre!=null){
                min=Math.min(min,root.val-pre.val);
            }
            pre=root;
            root=root.right;
        }
        return min;
    }

    // 3.morris

    public int getMinimumDifference3(TreeNode root) {
        // can br improved
        if(root==null)return 0;
        int min=Integer.MAX_VALUE;
        TreeNode pre=null;
        while(root!=null){
            if(root.left==null){
                if(pre!=null){
                    min=Math.min(min,root.val-pre.val);
                }
                pre=root;
                root=root.right;
            }else{
                TreeNode maxRight=root.left;
                while(maxRight.right!=null&&maxRight.right!=root){
                    maxRight=maxRight.right;
                }
                if(maxRight.right==null){
                    maxRight.right=root;
                    root=root.left;
                }else{
                    if(pre!=null){
                        min=Math.min(min,root.val-pre.val);
                    }
                    pre=root;
                    maxRight.right=null;
                    root=root.right;
                }
            }
        }
        return min;
    }
}