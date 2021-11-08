public class SquareSorted {

    // 2 pointers
    public int[] sortedSquares(int[] nums) {
        // negative causes problem but we are sure that -ve will comes first then 0 then
        // +ve as the given array is sorted
        // so to avoid negative's square being bigger and cauding the problem we will
        // compare -ves's with last +ve (bigger) elements ans put the greater one's
        // square at last

        int n = nums.length, p1 = 0, p2 = n - 1;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int sq1 = nums[p1] * nums[p1];
            int sq2 = nums[p2] * nums[p2];
            if (sq1 >= sq2) {
                ans[i] = sq1;
                p1++;
            } else {
                ans[i] = sq2;
                p2--;
            }
        }

        return ans;
    }
}
