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
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);// leave nodes
                curr = curr.right;
            } else {
                TreeNode rightmostNode = getRightMostNode(left, curr);
                if (rightmostNode.right == null) {
                    ans.add(curr.val);
                    // create thread : crearion is done in preorder
                    rightmostNode.right = curr;
                    curr = curr.left;
                } else {
                    // rihtmost.right == curr (already)
                    // delete thread
                    rightmostNode.right = null;
                    // print curr
                    // ans.add(curr.val);
                    curr = curr.right;
                }

            }

        }

        return ans;
    }

    // is valid bst : 3 ways
    // 1 using recursion
    private boolean validate(TreeNode root, TreeNode[] prev) {
        boolean res = true;
        if (root == null)
            return res;

        res = res && validate(root.left, prev);
        if (prev[0] != null && prev[0].val >= root.val)
            res = res && false;
        prev[0] = root;
        res = res && validate(root.right, prev);

        return res;
    }

    // using stack
    public boolean isValidBST2(TreeNode root) {
        Integer pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                return true;
            }
            TreeNode node = stack.pop();
            if (pre != null && pre >= node.val) {
                return false;
            }
            pre = node.val;
            root = node.right;
        }
    }
    // using morris

    private boolean validateMorris(TreeNode root) {
        if (root == null)
            return true;

        TreeNode curr = root;
        long prev = -(long) 1e17;

        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                // ans
                if (prev >= curr.val)
                    return false;
                prev = curr.val;

                curr = curr.right;

            } else {
                TreeNode righMost = getRightMost(left, curr);
                if (righMost.right == null) {
                    // thread creation
                    righMost.right = curr;
                    curr = curr.left;

                } else {
                    // thread deletion
                    righMost.right = null;

                    // work.ans

                    if (prev >= curr.val)
                        return false;
                    prev = curr.val;

                    // right call
                    curr = curr.right;
                }

            }

        }

        return true;
    }

}
