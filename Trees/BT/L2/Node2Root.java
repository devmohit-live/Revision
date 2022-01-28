
import java.util.ArrayList;

import javax.swing.tree.TreeNode;

public class Node2Root {
    // 1:
    public static ArrayList<TreeNode> nodeToRootPath(TreeNode node, int data) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        nodeToRootPath_(node, data, ans);
        return ans;
    }

    private static boolean nodeToRootPath_(TreeNode node, int data, ArrayList<TreeNode> res) {
        if (node == null)
            return false;

        if (node.val == data || nodeToRootPath_(node.left, data, res) || nodeToRootPath_(node.right, data, res)) {
            res.add(node);
            return true;
        }

        return false;

    }
}
