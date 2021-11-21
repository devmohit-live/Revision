public class Questions {

    //Leetcode 701
      public TreeNode insertIntoBSTRecursive(TreeNode root, int val) {
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }
        if(val < root.val) {
            if(root.left == null) root.left = new TreeNode(val);
            else insertIntoBST(root.left, val);
        } else {
            if(root.right == null) root.right = new TreeNode(val);
            else insertIntoBST(root.right, val);
        }
        return root;
    }
    
    
    // Iterative
     public TreeNode insertIntoBSTIterative(TreeNode root, int val){
        TreeNode node=new TreeNode(val);
        if(root==null)return node;

        TreeNode curr=root;

        while(curr!=null){
            TreeNode prev=curr;
            // move towards leaf while valid
            if(curr.val>val)
                curr=curr.left;
            else
                curr=curr.right;

            // on  reaching leaves
            if(curr==null){
                if(prev.val>val)
                    prev.left=node;
                else
                    prev.right=node;
                // break the loop
                break;
            }
        }

        return root;
     }
}
}
