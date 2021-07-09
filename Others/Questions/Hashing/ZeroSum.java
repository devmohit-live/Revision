public class ZeroSum {
    // expl:
    // https://www.youtube.com/watch?v=xmguZ6GbatA&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=23
    // https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1#
    // Larget Arrays with sum=0

    int maxLen(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // prefixsum, lowestidexofthatprefixsum
        int sum = 0, len = 0, maxlen = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == 0) {
                len = i + 1; // index
            } else if (map.containsKey(sum)) {
                len = i - map.get(sum); // not +1 bcz in prefix sum we have count from l+1 and we subtract the l
            } else if (!map.containsKey(sum)) {
                map.put(sum, i);
            }

            maxlen = Math.max(maxlen, len);
        }
        return maxlen;
    }
}
