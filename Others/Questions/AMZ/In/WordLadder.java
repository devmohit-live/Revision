public class WordLadder {
    // WOrd Ladder II : TLE
    // DFs cause tle , and I am not able to figure it out how to form multiple
    // ansers using bfs
    // as i will get to know whether this path forms an answer or not at last level
    // only
    Map<String, Set<String>> graph;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (beginWord.length() != endWord.length())
            return ans;

        Set<String> words = new HashSet<>(wordList);
        words.add(beginWord);
        if (!words.contains(endWord))
            return ans;

        // build graph for bfs .. string : nodes;
        graph = new HashMap<>();
        buildGraph(words);
        // System.out.println("Graph is : ");
        // System.out.println(graph);
        Set<String> vis = new HashSet<>();
        // dfs : as we have to find all paths
        List<String> small = new ArrayList<String>();
        small.add(beginWord);

        dfs(beginWord, endWord, small, ans, vis);
        // System.out.println(ans);
        final int minLen = ans.stream().map(arr -> arr.size()).reduce(Integer.MAX_VALUE, (a, b) -> Math.min(a, b));

        // System.out.println(minLen);
        List<List<String>> res = ans.stream().filter(ar -> ar.size() == minLen).collect(Collectors.toList());
        // System.out.println(res);

        return res;
    }

    // collect paths
    private void dfs(String src, String des, List<String> small, List<List<String>> ans, Set<String> vis) {
        vis.add(src);
        if (src.equals(des)) {
            ans.add(new ArrayList<>(small));
        }
        for (String nbr : graph.get(src)) {
            if (!vis.contains(nbr)) {
                small.add(nbr);
                dfs(nbr, des, small, ans, vis);
                small.remove(small.size() - 1);
            }
        }
        vis.remove(src);
    }

    // build a graph
    private void buildGraph(Set<String> words) {
        for (String word : words) {
            char[] arr = word.toCharArray();
            graph.put(word, new HashSet<String>());

            for (int i = 0; i < arr.length; i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch == arr[i]) {
                        // System.out.println(word+" "+i+" "+ch);
                        continue;
                    }
                    char tmp = arr[i];
                    arr[i] = ch;
                    String transformed = new String(arr);
                    if (words.contains(transformed)) {
                        graph.get(word).add(transformed);
                    }

                    arr[i] = tmp; // change back string to original
                } // chars

            } // index

        } // words

    }

    // Using Lables : and BFS
    public List<List<String>> findLaddersOptmized(String beginWord, String endWord, List<String> wordList) {
        // boolean value indicate visited
        Map<String, Boolean> wordDict = new HashMap<>();
        for (String word : wordList) {
            wordDict.put(word, false);
        }

        if (!wordDict.containsKey(endWord)) {
            return new ArrayList<>();
        }

        Queue<String> q = new LinkedList<>();
        // level of bfs
        List<List<String>> levels = new ArrayList<>();
        boolean reached = false;
        wordDict.remove(beginWord);
        q.offer(beginWord);

        bfs: while (!q.isEmpty()) { // label : can be put into boolean fx with global/passing variables
            int qSize = q.size();
            List<String> currentLevel = new ArrayList<>();
            for (int i = 0; i < qSize; i++) {
                String curr = q.poll();
                currentLevel.add(curr);
                if (curr.equals(endWord)) {
                    reached = true;
                    break bfs;
                }

                for (String word : wordDict.keySet()) {
                    boolean visited = wordDict.get(word);
                    if (visited || !isConnected(word, curr))
                        continue;

                    wordDict.put(word, true);
                    q.offer(word);
                }
            }
            levels.add(currentLevel);
        }

        if (!reached) {
            return new ArrayList<>();
        }

        LinkedList<String> endPath = new LinkedList<>();
        endPath.add(endWord);

        List<List<String>> paths = new ArrayList<>();
        paths.add(endPath);

        for (int i = levels.size() - 1; i >= 0; i--) {
            List<List<String>> tmpPaths = new ArrayList<>();
            List<String> currentLevel = levels.get(i);
            for (List<String> path : paths) {
                String curr = path.get(0);

                for (String word : currentLevel) {
                    if (!isConnected(word, curr))
                        continue;
                    LinkedList<String> newPath = new LinkedList<>(path);
                    newPath.addFirst(word);
                    tmpPaths.add(newPath);
                }
            }
            paths = tmpPaths;
        }
        return paths;
    }

    private boolean isConnected(String a, String b) {
        if (a.length() != b.length())
            return false;
        int diffCount = 0;
        for (int i = 0; i < a.length() && diffCount < 2; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount == 1;
    }
}
