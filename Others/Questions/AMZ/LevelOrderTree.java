public class LevelOrderTree {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        q.add(root);

        // bfs type : level order traverse
        while (q.size() > 0) {
            int size = q.size();
            List<Integer> small = new ArrayList<>();
            while (size-- > 0) {
                TreeNode rm = q.remove();
                small.add(rm.val);
                if (rm.left != null)
                    q.add(rm.left);
                if (rm.right != null)
                    q.add(rm.right);
            }
            ans.add(small);

        }
        return ans;
    }
}
