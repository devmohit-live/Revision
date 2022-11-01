public class FirstDuplicateNumber {
    // All the integers in nums appear only once except for precisely one integer
    // which appears two or more times.
    // ek el multiple times b repeat ho skta h

    // Can't modify array : Cycle Detetction : Hare and tortoise
    // can modify : cyclic sort
    public int findDuplicate(int[] nums) {
        /*
         * // approach 1: using set O(n) space // Set<Integer> set=new HashSet<>(); //
         * for(int i:nums){ // if(!set.add(i)){ // return i; // } // } // return
         * nums[nums.length -1];
         */

        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        fast = nums[0];
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    private int cyclicSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        while (i < nums.length) {
            if (nums[i] < nums.length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return nums[j];
            }
        }
        return 0;
    }
}
