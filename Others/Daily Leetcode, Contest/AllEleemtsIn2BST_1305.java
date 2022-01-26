import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/all-elements-in-two-binary-search-trees/discuss/1720619/2-ways-or-O(n+m)-Time-and-Space-or-Inorder-Traversal-or-BST-Propertyor-JAVA
class AllEleemtsIn2BST_1305 {
    // Approach1 :
    // Time: O(2n) + O(2m) + O(n+m) => O(n+m) : inorder + inorder + merge
    // Space: O(n) + O(m) + O(n+m) => O(n+m) : listA + listB + ListAns
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        inorder(root1, a);
        inorder(root2, b);
        mergeLists(a, b, ans);
        return ans;
    }

    // O(n): skew tree
    private void inorder(TreeNode root, List<Integer> ans) {
        if (root == null)
            return;

        inorder(root.left, ans);
        ans.add(root.val);
        inorder(root.right, ans);
    }

    // O(m+n)
    private void mergeLists(List<Integer> list1, List<Integer> list2, List<Integer> ans) {
        int n = list1.size(), m = list2.size(), i = 0, j = 0;
        while (i < n && j < m) {
            if (list1.get(i) <= list2.get(j))
                ans.add(list1.get(i++));
            else
                ans.add(list2.get(j++));
        }

        while (i < n)
            ans.add(list1.get(i++));
        while (j < m)
            ans.add(list2.get(j++));
    }

    // Approach 2 : Simulating inorder recursion using Stack: maintaingn two stacks
    // to compare values : One pass
    // Time:O(2n+2m)=>O(n+m)
    // Space: O(n) + O(m) +O(n+m) : stack,stack,res
    public List<Integer> getAllElements2(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();

        while (root1 != null || root2 != null || st1.size() > 0 || st2.size() > 0) {
            // make left calls
            while (root1 != null) {
                st1.push(root1);
                root1 = root1.left;
            }

            while (root2 != null) {
                st2.push(root2);
                root2 = root2.left;
            }

            // Perform operation in inoredr area : compare and all values ans make right
            // calls according to the comparison condition

            if (st2.isEmpty() || (!st1.isEmpty() && st1.peek().val <= st2.peek().val)) {
                root1 = st1.pop();
                ans.add(root1.val);
                // right calls
                root1 = root1.right;
            } else {
                root2 = st2.pop();
                ans.add(root2.val);
                // right call
                root2 = root2.right;
            }
        }

        return ans;
    }

}