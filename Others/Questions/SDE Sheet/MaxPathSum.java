public class MaxPathSum {
    int max = Integer.MIN_VALUE;;

    public int maxPathSum(TreeNode root) {
        getPathSum(root);
        return max;
        // return maxPathSum_(root).maxans;
    }

    private int getPathSum(TreeNode root) {
        if (root == null)
            return 0;
        int left = Math.max(0, getPathSum(root.left));
        int right = Math.max(0, getPathSum(root.right));

        int curvedPath = left + right + root.val; //
        int straightPath = Math.max(left, right) + root.val;
        max = getMaximum(curvedPath, straightPath, root.val, max);
        return straightPath;
    }

    private int getMaximum(int... arr) {
        int max = Integer.MIN_VALUE;
        for (int el : arr)
            max = Math.max(el, max);
        return max;
    }

    // ntn(src to des): max (starightpath, root.val)
    class NTNS {
        int ntns = 0;
        int maxans = -(int) 1e9;
    }

    private NTNS maxPathSum_(TreeNode root) {
        NTNS myans = new NTNS();
        if (root == null)
            return myans;

        NTNS left = maxPathSum_(root.left);
        NTNS right = maxPathSum_(root.right);
        int straightPath = root.val + Math.max(left.ntns, right.ntns);
        myans.ntns = Math.max(straightPath, root.val);

        myans.maxans = getMaximum(left.maxans, right.maxans, root.val, straightPath, left.ntns + root.val + right.ntns);

        return myans;
    }
}
