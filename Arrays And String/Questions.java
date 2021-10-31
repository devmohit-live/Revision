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

    // Leetcode 167 2sum 2
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int f = target - nums[i];
            if (map.containsKey(f)) {
                return new int[] { map.get(f), i + 1 };
            } else {
                map.put(nums[i], i + 1);
            }
        }

        return new int[] { -1, -1 };
    }

    // KSUM
    public void makeAns(List<List<Integer>> ans, List<List<Integer>> smallAns, int fix) {
        for (List<Integer> s : smallAns) {
            s.add(fix);
            ans.add(s);
        }
    }

    public void ksum(int[] arr, int k, int target) {
        KSum(arr, target, k, 0, nums.length - 1);
    }

    public List<List<Integer>> KSum(int[] nums, int target, int k, int si, int ei) {
        if (k == 2) {
            return 2sum(nums, target, si, ei);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = si; i < ei; i++) {
            int fix = nums[i];
            List<Integer> smallAns = KSum(nums, target - fix, k - 1, i + 1, ei);
            makeAns(ans, smallAns, fix);
            i++;

            // to remove duplicates
            while (i < ei && nums[i] == nums[i - 1])
                i++;
        }

        return ans;
    }

    // https:// practice.geeksforgeeks.org/problems/count-pair-sum5956/1# with
    // duplicates allowed

    // here the trick was to so ans + = map.get(tar - num[i]) as duplicate items
    // forms multiple answers, that why map not set
    // as freq needs to be stored

    int countPairs(int arr1[], int arr2[], int M, int N, int x) {
        int ans = 0;
        HashMap<Integer, Integer> set = new HashMap<>();
        for (int el : arr2)
            set.put(el, set.getOrDefault(el, 0) + 1);
        for (int i = 0; i < M; i++)
            if (set.containsKey(x - arr1[i]))
                ans += set.get(x - arr1[i]);

        return ans;
    }

    // Leetcode 454 : 4sum II
    // here th etrick was to combine 2 arrays into one find in O(1) for compliment
    // any combination is possible so maintainf frequncey is required
    // getting th ecompliment n time results in n answers

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int target = 0;
        for (int e : nums1) {
            for (int f : nums2) {
                map.put(e + f, map.getOrDefault(e + f, 0) + 1);
            }
        }

        int ans = 0;
        for (int e : nums3) {
            for (int f : nums4) {
                if (map.containsKey(target - (e + f))) {
                    ans += map.get(target - (e + f));
                }
            }
        }

        return ans;
    }

}
