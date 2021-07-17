import java.io.*;
import java.util.*;

public class ExitPoint {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = sc.nextInt();

        int[] sol = exitPoint(arr);
        System.out.println(sol[0]);
        System.out.println(sol[1]);
    }

    public static int[] exitPoint(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int r = 0, c = 0, d = 0;

        while (true) {

            d = (d + arr[r][c]) % 4;

            if (d == 0) {
                // east
                c++;
                if (c == m) {
                    c--;
                    break;
                }
            } else if (d == 1) {
                // south
                r++;
                if (r == n) {
                    r--;
                    break;
                }
            } else if (d == 2) {
                // west
                c--;
                if (c == -1) {
                    c++;
                    break;
                }
            } else {
                // north
                r--;
                if (r == -1) {
                    r++;
                    break;
                }
            }

        }

        return new int[] { r, c };

    }

}