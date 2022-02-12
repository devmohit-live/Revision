public class WordLadder_127 {
    // Leetcode 127 : Word Ladder
    // Treat every word as node and every transformation as an edge or wt 1;
    // shortest path in terms of edges: bfs

    // Space: O(n) Haset, Q: O(l*26*N) : O(l*n)
    // Time: set: O(n) + ( possible word creation: O(26*l*l*) * N(we are adding only
    // elements in q which exists in wordlist) : O(n) + O(N*26*l*l) => O(N*l*l)

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int lv = 0, n = wordList.size(), l = endWord.length();
        Set<String> set = new HashSet<>();
        set.addAll(wordList); // N

        if (!set.contains(endWord))
            return 0; // end woprd not present in wordlist: not possible
        // System.out.println(set);
        LinkedList<String> q = new LinkedList<>();
        q.addLast(beginWord);
        if (set.contains(beginWord))
            set.remove(beginWord);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String rm = q.removeFirst();
                // System.out.println(rm);
                if (rm.equals(endWord))
                    return lv + 1; // l

                // generate all possible words which can be formed by chagin a single letter in
                // rm
                for (int i = 0; i < l; i++) { // l
                    char ch[] = rm.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) { // 26
                        ch[i] = c;
                        String modified = new String(ch); // l
                        if (set.contains(modified)) {
                            q.addLast(modified);
                            set.remove(modified); // transformed once
                            // hence remove from set(node need fo transformation again : no need for cycle)
                        }
                    }
                }

            }

            lv++;
        }

        return 0; // not possible
    }
}
