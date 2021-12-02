package Questions.Trees;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class PathSum {
    // LC: 112

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        return sum(root, 0, targetSum);
    }

    private boolean sum(TreeNode node, int ssf, int tar) {
        if (node == null)
            return false;

        if (node.left == null && node.right == null) {
            // System.out.println(ssf+" "+node.val);
            if (ssf + node.val == tar)
                return true;
            return false;
        }

        boolean res = false;
        res = res || sum(node.left, ssf + node.val, tar);
        res = res || sum(node.right, ssf + node.val, tar);

        return res;
    }

    // LC: 113

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();

        pathSum(root, targetSum, small, ans);
        return ans;
    }

    private void pathSum(TreeNode node, int tar, List<Integer> small, List<List<Integer>> ans) {

        if (node == null)
            return;

        // or

        if (node.left == null && node.right == null) {

            if (tar - node.val == 0) {
                List<Integer> base = new ArrayList<>(small);
                base.add(node.val);

                ans.add(base);

            }

            // //or
            // if (tar - node.val == 0) {
            // small.add(node.val); // caluculations
            // ans.add(new ArrayList<>(small));
            // small.remove(small.size() - 1); // remove the calculation here too

            // }

            return;
        }

        small.add(node.val);

        pathSum(node.left, tar - node.val, small, ans);

        pathSum(node.right, tar - node.val, small, ans);

        small.remove(small.size() - 1);

    }

    // LC: 114

}
