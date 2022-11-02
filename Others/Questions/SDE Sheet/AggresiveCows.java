public class AggresiveCows {
    public static void main(String[] args) throws java.lang.Exception {
        final Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int cows = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(getMaxMinDistance(arr, cows));
        }

    }

    private static int getMaxMinDistance(int[] arr, int cows) {
        int n = arr.length;
        Arrays.sort(arr);
        int si = 1, ei = arr[n - 1] - arr[0];
        int ans = -1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (isValidMinDistance(arr, mid, cows)) {
                ans = mid;
                // try for others greater values of minDtance
                si = mid + 1;

            } else {
                ei = mid - 1;// we have to chekc for lesser min distance try smaller values

            }

        }

        return ans;

    }

    // sonwhow we got the min distance and we hacve to chek wheather we are able to
    // place all the given cows
    private static boolean isValidMinDistance(int[] arr, int minDist, int cows) {
        int n = arr.length;
        int cowsPlaced = 1; // we have placed this cow at 0th postiton
        int lastPlaced = arr[0]; //last placed at

        for (int i = 1; i < n; i++) {
            if (arr[i] - lastPlaced >= minDist) {
                cowsPlaced++;
                lastPlaced = arr[i];
            }

        }

        if (cowsPlaced >= cows)
            return true;

        return false;

    }
}
