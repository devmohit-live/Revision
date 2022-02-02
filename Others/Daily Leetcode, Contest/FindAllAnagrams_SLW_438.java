class FindAllAnagrams_SLW_438 {
    public List<Integer> findAnagrams(String s, String t) {
        int si = 0, ei = 0, n = s.length(), k = p.length();
        List<Integer> ans = new ArrayList<>();
        if (k > n)
            return ans;

        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : p.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        int counter = map.size(); // distinct elemets in string

        while (ei < n) {
            char ch = s.charAt(ei);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0)
                    counter--;
            }

            while (counter == 0) {
                char rm = s.charAt(si);

                if (map.containsKey(rm)) {
                    map.put(rm, map.get(rm) + 1);
                    if (map.get(rm) > 0)
                        counter++;

                }

                if (ei - si + 1 == k)
                    ans.add(si);
                si++;
            }

            ei++;
        }

        return ans;
    }

    // Approach 2: Using two maps

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        Map<Character, Integer> reference = new HashMap<>(); // reference map for p
        for (int i = 0; i < p.length(); i++)
            reference.put(p.charAt(i), reference.getOrDefault(p.charAt(i), 0) + 1);

        Map<Character, Integer> map = new HashMap<>(); // map to cache the chars in sliding window
        int start = 0, end = 0, match = 0;
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (reference.containsKey(c1)) {
                map.put(c1, map.getOrDefault(c1, 0) + 1);
                if (map.get(c1).equals(reference.get(c1)))
                    match++;
            }
            while (match == reference.size()) {
                if (end - start + 1 == p.length())
                    ans.add(start);

                char c2 = s.charAt(start);
                if (reference.containsKey(c2)) {
                    map.put(c2, map.get(c2) - 1);
                    if (map.get(c2) < reference.get(c2))
                        match--;
                }
                start++;
            }
            end++;
        }
        return ans;
    }
}
