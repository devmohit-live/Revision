/**
 * SingleElementinSortedArray
 */
public class SingleElementinSortedArray {

    // Approach :

    // check these two conditions,
    // if any of the conditions is satisfied,
    // then pattern is not missed,
    // so check in next half of the array. i.e, left = mid + 1
    //

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length, si = 0, ei = n - 1;

        while (si < ei) {
            int mid = si + (ei - si) / 2;
            // if mid is even, then it's duplicate should be in next index.
            // if mid is odd, then it's duplicate should be in previous index.
            if (nums[mid] == nums[(mid ^ 1)]) { // to avoid mid-1, mid+1 seperate writing

                // everything is fine till now check for the next part of array : after mid
                si = mid + 1;
            } else {
                // if condition is not satisfied, then the pattern is missed.
                // so, single number must be before mid. so, update end to mid.
                ei = mid; // this can be a possible ans
            }
        }

        return nums[si];

    }
}