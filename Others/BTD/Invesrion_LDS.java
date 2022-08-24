import java.util.Arrays;

public class Invesrion_LDS {
    static int numOfIncSubseqOfSizeK(int arr[], int n, int k) {
        int dp[][] = new int[k][n], sum = 0;

        // count of increasing subsequences of size 1
        // ending at each arr[i]
        Arrays.fill(dp[0], 1);
        // or
        // for (int i = 0; i < n; i++) {
        // dp[0][i] = 1;
        // }

        // building up the matrix dp[][]
        // Here 'l' signifies the size of
        // increasing subsequence of size (l+1).
        for (int l = 1; l < k; l++) {

            // for each increasing subsequence of size 'l'
            // ending with element arr[i]
            for (int i = l; i < n; i++) {

                // count of increasing subsequences of size 'l'
                // ending with element arr[i]
                dp[l][i] = 0;
                for (int j = l - 1; j < i; j++) {
                    if (arr[j] < arr[i]) {
                        dp[l][i] += dp[l - 1][j];
                    }
                }
            }
        }

        // sum up the count of increasing subsequences of
        // size 'k' ending at each element arr[i]
        for (int i = k - 1; i < n; i++) {
            sum += dp[k - 1][i];
        }

        // required number of increasing
        // subsequences of size k
        return sum;
    }

    static int numOfDecSubseqOfSizeK(int arr[], int n, int k) {
        int dp[][] = new int[k][n], sum = 0;

        // count of increasing subsequences of size 1
        // ending at each arr[i]
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        // building up the matrix dp[][]
        // Here 'l' signifies the size of
        // increasing subsequence of size (l+1).
        for (int l = 1; l < k; l++) {

            // for each increasing subsequence of size 'l'
            // ending with element arr[i]
            for (int i = l; i < n; i++) {

                // count of increasing subsequences of size 'l'
                // ending with element arr[i]
                dp[l][i] = 0;
                for (int j = l - 1; j < i; j++) {
                    if (arr[j] > arr[i]) {
                        dp[l][i] += dp[l - 1][j];
                    }
                }
            }
        }

        // sum up the count of increasing subsequences of
        // size 'k' ending at each element arr[i]
        for (int i = k - 1; i < n; i++) {
            sum += dp[k - 1][i];
        }

        // required number of increasing
        // subsequences of size k
        return sum;
    }

    // Driver program to test above
    public static void main(String[] args) {
        // int arr[] = {12, 8, 11, 13, 10, 15, 14, 16, 20};
        int[] arr = { 5, 3, 4, 2, 1 }; // 7
        int[] arr2 = { 4, 1, 3, 2, 5 }; // 1
        int n = arr.length;
        final int k = 3;

        System.out.print("Number of Increasing Subsequences of size " + k + " = " + numOfDecSubseqOfSizeK(arr, n, k));

        System.out.print("Number of Increasing Subsequences of size " + k + " = " + numOfDecSubseqOfSizeK(arr2, n, k));

    }
}
