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

    //Left and right view using dfs : If someone asks to do it withot Queue
    //Approac: In bfs we weretaking decisions based on level 
    //at every new level we have to take a starting/ending elemet(if using linkedlist)
    //else if using standard q : taking the first element at every new level and making the left,right or right,left calls accordingly
    //left call first in left view and right call first in right view

    // LC 199 : Right view
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightSideViewDFS(root, 0, ans);
        return ans;
    }

    public void rightSideViewDFS(TreeNode root, int level, List<Integer> ans) {
        if (root == null)
            return;

        if (level == ans.size()) { // insures a new level is achevied
            ans.add(root.val);
        }

        // right view so making right call first
        rightSideViewDFS(root.right, level + 1, ans);
        rightSideViewDFS(root.left, level + 1, ans);

    }

    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        leftSideViewDFS(root, 0, ans);
        return ans;
    }

    public void leftSideViewDFS(TreeNode root, int level, List<Integer> ans) {
        if (root == null)
            return;

        if (level == ans.size()) { // insures a new level is achevied
            ans.add(root.val);
        }

        // left view so making left call first
        leftSideViewDFS(root.left, level + 1, ans);
        leftSideViewDFS(root.right, level + 1, ans);

    }

}
