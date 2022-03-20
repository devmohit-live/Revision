public class SubtreeOfAnotherTree_572 {
    // O(k+p) => 2n(max) => O(n)

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return false;
        if (isSame(s, t))
            return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t); // O(k)
    }

    // O(p)
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null)
            return false;

        if (s.val != t.val)
            return false;

        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }

}
