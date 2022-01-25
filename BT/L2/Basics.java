import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class Basics {

    // find
    public static boolean find(TreeNode root, int val) {
        if (root == null)
            return false;
        return root.val == val || find(root.left, val) || find(root.right, val);
    }

    // node ro all leaf paths
    public static List<List<Integer>> node2AllLeafes(TreeNode node) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        node2AllLeafes(node, small, ans);
        return ans;
    }

    // node2rootpath return type

    // wapas aate hue hm apna decision lete h
    public static List<Integer> n2rp(TreeNode root, TreeNode node) {
        if (root == null)
            return new ArrayList<>();
        if (root == node) {
            List<Integer> base = new ArrayList<>();
            base.add(node.val);
            return base;
        }
        List<Integer> left = n2rp(root.left, node);
        if (left.size() > 0) {
            left.add(root.val);
            return left;
        }
        List<Integer> right = n2rp(root.right, node);
        if (right.size() > 0) {
            right.add(root.val);
            return right;
        }

        return new ArrayList<>();
    }

    // void type
    public static void n2rp2(TreeNode root, TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        n2rp2(root, node, ans);
    }

    private static boolean n2rp2(TreeNode root, TreeNode node, List<Integer> ans) {
        if (root == null)
            return false;
        if (root == node) {
            ans.add(node.val);
            return true;
        }
        boolean found = n2rp2(root.left, node, ans) || n2rp2(root.right, node, ans);
        if (found)
            ans.add(root.val);
        return found;
    }

    private static void node2AllLeafes(TreeNode node, List<Integer> small, List<List<Integer>> ans) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            List<Integer> base = new ArrayList<>(small);
            base.add(node.val);
            ans.add(base);
        }
        small.add(node.val);
        node2AllLeafes(node.left, small, ans);
        node2AllLeafes(node.right, small, ans);
        small.remove(small.size() - 1);

    }

    // parent with single child
    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root, ans);
        return ans;
    }

    private static void exactlyOneChild(TreeNode node, ArrayList<Integer> res) {
        if (node == null)
            return;
        if (node.left == null ^ node.right == null)
            res.add(node.val);
        exactlyOneChild(node.left, res);
        exactlyOneChild(node.right, res);
    }

    // count single child :
    // Approach 1
    public int countSingleParents(TreeNode node) {
        int count = 0;

        if (node == null || (node.left == null && node.right == null))
            return 0;

        if (node.left == null && node.right != null)
            count++;
        else if (node.left != null && node.right == null)
            count++;
        count += countSingleParents(node.left);
        count += countSingleParents(node.right);

        return count;
    }
    // Approach 2: post order me kaam hoga

    public int countSingleParents2(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int left = countSingleParents(node.left);
        int right = countSingleParents(node.right);
        int ans = left + right;
        if (node.left == null || node.right == null)
            ans++;
        return ans;
    }

    // find predecessor,succesor of a node and floor and ceil of a data(may not be
    // present)

    class PSFC {
        TreeNode pred, succ;
        TreeNode prev; // to vaoid static and maintains prev,curr relationship and inorder traversal
                       // for pred,,succ
        int floor, ceil;

        PSFC() {
            this.prev = this.succ = this.prev = null;
            this.floor = Integer.MIN_VALUE;
            this.ceil = Integer.MAX_VALUE;
        }
    }

    // 448 · Inorder Successor in BST:https://www.lintcode.com/problem/448/
    public void findPredSucc(TreeNode root, TreeNode node, int x) {
        if (root == null || node == null)
            return;
        PSFC ans = new PSFC();
        inorder(root, node, ans);
        System.out.println("Predecessor and Successor of " + root.val + " is " + ans.pred + " " + ans.succ);
    }

    public void floorAndCeil(TreeNode root, int x) {
        PSFC ans = new PSFC();
        System.out.println("Floor and Ceil of " + x + " is " + ans.floor + " " + ans.ceil);
    }

    // inorder traversal stratergy of maintains a global prev and performing
    // operation on prev(global),curr(recursive stack)
    private void inorder(TreeNode root, TreeNode node, PSFC ans) {
        if (root == null)
            return;

        inorder(root.left, node, ans);

        // pred,succ

        if (root == node) { // curr node is equal to the given node
            ans.pred = ans.prev; // prev node is pointing to predecessor
        }
        if (ans.prev != null && ans.prev == node) { // curr node is pointing to succ and prev node is now on node
            ans.succ = root; // curr node at succ
        }

        ans.prev = root; // curr

        inorder(root.right, node, ans);
    }

    private void inorder(TreeNode root, TreeNode node, int x, PSFC ans) {
        if (root == null)
            return;

        inorder(root.left, node, x, ans);

        // floor and ceil

        if (root.val > x)
            ans.ceil = Math.min(ans.ceil, root.val);
        if (root.val < x)
            ans.ceil = Math.max(ans.floor, root.val);

        ans.prev = root; // curr

        inorder(root.right, node, x, ans);
    }

}
