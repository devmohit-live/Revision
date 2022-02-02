class FindAllAnagrams_SLW_438 {
    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if (t.length() > s.length())
            return result;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0)
                    counter--;
            }
            end++;

            while (counter == 0) {
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);
                    if (map.get(tempc) > 0) {
                        counter++;
                    }
                }
                if (end - begin == t.length()) {
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
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
