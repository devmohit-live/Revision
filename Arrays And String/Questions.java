import java.uti.*;
import java.util.Arrays;

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

    // rotate an array

    public void rotate(int[] nums, int k) {
        int n = nums.length;

        if (k < 0)
            k = n - k; // left to effective right rotate

        k = k % n;

        // or directly (used to put a number in a range(either given number is negative
        // or positive))
        // k = (k%n +n)%n ;

        if (n == 0 || n == 1 || k == 0 || n == k)
            return;

        k = n - k - 1;

        // why? demorgans's law (complement (complemet(a)) => a )
        reverse(nums, 0, k); // ~a
        reverse(nums, k + 1, n - 1); // ~b
        // now we have ~a + ~b
        reverse(nums, 0, n - 1); // ~(~a + ~b) => a+b => original array

    }

    private void reverse(int[] arr, int si, int ei) {
        while (si < ei) {
            int tmp = arr[si];
            arr[si] = arr[ei];
            arr[ei] = tmp;

            si++;
            ei--;
        }
    }

    // move all negative to start : partiton type
    public void moveNegatives(int[] arr) {
        int n = arr.length;
        int p = -1, itr = 0;

        System.out.println("Before: " + Arrays.toString(arr));
        while (itr < n) {
            if (arr[itr] < 0)
                swap(arr, ++p, itr);
            itr++;
        }

        System.out.println("After: " + Arrays.toString(arr));

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // O(2n)
    private void moveZerors01(int[] arr) {
        System.out.println("Before: " + Arrays.toString(arr));
        int idx = 0, n = arr.length;
        for (int i = 0; i < n; i++)
            if (arr[i] == 0)
                arr[idx++] = 0;
        for (int i = idx; i < n; i++)
            arr[i] = 1;
        System.out.println("After: " + Arrays.toString(arr));

    }

    // O(n), using same partition technique
    private void moveZerors(int[] arr) {
        System.out.println("Before: " + Arrays.toString(arr));
        int p = -1, itr = 0, n = arr.length;
        while (itr < n) {
            if (arr[itr] == 0)
                swap(arr, ++p, itr);
            itr++;
        }
        System.out.println("After: " + Arrays.toString(arr));
    }

    private void move012(int[] arr) {
        System.out.println("Before: " + Arrays.toString(arr));
        int p = -1, itr = 0, n = arr.length, p2 = n - 1;
        while (itr <= p2) {
            if (arr[itr] == 0) {
                swap(arr, ++p, itr);
                itr++;
            }

            else if (arr[itr] == 2) {
                swap(arr, itr, p2);
                p2--;
                // now check wheter the swaped elemetn which is now residing at itr maybe 0,1,2
                // basically continue;
                continue;
            } else // arr[itr] was 1
                itr++;
        }

        System.out.println("After: " + Arrays.toString(arr));
    }

    // LC: 532. K-diff Pairs in an Array

    /*
     * Similar to Two sum Count the elements with Counter If k > 0, for each element
     * i, check if i + k exist. If k == 0, for each element i, check if count[i] > 1
     * // appears more than once x-x => 0
     * 
     */
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        int res = 0;
        for (int x : cnt.keySet()) {
            if ((k > 0 && cnt.containsKey(x + k)) || (k == 0 && cnt.get(x) > 1)) {
                res++;
            }
        }
        return res;
    }

    //lC : 506 : Subarray Sum equals k : no sliding window as it contains -ve element
     /*
    I see ...After spending some time on the analysis, I found the reason behind having initialize preSum.put(0,1)....it is for those (sum - k) == 0 calculations which are valid subarrays but need to get counted. e.g. if k = 7 and sum = 7 (at second element for array is : 3, 4, 3, 8) at some iteration.....then sum - k = 0....this 0 will get counted in statement result += preSum.get(sum - k);

#############

So in conclusion, the initial entry preSum.put(0, 1) can be exchanged with statement :
if (sum == k) count++;
we can put it just below sum += nums[i]; statement. This will make code more intuitive...as we are trying to find the sum which matches to k!
    
    */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > sumOccurrencesMap = new HashMap < > ();
        sumOccurrencesMap.put(0, 1); // sum = 0, which will occur but not counted for first iteration/occurance
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumOccurrencesMap.containsKey(sum - k))
                count += sumOccurrencesMap.get(sum - k);
            sumOccurrencesMap.put(sum, sumOccurrencesMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    //1029: Two City Scheduing
    // Approach:
    // https://leetcode.com/problems/two-city-scheduling/discuss/667786/Java-or-C%2B%2B-or-Python3-or-With-detailed-explanation
    public int twoCitySchedCost(int[][] costs) {
        // try sending everyone to city A
        int cost = 0, n = costs.length;
        for (int[] el : costs)
            cost += el[0];

        // now try for send half persons to city B such that we can get refunds =>
        // we have send person to city a with cost x and if we try to send them for B
        // instead the difference of ticket prices will be refunded
        int[] refund = new int[n];

        for (int i = 0; i < n; i++)
            refund[i] = costs[i][1] - costs[i][0];
        // -ve value means we get refund +ve means we have to pay those extra
        Arrays.sort(refund); // put -ve at start
        for (int i = 0; i < n / 2; i++)
            cost += refund[i];

        return cost;

    }

}
