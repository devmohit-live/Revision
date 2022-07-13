import java.util.HashMap;

public class LongestSubstringWORepChars {
    private int exectKuniChars(String s, int k) {
        return atMostKUniqueChars(s, k)[1] - atMostKUniqueChars(s, k - 1)[1];
    }


    // https://www.lintcode.com/problem/386/
    // 0: maxlen of string having atmost k uniq chars
    // 1: no of string having atmost k unqiue chars
    private int[] atMostKUniqueChars(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0, right = 0, left = 0, len = s.length(), maxLen = 0;
        while (right < len) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
            while (map.size() > k) { // constraint is violated so we start shrinking the left window to restore the
                                     // constraint
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if (map.get(s.charAt(left)) == 0)
                    map.remove(s.charAt(left));
                left++;
            }

            int currLen = right - left + 1;
            maxLen = Math.max(maxLen, currLen); // this is a valid window [left, right] with at most K distinct elemetns
            right++;
            count += currLen; // this complete window an all of it's parts have <= k uniw chars

        }
        return new int[] { maxLen, count };
    }

}
