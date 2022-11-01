public class LongestSubstringWithoutRepeatingChars {
    public int lengthOfLongestSubstring(String s) {
        return solve(s.toCharArray());
    }

    //Brute: create all substring and check freq of each chars in substring is 1 or not

    private int solve(char[] arr) {
        int si = 0, n = arr.length, ei = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (ei < n) {
            char ch = arr[ei];
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            if (ei - si + 1 == map.size()) // every char is unique
                max = Math.max(max, ei - si + 1);

            while (ei - si + 1 > map.size()) {
                char rm = arr[si];
                map.put(rm, map.get(rm) - 1);
                if (map.get(rm) == 0)
                    map.remove(rm);

                si++;
                if (ei - si + 1 == map.size()) //every char is unique
                    max = Math.max(max, ei - si + 1);
            }
            ei++;
        }

        return max;
    }
}
