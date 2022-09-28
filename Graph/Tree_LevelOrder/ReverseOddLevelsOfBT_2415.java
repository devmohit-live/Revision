import java.util.LinkedList;

import javax.swing.tree.TreeNode;

public class ReverseOddLevelsOfBT_2415 {
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null)
            return root;

        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        int lv = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode rm = q.removeFirst();
                if (rm.left != null)
                    q.addLast(rm.left);
                if (rm.right != null)
                    q.addLast(rm.right);
            }

            lv++; // these elemets are belonging to next level
            if ((lv & 1) == 1) {
                reverseValues(q);
            }
        }
        return root;
    }

    private void reverseValues(LinkedList<TreeNode> list) {
        int n = list.size(), i = 0, j = n - 1;
        int[] vals = new int[n];
        for (TreeNode node : list)
            vals[i++] = node.val; // fill the vals in order
        for (TreeNode node : list)
            node.val = vals[j--]; // set in reverse order
    }

    // Using DFS
    public TreeNode reverseOddLevelsUsingDFS(TreeNode root) {
        dfs(root.left, root.right, 1);
        return root;
    }

    private void dfs(TreeNode leftNode, TreeNode rightNode, int level) {
        if (leftNode == null || rightNode == null)
            return;

        if ((level & 1) == 1) {
            int tmp = rightNode.val;
            rightNode.val = leftNode.val;
            leftNode.val = tmp;
        }

        // from outside to inside
        dfs(leftNode.left, rightNode.right, level + 1);
        dfs(leftNode.right, rightNode.left, level + 1);
    }

}
