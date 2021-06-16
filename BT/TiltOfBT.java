class TiltOfBT {
    // Leetcode : 563
    static class MyPair {
        int tiltSF = 0;
        int sumSF = 0;
    }

    public static int tilt(Node node) {
        // MyPair ans = findTilt(node);
        // return ans.tiltSF;
        int[] ans = findTilt(node);
        return ans[0];
    }

    private static MyPair findTilt(Node node) {
        if (node == null)
            return new MyPair();

        MyPair myans = new MyPair();

        MyPair lTilt = findTilt(node.left);
        MyPair rTilt = findTilt(node.right);

        myans.tiltSF = lTilt.tiltSF + rTilt.tiltSF + Math.abs(lTilt.sumSF - rTilt.sumSF);
        myans.sumSF = lTilt.sumSF + rTilt.sumSF + node.data;
        return myans;
    }

    private static int[] findTilt2(Node node) {
        if (node == null)
            return new int[2];

        int[] myans = new int[2];

        int[] lTilt = findTilt2(node.left);
        int[] rTilt = findTilt2(node.right);

        myans[0] = lTilt[0] + rTilt[0] + Math.abs(lTilt[1] - rTilt[1]);
        myans[1] = lTilt[1] + rTilt[1] + node.data;
        return myans;
    }
}
