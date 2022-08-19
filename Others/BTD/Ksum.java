import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ksum {
    public List<List<Integer>> kSum(int[] nums, int k, int target) {
        Arrays.sort(nums);
        if (k <= 1) {
            List<List<Integer>> res = new ArrayList<>();
            if (k == 1 && Arrays.binarySearch(nums, target) >= 0) {
                res.add(Collections.singletonList(target));
            }
            return res;
        }
        return kSum(nums, k, target, 0);
    }

    private List<List<Integer>> kSum(int[] nums, int k, int target, int idx) {
        int len = nums.length;
        if (k == 2) {
            List<List<Integer>> res = new ArrayList<>();
            int left = idx, right = len - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(new ArrayList<>(Arrays.asList(nums[left], nums[right])));
                    while (left < right && nums[left] == nums[left + 1]) {
                        ++left;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        --right;
                    }
                    ++left;
                    --right;
                } else if (sum < target) {
                    ++left;
                } else {
                    --right;
                }
            }
            return res;
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = idx; i <= len - k; i++) {
            if (i > idx && nums[i] == nums[i - 1] || nums[i] + (k - 1) * nums[len - 1] < target) {
                continue;
            }
            if (nums[i] + (k - 1) * nums[i + 1] > target) {
                break;
            }
            List<List<Integer>> sub = kSum(nums, k - 1, target - nums[i], i + 1);
            for (List<Integer> list : sub) {
                list.add(0, nums[i]);
            }
            res.addAll(sub);
        }
        return res;
    }
}
