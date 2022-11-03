public class SlidingWindowMax {
    // Application of ArrayDequeue

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length, idx = 0;
        LinkedList<Integer> dq = new LinkedList<>();
        int[] ans = new int[n - k + 1];
        // we stores max in descending order// actual storage = index
        // remove anything which is out of range fron front
        // remove all the elemts which are min from the back => no use
        // check max fron front

        for (int i = 0; i < n; i++) {
            // remove numbers out of range k
            if (!dq.isEmpty() && dq.getFirst() <= i - k) {
                dq.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!dq.isEmpty() && nums[dq.getLast()] <= nums[i]) {
                dq.removeLast();
            }

            dq.addLast(i);
            if (i >= k - 1) {
                ans[idx++] = nums[dq.getFirst()];
            }
        }
        return ans;

    }

    // tle
    // public int[] maxSlidingWindow(int[] nums, int k) {
    // int n = nums.length;

    // int[] ans = new int[n-k+1];

    // for(int i=0;i<n-k+1;i++){
    // int max = Integer.MIN_VALUE;
    // for(int j=0;j<k;j++){
    // max = Math.max(max,nums[i+j]);
    // }
    // ans[i] = max;
    // }

    // return ans;

    // }

}
