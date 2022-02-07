public class SumTechnquies_Cover {
    // LC 968. Binary Tree Cameras
    public int minCameraCover(TreeNode root) {
        int[] cameras = new int[1];
        int rootres = minCameraCover(root, cameras);
        if (rootres == -1)
            cameras[0]++;
        return cameras[0];
    }

    // -1: camera is required, 0: covered, 1: already have a camera on it
    private int minCameraCover(TreeNode root, int[] cameras) {
        if (root == null)
            return 0;
        int lr = minCameraCover(root.left, cameras);
        int rr = minCameraCover(root.right, cameras);
        // if any of the child is left uncovered: I have to apply camera on myself
        if (lr == -1 || rr == -1) {
            cameras[0]++;
            return 1; // putted a camera
        }
        // any of them is/are covered by their child, so to cover myself I require a
        // camera: pass this info to parent
        if (lr == 0 && rr == 0) {
            return -1;
        }

        return 0;
    }

    // LC 337 : House RObberIII
    class ROB {
        int robbingCurrent, leavingCurrent;

        ROB() {
            this.robbingCurrent = robbingCurrent;
            this.leavingCurrent = leavingCurrent;
        }

    }

    public int rob(TreeNode root) {
        ROB ans = robbery(root);
        return Math.max(ans.robbingCurrent, ans.leavingCurrent);
    }

    public ROB robbery(TreeNode root) {
        if (root == null)
            return new ROB();

        ROB left = robbery(root.left);
        ROB right = robbery(root.right);

        // robbing current house : can't rob next adjancent houses (left,right)
        ROB myans = new ROB();
        myans.robbingCurrent = root.val + left.leavingCurrent + right.leavingCurrent;

        // not robbing this house : 2 choice either to rob the adjacent houses or leave
        // them too
        myans.leavingCurrent = Math.max(left.leavingCurrent, left.robbingCurrent)
                + Math.max(right.leavingCurrent, right.robbingCurrent);
        return myans;
    }

}
