public class Questions {
    // Leetcode 34

    public int[] searchRange(int[] num, int target) {
        return new int[] { firstIndex(num, target), lastIndex(num, target) };
    }

    private static int firstIndex(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;

        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (data < arr[mid])
                ei = mid - 1;
            else if (data > arr[mid])
                si = mid + 1;
            else {
                // equal

                // more elements are present to left
                if (mid - 1 >= 0 && arr[mid - 1] == data)
                    ei = mid - 1;

                else
                    return mid;
            }
        }

        return -1;
    }

    private static int lastIndex(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;

        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (data < arr[mid])
                ei = mid - 1;
            else if (data > arr[mid])
                si = mid + 1;
            else {
                // equal

                // more elements are present to right
                if (mid + 1 < arr.length && arr[mid + 1] == data)
                    si = mid + 1;

                else
                    return mid;
            }
        }

        return -1;
    }

    // or
    private int firstIdx(int[] arr, int x) {
        int lo = 0, high = arr.length - 1, ans = -1;
        while (lo <= high) {
            int mid = lo + (high - lo) / 2;
            if (arr[mid] == x) {
                ans = mid;
                high = mid - 1; // more elements can be on left
                continue;
            }
            if (arr[mid] > x) {
                high = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private int lastIdx(int[] arr, int x) {
        int lo = 0, high = arr.length - 1, ans = -1;
        while (lo <= high) {
            int mid = lo + (high - lo) / 2;
            if (arr[mid] == x) {
                ans = mid;
                lo = mid + 1; // more elements can be on right
                continue;
            }
            if (arr[mid] > x) {
                high = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    // closest to the target (if present then return target) else return closes to
    // it (left oriented)
    int closestPoint(int[] arr, int tar) {
        int si = 0, ei = arr.length - 1, diff = (int) 1e9, ans = -1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] == tar)
                return tar;

            if (diff < Math.abs(tar - arr[mid])) {
                diff = Math.abs(tar - arr[mid]);
                ans = arr[mid];
            }

            else if (arr[mid] > tar) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return ans;
    }

    // returns index
    // or : si,ei will be actual ceil and floor in the end at last si crosses ei and
    // beomes ceil, ei becoomes floor
    int closestPointBetter(int[] arr, int tar) {
        int si = 0, ei = arr.length - 1;

        // corner
        if (data < arr[si])
            return si;
        else if (data > arr[ei])
            return ei;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return data - arr[ei] < arr[si] - data ? ei : si;
    }

    // perfect location :

    int perfectLocation(int[] arr, int tar) {
        if (arr[0] > tar)
            return 0;
        int n = arr.length;
        if (arr[n - 1] < tar)
            return n;
        else {
            int si = 0, ei = n - 1;
            while (si <= ei) {
                int mid = si + (ei - si) / 2;
                if (arr[mid] > tar) {
                    ei = mid - 1;
                } else if (arr[mid] < tar) {
                    si = mid + 1;
                } else {

                    if (mid + 1 <= ei && arr[mid + 1] > tar)
                        si = mid + 1;
                    else
                        return mid + 1;

                }
            }
        }
        return -1;
    }

    // or perfect location: ei will be pointing to the potential index

    // gives last idx in case of repeated elements ex: [1,2,5,5,5,5,7,8,9] tar= 5 =?
    // gives=>5
    // if you want idx = 2 in this case that is first idx remove the ''='' condition
    // from if

    int perfectLocationBetter(int[] arr, int tar) {
        int si = 0, ei = arr.length; // not -1 as element can be added to last
        // as here we don't want si to cross ei(potential index)
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] <= tar)
                si = mid + 1; // damn sure about next idx as me or previos are lesser
            else
                ei = mid; // not mid-1 as it can be the case that from 0 to mid-1 be the same duplicate
                          // element == tar

        }

        return ei;
    }

    // Important : UpperBound => idx of key or greater than that (whichever
    // exits)(if both doesb't exitsts returns arr.length)(return s the first idx in
    // case of duplicates too)

    private int upperbound(int[][] events, int el) {
        int si = 0, ei = n - 1;

        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (events[mid][0] >= el)
                ei = mid;
            else
                si = mid + 1;
        }

        if (events[si][0] >= el)
            return si;
        return n;

    }

    // floor and ceil (lower and upper bound of al given key)
    // if key exists that key will be lb as welll as ub,
    // if lb/ub desn't exixts -1 will be returned
    // in case of duplicates it it not sure that the first index of the lemenet is
    // retunred.
    // in case you want a surety of first idx pass that elemet obtained from lb to
    // first idx function

    private static int[] floorAndCeil(int[] arr, int data) {
        int si = 0, ei = arr.length - 1;

        if (data < arr[si])
            return new int[] { -1, si };
        else if (data > arr[ei])
            return new int[] { ei, -1 };

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data)
                return new int[] { mid, mid };
            else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return new int[] { ei, si }; // floor,ceil(si crosses over ei)
    }

    // Leetcode 74: Search in 2d
    // 2d to 1d binary search
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        if (n == 0 && m == 0)
            return false;
        int high = m * n - 1, low = 0; // 2d to 1d approach

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int r = mid / m;
            int c = mid % m;
            if (matrix[r][c] == target) {
                System.out.println("Fpund at " + r + " row and " + c + " column");
                return true;
            } else if (matrix[r][c] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }
        System.out.println("Not Found " + -1);
        return false;
    }

    // GFG:
    // https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1#

    public static long totalInversionCount(long[] arr, long[] sortedArray, long si, long mid, long ei) {
        int i = (int) si, j = (int) mid + 1, k = (int) si;
        long count = 0;

        while (i <= mid && j <= ei) {
            if (arr[i] <= arr[j])
                sortedArray[k++] = arr[i++];
            else {
                sortedArray[k++] = arr[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid || j <= ei)
            sortedArray[k++] = arr[i <= mid ? i++ : j++];

        while (si <= ei)
            arr[(int) si] = sortedArray[(int) si++];

        return count;
    }

    // Sqrt Leetcode 69
    public int mySqrt(int x) {
        // binary searh
        // putting high = x/2 fails at the case for x == 1
        // always take long when you are not sure about the last/first limit range of
        // container
        long low = 0, high = x, ans = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (mid * mid <= x) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid - 1;
            }

        }

        return (int) ans;

    }

    public static long inversionCount(long[] arr, long[] sortedArray, long si, long ei) {
        if (si >= ei)
            return 0;

        long mid = (si + ei) / 2;
        long count = 0;

        count += inversionCount(arr, sortedArray, si, mid);
        count += inversionCount(arr, sortedArray, mid + 1, ei);

        count += totalInversionCount(arr, sortedArray, si, mid, ei);
        return count;
    }

    public static long inversionCount(long arr[], long N) {
        if (N == 0)
            return 0;

        long[] sortedArray = new long[(int) N];
        return inversionCount(arr, sortedArray, 0, N - 1);
    }

    // Leetcode 658. Find K Closest Elements

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        List<Integer> ans = new ArrayList<>();
        if (x <= arr[0]) {
            for (int i = 0; i < k; i++)
                ans.add(arr[i]);
            return ans;
        }

        else if (x >= arr[n - 1]) {
            for (int i = n - k; i < n; i++)
                ans.add(arr[i]);
            return ans;
        }

        int idx = perfectLocationBetter(arr, x);
        int si = Math.max(0, idx - k);
        int ei = Math.min(n - 1, idx + k);

        while ((ei - si + 1) > k) {
            if ((x - arr[si]) > (arr[ei] - x))
                si++;
            else
                ei--;
        }
        for (int i = si; i <= ei; i++)
            ans.add(arr[i]);

        return ans;
    }

    // Leetcode 33
    public int search(int[] nums, int target) {
        int si = 0, ei = nums.length - 1;

        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (nums[mid] == target)
                return mid;

            // check for sorted part
            // left part is sorted
            if (nums[si] < nums[mid] || si == mid) {
                if (nums[si] <= target && target < nums[mid]) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }

            } else {
                // right part sorted
                if (nums[mid] < target && target <= nums[ei]) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            }
        }

        return -1;
    }

    // TODO: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/ 81

    // leetcode 153
    public int findMin(int[] nums) {
        int n = nums.length, si = 0, ei = n - 1;

        while (si < ei) {
            int mid = si + (ei - si) / 2;

            if (nums[mid] < nums[ei]) {
                // right part is sorted
                // go left
                ei = mid;
            } else {
                si = mid + 1;
            }

        }
        return nums[si];
    }
    // TODO: 154 => min in sorted rotaated array

    // Leetcode 875 : koko and bananas
    // Approach : in this question how we think that we should use binary search:
    // Wherever searching something is required look for binary search(sorted??? =>
    // indexes are alaways sorted)
    // When you somehow know the range of your answer (x--->y) which is
    // increasing/decreasing you can apply binary search

    public int minEatingSpeed(int[] arr, int h) {
        // set the range for bs

        int si = 1, ei = -1;
        for (int el : arr)
            ei = Math.max(ei, el);
        // mid = > speed

        while (si < ei) {
            int mid = si + (ei - si) / 2;

            if (!iSpeedPossible(arr, mid, h)) {
                // look for greater hours : go right
                si = mid + 1;
            } else {
                // found but try to look for minimum possible value
                ei = mid;
            }
        }

        return si;

    }

    private boolean iSpeedPossible(int[] arr, int speed, int hr) {
        int time = 0;
        for (int el : arr) {
            time += el / speed;
            if (el % speed != 0)
                time++;

        }
        return time <= hr;

    }

    // similar question but instead of speed here we are asked for the capacity is
    // so taht work is finihed within that day

    // also here we can use multiple elements of array at a time

    // Leetcode 1011 : Capacity To Ship Packages Within D Day
    public int shipWithinDays(int[] arr, int days) {
        int si = (int) 1e9, ei = 0;

        for (int el : arr) {
            ei += el; // max range of bs here would be sum
            si = Math.min(si, el);
        }

        // si<ei ,so ei = mid
        while (si < ei) {
            int mid = si + (ei - si) / 2;

            if (!isDayPossible(arr, mid, days)) {
                // move to higher values;
                si = mid + 1;
            } else {
                ei = mid;
            }
        }
        return si;

    }

    private boolean isDayPossible(int[] arr, int capacity, int lim) {
        int total_days = 1, curr = 0;
        for (int i = 0; i < arr.length; i++) {
            // if single elemet is higher than the cap => not possible
            if (arr[i] > capacity)
                return false;

            curr += arr[i];
            if (curr > capacity) {
                total_days++;
                curr = arr[i];
            }

        }

        return total_days <= lim;

    }

    // Leetcode 441 : Arrange Coins: BS + AP series
    // O(log n)(faster)
    public int arrangeCoinsBS(int n) {
        // binary search and ap
        // m*(m+1)/2 = sum => no of coins

        long si = 0, ei = n;
        while (si <= ei) {
            long mid = si + (ei - si) / 2;
            long sum = mid * (mid + 1) / 2;

            if (sum == n)
                return (int) mid;
            else if (sum > n) {
                ei = mid - 1;
            } else
                si = mid + 1;
        }

        return (int) ei;
    }

    // Leetcode 162:
    public int findPeakElement(int[] nums) {
        int lo = 0, high = nums.length - 1;
        while (lo < high) {
            int mid = lo + (high - lo) / 2;
            // extremes are -infi
            if (nums[mid] > nums[mid + 1]) {
                // we are assured the left contains 1 peak
                high = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    // Leetcode 209
    public int minSubArrayLen(int target, int[] nums) {
        // bs on answers
        int n = nums.length;
        int si = 1, ei = n + 1; // start point is size of window 1, end point is size of window n
        // but since si<ei is the condition(ei=mid) to enclude n too we have taken ei =
        // n+1
        // can't so si<=ei ,as tle => si == ei == mid(repeated)
        boolean isPossible = false;
        while (si < ei) {
            int mid = si + (ei - si) / 2;

            if (slw(nums, target, mid)) {
                isPossible = true;
                // try/search for lower window size
                ei = mid;
            } else {
                si = mid + 1;
            }
        }
        return isPossible ? si : 0;

    }

    private boolean slw(int[] arr, int target, int k) {
        int n = arr.length, sum = 0;
        ;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int max = sum;
        int l = 0, r = k - 1; // window start,end point
        // end point of window is not at last idx push the slider

        while (r < n - 1) {
            sum -= arr[l];
            l++;
            r++;
            sum += arr[r];

            max = Math.max(max, sum);
        }

        return (max >= target);
    }

    // Leetcode:1413. Minimum Value to Get Positive Step by Step Sum
    // can also be done by maintainng prefix sum in a var
    public int minStartValue(int[] nums) {
        int n = nums.length;

        // ei = no of el * max value of that el possible(contraint)
        // as all elements can be -ve in nums
        int si = 1, ei = n * 100 + 1;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (isValidStart(nums, mid)) {
                // look for lower possible start
                ei = mid;
            } else {
                si = mid + 1;
            }

        }

        return si;
    }

    private boolean isValidStart(int[] arr, int start) {
        for (int el : arr) {
            start += el;
            if (start < 1)
                return false;
        }
        return true;
    }

    // Leetcode 4. Median of Two Sorted Arrays

    // Approach 1 : Using merge 2 sorted aArrays => TIme = O(m+n), Space = > O(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] arr = mergerSortedArrays(nums1, nums2, n, m);

        // find mid;
        int mid = arr.length / 2;
        if (arr.length % 2 == 0) {
            return (arr[mid] + arr[mid - 1] * 1.0) / 2;
        }
        return arr[mid];
    }

    private int[] mergerSortedArrays(int[] nums1, int[] nums2, int n, int m) {
        int[] ans = new int[m + n];
        int i = 0, j = 0, idx = 0;
        // make combined sorted array
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                ans[idx++] = nums1[i];
                i++;
            } else {
                ans[idx++] = nums2[j];
                j++;
            }
        }

        while (i < n) {
            ans[idx++] = nums1[i++];
        }
        while (j < m) {
            ans[idx++] = nums2[j++];
        }
        return ans;

    }

    // Appraoch Binary Search (Finding the correct position of partiton (as arrays
    // are sorted))
    // valid condition of partitioning at position x : max(ax,bx) <= min(ax+1,bx+1)
    // =>(O(1) as arrays is sorted)

    // leetocde 668 Kth Smallest Number in Multiplication Table
    // Using pq gives memory limit exceeded
    // elimination staratergy
    // Approach 1: Using pq (K >>> large) (memory limit exceed)
    private int findKthNumberPQ(int m, int n, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a[0], j1 = a[1], i2 = b[0], j2 = b[1];
            int el1 = (i1 + 1) * (j1 + 1), el2 = (i2 + 1) * (j2 + 1);
            return el2 - el1; // max pq
        });

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {

                pq.add(new int[] { i, j });

                if (pq.size() > k)
                    pq.remove();

                // int el = (i+1)*(j+1);
                // int x = i*r + j;
                // v[x] = el;
            }

        // System.out.println("Array is "+ Arrays.toString(v));
        int[] rm = pq.remove();
        int el = (rm[0] + 1) * (rm[1] + 1);
        return el;
    }

    // Using BS in answes
    // Approach bs(on answers): since we know that we have to search something
    // from min(mat)=1, to max(mat)=m*n => bs

    // we also know that the row in intself will bw sorted (multiplication keeps on
    // increasing)

    // find the count of numbers smaller than the mid if that count == k (it can be
    // the possible ans)
    // mid will act as the kth number having k numbers <= itself
    public int findKthNumber(int m, int n, int k) {
        int si = 1, ei = m * n;
        // countOfLesser describes whether mid is large enough to be the k^th value in
        // the multiplication table.
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (countOfLesser(mid, k, m, n) >= k) {
                ei = mid;
            } else
                si = mid + 1;
        }

        return si;

    }

    /*
     * counting how many values are less than or equal to mid. For each of m rows,
     * the ith row looks like[i, 2*i, 3*i, ..., n*i].
     * 
     * The largest possible \text{k*i ≤ x} that could appear is \text{k = x // i}k =
     * x // i. However, if \text{x}x is really big, then perhaps \text{k > n}k > n,
     * so in total there are \text{min(k, n) = min(x // i, n)}min(k, n) = min(x //
     * i, n) values in that row that are less than or equal to \text{x}x.
     */
    private int countOfLesser(int mid, int k, int m, int n) {
        // basically counting numbers less than mid
        // mid/i => numer/mid <=0 => number is lesser than or equal to mid
        // min(mid/i,n) => if all numbers are divisble than n(columns)
        int count = 0;
        for (int i = 1; i <= m; i++)
            count += Math.min(mid / i, n);

        return count;
    }

}
