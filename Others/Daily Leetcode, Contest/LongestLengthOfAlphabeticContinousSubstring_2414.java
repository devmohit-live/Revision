/**
 * LongestLengthOfAlphabeticContinousSubstring_2414
 */
public class LongestLengthOfAlphabeticContinousSubstring_2414 {

    public int longestContinuousSubstring(String s) {
        int res = 1, len = 1;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == 1) {
                len++;
            } else
                len = 1;

            res = Math.max(res, len);
        }

        return res;
    }
}