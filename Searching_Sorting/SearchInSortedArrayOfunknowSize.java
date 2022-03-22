public class SearchInSortedArrayOfunknowSize {
    public int search(ArrayReader reader, int target) {
        int lo = 0, hi = 1;

        // first find the actual range

        while (reader.get(hi) < target) {
            lo = hi;
            hi = hi * 2;
        }

        // then apply bs
        while (low <= hi) {
            int mid = low + (hi - low) / 2;
            int val = reader.get(mid);
            if (val == target) {
                return mid;
            } else if (val > target) {
                hi = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
