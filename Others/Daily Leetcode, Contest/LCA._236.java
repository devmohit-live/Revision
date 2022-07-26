/**
 * LCA._236
 */
public class LCA._236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = new TreeNode();
        lca(root,p,q,ans);
        return ans.left;
    }

    private boolean lca(TreeNode root, TreeNode p, TreeNode q, TreeNode ans) {
        if (root == null)
            return false;
        boolean self = false;
        if (root == p || root == q) {
            self = true;
        }
        boolean left = lca(root.left, p, q, ans);
        boolean right = lca(root.right, p, q, ans);

        if ((left & right) || (left & self) || (self & right)) {
            ans.left = root;
        }

        return left || self || right;

    }
}