public class SetMismatch_645 {
    public int[] findErrorNums(int[] nums) {
        // Set<Integer> set = new HashSet<>();
        // int[] ans = new int[2];
        // int n = nums.length;

        // if(n<=1) return new int[0];

        // long sum = (n*(n+1))/2, actual = 0;
        // for(int el : nums) {
        // if(!set.add(el)) ans[0] = el;
        // actual+=el;
        // }

        // actual-=ans[0];

        // ans[1] = (int)(sum-actual);

        // return ans;

        return constantSpace(nums);
    }

    private int[] constantSpace(int[] nums) {
        int[] res = new int[2];
        for (int i : nums) {
            if (nums[Math.abs(i) - 1] < 0)
                res[0] = Math.abs(i);
            else
                nums[Math.abs(i) - 1] *= -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                res[1] = i + 1;
        }

        return res;
    }
}
