public class ValidWordInPuzzle {

    // Leetcode 1178
    // It is basically a BIts based question on basis of constraint

    // First approach using set : TLE

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int pl = puzzles.length, wl = words.length;
        List<Integer> ans = new ArrayList<>(pl);
        HashSet<Character>[] pset = new HashSet[pl];
        HashSet<Character>[] wset = new HashSet[wl];
        for (int i = 0; i < pl; i++) {
            pset[i] = new HashSet<>();
            for (char ch : puzzles[i].toCharArray())
                pset[i].add(ch);
        }

        for (int i = 0; i < wl; i++) {
            wset[i] = new HashSet<>();
            for (char ch : words[i].toCharArray())
                wset[i].add(ch);
        }

        for (int i = 0; i < pl; i++) {
            Set<Character> puzzle = pset[i];
            int count = 0;

            // first char of puzzle should be present
            char fchar = puzzles[i].charAt(0);
            for (int j = 0; j < wl; j++) {
                boolean isPresent = false, isInSet = true;
                Set<Character> word = wset[j];
                if (word.contains(fchar))
                    isPresent = true;

                for (char ch : word)
                    if (!puzzle.contains(ch))
                        isInSet = false;

                if (isPresent && isInSet)
                    count++;
            }
            ans.add(count);
        }

        return ans;
    }

    // why? map<char,arraylist<wordmask>>
    // map ensures that map[a] => corresponds to all the words that have a in it,
    // using that we can reduce or operationsal
    // space for current puzzle to only that elemets which contains the starting
    // char of current puzzle

    // what arraylist in map excatly holds:
    // it doesn't exactly holds the wors(string) but the processesd bitmask
    // why porcessed bitmask? => so that we don't have to compute the bitmaks when
    // we are inside the loop , making the operation more slower(n*n), we can
    // preprocess the bitmasking and get it in the operational loop time

    private int getBitMask(String s) {
        int ans = 0;
        for (char ch : s.toCharArray()) {
            int x = (ch - 'a');
            ans = (ans | (1 << x));
        }
        return ans;
    }

    public List<Integer> findNumOfValidWordsBetter(String[] words, String[] puzzles) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            map.put((char) ('a' + i), new ArrayList<>());

        // Step1 : make mask for puzzles and words
        for (String word : words) {
            int mask = getBitMask(word);
            // adding this mask for all the unique character it contains into map's
            // arraylist
            HashSet<Character> set = new HashSet<>();
            // to avoid adding same mask to arraylist for repeated characters
            for (char ch : word.toCharArray()) {
                if (!set.contains(ch)) {
                    set.add(ch);
                    map.get(ch).add(mask);
                }
            }

        }
        // System.out.println(map);

        for (String puzzle : puzzles) {
            int pmask = getBitMask(puzzle);

            // get all the words(basically their masks) whoch contains first letter of this
            // puzzle
            char first = puzzle.charAt(0);
            int count = 0;
            List<Integer> validWords = map.get(first);

            for (int wmask : validWords) {
                if ((pmask & wmask) == wmask)
                    count++;
            }

            ans.add(count);

        }

        return ans;
    }

    // Approach 3: Best : Instead of stroing arrayslist just store the count based
    // on the mask
    // and do bits sbmasking enmeration Provided solution on Leetcode

    // Bits Enumeration / Submasking:
    // https://cp-algorithms.com/algebra/all-submasks.html

    // Approach 4: Tries(complicated as well as not very optimal)

}
