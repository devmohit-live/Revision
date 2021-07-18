import java.util.Arrays;

public class Rotate90 {

    public static void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int tmp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = tmp;
    }

    //
    static int[][] transpose(int[][] arr) {
        // m==n
        if (arr.length == arr[0].length)
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr[0].length; j++) {
                    swap(arr, i, j, j, i);
                }
            }

        else {
            System.out.println("Rows and Columns aren't same ");
            int[][] temp = new int[arr[0].length][arr.length];
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[0].length; j++) {
                    temp[i][j] = arr[j][i];
                }
            }
            arr = temp;
        }

        // m!=n

        System.out.println("Transposed");
        display(arr);
        return arr;
    }

    static void display(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }

    public static void rotateClockwise(int[][] arr) {
        System.out.println("Original");
        display(arr);

        arr = transpose(arr);
        // column shifting
        // if you calculate n,m before tansposing then you have to recalculate it as
        // when nor!=noc trabsposing changes the dimention
        int n = arr.length, m = arr[0].length;
        int startc = 0, endc = m - 1;

        while (startc < endc) {
            // for every row swap column elemets startc , endc
            for (int i = 0; i < n; i++) {
                swap(arr, i, startc, i, endc);
            }
            startc++;
            endc--;
        }
        System.out.println("Rotated Clockwise");
        display(arr);

    }

    public static void rotateAntiClockwise(int[][] arr) {
        System.out.println("Original");
        display(arr);

        arr = transpose(arr);
        // column shifting
        int n = arr.length, m = arr[0].length;
        int startr = 0, endr = n - 1;

        while (startr < endr) {
            // for every clolumn swap row elemets startr , endr
            for (int j = 0; j < m; j++) {
                swap(arr, startr, j, endr, j);
            }
            startr++;
            endr--;
        }
        System.out.println("Rotated AntiClockwise");
        display(arr);
    }

    public static void main(String[] args) {

        // m!=n
        int[][] test1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };

        int[][] test2 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };

        // m==n
        int[][] arr1 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[][] arr2 = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

        rotateClockwise(arr1);
        rotateAntiClockwise(arr2);

        rotateClockwise(test1);
        rotateAntiClockwise(test2);

    }
}
