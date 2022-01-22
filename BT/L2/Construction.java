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

    // Preorder traversal to BST
    // Inorder traversal to BST
    public TreeNode inorderToBst(int[] inorder) {
        // fact: root in mid : bale to identify left and right subtree
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
