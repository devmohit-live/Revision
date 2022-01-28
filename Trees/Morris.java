import java.util.List;
import java.util.ArrayList;

public class Morris {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

    }

    public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        // node == curr : means reached to parent (as we have attached a thread)and have
        // to stop
        // :else it will keep on going to tree's rightmost node
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public static List<Integer> morrisInorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightmostNode = getRightMostNode(left, curr);
                if (rightmostNode.right == null) {
                    // create thread
                    rightmostNode.right = curr;
                    curr = curr.left;
                } else {
                    // rihtmost.right == curr (already)
                    // delete thread
                    rightmostNode.right = null;
                    // print curr
                    ans.add(curr.val);
                    curr = curr.right;
                }

            }

        }

        return ans;
    }

    public static List<Integer> morrisPreordreTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        return ans;
    }
}
