package Questions.Trees;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class KDistanceAway {

    // Approach 1: Using n2r and kdown seperately and creatinf extra space for n2r
    // storage
    public static ArrayList<Integer> distanceK(TreeNode root, int target, int k) {
        // using extra space apart from recursion
        ArrayList<TreeNode> n2r = new ArrayList<>();
        node2rootPath(root, target, n2r);
        ArrayList<Integer> ans = new ArrayList<>();
        int n = n2r.size(), i = 0;
        TreeNode prev = null;
        while (i < n) {
            TreeNode curr = n2r.get(i);
            kDown(curr, k - i, prev, ans);
            prev = curr;
            i++;
        }

        return ans;
    }

    private static boolean node2rootPath(TreeNode node, int data, ArrayList<TreeNode> res) {
        if (node == null)
            return false;
        if (node.val == data || node2rootPath(node.left, data, res) || node2rootPath(node.right, data, res)) {
            res.add(node);
            return true;
        }

        return false;
    }

    private static void kDown(TreeNode root, int k, TreeNode blocked, ArrayList<Integer> res) {
        if (root == null || k < 0 || root == blocked)
            return;

        if (k == 0) {
            res.add(root.val);
            return; // reached the destination
        }

        kDown(root.left, k - 1, blocked, res); // distance from root was k => distance from child will be k-1
        kDown(root.right, k - 1, blocked, res);

    }

    // Approach 2 :

    public static ArrayList<Integer> distanceKSpacewOptimized(TreeNode root, int target, int k) {
        // withou using extra space apart from recursion

        ArrayList<Integer> ans = new ArrayList<>();
        node2rootPath2(root, target, k, ans);

        return ans;
    }

    private static int node2rootPath2(TreeNode node, int data, int k, ArrayList<Integer> res) {
        if (node == null)
            return -1; // identification mark that data was not found

        if (node.val == data) {
            kDown2(node, k - 0, null, res);
            return 1; // distance of this node from it's parent
        }

        int ld = node2rootPath2(node.left, data, k, res);
        if (ld != -1) {
            // it means we have found data in left side
            kDown2(node, k - ld, node.left, res);
            return ld + 1;
        }

        int rd = node2rootPath2(node.right, data, k, res);
        if (rd != -1) {
            // it means we have found data in right side
            kDown2(node, k - rd, node.right, res);
            return rd + 1;
        }

        return -1;
    }

    private static void kDown2(TreeNode root, int k, TreeNode blocked, ArrayList<Integer> res) {
        if (root == null || k < 0 || root == blocked)
            return;

        if (k == 0) {
            res.add(root.val);
            return; // reached the destination
        }

        kDown2(root.left, k - 1, blocked, res); // distance from root was k => distance from child will be k-1
        kDown2(root.right, k - 1, blocked, res);

    }

    
    
    // 863. All Nodes Distance K in Binary Tree
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        node2rootPath2(root, target, k, ans);

        return ans;
    }

    private int node2rootPath2(TreeNode node, TreeNode target, int k, List<Integer> res) {
        if (node == null)
            return -1; // identification mark that data was not found

        if (node == target) {
            kDown2(node, k - 0, null, res);
            return 1; // distance of this node from it's parent
        }

        int ld = node2rootPath2(node.left, target, k, res);
        if (ld != -1) {
            // it means we have found data in left side
            kDown2(node, k - ld, node.left, res);
            return ld + 1;
        }

        int rd = node2rootPath2(node.right, target, k, res);
        if (rd != -1) {
            // it means we have found data in left side
            kDown2(node, k - rd, node.right, res);
            return rd + 1;
        }

        return -1;
    }

    private void kDown2(TreeNode root, int k, TreeNode blocked, List<Integer> res) {
        if (root == null || k < 0 || root == blocked)
            return;

        if (k == 0) {
            res.add(root.val);
            return; // reached the destination
        }

        kDown2(root.left, k - 1, blocked, res); // distance from root was k => distance from child will be k-1
        kDown2(root.right, k - 1, blocked, res);

    }

}
