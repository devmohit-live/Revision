import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class Basics {
    // IN BST try to do eveything iteratively

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> la = new ArrayList<>();
        rootToNodePath(root, p, la);
        List<TreeNode> lb = new ArrayList<>();
        rootToNodePath(root, q, lb);
        int sa = 0, sb = 0;
        while (sa < la.size() && sb < lb.size() && la.get(sa).val == lb.get(sb).val) {
            sa++;
            sb++;
        }
        sa--;
        return la.get(sa);

    }

    // n
    private void rootToNodePath(TreeNode root, TreeNode node, List<TreeNode> list) {
        if (root == null)
            return;
        while (root != null) {
            list.add(root);
            if (root == node)
                return;
            else if (root.val < node.val)
                root = root.right;
            else
                root = root.left;

        }

        list.clear(); // not found clear list
        return;
    }

    // 2n
    private List<TreeNode> n2rp(TreeNode root, TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        rootToNodePath(root, node, list);
        int i = 0, j = list.size();
        while (i < j) {
            TreeNode tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
            i++;
            j--;
        }
        return list;
    }

    //

    // LC173
    // Approach 1: O(n) time ans space
    class BSTIterator {
        private List<Integer> list; // sapce:n
        private int curr = 0;

        public BSTIterator(TreeNode root) {
            this.list = new ArrayList<>();
            inorder(root, list);
        }

        // 1
        public int next() {
            return this.list.get(curr++);
        }

        // 1
        public boolean hasNext() {
            return curr < this.list.size();
        }

        // n
        private void inorder(TreeNode root, List<Integer> ans) {
            if (root == null)
                return;
            inorder(root.left, ans);
            ans.add(root.val);
            inorder(root.right, ans);
        }
    }

    // log n time and sapce
    class BSTIterator2 {
        private Stack<TreeNode> st;

        // addleft : log n : and also this will not be called eveytime: and eberytime
        // the time will not be logn
        public BSTIterator(TreeNode root) {
            st = new Stack<>();
            addLeft(root);
        }

        public int next() {
            TreeNode rm = this.st.pop();
            int val = rm.val;
            addLeft(rm.right);

            return val;
        }

        // logn
        private void addLeft(TreeNode node) {
            while (node != null) {
                this.st.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return st.size() != 0;
        }
    }

    // Predecessor and succsor:
    // inorder: increasing order: prede = root.left, succ = root.right , where root
    // == node

    public Treenode[] findSuccecor(TreeNode root, TreeNode node) {
        TreeNode[] predsucc = new TreeNode[2];

        if (node == null || root == null)
            return null;
        while (root != null) {
            if (root.val == node.val) {
                predsucc[1] = root.right;
                predsucc[0] = root.left;
                break;
            } else if (root.val < node.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return predsucc;
    }

}
