public class SortingQuestions {

    // https://practice.geeksforgeeks.org/problems/punish-the-students5726/1#

    // Sol : Pecoding solution Punish Student
    public static int shouldPunish(int roll[], int marks[], int n, double avg) {
        int sum = 0;
        for (int val : marks)
            sum += val;

        int newMarks = sum - swap_bubbleSort(roll);
        double newAvg = (newMarks * 1.0) / n;
        if (newAvg >= avg)
            return 1;
        return 0;
    }

    private static int swap_bubbleSort(int[] arr) {
        int swaps = 0, n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, i, j);
                    swaps += 2;
                    // both the numbers are changes so contributing to swap of each number
                }
            }
        }

        return swaps;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
