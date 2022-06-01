import java.util.HashMap;

public class LongestSubstringWORepChars {
    // LC : 9
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        int si = 0, ei = 0, n = s.length();
        int max = 0;
        while (ei < n) {
            char ch = s.charAt(ei);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // window size and hashmap size are equals: means all the characters are
            // unique(single occurance)
            if (ei - si + 1 == map.size()) {
                max = Math.max(ei - si + 1, max);
            }

            // while the winow size > map (some chars must habe been repeated)
            while (ei < n && ei - si + 1 > map.size()) {
                char rm = s.charAt(si);
                map.put(rm, map.get(rm) - 1);

                if (map.get(rm) == 0)
                    map.remove(rm);

                si++;

                max = Math.max(ei - si + 1, max);
            }

            ei++;

        }

        return max;
    }

}
