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
}
