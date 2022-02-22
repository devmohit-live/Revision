public class LongestSubstrWithAtleastKRep {
    // brute forcew: Create all posible substring and check if all the chars in the
    // created string have freq > k or mot if freq> k take max of them

    public int longestSubstring(String s, int k) {
        int n = s.length(), max = 0;
        if (n == 0 || k == 0 || k > n)
            return 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String str = s.substring(i, j + 1);
                if (isValid(str, k))
                    max = Math.max(max, str.length());
            }
        }

        return max;
    }

    private boolean isValid(String s, int k) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray())
            freq[ch - 'a']++;
        for (int el : freq)
            if (el != 0 && el < k)
                return false;
        return true;
    }

    // Optimized: We know that if any character comes in substring that has freq < k
    // it will make the current substring invalid
    // so what we can do is : go till the characters having freq >=k o encountering
    // character < k freq make a substring invalid
    // make two parts 1 before that encounter(valid) and one after all the freq <k
    // are done to end and take max of length

    // Dividing the string into valid parts instead of creating all substrngs

    public int longestSubstringOptmized(String s, int k) {
        int n = s.length();
        if (n == 0 || k == 0 || k > n)
            return 0;
        if (k <= 1)
            return n;

        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        int j = 0;
        // break into two areas : s1: all chars have freq>=k(which also need to be
        // checked further wheter those ch whose freq >k in completee string is actually
        // occuring >=k times ) , s2 = unkown

        while (j < n && map.get(s.charAt(j)) >= k)
            j++;
        // get the same for rest of the string
        if (j >= n - 1)
            return j; // no further string is left this is the complete string

        // s1
        int l1 = longestSubstringOptmized(s.substring(0, j), k);
        // s2 : start from the next valid point
        while (j < n && map.get(s.charAt(j)) < k)
            j++; //

        int l2 = (j < n) ? longestSubstringOptmized(s.substring(j), k) : 0;
        return Math.max(l1, l2);

    }
}
