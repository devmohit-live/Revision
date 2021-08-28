import java.util.Arrays;

public class QuickSort {
    QuickSort() {
        System.out.println("\nCalling QuickSort\n");
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private int partitonInRange(int[] arr, int si, int ei, int pivotIdx) {
        if (pivotIdx > arr.length || pivotIdx < 0)
            return -1;

        int p = si - 1, itr = si;
        swap(arr, pivotIdx, ei);
        int data = arr[pivotIdx];

        while (itr <= ei) {
            if (arr[itr] <= data) {
                swap(arr, ++p, itr);
            }
            itr++;
        }

        return p;
    }

    private void quickSort(int[] arr, int si, int ei) {
        if (si > ei)
            return;

        int pivot = ei;
        int pivotIdx = partitonInRange(arr, si, ei, pivot);
        quickSort(arr, si, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, ei);

    }
}
