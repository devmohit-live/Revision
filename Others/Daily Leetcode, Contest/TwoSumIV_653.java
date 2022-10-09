public class TwoSumIV_653 {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false;
        List<Integer> nodes = new ArrayList<>();
        inorder(root, nodes);
        int i = 0, j = nodes.size() - 1;
        while (i < j) {
            if (nodes.get(i) + nodes.get(j) == k)
                return true;
            else if (nodes.get(i) + nodes.get(j) > k)
                j--;
            else
                i++;
        }

        return false;
    }

    private void inorder(TreeNode root, List<Integer> nodes) {
        if (root == null)
            return;

        inorder(root.left, nodes);
        nodes.add(root.val);
        inorder(root.right, nodes);

    }

    // Using HashSet
    public boolean findTarget2(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}
