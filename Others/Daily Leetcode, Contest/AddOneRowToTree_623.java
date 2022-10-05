/**
 * AddOneRowToTree_623
 */
public class AddOneRowToTree_623 {

    public TreeNode addOneRow(TreeNode root, int val, int d) {
        if (d == 1) {
            TreeNode nroot = new TreeNode(val);
            nroot.left = root;
            return nroot;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        int depth = 1;
        while (!q.isEmpty()) {
            if (depth == d - 1)
                break;
            int size = q.size();
            while (size-- > 0) {
                TreeNode rm = q.removeFirst();
                if (rm.left != null)
                    q.addLast(rm.left);
                if (rm.right != null)
                    q.addLast(rm.right);
            }
            depth++;
        }

        while (!q.isEmpty()) {
            TreeNode rm = q.removeFirst();
            TreeNode orLeft = rm.left, orRight = rm.right;
            rm.left = new TreeNode(val);
            rm.right = new TreeNode(val);
            rm.left.left = orLeft;
            rm.right.right = orRight;
        }

        return root;
    }
}