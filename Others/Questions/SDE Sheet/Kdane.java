public class Kdane {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int el : nums) {
            sum += el;
            max = Math.max(max, sum);
            sum = Math.max(0, sum);
        }
        return max;
    }
}
