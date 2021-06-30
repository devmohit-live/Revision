public class ZigZagOfBT {
    // Leetcode 103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        int lv = 0;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        while (q.size() > 0) {
            List<Integer> tmp = new ArrayList<>();
            int curr = q.size();
            while (curr-- > 0) {
                TreeNode rm = q.removeFirst();
                tmp.add(rm.val);

                if (rm.left != null)
                    q.add(rm.left);
                if (rm.right != null)
                    q.add(rm.right);

            }
            if ((lv & 1) == 1)
                Collections.reverse(tmp);

            res.add(tmp);
            lv++;

        }

        return res;
    }
}

// Different Way (Class):