public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int[] res = new int[2];

        // doing it into single pass : adding in map at last hadles the case when
        // res[0],res[1] = points to same index => duplicates in array causes this case
        for (int i = 0; i < n; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i], i);
        }

        return res;
    }

    /*
     * Input: nums = [3,3], target = 6 Output: [0,1]
     * 
     * // if we do it in 2 pass , 1 for adding element in map .. 2nd fpr operation,
     * then result would be 0,0 map(3,1) is updated by 3,0
     */
}