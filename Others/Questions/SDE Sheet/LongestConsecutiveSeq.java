public class LongestConsecutiveSeq {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        // n
        for (int el : nums)
            set.add(el);
        // n

        int max = 0, start = -1, end = -1;
        for (int el : nums) {
            int prev = el - 1, next = el + 1;

            while (set.contains(prev))
                set.remove(prev--);

            while (set.contains(next))
                set.remove(next++);
            int len = next - prev - 1; // both not included

            if (len > max) {
                start = prev + 1;
                end = next - 1;
                max = len;
            }

        }

        return max;
    }
}
