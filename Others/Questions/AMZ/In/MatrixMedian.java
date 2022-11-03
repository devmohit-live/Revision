public class MatrixMedian {
    public int countSmallerThanEqualToMid(int arr[], int limit) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int md = (l + r) >> 1;
            if (arr[md] <= limit) {
                l = md + 1;
            } else {
                r = md - 1;
            }
        }
        return l;
    }

    public int findMedian(int[][] arr) {

        int n = arr.length;
        int m = arr[0].length;
        int l = 1;
        int r = (int) 1e9;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0; // <=mid
            for (int i = 0; i < n; i++) {
                cnt += countSmallerThanEqualToMid(arr[i], mid);
            }
            if (cnt <= (n * m) / 2) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;

    }
}
