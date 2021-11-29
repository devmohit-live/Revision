import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.tree.TreeNode;

import org.w3c.dom.Node;

public class LeftRightView {

    // Basically BFS: Level Order Traversal of tree and addition of first/last node
    // at the start before we are goinf to to the operation of remove and add
    // that is at the start of each level: q contains the node of that level
    // only(according to the order they are added)

    // Left view :
    // https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1#
    ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int level = 0;
        // level order traversal

        while (!q.isEmpty()) {
            int size = q.size(); // to know for how many nodes we have to add it's children
            ans.add(q.peek().data); // we know that after completion of inner loop one level is completed and for
                                    // next level the first node added is present at front of q

            while (size-- > 0) {
                Node rm = q.remove();
                if (rm.left != null)
                    q.add(rm.left);
                if (rm.right != null)
                    q.add(rm.right);
            }
            level++;
        }

        return ans;
    }

    // Right view: https://leetcode.com/problems/binary-tree-right-side-view/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int level = 0;
        // level order traversal

        while (!q.isEmpty()) {
            int size = q.size(); // to know for how many nodes we have to add it's children
            ans.add(q.peek().val); // we know that after completion of inner loop one level is completed and for
                                   // next level the first node added is present at front of q

            // or adding q.getLast() using linkedlist and doing normal left and right call
            while (size-- > 0) {
                TreeNode rm = q.remove();
                if (rm.right != null)
                    q.add(rm.right);
                if (rm.left != null)
                    q.add(rm.left);
            }
            level++;
        }
        return ans;
    }

}
