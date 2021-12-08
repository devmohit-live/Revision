import java.util.HashMap;

public class SubaarySumByK {
    /*
     * We need to track the frequency of the orccurence of remainder of the
     * cumulative sum. If a remainder occurs twice, it means the sum between these 2
     * are divisible by k, and hence the subarray between these two adds to the
     * solution.
     * 
     * 1. Keep getting the cumulative sum
     * 
     * 2. Calculate the remainder and add K to it if it is negative
     * 
     * 3. If the remainder has occured before, it means anything between these two,
     * plus the number of times the remainder came before, those many sub arrays are
     * divisible by k. So add the already seen frequency to the response
     * 
     * 4. Update the frequency now and continue
     * 
     * Note: 0 is seen once in the beginning, ie, when no element is added in
     * cumSum, cumSum = 0 and so 0 has to initialize with frequency 1
     */
    class Solution {
        public int subarraysDivByK(int[] nums, int K) {
            if (nums == null || nums.length == 0)
                return 0;

            int ans = 0;
            int remSum = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1); // remainder 0 : multiple of k (for first occurance of multiple of k we have to
                           // add +1)

            for (int i = 0; i < nums.length; i++) {
                remSum += nums[i];
                int temp = remSum % K; // complement
                if (temp < 0)
                    temp += K; // as numbers can be negative so suing mod property

                if (map.containsKey(temp)) {
                    ans += map.get(temp);
                }
                // finding the complement of numbers
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
            return ans;
        }

    }
}