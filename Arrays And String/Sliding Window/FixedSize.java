public class FixedSize {

    // 1.
    // https://practice.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1#
    static int maximumSumSubarray(int k, ArrayList<Integer> arr, int n) {
        int si = 0, ei = 0, sum = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++)
            sum += arr.get(ei++);
        max = Math.max(sum, max); // or max = sum

        while (ei < n) {
            sum -= arr.get(si++);
            sum += arr.get(ei++);
            max = Math.max(max, sum);
        }

        return max;
    }
}
