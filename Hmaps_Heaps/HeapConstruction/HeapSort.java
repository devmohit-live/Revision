public class HeapSort {
    // O(n)
    private static void createHeap(int[] arr, boolean isIncreasing) {
        for (int i = arr.length - 1; i >= 0; i--) {
            downHeapify(arr, i, arr.length, isIncreasing);
        }
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

    public static void heapSort(int[] arr, boolean isIncreasing) {
        // isIncreaing = minHeap
        createHeap(arr, isIncreasing);
        int lidx = arr.length - 1;
        // remove code
        while (lidx >= 0) {
            swap(arr, 0, lidx);
            downHeapify(arr, 0, lidx, isIncreasing);
            lidx--; // decreading the heaping boundary as last elements are sorted
        }
        display(arr);
    }

    private static void display(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = { -15, 2, 5, 8, 9, 1, 1, -5, 1, 10, 30, 20, 40 };
        heapSort(arr, true);
        // heapSort(arr, false);
    }
}