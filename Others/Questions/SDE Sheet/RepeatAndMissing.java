public class RepeatAndMissing {

    public int[] repeatedNumber(final int[] A) {
        // return constantSpace(A);
        return constantSpaceMaths(A);
    }

    private int[] onTimespace(int[] A) {
        int n = A.length;
        int missing = -1, repeated = -1;
        int[] count = new int[n + 1];
        for (int el : A) {
            count[el]++;
            if (count[el] > 1)
                repeated = el;
        }

        for (int i = 1; i <= n; i++)
            if (count[i] == 0) {
                missing = i;
                break;
            }
        return new int[] { repeated, missing };
    }

    private int[] constantSpaceMaths(int[] A) {
        long len = A.length;

        long S = (len * (len + 1)) / 2; // 1+2+..n
        long P = (len * (len + 1) * (2 * len + 1)) / 6; // sum of squares
        long missingNumber = 0, repeating = 0;

        for (int i = 0; i < len; i++) {
            S -= A[i];
            P -= (long) A[i] * A[i];
        }

        missingNumber = (S + P / S) / 2;
        repeating = missingNumber - S;

        return new int[] { (int) repeating, (int) missingNumber };
    }

    private int[] constantSpace(int[] arr) {
        int xor1 = 0;
        int set_bit_no;
        int i, missing = 0, repeated = 0, n = arr.length;

        xor1 = arr[0];

        /* Get the xor of all array elements */
        for (i = 1; i < n; i++)
            xor1 = xor1 ^ arr[i];

        /*
         * XOR the previous result with numbers from 1 to n
         */
        for (i = 1; i <= n; i++)
            xor1 = xor1 ^ i;

        /* Get the rightmost set bit in set_bit_no */
        set_bit_no = xor1 & ~(xor1 - 1);

        /*
         * Now divide elements into two sets by comparing rightmost set bit of xor1 with
         * the bit at the same position in each element. Also, get XORs of two sets. The
         * two XORs are the output elements. The following two for loops serve the
         * purpose
         */

        for (i = 0; i < n; i++) {
            if ((arr[i] & set_bit_no) != 0)
                /* arr[i] belongs to first set */
                missing = missing ^ arr[i];

            else
                /* arr[i] belongs to second set */
                repeated = repeated ^ arr[i];
        }
        for (i = 1; i <= n; i++) {
            if ((i & set_bit_no) != 0)
                /* i belongs to first set */
                missing = missing ^ i;

            else
                /* i belongs to second set */
                repeated = repeated ^ i;
        }

        // at last do a linear check which amont x and y is missing or repeating

        /* *x and *y hold the desired output elements */

        // can't determine which is missing and which is repeated
        return new int[] { repeated, missing };
    }
}
