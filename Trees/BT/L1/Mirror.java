class Mirror {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        return areMirror(root, root);
    }

    // mathing data and shape too
    // time : O(n) , space: O(N) -> worst case
    private boolean areMirror(TreeNode n1, TreeNode n2) {
        // if both are null at same time
        if (n1 == null && n2 == null)
            return true;

        if (n1 == null || n2 == null)
            return false;

        return (n1.val == n2.val) && areMirror(n1.right, n2.left) && areMirror(n1.left, n2.right);

    }

    // iterative mathing values data
    // time : O(n) , space: O(N)
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null)
                continue;
            if (t1 == null || t2 == null)
                return false;
            if (t1.val != t2.val)
                return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}