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

    // 4sum Leetcode 18
    public List<List<Integer>> fourSum(int[] arr, int target) {
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 3)
            return res;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int tar = target - (arr[i] + arr[j]);
                int front = j + 1, back = n - 1;

                while (front < back) {
                    if (arr[front] + arr[back] < tar)
                        front++;
                    else if (arr[front] + arr[back] > tar)
                        back--;
                    else {
                        List<Integer> small = new ArrayList<>();
                        small.add(arr[i]);
                        small.add(arr[j]);
                        small.add(arr[front]);
                        small.add(arr[back]);

                        res.add(small);
                        int tmpfront = front, tmpback = back;
                        // Handle duplicates for fron and back

                        while (front < back && arr[tmpfront] == arr[front])
                            front++;
                        while (front < back && arr[tmpback] == arr[back])
                            back--;
                    }

                }

                // +1 < n bcz for loop will also update the j and i
                // handle duplicate j's
                while (j + 1 < n && arr[j] == arr[j + 1])
                    j++;
            }

            // handle duplicate i's
            while (i + 1 < n && arr[i] == arr[i + 1])
                i++;

        }

        return res;
    }

    // KSUM

}
