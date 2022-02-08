import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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

    // ++++++++++++++++++++++++ BST Construction +++++++++++++++++++++++
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
        // NOTE: making right call first as root is at last idx and just before that we
        // have right child

        root.right = postOrderToBST(postorder, root.val, right_range);
        root.left = postOrderToBST(postorder, left_range, root.val);
        return root;
    }

    // BST From LevelOrder Traversal
    // Steps:
    // 1: define par, lr,rr
    // 2: check if the idx'es val is within the range of current parent : if nor
    // continue with next parent(next el in q)
    // else the only create a node and increment idx
    // check where to attach this created node left/right of par acc to it's value
    // (compare with par)
    // add new elemets in q according to the cretaed node: acting as a parent
    // havinfg a determined value for it's ledt and right range subtree
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

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // ================== Building Binary Tree =======================
    public static TreeNode preInBT(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length || preorder.length == 0)
            return null;
        return preInBT(preorder, inorder, 0, inorder.length - 1, 0, preorder.length - 1);
    }

    public static TreeNode preInBT(int[] pre, int[] in, int isi, int iei, int psi, int pei) {
        if (iei < iei || pei < psi)
            return null;

        int rootval = pre[psi];
        TreeNode root = new TreeNode(rootval);
        // find the rootidx in inorder
        int iridx = -1;
        for (int i = isi; i <= iei; i++) {
            if (in[i] == rootval) {
                iridx = i;
                break;
            }
        }

        int tel = iridx - isi; // total no of elemets (will be helpful in finding th e no. of ele in left and
                               // right )
        // range for left and right in prefix
        // Inorder:
        // left : si->root-, right: root+1->ei
        // Preorder: psi => root in preorder
        // left: no of elemets in left subtree of inorder : rootidx - si :
        // psi+1->psi+tel
        // right: starts from psi+tel+1 -> ei

        TreeNode left = preInBT(pre, in, isi, iridx - 1, psi + 1, psi + tel);
        TreeNode right = preInBT(pre, in, iridx + 1, iei, psi + tel + 1, pei);

        root.left = left;
        root.right = right;

        return root;
    }

    public static TreeNode postInBT(int[] preorder, int[] inorder) {
        int n = inorder.length, m = postorder.length;
        if (n != m || n == 0)
            return null;

        return postInBT(inorder, postorder, 0, n - 1, 0, m - 1);
    }

    public static TreeNode postInBT(int[] in, int[] post, int isi, int iei, int psi, int pei) {
        if (isi > iei || psi > pei)
            return null;

        TreeNode root = new TreeNode(post[pei]);

        int ridx = -1;
        for (int i = 0; i < in.length; i++)
            if (post[pei] == in[i]) {
                ridx = i;
                break;
            }

        int tel = ridx - isi;

        TreeNode left = postInBT(in, post, isi, ridx - 1, psi, psi + tel - 1);
        TreeNode right = postInBT(in, post, ridx + 1, iei, psi + tel, pei - 1);// root : pei
        root.left = left;
        root.right = right;
        return root;
    }

    public static TreeNode inLvlBT(int[] level, int[] inorder) {
        int n = inorder.length;
        return inLvlBT(inorder, 0, n - 1, level);
    }

    private static TreeNode inLvlBT(int[] in, int isi, int iei, int[] lv) {
        if (isi > iei)
            return null;
        TreeNode root = new TreeNode(lv[0]);
        if (lv.length == 1)
            return root;

        int ridx = 0;
        while (in[ridx] != root.val)
            ridx++;

        // now differetiate between leftsubtree elemets and righsubtree elements in
        // levelorder
        Set<Integer> leftsubtree = new HashSet<>();

        for (int i = 0; i < ridx; i++) {
            leftsubtree.add(in[i]); // adding left subtree fro inorder array
        }

        // make leftr and right subtree array for leveorder left and right call
        int[] lvl_left = new int[ridx - isi];
        int[] lvl_right = new int[iei - ridx];
        int left = 0, right = 0;

        // why running loop from level order array ? => to maintain the elements
        // relative order for tree creation
        // 0th elemet is root (already considered)
        for (int i = 1; i < lv.length; i++) {
            if (leftsubtree.size() > 0 && leftsubtree.contains(lv[i])) {
                // part of leftarray
                lvl_left[left++] = lv[i];
                leftsubtree.remove(lv[i]);

            } else {
                // part of leftarray
                lvl_right[right++] = lv[i];
            }
        }

        root.left = inLvlBT(in, isi, ridx - 1, lvl_left);
        root.right = inLvlBT(in, ridx + 1, iei, lvl_right);

        return root;

    }

    public static TreeNode prePost(int[] preorder, int[] postOrder) {
        // Approach : in postorder the root is at last after all it's childreb
        // so for left subtree the left subtree's root will be at last before all of its
        // children in left subtree
        // similarly the left subtree root in pre order is printed before all it's
        // children
        // using this we can get the number of elements, range in left subtree

        int n = preorder.length;
        return prePost(preorder, postOrder, 0, n - 1, 0, n - 1);
    }

    //Works on Full Binary Tree

    private static TreeNode prePost(int[] pre, int[] post, int psi, int pei, int posi, int poei) {
        if (psi > pei || posi > poei)
            return null;

        TreeNode root = new TreeNode(pre[psi]);
        if (psi == pei)
            return root; // only 1 element

        int idx = posi; // will store: start of left subtree in postorder
        
        // left subtree's root finding in postorder
        while (post[idx] != pre[psi + 1])
            idx++;

        int tel = idx - posi + 1; // ridx is also included this time(root of left subtree)
        root.left = prePost(pre, post, psi + 1, psi + tel, posi, idx);
        root.right = prePost(pre, post, psi + 1 + tel, pei, idx + 1, poei - 1);
        return root;

    }

    // ===================================================================

}
