package Questions.Trees;

public class BurnTree {
    // based on k level(in recursion with node2root path) down with node 2 root path
    int max = 0;

    public int solve(TreeNode A, int B) {
        burnTree(A, B);
        return max;
    }

    private int burnTree(TreeNode root, int fire) {
        if (root == null)
            return -1;

        if (root.val == fire) {
            burn(root, null, 0);
            return 1;
        }

        // lerft
        int leftTime = burnTree(root.left, fire);
        if (leftTime != -1) {
            burn(root, root.left, leftTime);
            return leftTime + 1;
        }
        int rightTime = burnTree(root.right, fire);
        if (rightTime != -1) {
            burn(root, root.right, rightTime);
            return rightTime + 1;
        }

        return -1;
    }

    private void burn(TreeNode root, TreeNode blocked, int time) {
        if (root == null || root == blocked)
            return;

        max = Math.max(max, time);
        burn(root.left, blocked, time + 1);
        burn(root.right, blocked, time + 1);
    }
}
