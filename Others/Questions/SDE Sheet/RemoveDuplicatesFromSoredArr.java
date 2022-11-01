public class RemoveDuplicatesFromSoredArr {


    //brute : add the indexes of distince ele in set => return set.size : no use of sorted 
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int n = nums.length;

        int i = 0, j = 1;
        while (i < n && j < n) {
            if (nums[i] == nums[j])
                j++;
            else {
                i++;
                nums[i] = nums[j];
                // change with next actual place for ditinct element with to be place in
                // modified arr
            }
        }
        // i -> pos

        return i + 1;
    }

}
