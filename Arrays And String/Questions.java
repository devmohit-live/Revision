import java.uti.*;

public class Questions {
    // leetcode 1 : Two Sum :
    // tim effivient:
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
    // space efficient : here idexes will change so not relevant to leetcode
    // question
    // sort it first=> apply 2 pointers

}
