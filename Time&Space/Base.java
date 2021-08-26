import java.util.Arrays;

public class Base {

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void display(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    static void sort01(int[] arr) {
        int zero = -1, one = 0, n = arr.length;
        ;

        while (one < n) {
            if (arr[one] == 0) {
                zero++; // go to first element of 1's area
                swap(arr, zero, one);
            }
            one++;
        }
        display(arr);
    }

    static void sort012(int[] arr) {
        int zero = -1, one = 0, n = arr.length, two = n - 1;

        while (one <= two) {
            if (arr[one] == 0) {
                zero++;
                swap(arr, zero, one);
                // ones area is shifted too
                one++;
            } else if (arr[one] == 2) {
                // can't increment one/itr as we dont know with what element we have swapped 2
                // with
                swap(arr, one, two);
                two--;
            } else {
                // one++;
                one++;
            }
        }
        display(arr);
    }

    public static void main(String[] args) {
        int[] arr = { 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1 };
        int[] arr2 = { 0, 0, 2, 2, 2, 0, 1, 2, 0, 1, 2, 2, 2, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 2, 0 };
        sort01(arr);
        sort012(arr2);
    }
}
