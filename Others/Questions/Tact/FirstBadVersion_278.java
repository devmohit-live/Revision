public class FirstBadVersion_278 {
    public int firstBadVersion(int n) {
        int si = 1, ei = n;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (isBadVersion(mid)) {
                ei = mid;
            } else
                si = mid + 1;
        }

        return ei;
    }
}
