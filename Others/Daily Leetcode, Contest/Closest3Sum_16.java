public class Closest3Sum_16 {
    public int threeSumClosest(int[] nums, int target) {
        int i = 0, n = nums.length, min = Integer.MAX_VALUE, csum = 0;
        Arrays.sort(nums);

        while (i < n) {
            int a = nums[i], j = i + 1, k = n - 1;
            // 2sum
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (min > Math.abs(target - sum)) {
                    min = (int) Math.abs(target - sum);
                    csum = sum;
                }
                if (sum > target)
                    k--;
                else if (sum < target)
                    j++;
                else {
                    return sum;
                }
            }

            i++;
        }

        return csum;
    }
}
