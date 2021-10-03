public class LCS {
    // Leetcode 128
    class Solution {
        public int longestConsecutive(int[] nums) {
            if (nums.length == 0 || nums.length == 1)
                return nums.length;

            Set<Integer> set = new HashSet<>();
            for (int el : nums)
                set.add(el);
            int max = 0;
            for (int el : nums) {
                int tmp = el;
                int count = 1;

                /* Main zist */

                // we will only start countinh from mininmla number
                // else for every numer we will check with while loop making O(n*n)
                if (!set.contains(tmp - 1)) {
                    while (set.contains(++tmp))
                        count++;
                    max = Math.max(max, count);
                }

                /**/

            }
            return max;

        }
    }
}
