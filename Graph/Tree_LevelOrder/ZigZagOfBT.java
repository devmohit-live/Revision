package Tree_LevelOrder;

import java.util.*;

public class ZigZagOfBT {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Leetcode 103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        int lv = 0;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        while (q.size() > 0) {
            List<Integer> tmp = new ArrayList<>();
            int curr = q.size();
            while (curr-- > 0) {
                TreeNode rm = q.removeFirst();
                tmp.add(rm.val);

                if (rm.left != null)
                    q.add(rm.left);
                if (rm.right != null)
                    q.add(rm.right);

            }
            if ((lv & 1) == 1)
                Collections.reverse(tmp);

            res.add(tmp);
            lv++;

        }

        return res;
    }

    // Different Way (Class): Using LinkedList for swapping st and q

    // add children to stack and after adding all children of a node swap the ref of
    // q and st

    // addition of children will be according to level =>

    // level ==even => left to right
    // level -- odd => right to left

    // (same approach used for generic tree)

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {

        int lv = 0;
        LinkedList<TreeNode> q = new LinkedList<>();
        LinkedList<TreeNode> st = new LinkedList<>();
        q.addLast(root);
        List<List<Integer>> res = new ArrayList<>();

        if (root == null)
            return res;

        while (q.size() > 0) {
            List<Integer> smallAns = new ArrayList<>();
            int curr = q.size();
            while (curr-- > 0) {
                TreeNode rm = q.removeFirst();
                smallAns.add(rm.val);

                if (lv % 2 == 0) { // add left then right
                    if (rm.left != null)
                        st.addFirst(rm.left);
                    if (rm.right != null)
                        st.addFirst(rm.right);
                } else {
                    // add right then left
                    if (rm.right != null)
                        st.addFirst(rm.right);
                    if (rm.left != null)
                        st.addFirst(rm.left);
                }

            }

            LinkedList<TreeNode> tmp = q;
            q = st;
            st = tmp;
            res.add(smallAns);
            lv++;

        }
        return res;
    }

}
