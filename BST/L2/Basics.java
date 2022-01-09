public class Basics {
    // IN BST try to do eveything iteratively

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> la = new ArrayList<>();
        rootToNodePath(root, p, la);
        List<TreeNode> lb = new ArrayList<>();
        rootToNodePath(root, q, lb);
        int sa = 0, sb = 0;
        while (sa < la.size() && sb < lb.size() && la.get(sa).val == lb.get(sb).val) {
            sa++;
            sb++;
        }
        sa--;
        return la.get(sa);

    }

    private void rootToNodePath(TreeNode root, TreeNode node, List<TreeNode> list) {
        if (root == null)
            return;

        while (root != null) {
            list.add(root);
            if (root == node)
                return;
            else if (root.val < node.val)
                root = root.right;
            else
                root = root.left;

        }

        list.clear(); // not found clear list
        return;
    }
}
