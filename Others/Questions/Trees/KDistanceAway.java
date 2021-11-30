package Questions.Trees;

import java.util.ArrayList;

import javax.swing.tree.TreeNode;

public class KDistanceAway {
    
    // Approach 1: Using n2r and kdown seperately and  creatinf extra space for n2r storage
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

    //Approach 2 : 


    

}
