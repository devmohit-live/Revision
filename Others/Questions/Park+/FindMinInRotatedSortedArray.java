public class FindMinInRotatedSortedArray {
    // 153 : doesn't conatains duplicates
    public int findMin(int[] nums) {
        int n = nums.length, si = 0, ei = n - 1;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (nums[mid] > nums[ei]) {
                // right [art contains lesser elemets
                si = mid + 1; // move towards right
            } else
                ei = mid;
        }
        return nums[ei];
    }

    // 154
    // findMinII : contians duplicates
    public int findMinII(int[] nums) {
        int si = 0, ei = nums.length - 1;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (nums[mid] > nums[ei])
                si = mid + 1;
            else if (nums[mid] == nums[ei] && nums[mid] == nums[si]) {
                ei--;
                si++;
            } else {
                ei = mid;
            }
        }

        return nums[ei];
    }
}
