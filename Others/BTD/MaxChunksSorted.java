/**
 * MaxChunksSorted
 */
public class MaxChunksSorted {

    //LC : 
    public int maxChunksToSorted(int[] arr) {
        // array to be finally sorted, sorted after division into each chunks
        // sorted at any point => max of left <= min of right
        int n = arr.length, ans = 1; // complete array is sorted(biggest chunk)
        int[] maxOfLeft = new int[n], minOfRight = new int[n];

        // init
        maxOfLeft[0] = arr[0];
        minOfRight[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++)
            maxOfLeft[i] = Math.max(maxOfLeft[i - 1], arr[i]);

        for (int i = n - 2; i >= 0; i--)
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);

        boolean selfSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (minOfRight[i + 1] >= maxOfLeft[i])
                ans++;
        }

        return ans;

    }
}