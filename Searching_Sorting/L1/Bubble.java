import java.util.Arrays;

public class Bubble {
    Bubble() {
        System.out.println("\nCalling Bubble Sort  \n");
    }

    void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1])
                    swap(arr, j + 1, j);
            }
        }
        System.out.println("Bubble Sort : " + Arrays.toString(arr));
    }

    void sort_opti(int[] arr) {
        int n = arr.length;
        boolean isSorted = false;

        for (int i = 0; i < n; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSwapped = true;
                }
            }
            // already sorted
            if (!isSwapped) {
                isSorted = true;
                break;
            }
        }
        System.out.println("Bubble Sort Best case opti  : " + Arrays.toString(arr));
        System.out.println("Is Arrays already sorted  : " + isSorted);
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
