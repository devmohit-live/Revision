public class LC_744 {
    // 744. Find Smallest Letter Greater Than Target
    public char nextGreatestLetter(char[] arr, char target) {
        int n = arr.length, si = 0, ei = n - 1;
        // wrap around: ex: target = z and no element > z so return a(0)

        // in case of ceil type return si
        // in case of floor types return ei

        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            char ch = arr[mid];
            if (ch > target) {
                // look for more smaller ele
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return arr[si % n];
    }
}
