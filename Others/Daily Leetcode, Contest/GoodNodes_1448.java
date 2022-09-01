public class GoodNodes_1448 {
    private int ans;

    public int goodNodes(TreeNode root) {
        this.ans = 0;
        goodNodes(root, -(int) (1e5));
        return this.ans;
    }

    private void goodNodes(TreeNode root, int parentValue) {
        if (root == null)
            return;

        if (root.val >= parentValue)
            ans++;

        parentValue = Math.max(root.val, parentValue);

        goodNodes(root.left, parentValue);
        goodNodes(root.right, parentValue);

    }
}
