import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * MaxKSumPairs_1679
 */
public class MaxKSumPairs_1679 {

    public int maxOperations(int[] nums, int k) {
        return optimal(nums, k);
    }

    // Time : nlogn, Space: O(1)
    private int Sorting(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        if (n < 2 || k == 0)
            return 0;
        Arrays.sort(nums);

        int i = 0, j = n - 1, count = 0;
        while (i < j) {
            long sum = nums[i] + nums[j];
            if (sum > k)
                j--;
            else if (sum < k)
                i++;
            else {
                i++;
                j--;
                count++;
            }
        }
        return count;
    }

    // Time : n, Space: O(n)
    private int optimal(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int el : arr) {
            if (map.getOrDefault(k - el, 0) > 0) {
                count++;
                map.put(k - el, map.get(k - el) - 1);
            } else {
                map.put(el, map.getOrDefault(el, 0) + 1);
            }
        }

        return count;
    }

}