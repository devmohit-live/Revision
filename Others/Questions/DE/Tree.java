public class Tree {
    //max path sum 
    public int maxPathSum(TreeNode root) {
        return maxPathSum_(root).maxans;
    }

    class NTNS {
        int ntns = 0;
        int maxans = -(int) 1e9;
    }

    private int getMaximum(int... args) {
        int ans = -(int) 1e9;
        for (int el : args)
            ans = Math.max(ans, el);
        return ans;
    }

    private NTNS maxPathSum_(TreeNode root) {
        NTNS myans = new NTNS();
        if (root == null)
            return myans;

        NTNS left = maxPathSum_(root.left);
        NTNS right = maxPathSum_(root.right);
        int onesidedIncludingMe = root.val + Math.max(left.ntns, right.ntns);
        myans.maxans = getMaximum(left.maxans, right.maxans, root.val, onesidedIncludingMe,
                left.ntns + root.val + right.ntns);

        myans.ntns = Math.max(onesidedIncludingMe, root.val);

        return myans;
    }
        
}
