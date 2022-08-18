public class MinFlips {

    /*
     * Q1. Find min number of flips to make a binary string with all 1. flips can be
     * of two operations- a) Take any two adjacents chars and flip them. b) Take any
     * three adjacents chars and flip them.
     * 
     * Ex. 1010 - 2 (first convert to 1001 by a) then again convert 1001 to 1111 by
     * a) method)
     * 
     */
    public static void main(String[] args) {
        String s = "1010";
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = s.charAt(i) == '1' ? 1 : 0;

        int ans = Math.min(minKBitFlips(arr, 2), minKBitFlips(arr, 3));
        System.out.println(ans);
    }

    public static int minKBitFlips(int[] A, int K) {
        int n = A.length, flipped = 0, res = 0;
        int[] isFlipped = new int[n];
        for (int i = 0; i < A.length; ++i) {
            if (i >= K)
                flipped ^= isFlipped[i - K];
            if (flipped == A[i]) {
                if (i + K > A.length)
                    return Integer.MAX_VALUE;
                isFlipped[i] = 1;
                flipped ^= 1;
                res++;
            }
        }
        return res;
    }
}
