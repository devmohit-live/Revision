import java.io.*;
import java.util.*;

public class SearchInSortedLists {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n;
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = sc.nextInt();

        int x = sc.nextInt();

        findInSorted(arr, x);
    }

    // WORKS on all: sqaure matrix, m*n matrix, list of lists having inner lists of
    // different size

    public static void findInSorted(int[][] arr, int x) {
        int n = arr.length;
        // why not c= n-1
        int r = 0, c = arr[0].length - 1;
        boolean found = false;

        // why not c<n directly
        // Reason: beacuse it wouldn't work if we have to search in lists of lists or
        // array of lists
        // each list inside a list may be of different length

        while (r < n && r >= 0 && c < arr[r].length && c >= 0) {
            if (arr[r][c] > x) {
                c--;
            } else if (arr[r][c] < x) {
                r++;
            } else {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println(r);
            System.out.println(c);
        } else {
            System.out.println("Not Found");
        }

    }

}