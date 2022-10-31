class ReversePairs_493 {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    // nlong : Merge Sort : reverse path : merging on the way : similar to count
    // inversion
    private int mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return 0;

        int mid = lo + ((hi - lo) >> 1);
        int count = 0;

        count += mergeSort(arr, lo, mid);
        count += mergeSort(arr, mid + 1, hi);
        count += merge(arr, lo, mid, hi);

        return count;
    }

    private int merge(int[] arr, int lo, int mid, int hi) {
        int n = hi - lo + 1, idx = 0, i = lo, j = mid + 1, count = 0;
        int[] tmp = new int[n];

        // count pairs
        while (i <= mid) {
            while (j <= hi && arr[i] > 2l * arr[j])
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

        // for(int itr = lo; itr <= hi; itr++) arr[itr] = tmp[itr-lo];
        // System.arraycopy(source_arr, sourcePos, dest_arr,destPos, len);
        System.arraycopy(tmp, 0, arr, lo, hi - lo + 1);
        return count;
    }

    // brute : O(n*n)

    private int brute(int[] arr) {
        int count = 0;
        final int N = arr.length;
        if (N == 0)
            return count;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if ((long) arr[i] > 2L * arr[j])
                    count++;
            }
        }

        return count;
    }

    // Coding ninjas :
    public static int reversePairs(ArrayList<Integer> arr) {
        int n = arr.size();
        if (n < 2)
            return 0;
        return mergeSort(arr, 0, n - 1);
    }

    private static int mergeSort(List<Integer> arr, int si, int ei) {
        if (si >= ei)
            return 0;
        int mid = si + ((ei - si) / 2);
        int count = 0;
        count += mergeSort(arr, si, mid);
        count += mergeSort(arr, mid + 1, ei);
        count += merge(arr, si, mid, ei);
        return count;
    }

    private static int merge(List<Integer> arr, int si, int mid, int ei) {
        final int N = arr.size();
        int i = si, j = mid + 1, newStart = j, count = 0, idx = 0;
        // counting pairs code
        while (i <= mid) {
            while (j <= ei && (long) arr.get(i) > 2l * arr.get(j))
                j++;
            count += (j - newStart); // newStart => start point of second Array with which we are comparing to
            i++;
        }

        i = si;
        j = newStart;
        // merging code
        int[] tmp = new int[ei - si + 1];
        while (i <= mid && j <= ei) {
            if (arr.get(i) <= arr.get(j))
                tmp[idx++] = arr.get(i++);
            else
                tmp[idx++] = arr.get(j++);
        }
        while (i <= mid)
            tmp[idx++] = arr.get(i++);
        while (j <= ei)
            tmp[idx++] = arr.get(j++);

        for (int itr = si; itr <= ei; itr++)
            arr.set(itr, tmp[itr - si]);

        return count;
    }

}