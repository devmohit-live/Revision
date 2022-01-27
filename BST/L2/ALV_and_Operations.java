public class ALV_and_Operations {

    // add into bst

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);

        root = getRotation(root); // extra line for alv
        return root;
    }

    // iterative
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        TreeNode ans = root;
        while (root != null) {
            if (root.val > val) {
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                }
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                }
                root = root.right;
            }
        }

        return ans;
    }

    // Delete Node LC 450
    private int getMaximum(TreeNode root) {
        if (root == null)
            return -1;
        while (root.right != null) {
            root = root.right;
        }

        return root.val;
    }

    private TreeNode delete(TreeNode root, int data) {
        if (root == null)
            return null;

        if (root.val == data) {
            if (root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;
            else {
                // both child exists
                int max = getMaximum(root.left);
                root.val = max;
                root.left = delete(root.left, max);
            }
        }

        if (root.val < data)
            root.right = delete(root.right, data);
        else
            root.left = delete(root.left, data);

        root = getRotation(root); // extra line for alv
        return root;
    }

    // ============================ AVL ==============================
    class TreeNode {
        int val, bal, height;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.bal = 0;
            this.height = 0;
            this.left = this.right = null;
        }
    }

    // -1 <= balance factor < = 1

    // O(1)
    private static void upateBalanceHeight(TreeNode node) {
        int lh = node.left == null ? -1 : node.left.height;
        int rh = node.right == null ? -1 : node.right.height;
        node.height = Math.max(lh, rh) + 1;
        node.bal = lh - rh;
    }

    // O(1)
    private static TreeNode rotateLeft(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BRight = B.right;
        B.right = A;
        A.left = BRight;
        upateBalanceHeight(A);
        upateBalanceHeight(B);
        return B; // new root
    }

    // O(1)
    private static TreeNode rotateRight(TreeNode A) {
        TreeNode B = A.right;
        TreeNode BLeft = B.left;
        B.left = A;
        A.right = BLeft;
        upateBalanceHeight(A);
        upateBalanceHeight(B);
        return B;
        // new root
    }

    // identigy rotation
    private static TreeNode getRotation(TreeNode root) {
        upateBalanceHeight(root);
        int bf = root.bf;
        if (bf == 2) {// ll,lr
            if (root.left.bal == 1) {
                // ll
                return rotateRight(root);
            } else {
                // lr : as name suggest first left but on root.left than right on complete tree
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }

        } else if (bf == -2) {// rl,rr
            if (root.right.bal == -1) {
                // rr
                return rotateLeft(root);
            } else {
                // rl :as name suggest first right but on root.right than left on complete tree
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }

        }

        return root;
    }

}
