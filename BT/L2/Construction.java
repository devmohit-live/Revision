public class Construction {
    class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

        TreeNode() {
            this(-1);
        }
    }


    // NOTE:  for addition/deletion of nodes in tree work in postorder


    // Preorder traversal to BST
    int idx = 0;
    public TreeNode preOrderToBST(int[] preorder) {
        // fact: root in start : but not aable to identify left and right subtree
        // so we use concept of bst range left<=root<right
        idx = 0;
        return preOrderToBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode preOrderToBST(int[] preorder, int left_range, int right_range){
        if(idx>= preorder.length || preorder[idx]<left_range || preorder[idx]> right_range) return null;
        TreeNode root = new TreeNode(preorder[idx++]);
        root.left = preOrderToBST(preorder, left_range, root.val);
        root.right = preOrderToBST(preorder, root.val, right_range);
        return root;
    }

    // Inorder traversal to BST
    public TreeNode inorderToBst(int[] inorder) {
        // fact: root in mid : able to identify left and right subtree
        return inorderToBst(inorder, 0, inorder.length - 1);
    }

    private TreeNode inorderToBst(int[] inorder, int si, int ei) {
        if (si > ei)
            return null;
        int mid = si + (ei - si) / 2;

        TreeNode root = new TreeNode(inorder[mid]);

        root.left = inorderToBst(inorder, si, mid - 1);
        root.right = inorderToBst(inorder, mid + 1, ei);
        return root;
    }

    // PostOrder traversal to BST

}
