public class LongestSubstringWORC {
    // Leetcode 3:

    // O(2n) => visiting each char twice in worst case (each time doing l++) for
    // already existing char in set
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int len = 0, maxlen = 0, l = 0, r = 0, end = s.length();
        // int start = 0;

        while (r < end) {
            char ch = s.charAt(r);

            if (!set.contains(ch)) {
                len = (r - l) + 1; // first calculat len then update
                // maxlen = Math.max(maxlen,len);
                set.add(ch);
                r++;
            } else {
                set.remove(s.charAt(l));
                l++; // first update then calulate
                len = (r - l) + 1;
            }
            if (maxlen < len) {
                // start = l;
                maxlen = len;
            }
            maxlen = Math.max(maxlen, len);

        }
        // System.out.println(s.substring(start, start + maxlen));
        return maxlen;
    }

    // in previous approach l was pointing to the character to be removed in ch was
    // found in set and it keeps removing it
    // until char(l) were there in set

    // O(n) => directly moving l to correct position.
    public int lengthOfLongestSubstring2(String s) {
        int l = 0, r = 0, len = 0, max = 0, limit = s.length();
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        // char, lastexploredindex
        while (r < limit) {
            char ch = s.charAt(r);

            if (!map.containsKey(ch)) {
                len = r - l + 1;
                map.put(ch, r);
                // r++;
            } else {
                int lastFounfIndex = map.get(ch);
                // if(l < = lastFounfIndex < = r){
                // l = lastFounfIndex; //otherwise taht char is not in consideartion range of
                // subarray
                // }
                // or
                l = Math.max(l, lastFounfIndex + 1);

                map.put(ch, r); // update the last explored index
                len = r - l + 1;
            }
            r++;
            // max = Math.max(len, max);
            if (max < len) {
                max = len;
                start = l;
            }

        }
        System.out.println(s.substring(start, start + max));
        return max;
    }

}
