public class FlattenBTtoList {
    //LC 114
    TreeNode next = null;

    // postorder but right call is made first as we have to make right oriented tree
    public void flatten(TreeNode root) {
        next = root;
        preorder(root);
    }

    private void preorder(TreeNode root) {
        if (root == null)
            return;

        next.right = root;
        root.left = null;
        root = next;

        // right oriented
        flatten(root.left);
        flatten(root.right);
    }
}
