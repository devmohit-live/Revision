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

    // ceil and floor
    int[] ceilAndfloor(int[] arr, int tar) {
        int si = 0, ei = arr.length - 1;

        // corner
        if (data < arr[si])
            return si;
        else if (data > arr[ei])
            return ei;

        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data)
                return new int[] { mid, mid };
            // ..or if truely wanted the floor ceil
            // return new int[]{(mid-1),{mid+1}};
            // it can be -1, n
            else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return new int[] { ei, si }; // ceil,floor(si crosses over ei)
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

}
