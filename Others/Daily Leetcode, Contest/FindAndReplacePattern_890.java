public abstract class FindAndReplacePattern_890 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] p = normalize(pattern);
        List<String> res = new ArrayList<String>();
        for (String w : words)
            if (Arrays.equals(normalize(w), p))
                res.add(w);
        return res;
        // return Arrays.stream(words).filter(word -> Arrays.equals(p,
        // normalize(word))).collect(Collectors.toList());
    }

    public int[] normalize(String w) {
        HashMap<Character, Integer> m = new HashMap<>();
        int n = w.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            m.putIfAbsent(w.charAt(i), m.size());
            res[i] = m.get(w.charAt(i));
        }
        return res;
    }
}
