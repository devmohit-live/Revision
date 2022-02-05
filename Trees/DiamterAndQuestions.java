/**
 * DiamterAndQuestions
 */
public class DiamterAndQuestions {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int height(TreeNode root) {
        if (root == null)
            return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        // return dia(root);
        // return dia2(root)[0];
        int[] dia = new int[1]; // static
        dia3(root, dia);
        return dia[0];

    }

    // dia 1 : O(n^2)
    private int dia(TreeNode root) {
        if (root == null)
            return 0;
        int ld = dia(root.left);
        int rd = dia(root.right);
        int lh = height(root.left);
        int rh = height(root.right);
        int mydia = Math.max(Math.max(ld, rd), lh + rh + 2);// either left or irhgt or passing through root
        return mydia;

    }

    // returning dia and height both
    private int[] dia2(TreeNode root) {
        if (root == null)
            return new int[] { 0, -1 };
        int[] ld = dia2(root.left);
        int[] rd = dia2(root.right);
        int[] myans = new int[2];
        myans[1] = Math.max(ld[1], rd[1]) + 1; // height
        myans[0] = Math.max(ld[1] + rd[1] + 2, Math.max(ld[0], rd[0]));
        return myans;

    }

    // retutning height and static is saving max of left and right diameter till now
    // : faith
    private int dia3(TreeNode root, int[] ans) {
        if (root == null)
            return -1;
        int lh = dia3(root.left, ans);
        int rh = dia3(root.right, ans);
        ans[0] = Math.max(lh + rh + 2, ans[0]);
        return Math.max(lh, rh) + 1;
    }

    // LC : 112 ; Path sum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            return (targetSum - root.val) == 0;
        }

        boolean ans = hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        return ans;
    }

    // LC 113: Path Sum 2
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();

        pathSum(root, targetSum, small, ans);
        return ans;
    }

    private void pathSum(TreeNode node, int tar, List<Integer> small, List<List<Integer>> ans) {

        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            if (tar - node.val == 0) {
                small.add(node.val); // caluculations
                ans.add(new ArrayList<>(small));
                small.remove(small.size() - 1); // remove the calculation here too

            }
            return;
        }

        small.add(node.val);

        pathSum(node.left, tar - node.val, small, ans);
        pathSum(node.right, tar - node.val, small, ans);

        small.remove(small.size() - 1);
    }

}