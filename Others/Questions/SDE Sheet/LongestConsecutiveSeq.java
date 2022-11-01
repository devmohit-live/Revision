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

    // using concept of start point : as previous on id giving tle in ninja
    private int maxLength(int[] arr) {
        int mx = 0;

        // To store the length of current consecutive Sequence.
        int count = 0;

        // To store all the unique elements of array.
        HashSet<Integer> set = new HashSet<>();

        for (Integer element : arr) {
            set.add(element);
        }

        for (Integer element : arr) {
            int previousConsecutiveElement = element - 1;

            if (!set.contains(previousConsecutiveElement)) {

                // Element is the first value of consecutive sequence.
                int j = element;

                while (set.contains(j)) {
                    // The next consecutive element by will be j + 1.
                    j++;
                }

                // Update maximum length of consecutive sequence.
                mx = Math.max(mx, j - element);
            }

        }

        return mx;
    }

}
