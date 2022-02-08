public class UglyNumber {
    // Leetcode 264
    public static void main(String[] args) {
        // 1<=k<=200

        // Appraoch2:
        preprocessing();

        int[] ks = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 15, 16, 20, 22, 25, 28, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100,
                150, 175, 200 };
        for (int k : ks) {
            System.out
                    .println("k= " + k + " " + "Using naive: " + kthUglyNumberNaive(k) + " using DP: " + uglies[k - 1]);
        }
        // System.out.println(isUgly(7));
        // System.out.println(isUgly(8));

    }

    // time: freater than mlogn less than n*n as
    // m> n here ; we are skipping some numbers to get kth ugly number to get nth
    // ugly number it will go beyong range[1,n]
    // the upper boung of that range is represented ny m
    private static long kthUglyNumberNaive(int k) {
        if (k <= 6)
            return k;

        int count = 6; // already settled 6 uglyNumbers
        int i = 6;
        /**
         * or simlpy start with count=1 and i=1 as k=1 1 is an exceptional case ugly
         * number
         */
        while (count < k) {
            i++;
            if (isUgly(i))
                ++count;

        }
        return i;
    }

    // log n
    private static boolean isUgly(int n) {
        int[] divisors = { 2, 3, 5 };
        for (int i : divisors) {
            while (n >= i && n % i == 0) {
                n = n / i;
            }
        }
        if (n == 1)
            return true;
        return false;
    }

    /**
     * Approach 2: tiem:
     * 
     * optimal for range queries => time: (qn) q= number of queries
     */
    // better O(n) solution usinf dp => useful in range queries
    static int[] uglies;
    static int kmax;

    private static void preprocessing() {
        // kmax= from the constraint given

        kmax = 200;
        uglies = new int[kmax];
        uglies[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int next_multiple_of_2 = 2;
        int next_multiple_of_3 = 3;
        int next_multiple_of_5 = 5;
        int next_ugly_no = 1;

        for (int i = 1; i < kmax; i++) {
            next_ugly_no = Math.min(next_multiple_of_2, Math.min(next_multiple_of_3, next_multiple_of_5));

            uglies[i] = next_ugly_no;
            // checking for duplicacy

            if (next_ugly_no == next_multiple_of_2) {
                i2 = i2 + 1; // i2 here represtns the power of two in prime factorization
                next_multiple_of_2 = uglies[i2] * 2;
            }
            if (next_ugly_no == next_multiple_of_3) {
                i3 = i3 + 1;
                next_multiple_of_3 = uglies[i3] * 3;
            }
            if (next_ugly_no == next_multiple_of_5) {
                i5 = i5 + 1;
                next_multiple_of_5 = uglies[i5] * 5;
            }

        }
    }
}
