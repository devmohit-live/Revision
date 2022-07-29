public class FirstAndLastPos {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[] { -1, -1 };

        return new int[] { findFirstPosition(nums, target), findLastPosition(nums, target) };
    }

    private int findFirstPosition(int[] arr, int element) {
        int n = arr.length, si = 0, ei = n - 1, mid = 0, ans = 0;
        while (si < ei) {
            mid = si + (ei - si) / 2;

            if (arr[mid] >= element) {
                ei = mid;
            } else if (arr[mid] < element)
                si = mid + 1;
        }

        return arr[ei] == element ? ei : -1;
    }

    private int findLastPosition(int[] arr, int element) {
        int n = arr.length, si = 0, ei = n - 1, mid = 0, ans = 0;
        while (si <= ei) {
            mid = si + (ei - si) / 2;

            if (arr[mid] < element) {
                si = mid + 1;
            } else if (arr[mid] > element)
                ei = mid - 1;
            else {
                ans = mid;
                si = mid + 1;
            }

        }

        return arr[ans] == element ? ans : -1;
    }
}
