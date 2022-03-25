package Others.Questions.AMZ.In;

public class MaximumSubarray_Kdane_53 {
    public int maxSubArray(int[] nums) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int el : nums) {
            sum = Math.max(el, sum + el);
            max = Math.max(max, sum);
        }

        return max;
    }
}
