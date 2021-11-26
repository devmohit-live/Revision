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
        return findPivot(arr, n) + 1;
    }

    private int findPivot(int[] arr, int n) {
        int si = 0, ei = n - 1;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            // System.out.println(si+" "+ei+" "+mid);
            if (mid + 1 < n && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (mid - 1 >= 0 && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            } else if (arr[mid] <= arr[si]) {
                // bigger ele is towars start
                ei = mid; // mid can be the answer
            } else {
                si = mid + 1;
            }

        }
        return -1;
    }

}
