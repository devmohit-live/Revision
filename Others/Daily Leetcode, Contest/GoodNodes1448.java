/**
 * LC : GoodNodes1448
 */
public class GoodNodes1448 {
    // https://leetcode.com/problems/count-good-nodes-in-binary-tree/discuss/1646477/Simple-DFS-Solution-by-maintaining-max-value-encountered-in-the-path-or-Faster-than-98

    public int goodNodes(TreeNode root) {

        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        return traverse(root, root.val);
    }

    private int traverse(TreeNode root, int maxsf) {
        if (root == null)
            return 0;

        int ans = 0;

        maxsf = Math.max(maxsf, root.val);
        if (root.val >= maxsf)
            ans++;

        ans += traverse(root.left, maxsf);
        ans += traverse(root.right, maxsf);

        return ans;
    }

}