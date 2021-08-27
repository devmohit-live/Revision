import java.util.Arrays;

public class Selection {
    Selection() {
        System.out.println("\nCalling Selection Sort  \n");
    }

    void sort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int minidx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minidx])
                    minidx = j;
            }
            swap(arr, i, minidx);
        }

        System.out.println("Selection Sort : " + Arrays.toString(arr));
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
