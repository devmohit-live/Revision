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

    // Proper way of writing Sliding windoe : Template

    // Leetocde 567 : Permutaion in String
    // Basically anagrams of s1 in present in a s2
    public boolean checkInclusion(String pat, String s) {
        int pl = pat.length(), n = s.length();
        int si = 0, ei = 0, k = pl;
        int[] freq = new int[26];
        for (int i = 0; i < pl; i++) {
            freq[pat.charAt(i) - 'a']++;
        }

        // format of sliding window
        while (ei < n) { // <n
            // do the main ei type opearation beforehand
            freq[s.charAt(ei) - 'a']--;

            // if less than window size increase the ei
            if (ei - si + 1 < k)
                ei++;
            // check for window size is equal to required
            else {
                // operations => min/max
                if (isValid(freq))
                    return true;
                // remove the el from window
                freq[s.charAt(si) - 'a']++;
                // shift the pointers/window
                si++;
                ei++;
            }
        }

        return false;

    }

    private boolean isValid(int[] freq) {
        for (int el : freq)
            if (el != 0)
                return false;
        return true;
    }
}
