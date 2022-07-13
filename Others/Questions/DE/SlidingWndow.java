import java.util.HashMap;
import java.util.Map;

public class SlidingWndow {
    // no of substring with k distince characters
    // Given a string s and an int k, return an int representing the number of
    // substrings (not unique) of s with exactly k distinct characters. If the given
    // string doesn't have k distinct characters, return 0.
    // 992. Subarrays with K Different Integers(SIMILAR)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int ans[] = atMostKUniqueChars(s, k);
        int maxLen = ans[0], countOfAllSubstringWihtAtmostKUniq = ans[1];
        System.out.println(countOfAllSubstringWihtAtmostKUniq);
        return maxLen;
    }

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
