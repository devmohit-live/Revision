public class CountInversion {
    // same as reverse pairs : exactly same just we have
    // A[i] > A[j] condition here
    // there we have A[i] > 2* A[j]
    public static long getInversions(long arr[], int n) {
        if (n == 0)
            return 0;
        // return brute(arr);
        return mergeSort(arr, 0, n - 1);
    }

    // nlong : Merge Sort : reverse path : merging on the way : similar to count
    // inversion

    private static long mergeSort(long[] arr, int lo, int hi) {
        if (lo >= hi)
            return 0;

        int mid = lo + ((hi - lo) >> 1);
        long count = 0;

        count += mergeSort(arr, lo, mid);
        count += mergeSort(arr, mid + 1, hi);
        count += merge(arr, lo, mid, hi);

        return count;
    }

    private static long merge(long[] arr, int lo, int mid, int hi) {
        int n = hi - lo + 1, idx = 0, i = lo, j = mid + 1;
        long count = 0;
        long[] tmp = new long[n];

        // count pairs
        while (i <= mid) {
            while (j <= hi && arr[i] > arr[j])
                j++;
            count += (j - (mid + 1)); // mid+1 => start point from j
            i++;
        }

        i = lo;
        j = mid + 1;

        // merge
        while (i <= mid && j <= hi) {
            if (arr[i] <= arr[j])
                tmp[idx++] = arr[i++];
            else
                tmp[idx++] = arr[j++];
        }

        while (i <= mid)
            tmp[idx++] = arr[i++];
        while (j <= hi)
            tmp[idx++] = arr[j++];
        // System.arraycopy(tmp, 0, arr, lo, hi-lo+1);

        for (int itr = lo; itr <= hi; itr++)
            arr[itr] = tmp[itr - lo];
        // System.arraycopy(source_arr, sourcePos, dest_arr,destPos, len);
        return count;
    }

    // brute : O(n*n)

    private static long brute(long[] arr) {
        long count = 0;
        final int N = arr.length;
        if (N == 0)
            return count;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[i] > arr[j])
                    count++;
            }
        }

        return count;
    }
}
