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

    // 3sum Leetcode 15
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>();

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            // 2sum
            int j = i + 1, k = n - 1;
            while (j < k) {
                int curr = nums[i];
                curr += nums[j] + nums[k];

                if (curr == 0) {
                    List<Integer> small = new ArrayList<>();
                    small.add(nums[i]);
                    small.add(nums[j]);
                    small.add(nums[k]);
                    res.add(small);
                } else if (curr > 0) {
                    k--;
                } else
                    j++;

            }

        }

        return new ArrayList<>(res);
    }

}
