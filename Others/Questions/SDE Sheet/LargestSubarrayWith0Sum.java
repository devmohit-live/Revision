public class LargestSubarrayWith0Sum {
    
    class GfG {
       private int maxLen(int arr[], int n) {

            if (n == 0)
                return 0;
            Map<Integer, Integer> prefixSumOccurance = new HashMap<>();
            int prefixSum = 0, max = 0;
            prefixSumOccurance.put(0, -1); // defualt
            for (int i = 0; i < n; i++) {
                prefixSum += arr[i];
                if (prefixSum == 0)
                    max = Math.max(max, i); // till now sum is 0
                if (prefixSumOccurance.containsKey(prefixSum)) {
                    // check for previous sum occurance
                    int prevIdx = prefixSumOccurance.get(prefixSum);
                    int diff = i - prevIdx;
                    max = Math.max(max, diff);
                } else
                    prefixSumOccurance.put(prefixSum, i);
            }

            return max;
        }
    }
}
