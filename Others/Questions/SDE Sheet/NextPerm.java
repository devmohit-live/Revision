public class NextPerm {
    // brute create all permutaions and find the given one (idx) (idx+1) %n will be
    // the ans;
    public void nextPermutation(int[] nums) {
        final int n = nums.length;
        int breakPoint = -1;
        // first index from back that break the descending pattern
        // n-2 bcz comparing with +1
        for (int i = n - 2; i >= 0; i--)
            if (nums[i] < nums[i + 1]) { // ascending
                breakPoint = i;
                break;
            }

        if (breakPoint != -1) {
            // find the first element form back that is greater than the nums[breakpoint]
            int idx = -1;
            // n- 1 not n-2 as comparing with absolute number
            for (int i = n - 1; i >= 0; i--)
                if (nums[i] > nums[breakPoint]) {
                    idx = i;
                    break;
                }
            swap(nums, breakPoint, idx); // now that number will be the next number of permutation
            reverse(nums, breakPoint + 1, n - 1); // revese the rest
        } else {
            reverse(nums, 0, n - 1);
        }
    }

    private void reverse(int[] arr, int i, int j) {
        while (i < j)
            swap(arr, i++, j--);
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
