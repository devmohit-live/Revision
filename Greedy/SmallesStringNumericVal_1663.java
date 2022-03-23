import java.util.Arrays;

/**
 * SmallesStringNumericVal_1663
 */
public class SmallesStringNumericVal_1663 {

    // Approach:
    /*
     * Our main goal is to build a string lexiographically smallest. So the smallest
     * possible string for any n would be full of 'a'. Now we have to fuflill
     * another condition that is we want the sum of characters to be some k. By
     * maintaining the lexiograhically smallest string we have to full fill this
     * condition. So we start from rightmost part. If we have k greater than 25
     * ['z'] we replace it with 'z' and subtract it with 25 == 'z'. Else replace it
     * with the required character with k as number. Do this until k > 0.
     */

    public String getSmallestString(int n, int k) {
        if (n == 0)
            return "";

        char[] res = new char[n];
        Arrays.fill(res, 'a');
        k -= n; // filled with n*'a' initally

        while (k > 0) {
            int val = Math.min(k, 25); // fill with largest value possible
            k -= val;
            res[--n] = (char) ('a' + val);

        }

        return new String(res);
    }
}