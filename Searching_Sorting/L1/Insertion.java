import java.util.Arrays;

public class Insertion {
    Insertion() {
        System.out.println("\nCalling Insertion Sort  \n");
    }

    void sort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j])
                    swap(arr, j - 1, j);
                else // since left is already sorted no need to check further
                    break;
            }
        }

        System.out.println("Insertion Sort : " + Arrays.toString(arr));
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
