import java.util.Arrays;

public class MergeSort {
    MergeSort() {
        System.out.println("\n  Meger Sort is Called \n");
    }

    void sort(int[] arr) {
        arr = mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    int[] mergeSort(int[] arr, int si, int ei) {
        if (si >= ei)
            return new int[] { arr[si] };

        int mid = (si + ei) / 2;
        int[] left = mergeSort(arr, si, mid);
        int[] right = mergeSort(arr, mid + 1, ei);
        return merger2SortedArrays(left, right);

    }

    private int[] merger2SortedArrays(int[] left, int[] right) {
        if (left.length == 0 || right.length == 0)
            return left.length == 0 ? right : left;

        int i = 0, j = 0, itr = 0;
        int n = left.length, m = right.length;
        int[] res = new int[m + n];

        while (i < n && j < m) {
            if (left[i] <= right[j])
                res[itr++] = left[i++];
            else
                res[itr++] = right[j++];
        }

        while (i < n)
            res[itr++] = left[i++];
        while (j < m)
            res[itr++] = right[j++];

        return res;
    }
}
