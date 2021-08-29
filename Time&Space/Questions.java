public class Questions {
    // find kth largest element => Time: klogn(average), kn(worst)

    int quickSelect(int[] arr, int k) {
        // largest => from last => n-k
        // smallest => from start => k
        int n = arr.length, idx = n - k;
        quickSelect(arr, 0, n - 1, idx);
        return arr[idx];
    }

    void quickSelect(int[] arr, int si, int ei, int idx) {
        if (si >= ei)
            return;

        int p = partition(arr, si, ei, idx, ei);
        if (p == idx)
            return; // idx is at correct place
        else if (idx > p) {
            // look into right part
            quickSelect(arr, p + 1, ei, idx);
        } else
            quickSelect(arr, si, p - 1, idx);
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    int partition(int[] arr, int si, int ei, int idx, int pidx) {
        int p = si - 1, itr = si, data = arr[pidx];
        swap(arr, pidx, ei);
        while (itr <= ei) {
            if (arr[itr] <= data)
                swap(arr, ++p, itr);
            itr++;
        }
        return p;
    }

}
