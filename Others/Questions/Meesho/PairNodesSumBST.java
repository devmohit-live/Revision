public class PairNodesSumBST {
    // works in any tree wheter bst ot not
    // Time Complexity: O(n), Space Complexity: O(n).
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        int[] ans = new int[2];
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k, int[] ans) {
        if (root == null)
            return false;
        if (set.contains(k - root.val)) {
            ans[0] = root.val;
            ans[1] = k - root.val;
            return true;
        }

        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }

    // Using property of BST 's inorder
    // Time Complexity: O(n), Space Complexity: O(n).
    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int i = 0, j = arr.size() - 1;
        while (i < j) {
            int sum = arr.get(i) + arr.get(j);
            if (sum == k) {
                ans[0] = arr.get(i);
                ans[1] = arr.get(j);
                return true;
            }

            else if (sum > k)
                j--;
            else
                i++;
        }
        return false;
    }

    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null)
            return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    // Using Set:
    // The idea is to use binary search method. For each node, we check if k -
    // node.val exists in this BST.

    // Time Complexity: O(h), Space Complexity: O(h). h is the height of the tree,
    // which is logn at best case, and n at worst case.
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root, k);
    }

    public boolean dfs(TreeNode root, TreeNode cur, int k) {
        if (cur == null)
            return false;
        boolean res = false;
        res = res || search(root, cur, k - cur.val); // root.val and serach for k - root.val in tree
        res = res || dfs(root, cur.left, k); // do normal dfs in left
        res = res || dfs(root, cur.right, k); // do normal dfs in right
    }

    public boolean search(TreeNode root, TreeNode cur, int value, List<Integer> ans) {
        if (root == null)
            return false;

        boolean res = false;
        if ((root.val == value) && (root != cur)) { // not the same node is counted twice for same value ex 1,1 tar = 2
            res = true; // found the other node
            // ans.add(root.val);
        }
        if (root.val < value)
            res = res || search(root.right, cur, value);
        if (root.val > value)
            res = res || search(root.left, cur, value);

        return res;

    }
}
