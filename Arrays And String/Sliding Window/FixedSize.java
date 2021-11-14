public class FixedSize {

    // 1. Max sum in subarray of size k
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

    // 2. First negative element in window of size k
    // https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1#
    public long[] printFirstNegativeInteger(long arr[], int n, int k) {
        long[] ans = new long[n - k + 1];
        LinkedList<Long> q = new LinkedList<>();
        int ei = 0, si = 0, idx = 0;
        for (int i = 0; i < k; i++) {
            if (arr[ei] < 0)
                q.add(arr[ei]);
            ei++;
        }
        ans[idx++] = q.size() == 0 ? 0 : q.peek();
        while (ei < n) {
            if (arr[si] < 0)
                q.remove();
            si++;
            if (arr[ei] < 0)
                q.add(arr[ei]);
            ei++;

            ans[idx++] = q.size() == 0 ? 0 : q.peek();

        }
        q = null;
        return ans;
    }
}
