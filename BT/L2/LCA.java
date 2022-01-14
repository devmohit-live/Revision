public class LCA {
    // LC : 236

    // 1 : Using extra sapce
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        List<TreeNode> plist = new ArrayList<>();
        n2rp(root, p, plist);
        List<TreeNode> qlist = new ArrayList<>();
        n2rp(root, q, qlist);

        int i = plist.size() - 1, j = qlist.size() - 1;
        while (i >= 0 && j >= 0 && plist.get(i) == qlist.get(j)) {
            i--;
            j--;
        }
        i++;
        return plist.get(i);

    }

    private boolean n2rp(TreeNode root, TreeNode node, List<TreeNode> ans) {
        if (root == null)
            return false;
        if (root == node) {
            ans.add(node);
            return true;
        }

        boolean found = n2rp(root.left, node, ans) || n2rp(root.right, node, ans);
        if (found) {
            ans.add(root);
            return true;
        }
        return false;
    }

    // 2: Using only recursive space
    public TreeNode lowestCommonAncestorBetter(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = new TreeNode();
        lca(root, p, q, ans);// sending ans instead of making it global
        return ans.left; // refrece game:
    }

    // Memory Management : when we pass arr[], Arraylist ,class basically we pass
    // address
    // on chaging something / some attribute ex: arr[i],arr.set(i,x), class.a=x, we
    // are basically goinging inside that address space and making changes to that
    // so that it remains intact throught the recursion calls

    // but setting somthing like arr = new int[5], will temporarly changes the
    // address for next calls and makeing changes in new array on furthre cakss will
    // be deleted while backtracking
    // so instead of doing lca = root, we have set some property of it to save the
    // permanent data

    private boolean lca(TreeNode root, TreeNode p, TreeNode q, TreeNode ans) {
        if (root == null)
            return false;
        boolean self = false;
        // found data
        if (root == p || root == q) {
            self = true;
        }
        boolean left = lca(root.left, p, q, ans);
        boolean right = lca(root.right, p, q, ans);

        if ((left & right) || (left & self) || (self & right)) { // only set lca when both the data is found
            ans.left = root;
        }

        return left || self || right; // return true if found data

    }
}
