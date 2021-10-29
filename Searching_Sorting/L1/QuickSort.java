import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    QuickSort() {
        System.out.println("\nCalling QuickSort\n");
    }

    Random random = new Random();

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
        if (pivotIdx >= arr.length || pivotIdx < 0)
            return -1;

        int p = si - 1, itr = si;
        int data = arr[pivotIdx];
        swap(arr, pivotIdx, ei);

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

        // int pivotIdx = ei; // quicksort with ending index as pivot;

        // int pivotIdx = si; // quicksort with ending index as pivot;

        int pivotIdx = random.nextInt(ei - si + 1) + si; // quicksort with Random index as
        // pivot;
        int p = partitonInRange(arr, si, ei, pivotIdx);
        quickSort(arr, si, p - 1);
        quickSort(arr, p + 1, ei);

    }
}
