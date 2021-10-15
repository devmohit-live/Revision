import java.util.ArrayList;

public class Questions {
    // https://practice.geeksforgeeks.org/problems/kth-smallest-element5635/1#
    // kth smallest : N logk
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > k)
                pq.remove();
        }

        return pq.peek();
    }

    // n + klogn => using heap as we have the complete array
    public static int kthSmallestBetter(int[] arr, int l, int r, int k) {
        // create heap => O(n)
        for (int i = arr.length - 1; i >= 0; i--) {
            downHeapify(arr, i, arr.length, false);
        }

        int lidx = arr.length - 1;
        // remove code
        int K = k;
        while (K >= 0) {
            swap(arr, 0, lidx);
            downHeapify(arr, 0, lidx, isIncreasing);
            lidx--; // decreading the heaping boundary as last elements are sorted
        }
        return arr[n - k];

    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int compareTo(int t, int o, boolean isIncreaing) {
        int res = o - t;
        if (isIncreaing) {
            res *= -1;
        }
        return res;
    }

    // (log n)
    private static void downHeapify(int[] arr, int pi, int limit, boolean isIncreaing) {
        int mxidx = pi;
        int lic = 2 * pi + 1;
        int ric = 2 * pi + 2;

        if (lic < limit && compareTo(arr[lic], arr[mxidx], isIncreaing) > 0)
            mxidx = lic;
        if (ric < limit && compareTo(arr[ric], arr[mxidx], isIncreaing) > 0)
            mxidx = ric;

        if (pi != mxidx) {
            swap(arr, pi, mxidx);
            downHeapify(arr, mxidx, limit, isIncreaing);
        }
    }

}
