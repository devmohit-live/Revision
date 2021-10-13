public class basic {
    public static int heightOfTree(int[] arr, int idx) {
        if (idx >= arr.length)
            return -1;

        int lh = heightOfTree(arr, 2 * idx + 1);
        int rh = heightOfTree(arr, 2 * idx + 2);

        return Math.max(lh, rh) + 1;
    }

    // leftmost
    public static int leftmost(int[] arr) {
        int idx = 0;
        while (2 * idx + 1 < arr.length)
            idx = 2 * idx + 1;
        return arr.length == 0 ? -1 : arr[idx];
    }

    // rightmost
    public static int rightmost(int[] arr) {
        int idx = 0;
        while (2 * idx + 2 < arr.length)
            idx = 2 * idx + 2;
        return arr.length == 0 ? -1 : arr[idx];
    }

    // main
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13, 14 };
        System.out.println("Height " + heightOfTree(arr, 0));
        System.out.println("Leftmost " + leftmost(arr));
        System.out.println("Rightmost " + rightmost(arr));
    }
}