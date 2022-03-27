public class LCABST_235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = new TreeNode(-1);

        n2rp(root, p, q, res);

        return res.right;
    }

    // O(n) Approach
    private boolean n2rp(TreeNode root, TreeNode p, TreeNode q, TreeNode res) {
        if (root == null)
            return false;

        boolean self = false, left = false, right = false;
        if (root == p || root == q) {
            if (p == q) {
                left = true;
            }
            self = true;
        }

        left = n2rp(root.left, p, q, res);
        right = n2rp(root.right, p, q, res);

        if (self && left || self && right || left && right) {
            if (res.right == null)
                res.right = root;
            return true;
        }

        return self || left || right;

    }
}
