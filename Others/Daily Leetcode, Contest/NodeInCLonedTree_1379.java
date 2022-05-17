public class NodeInCLonedTree_1379 {
    // Find a Corresponding Node of a Binary Tree in a Clone of That Tree

    // Works even there are repeated values, as we are comparing refrences

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        TreeNode ans = new TreeNode(-1);
        find(original, cloned, target, ans);
        return ans.right;
    }

    private void find(TreeNode A, TreeNode B, TreeNode tar, TreeNode ans) {
        if (A == null || B == null || tar == null)
            return;

        if (A == tar) {
            ans.right = B;
            return;
        }

        find(A.left, B.left, tar, ans);
        find(A.right, B.right, tar, ans);

    }
}
