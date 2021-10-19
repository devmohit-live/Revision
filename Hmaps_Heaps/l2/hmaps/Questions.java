public class Questions {
    // Leetcode 128 : Longest Consecutive Subsequnce
    public int longestConsecutive(int[] nums) {

        // idea is to remove the consecutive elemets from the set as we explre them
        // once, so that all the consecutive elemets related to an element e is removed
        // at once
        if (nums.length == 0 || nums.length == 1)
            return nums.length;
        int maxlen = 0, start = -1;
        Set<Integer> set = new HashSet<>();
        for (int el : nums)
            set.add(el);
        int max = 0;
        for (int el : nums) {
            int prev = el - 1, next = el + 1;

            // set.remove actually decreases the search complexity/time of element in a set
            while (set.contains(prev))
                set.remove(prev--);
            while (set.contains(next))
                set.remove(next++);
            int len = next - prev - 1;
            if (len > maxlen) {
                maxlen = len;
                start = prev + 1;
            }
        }

        // System.out.println(start+" "+(start+maxlen-1));
        return maxlen;

    }
}
