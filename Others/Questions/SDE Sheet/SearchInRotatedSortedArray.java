public class SearchInRotatedSortedArray {
    public boolean search(int[] nums, int tar) {
        int n = nums.length, si = 0, ei = n - 1, mid = 0;
        while (si <= ei) {
            mid = si + (ei - si) / 2;
            if (nums[mid] == tar)
                return true;

            // tricky case : nums[si] == nums[ei] == nums[mid] => can't decide where to go
            if (nums[si] == nums[mid] && nums[ei] == nums[mid]) {
                si++;
                ei--;
                continue;
            }

            // basic area slection
            if (nums[si] <= nums[mid]) {
                // left part is sorted
                // check for range
                if (nums[si] <= tar && nums[mid] > tar) { // lies in sotred range
                    ei = mid - 1;
                } else
                    si = mid + 1;
            } else {
                // right part is sorted
                if (nums[ei] >= tar && nums[mid] < tar) {
                    si = mid + 1;
                } else
                    ei = mid - 1;
            }

        }

        return false;
    }
}
