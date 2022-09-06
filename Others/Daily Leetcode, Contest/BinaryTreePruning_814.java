public class BinaryTreePruning_814 {
    public TreeNode pruneTree(TreeNode root) {
        // return doesSubtreeontainsOne(root) ? root : null;
        return better(root);
    }

    private boolean doesSubtreeontainsOne(TreeNode root) {
        if (root == null)
            return false;

        boolean leftSubtree = doesSubtreeontainsOne(root.left);
        boolean rightSubtree = doesSubtreeontainsOne(root.right);

        if (!leftSubtree)
            root.left = null;
        if (!rightSubtree)
            root.right = null;

        return root.val == 1 || leftSubtree || rightSubtree;
    }

    private TreeNode better(TreeNode root) {
        if (root == null)
            return root;

        root.left = better(root.left);
        root.right = better(root.right);

        if (root.val != 1 && root.left == null && root.right == null)
            return null;

        return root;
    }
}
