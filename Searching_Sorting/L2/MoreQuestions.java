public class MoreQuestions {
    public static void main(String[] args) {
        int arr[] = new int[] { 3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };
        System.out.println(findInInfi(arr, 10));
    }

    public char nextGreatestLetter(char[] arr, char target) {
        int n = arr.length, si = 0, ei = n - 1;
        // wrap around: ex: target = z and no element > z so return a(0)

        // in case of ceil type return si
        // in case of floor types return ei

        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            char ch = arr[mid];
            if (ch > target) {
                // look for more smaller ele
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return arr[si % n];
    }

    // https://
    // www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/#:~:text=If%20the%20array%20is%20infinite,bounds%20to%20apply%20binary%20search.&text=Let%20low%20be%20pointing%20to,and%20double%20the%20high%20index.
    // so basically in this question we are first going in reverse order of divide
    // and conquer(binary search) to find the range for searching the element
    // since it is an infinte array: we can't use length;
    public static int findInInfi(int[] nums, int tar) {
        // find the appropriate range to search the element -> log n

        int si = 0, ei = 1, len = ei - si + 1;
        while (tar > nums[ei]) {
            si = ei + 1;
            ei = ei + len * 2;
        }

        // /apply bs
        return bs(nums, si, ei, tar);
    }

    private static int bs(int[] nums, int si, int ei, int tar) {
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (nums[mid] == tar)
                return mid;
            else if (tar > nums[mid])
                si = mid + 1;
            else
                si = mid - 1;
        }
        return -1;
    }

    // binary search in order sorted array: order is not confirmed
    public static int binarySearchOrder(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;
        boolean isAsc = arr[0] <= arr[ei];
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] == tar)
                return mid;
            else if (tar > arr[mid]) {
                if (isAsc) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            } else {
                if (isAsc) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            }
        }
        return -1;
        // not found
    }

    // Leetcode 162,852 exactly same
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length, si = 0, ei = n - 1;
        // we have to find the peak(greatest el in between)
        while (si < ei) {
            int mid = si + (ei - si) / 2;

            if (arr[mid] > arr[mid + 1]) { // chunck is in descending order hence greater elemetns would lie on left
                                           // part
                ei = mid; // mid is greater it can be possible ans
            } else {// chunck is in ascending order hence greater elemetns would lie on left part
                si = mid + 1; // bc mid+1 is greater and can be the possible ans
            }
            // when si == ei => both are pointing to the same element (hence peak)
        }

        return si; // return the index

    }

    // Pivot != peak
    // Pivot [5,6,7,8,1,2,3,4] => increasing ->8(pivot)-> increasing from another
    // start point
    // Peak [5,6,7,8,4,3,2] => increasing->8(peak)->decreasing

    // find no of rotations done in the array
    // https://practice.geeksforgeeks.org/problems/rotation4723/1#
    int findKRotation(int arr[], int n) {
        return findPivot(arr) + 1;
    }

    private int findPivot(int[] arr) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (mid + 1 < n && arr[mid] > arr[mid + 1])
                return mid;
            if (mid - 1 >= 0 && arr[mid - 1] > arr[mid])
                return mid - 1;
            else if (arr[mid] <= arr[si]) {
                // bigger ele is towars start
                ei = mid; // mid can be the answer
            } else {
                si = mid + 1;
            }
        }
        return -1; // no pivot found already in sorted no rotation
    }

    // leetcode 33 : search in rotated sorted array(distinct numbers)
    public int search(int[] nums, int target) {
        int n = nums.length;
        int pivotIdx = findPivot(nums);

        if (pivotIdx == -1)
            return bs(nums, 0, n - 1, target);

        if (nums[pivotIdx] == target)
            return pivotIdx;
        // now we are left with 2 sorted arrays in ascending order
        // 0-> pivot idx-1, pivotidx+1->n-1
        int leftpart = bs(nums, 0, pivotIdx - 1, target);
        if (leftpart != -1)
            return leftpart;
        int rightpart = bs(nums, pivotIdx + 1, n - 1, target);
        return rightpart;
    }

    // Aggresive Cows : SPOJ
    private static int getMaxMinDistance(int[] arr, int cows) {
        int n = arr.length;
        Arrays.sort(arr);
        int si = 1, ei = arr[n - 1] - arr[0];
        int ans = -1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (isValidMinDistance(arr, mid, cows)) {
                ans = mid;
                // try for others greater values of minDtance
                si = mid + 1;

            } else {
                ei = mid - 1;// we have to chekc for lesser min distance try smaller values

            }

        }

        return ans;

    }

    // sonwhow we got the min distance and we hacve to chek wheather we are able to
    // place all the given cows
    private static boolean isValidMinDistance(int[] arr, int minDist, int cows) {
        int n = arr.length;
        int cowsPlaced = 1; // we have placed this cow at 0th postiton
        int placedAt = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] - placedAt >= minDist) {
                cowsPlaced++;
                placedAt = arr[i];
            }

        }

        if (cowsPlaced >= cows)
            return true;

        return false;

    }

    // Min allocation of pages
    public int books(int[] books, int students) {
        if (students > books.length)
            return -1; // each studetns must be alloted 1 books
        int sum = 0, max = -1;
        for (int el : books) {
            sum += el;
            max = Math.max(max, el);
        }
        int si = max, ei = sum; // why max bcz that no of pages have to be alloted to some candiates
        // we can't divide the pages
        int ans = -1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (isPossible(books, students, mid)) {
                ans = mid;
                // try to resuce burdern on each student
                ei = mid - 1;
            } else {
                // more student we required to finish books
                // increase the pages per head;
                si = mid + 1;
            }
        }

        return ans;

    }

    boolean isPossible(int[] arr, int students, int mid) {
        int pages = 0, count = 1;// start with first student
        for (int el : arr) {
            if (pages > mid)
                return false;
            pages += el;
            if (pages > mid) {
                pages = el;
                count++;
            }

        }

        if (count <= students)
            return true;
        return false;

    }

    // Kth elemets in 2 sorted arrays :
    // when mergin isn't allowed: extra space isn't allowed
    // time : O(log min(m,n)), space: O(1)
    // https://practice.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1#
    public long kthElement(int arr1[], int arr2[], int n, int m, int k) {
        // bs
        // mid corresponds to the cut1 => cut i first aray

        // cond le<r2 && l2<r1

        if (n > m) {
            return kthElement(arr2, arr1, m, n, k);
        } // do the bs in shorter array

        // we are doing bs in smaller array so the start and end point for bs should be
        // valid
        // basicallly low,high condition boils down for conditions when we try to take
        // all k elemets from smaller and larger

        int low = Math.max(0, k - m); // valid start = 0, or k-m
        // k-m: k>m(bigger array size) if all elemet are taken from bigger array still
        // there are some elemets left then we have to take them from smaller array so
        // staring from 0(not taking any elemet wouldn't help)
        int high = Math.min(k, n);// end point n(size of smaller array)<k ? n : k
        // at most we can try taking all k from smaller array

        while (low <= high) {
            int cut1 = (low + high) >> 1;
            int cut2 = k - cut1;
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r1 = cut1 == n ? Integer.MAX_VALUE : arr1[cut1];
            int r2 = cut2 == m ? Integer.MAX_VALUE : arr2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return 1;
    }

    // similar stratergy in finding the median of 2 sorted arrays: Leetcode 4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        if (n > m) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // apply bs in smaller array
        int si = 0, ei = n;
        int total = (n + m);
        while (si <= ei) {
            int cut1 = si + (ei - si) / 2;
            int cut2 = (total + 1) / 2 - cut1;

            int left1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = cut1 == n ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = cut2 == m ? Integer.MAX_VALUE : nums2[cut2];

            // condition for correctness

            if (left1 <= right2 && left2 <= right1 && Math.max(left1, left2) <= Math.min(right1, right2)) {
                // even
                if (total % 2 == 0) {
                    double ans = (Math.max(left1, left2) + Math.min(right1, right2) * 1.0) / 2;
                    return ans;
                } else {
                    return 1.0 * Math.max(left1, left2);
                }
                // odd
            } else if (left1 > right2) {
                ei = cut1 - 1;
            } else {
                si = cut1 + 1;
            }

        }

        return 0.0;
    }

    // Leetcode 878. Nth Magical Number
    /*
    Intution: Before solving this problem lets first understand some of the concepts:
1.) If you have to tell how many numbers less than x are divisible by n, then you do x/n to calculate. For example if x = 14 and n = 3 then x/n = 4 that means there are 4 numbers less than 14 which are divisible by 3 (i.e., 3, 6, 9, 12).
2.) So in case you are given 2 numbers n1 and n2 then you have to check for both the numbers similarly. For example, x = 25, n1 = 3, n2 = 4, then x/n1 = 8 (3, 6, 9, 12,15,18,21,24) and x/n2 = 6 (4, 8, 12,16,20,24), so the answer should be 8+6 = 14. But if u see then we have 12 and 24 repeated for n1 and n2 and these are nothing but the multiples of LCM of n1 and n2. And this is obvious also, the common multiples will repeat in both the cases, so we need to subtract common multiples.
3.) So now total will be x/n1 + x/n2 - x/LCM(a,b) = 25/3 + 25/4 - 25/12 = 8 + 6 - 2 = 12. And this is the maths for this question.
4.) Now to find LCM we know that n1 * n2 = LCM(n1,n2) * GCD(n1,n2)
5.) Now we just need to binary search between lowest and highest range.
6.) Lower limit will be min(n1,n2) and higher limit n * min(n1,n2)

Time Complexity: O(log(n*min(n1,n2))
    */
     public int nthMagicalNumber(int n, int a, int b) {
        final int mod = (int)(1e9 +7);
        long si = Math.min(a,b), ei = n*si;
        
        while(si<ei){
            long mid = si + (ei-si)/2;
            long count = (mid/a) + (mid/b) - (mid/lcm(a,b));
            if(count<n){
                si = mid +1;
            }else ei = mid;
        }
        
        return (int)(si%mod);
    }
    private long lcm(int a, int b){
        return (long) 1.0*a*b/gcd(a,b);
    }
    
    private int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }

}
