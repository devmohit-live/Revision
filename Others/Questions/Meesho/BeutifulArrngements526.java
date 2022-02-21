public class BeutifulArrngements526 {
    int count = 0;

    public int countArrangement(int n) {
        int[] arr = new int[n + 1]; // 1 based
        System.out.println("For N = " + n);
        fillArray(arr, 1, n); // starting from 1
        return count;
    }

    private void fillArray(int[] arr, int val, int n) {
        if (val > n) {
            // filled all 1 to val values in array so increase count ans backtrack(return)
            count++;
            // System.out.println(Arrays.toString(arr));
        }

        // try this value for each index if that idx is not yet filled
        for (int i = 1; i <= n; i++) {
            // if not already filled and satisfies the given condition
            if (arr[i] == 0 && (val % i == 0 || i % val == 0)) {
                // fill
                arr[i] = val;
                // try filling with next value
                fillArray(arr, val + 1, n);

                // backtrack
                arr[i] = 0;
            }
        }
    }
}
