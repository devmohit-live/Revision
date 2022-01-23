public class Construction {
    class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

        TreeNode() {
            this(-1);
        }
    }

    // NOTE: for addition/deletion of nodes in tree work in postorder

    // Preorder traversal to BST
    int idx = 0;

    public TreeNode preOrderToBST(int[] preorder) {
        // fact: root in start : but not aable to identify left and right subtree
        // so we use concept of bst range left<=root<right
        idx = 0;
        return preOrderToBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode preOrderToBST(int[] preorder, int left_range, int right_range) {
        if (idx >= preorder.length || preorder[idx] < left_range || preorder[idx] > right_range)
            return null;
        TreeNode root = new TreeNode(preorder[idx++]);
        root.left = preOrderToBST(preorder, left_range, root.val);
        root.right = preOrderToBST(preorder, root.val, right_range);
        return root;
    }

    // Inorder traversal to BST
    public TreeNode inorderToBst(int[] inorder) {
        // fact: root in mid : able to identify left and right subtree
        return inorderToBst(inorder, 0, inorder.length - 1);
    }

    private TreeNode inorderToBst(int[] inorder, int si, int ei) {
        if (si > ei)
            return null;
        int mid = si + (ei - si) / 2;

        TreeNode root = new TreeNode(inorder[mid]);

        root.left = inorderToBst(inorder, si, mid - 1);
        root.right = inorderToBst(inorder, mid + 1, ei);
        return root;
    }

    // PostOrder traversal to BST : similar to preorder just idx will start from
    // length-1 as root is at last
    public TreeNode postOrderToBST(int[] postorder) {
        // fact: root in start : but not aable to identify left and right subtree
        // so we use concept of bst range left<=root<right
        idx = postorder.length - 1;
        return postOrderToBST(postorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode postOrderToBST(int[] postorder, int left_range, int right_range) {
        if (idx < 0 || postorder[idx] < left_range || postorder[idx] > right_range)
            return null;
        TreeNode root = new TreeNode(postorder[idx--]);
        //NOTE: making right call first as root is at last idx and just before that we have right child

        root.right = postOrderToBST(postorder, root.val, right_range);
        root.left = postOrderToBST(postorder, left_range, root.val);
        return root;
    }


    //BST From LevelOrder Traversal
    //Steps:
    // 1: define par, lr,rr
    //2: check if the idx'es val is within the range of current parent : if nor continue with next parent(next el in q)
    // else the only create a node and increment idx
    // check where to attach this created node left/right of par acc to it's value (compare with par)
    // add new elemets in q according to the cretaed node: acting as a parent havinfg a determined value for it's ledt and right range subtree
    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        if (LevelOrder.length == 0)
            return null;

        int idx = 0;
        TreeNode root = new TreeNode(LevelOrder[idx++]);

        LinkedList<BSTLPair> q = new LinkedList<>();
        // initially root is taken as self parent
        q.addLast(new BSTLPair(root, Integer.MIN_VALUE, root.val)); // define all the lest subtree child range <=
                                                                    // root.val
        q.addLast(new BSTLPair(root, root.val, Integer.MAX_VALUE)); // define all the right subtree child range >
                                                                    // root.val

        while (idx < LevelOrder.length) {
            BSTLPair rm = q.removeFirst();
            TreeNode par = rm.par;
            int lr = rm.lr, rr = rm.rr;

            int val = LevelOrder[idx];
            // Important: first check if it is the the current paren node's range or not
            if (val > rr || val < lr)
                continue;

            // withing range: create a node
            TreeNode node = new TreeNode(LevelOrder[idx++]);

            // attach to the correct location
            if (par.val > node.val)
                rm.par.left = node;
            else
                rm.par.right = node;

            // add children
            q.addLast(new BSTLPair(node, lr, node.val));
            q.addLast(new BSTLPair(node, node.val, rr));
        }
        q = null;
        return root;
    }

}
