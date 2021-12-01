package Questions.Trees;

// https://leetcode.com/problems/cousins-in-binary-tree/discuss/1605778/BFS-Approach-for-finding-cousins-faster-than-100.00
public class CousinsBT {
    class pair {
        TreeNode node;
        TreeNode parent;

        pair(TreeNode node, TreeNode parent) {
            this.node = node;
            this.parent = parent;
        }

        pair() {
            this(null, null);
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return false;

        int level = 0;
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(root, null));
        pair[] candidates = new pair[2];

        while (!q.isEmpty()) {

            int size = q.size();
            while (size-- > 0) {
                pair rm = q.remove();
                TreeNode node = rm.node;
                TreeNode parent = rm.parent;

                if (node.val == x) {
                    candidates[0] = rm;
                } else if (node.val == y)
                    candidates[1] = rm;

                if (node.left != null)
                    q.add(new pair(node.left, node));
                if (node.right != null)
                    q.add(new pair(node.right, node));
            }

            pair c1 = candidates[0], c2 = candidates[1];
            if ((c1 == null && c2 != null) || (c2 == null && c1 != null))
                return false;
            // hence they will be on different level(depth)

            if (c1 != null && c2 != null) { // both are present on same depth
                if (c1.parent != c2.parent)
                    return true;
                else
                    return false;
            }

        }

        return false;
    }
}
