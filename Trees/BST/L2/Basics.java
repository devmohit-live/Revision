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

    // 448 · Inorder Successor in BST: https://www.lintcode.com/problem/448/
    // Predecessor and succsor:
    // pred!=succ at anytime except there is a single node in a tree and pred = succ
    // = null
    // pred(lesser than me) => node == root => node.left ka rightmost (floor)
    // succ(greater than me) => node == root => node.right ka leftmost (ceil)

    public TreeNode getLeftMostNode(TreeNode node) {
        if (node == null)
            return node;
        while (node.left != null)
            node = node.left;
        return node;
    }

    public TreeNode getRightMostNode(TreeNode node) {
        if (node == null)
            return node;
        while (node.right != null)
            node = node.right;
        return node;
    }

    public TreeNode[] findPredecesorAndSuccecor(TreeNode root, TreeNode node) {
        TreeNode pred = null, succ = null, curr = root;
        if (node == null || root == null)
            return null;
        while (curr != null) {
            if (curr.val == node.val) {
                TreeNode leftmost = getLeftMostNode(curr.right);
                TreeNode righttmost = getRightMostNode(curr.left);
                pred = righttmost != null ? righttmost : pred;
                succ = leftmost != null ? leftmost : succ;
                break;
            } else if (curr.val > node.val) {
                // the node is lesser then me so I am greater hence a potential successor
                succ = curr;
                curr = curr.left;
            } else {
                // the node is greater then me so I am lesser hence a potential predecessor
                pred = curr;
                curr = curr.right;
            }
        }
        TreeNode[] predsucc = new TreeNode[] { pred, succ };
        return predsucc;
    }

    // Kth smallest element in BST : LC 230
    // providing k-1 in recursion for left and right calls and checking k==0 doesn't
    // works
    // have to count k time using a global param

    // int count = 0, ans = 0;
    // recursive
    public int kthSmallest(TreeNode root, int k) {
        int[] ans = { k, -1 };
        inorder(root, ans);
        return ans[1];
    }

    private void inorder(TreeNode root, int[] ans) {
        if (root == null)
            return;
        inorder(root.left, ans);
        ans[0]--;
        if (ans[0] == 0) {
            ans[1] = root.val;
            return;
        }
        inorder(root.right, ans);
    }

    // largest :
    public int kthLargest(Node root, int k) {
        int[] ans = { k, -1 };
        inorder(root, ans);
        return ans[1];
    }

    private void inorder(Node root, int[] ans) {
        if (root == null)
            return;
        // just mae the right call first : desccending order
        inorder(root.right, ans);
        ans[0]--;
        if (ans[0] == 0) {
            ans[1] = root.data;
            return;
        }
        inorder(root.left, ans);
    }

    // Inorder SuccessorII Leetcode 510 (Premium)

    class Node {
        Node left = null, right = null, parent = null;
        int val;

        Node(int val) {
            this.val = val;
        }
    }

    public Node getLeftMostNode(Node node) {
        if (node == null)
            return node;
        while (node.left != null)
            node = node.left;
        return node;
    }

    // directly node is given not the root
    public Node inorderSuccesosor(Node node) {
        // Succesor : right's leftmost or parent's parents

        Node right = node.right;
        if (node != null) {
            return getLeftMostNode(node);
        }
        // go till the parent for which currnt node is a left child:
        // succesor is parent when I am the left child , else parent is predecessor
        while (node.parent != null && node.parent.left != node) {
            node = node.parent;
        }

        return node; // either current successor or null

    }

}
