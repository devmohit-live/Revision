import java.io.*;
import java.util.*;

public class Saddle {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n;
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = sc.nextInt();

        saddle(arr);

    }

    public static void saddle(int[][] arr) {
        int saddle = -1;

        for (int[] a : arr) {

            int minInRow = (int) 1e9, minidx = 0;

            for (int i = 0; i < a.length; i++) {

                if (a[i] < minInRow) {
                    minidx = i;
                    minInRow = a[i];
                }
            }

            int maxInColumn = -(int) 1e9, maxidx = 0;
            for (int i = 0; i < arr.length; i++) {
                maxInColumn = Math.max(maxInColumn, arr[i][minidx]);
            }

            if (maxInColumn == minInRow) {
                saddle = maxInColumn;
            }

        }

        if (saddle == -1)
            System.out.println("Invalid input");
        else
            System.out.println(saddle);
    }

}