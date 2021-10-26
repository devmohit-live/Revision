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
    int perfectLocationBetter(int[] arr, in tar){
        int si =0, ei = arr.length; // not -1 as element can be added to last 
        // as here we don't want si to cross ei(potential index)
        while(si<ei){
            int mid = si + (ei - si )/2;
            if(arr[mid]<=tar) si = mid+1; //damn sure about next idx as me or previos are lesser
            else ei = mid, // not mid-1 as it can be the case that from 0 to mid-1 be the same duplicate element == tar

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

}
