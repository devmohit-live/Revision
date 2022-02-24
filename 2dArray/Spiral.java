import java.io.*;
import java.util.*;

public class Spiral {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = sc.nextInt();

        spiralAntiClockWise(arr);
        spiralClockWise(arr);
    }

    public static void spiralAntiClockWise(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int rmin = 0, cmin = 0, rmax = n - 1, cmax = m - 1, total = m * n;

        while (total > 0) {

            for (int r = rmin; r <= rmax && total > 0; r++) {
                System.out.println(arr[r][cmin]);
                total--;
            }
            cmin++;

            for (int c = cmin; c <= cmax && total > 0; c++) {
                System.out.println(arr[rmax][c]);
                total--;
            }
            rmax--;

            for (int r = rmax; r >= rmin && total > 0; r--) {
                System.out.println(arr[r][cmax]);
                total--;
            }
            cmax--;

            for (int c = cmax; c >= cmin && total > 0; c--) {
                System.out.println(arr[rmin][c]);
                total--;
            }
            rmin++;

        }
    }

    // //wRONFG FAILS ON SOME CASES ON LEETCODE
    // public static void spiralClockWise(int[][] arr) {
    // int n = arr.length, m = arr[0].length;
    // int rmin = 0, cmin = 0, rmax = n - 1, cmax = m - 1, total = m * n;

    // while (total > 0) {
    // for (int itr = cmin; itr <= cmax; itr++) {
    // System.out.println(arr[rmin][itr]);
    // total--;
    // }
    // rmin++;

    // for (int itr = rmin; itr <= rmax; itr++) {
    // System.out.println(arr[itr][cmax]);
    // total--;
    // }
    // cmax--;

    // for (int itr = cmax; itr >= cmin; itr--) {
    // System.out.println(arr[rmax][itr]);
    // total--;
    // }
    // rmax--;

    // for (int itr = rmax; itr >= rmin; itr--) {
    // System.out.println(arr[itr][cmin]);
    // total--;
    // }
    // cmin++;
    // }
    // }

    // CORRECT:
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<Integer>();
        if (matrix == null || matrix.length == 0)
            return result;

        int startRow = 0, endRow = matrix.length - 1;
        int startCol = 0, endCol = matrix[0].length - 1;
        int dir = 0;

        while (startRow <= endRow && startCol <= endCol) {
            switch (dir) {
            case 0: // LEFT TO RIGHT
                for (int col = startCol; col <= endCol; col++)
                    result.add(matrix[startRow][col]);
                startRow++;
                break;

            case 1: // TOP TO DOWN
                for (int row = startRow; row <= endRow; row++)
                    result.add(matrix[row][endCol]);
                endCol--;
                break;

            case 2:// RIGHT TO LEFT
                for (int col = endCol; col >= startCol; col--)
                    result.add(matrix[endRow][col]);
                endRow--;
                break;

            case 3:// DOWN TO UP
                for (int row = endRow; row >= startRow; row--)
                    result.add(matrix[row][startCol]);
                startCol++;
                break;

            }

            dir = (dir + 1) % 4;
        }

        return result;
    }

}