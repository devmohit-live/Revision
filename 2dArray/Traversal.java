import java.util.Arrays;

public class Traversal {
    // wave Traversal
    public static void wave1(int[][] arr) {
        // down, up,down,up
        int n = arr.length, m = arr[0].length;

        for (int c = 0; c < m; c++) {
            if (c % 2 == 0)
                for (int i = 0; i < n; i++) {
                    System.out.print(arr[i][c] + "\t");
                }
            else
                for (int i = n - 1; i >= 0; i--) {
                    System.out.print(arr[i][c] + "\t");
                }
            System.out.println();
        }
    }

    public static void wave2(int[][] arr) {
        // left, right,left ,right
        int n = arr.length, m = arr[0].length;

        for (int r = 0; r < n; r++) {
            if (r % 2 == 0)
                for (int i = 0; i < m; i++) {
                    System.out.print(arr[r][i] + "\t");
                }
            else
                for (int i = m - 1; i >= 0; i--) {
                    System.out.print(arr[r][i] + "\t");
                }
            System.out.println();
        }
    }

    // GAP Statergy : USeful in graph and DP

    // diagonal means i,j both moves with same speed not necessarly i & j are equal,
    // rate change should be same:
    // ex: 2,3 => 3,4 => 4,5 ...

    // upper triangle print
    public static void diagonalTraversal0(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        // max gap can be = last idx of column
        for (int gap = 0; gap < m; gap++) {
            int i = 0, j = gap;
            while (i < n && j < m) {
                System.out.println(arr[i][j]);
                i++;
                j++;
            }
        }

    }

    // or
    public static void diagonalTraversal1(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        // max gap can be = last idx of column as we are goinf right
        for (int gap = 0; gap < m; gap++) {
            for (int i = 0, j = gap; j < m && i < n; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }

    }

    // full diagonal traversal
    public static void completeDiagonalTraversal(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        // lower half diagonal (avoid printing main diagonal twice)
        // lower diagnoal max limit for gap is no.of rows as we are going down
        for (int gap = n - 1; gap > 0; gap--) {
            for (int i = gap, j = 0; j < m && i < n; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }
        // upper half
        for (int gap = 0; gap < m; gap++) {
            for (int i = 0, j = gap; j < m && i < n; i++, j++) {
                System.out.println(arr[i][j]);
            }
        }

    }

    static void display(int[][] arr) {
        for (int[] a : arr)
            System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 }, { 17, 18, 19, 20 } };
        display(arr);
        System.out.println();
        wave1(arr);
        System.out.println();
        wave2(arr);
        System.out.println();
        diagonalTraversal1(arr);
        System.out.println("Complete Diagonal: \n");
        completeDiagonalTraversal(arr);
        System.out.println();
    }
}
