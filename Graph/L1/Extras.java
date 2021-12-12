//no of island Using direction vector and without using extra sapce
import java.io.*;
import java.util.*;

public class Extras {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][n];

        for (int i = 0; i < arr.length; i++) {
            String parts = br.readLine();
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
            }
        }
        int count = 0;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    count++;
                    dfs(arr, i, j, dir);
                }
            }
        }

        System.out.println(count);
    }

    private static void dfs(int[][] arr, int i, int j, int[][] dir) {

        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || arr[i][j] != 0) {
            return;
        } else {
            arr[i][j] = 2;
        }

        for (int[] d : dir) {

            int r = i + d[0];
            int c = j + d[1];
            dfs(arr, r, c, dir);
        }

    }

}