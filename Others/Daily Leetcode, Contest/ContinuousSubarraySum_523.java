public class ContinuousSubarraySum_523 {
    // explanation: say the the difference is d between a and b, such as d = b - a(b
    // is on the right of a). you want d is multiple of k, so you just need d % k =
    // 0. Because d = b - a, so d % k = 0 = (b - a) %k. so (b-a)%k=0 equal b%k - a%k
    // = 0, then b%k = a%k. Comparing other hashtable based problem, you need check
    // b-k whether in the hashtable. In this problem , you always check b%k, and
    // always pust a%k into hashtable. when k = 0, you need do it as other similar
    // problem.

    public boolean checkSubarraySum(int[] nums, int k) {
        final int N = nums.length;
        if (N <= 1 || k == 0)
            return false;
        if (k == 1)
            return true;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1)
                    return true;
            } else
                map.put(runningSum, i);
        }
        return false;
    }
}
