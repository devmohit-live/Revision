public class LongestCosscutiveSeq {

    public static int longestConsecutiveSequence(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : arr)
            set.add(ele);

        int len = 0, head = 0;
        for (int ele : arr) {
            if (!set.contains(ele))
                continue;

            // get the starting left and right point
            int left = ele - 1, right = ele + 1;
            set.remove(ele);

            // first invalid left point (left point that doesn't exixts in map)
            while (set.contains(left))
                set.remove(left--);

            // first invalid right point (left point that doesn't exixts in map)
            while (set.contains(right))
                set.remove(right++);

            // no of elements
            if (right - left - 1 > len) {
                len = right - left - 1;
                head = left + 1;
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.println(head + i);
        }

    }

    // OR
    static void findConsecutive(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++)
            set.add(arr[i]);

        int maxlen = 0, start = 0;

        for (int i : arr) {
            int len = 0, left = i - 1, right = i + 1;
            if (!set.contains(i))
                continue; // as we are removing item from set and arr may contains that item multiple
                          // times

            set.remove(i);

            while (set.contains(left))
                set.remove(left--);

            while (set.contains(right))
                set.remove(right++);

            len = right - left - 1;

            if (len > maxlen) {
                maxlen = len;
                start = left + 1;
            }
        }

        for (int i = 0; i < maxlen; i++) {
            System.out.println(start + i);
        }

    }

    // In case there are two sequences of equal length (and they are also the
    // longest), then print the one for which the starting pointis minimum

    static void findConsecutive2(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : arr)
            set.add(ele);
        int start = arr.length, maxlen = 0;

        for (int i : arr) {

            if (!set.contains(i))
                continue;

            int left = i - 1, right = i + 1;

            while (set.contains(left))
                set.remove(left--);

            while (set.contains(right))
                set.remove(right++);

            int len = right - left - 1;

            if (len > maxlen) {
                start = left + 1;
                maxlen = len;
            }
            // in case of two seq have same len (and are max seq)
            if (len == maxlen) {
                start = Math.min(left + 1, start);
            }

        }

        for (int i = 0; i < maxlen; i++) {
            System.out.println(start + i);
        }

    }

}
