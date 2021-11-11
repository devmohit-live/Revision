public class MinStepValue_1413 {
    // using Prefix sum
    // basically prefix sum is maintained in the variable
    // ans in the process of calulating the prfixsum , we store the min

    // since we can have steps such that number >=1 at any step(at any stage of
    // prefixsum)
    // so the ans would be -min(+ve)+1 => as we are getting the min(shortage) ans
    // that sould be >=1 to be valid

    public int minStartValue(int[] nums) {
        int start = 1, curr = 1;

        for (int el : nums) {
            curr += el;
            if (curr < 1) {
                start += 1 - curr;
                curr = 1;
            }
        }

        return start;
    }
}
