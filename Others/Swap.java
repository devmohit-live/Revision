public class Swap {
    // one liner swap => using memory stack and left to right evaluation concept
    private static int swap(int i, int j) {
        return i;
    }

    public static void display(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int i = 3, j = 5;
        arr[j] = swap(arr[i], arr[i] = arr[j]);
        display(arr);
    }
}
