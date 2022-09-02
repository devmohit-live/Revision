import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * AvgOfNodes_637
 */
public class AvgOfNodes_637 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if (root == null) {
            ans.add(0.0);
            return ans;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (!q.isEmpty()) {
            int size = q.size();
            long sum = 0, nodes = 0;
            while (size-- > 0) {
                TreeNode rm = q.removeFirst();
                ++nodes;
                sum += rm.val;

                if (rm.left != null)
                    q.addLast(rm.left);
                if (rm.right != null)
                    q.addLast(rm.right);
            }

            ans.add((sum * 1.0) / nodes);
        }

        return ans;
    }
}