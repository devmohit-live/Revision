public class Sort012 {
    public static void sort012(int[] arr) {
        final int N = arr.length;
        int ptr1 = -1, ptr2 = 0, ptr3 = N - 1;

        // partiton
        while (ptr2 <= ptr3) {
            if (arr[ptr2] == 0)
                swap(arr, ++ptr1, ptr2++);
            else if (arr[ptr2] == 2)
                swap(arr, ptr2, ptr3--);
            else if (arr[ptr2] == 1)
                ptr2++;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
