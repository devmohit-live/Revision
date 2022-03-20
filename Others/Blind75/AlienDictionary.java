import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class AlienDictionary {

    // For leetcode not Lintocde
    public static String alienOrderMine(String[] words) {
        if (words.length == 0)
            return "";

        Map<Integer, Set<Integer>> graph = new HashMap<>(); // using map bcz arraylist[26] will not determine which
                                                            // characters are actually present in words
        // map only contains entry for the characters which are present in all the word
        // in words
        int[] vis = new int[26]; // for topoDFS
        Map<Integer, Integer> indegree = new HashMap<>(); // for topoBFS

        boolean validDictionary = buildGraph(graph, vis, words, indegree);
        if (!validDictionary)
            return "";

        // topological sort

        StringBuilder sb = new StringBuilder();

        // DFS:
        /*
         * boolean acyclic = true; // vis=> 0: unvisited, 1: vis and part of current
         * path, -1 : visisted but not a // prt of current path for (int el :
         * graph.keySet()) if (vis[el] == 0) acyclic = acyclic && topoDFS(graph, el,
         * vis, sb); return acyclic ? sb.reverse().toString() : "";
         */

        // BFS
        boolean acyclic = topoBfs(graph, indegree, sb);
        return acyclic ? sb.toString() : "";

    }

    private static boolean topoDFS(Map<Integer, Set<Integer>> graph, int src, int[] vis, StringBuilder sb) {
        vis[src] = 1; // visited and part of current path
        boolean res = true;
        // TODO: chcek for default is required or not
        for (int nbr : graph.getOrDefault(src, new HashSet<>())) {
            if (vis[nbr] == 0)
                res = res && topoDFS(graph, nbr, vis, sb);
            else if (vis[nbr] == 1)
                return false; // cycle
            // else continue;
        }
        vis[src] = -1; // visisted but not in current path
        char ch = (char) (src + 'a');
        sb.append(ch);
        return res;
    }

    private static boolean buildGraph(Map<Integer, Set<Integer>> graph, int[] vis, String[] words,
            Map<Integer, Integer> indegree) {
        // initialize map : only contains the words which are actually present in words
        // chars
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                graph.putIfAbsent(ch - 'a', new HashSet<Integer>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            int l1 = first.length(), l2 = second.length();
            boolean samePrefix = true; // flag to chek for invalid dictionary order given

            // find first diff chatracter
            int l = Math.min(l1, l2);

            for (int j = 0; j < l; j++) {
                char u = first.charAt(j), v = second.charAt(j);
                if (u != v) {
                    // edge
                    graph.get(u - 'a').add(v - 'a');
                    indegree.put(u - 'a', indegree.getOrDefault(u - 'a', 0) + 1);
                    samePrefix = false;
                    break;
                }
            }

            if (samePrefix && l1 > l2)
                return false;

        }

        return true;
    }

    // Using Bfs topological sort: Kahn's Algo
    private static boolean topoBfs(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> indegree, StringBuilder sb) {
        LinkedList<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[26];
        for (int i = 0; i < indegree.size(); i++)
            if (indegree.get(i) == 0)
                q.addLast(i);

        int lv = 0;
        while (!q.isEmpty()) {
            int size = 0;
            while (size-- > 0) {
                int rm = q.removeFirst();
                sb.append((char) (rm + 'a'));
                for (int nbr : graph.getOrDefault(rm, new HashSet<>())) {
                    if (!vis[nbr]) {
                        indegree.put(nbr, indegree.get(nbr) - 1);
                        if (indegree.get(nbr) == 0)
                            q.addLast(nbr);
                    }

                }
            }
            lv++;
        }

        if (sb.length() != indegree.size())
            return true;
        return false; // cycle
    }

    // pep
    public static String alienOrderPep(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        Map<Character, Integer> degree = new HashMap<Character, Integer>();
        StringBuilder result = new StringBuilder();
        if (words == null || words.length == 0)
            return result.toString();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            boolean flag = false;
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = new HashSet<Character>();
                    if (map.containsKey(c1))
                        set = map.get(c1);
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    flag = true;
                    break;
                }
            }

            if (flag == false && next.length() < cur.length()) {
                return "";
            }
        }
        Queue<Character> q = new LinkedList<Character>();
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0)
                q.add(c);
        }
        while (!q.isEmpty()) {
            char c = q.remove();
            result.append(c);
            if (map.containsKey(c)) {
                for (char c2 : map.get(c)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2) == 0)
                        q.add(c2);
                }
            }
        }

        if (result.length() != degree.size()) {
            return "";
        }

        return result.toString();
    }

    // Lintcode: Giver the lexo smaller in case of TIE:
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        Map<Character, Integer> degree = new HashMap<Character, Integer>();
        StringBuilder result = new StringBuilder();
        if (words == null || words.length == 0)
            return result.toString();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            boolean flag = false;
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = new HashSet<Character>();
                    if (map.containsKey(c1))
                        set = map.get(c1);
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    flag = true;
                    break;
                }
            }

            if (flag == false && next.length() < cur.length()) {
                return "";
            }
        }
        PriorityQueue<Character> q = new PriorityQueue<Character>((a, b) -> {
            return a - b;
        });
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0)
                q.add(c);
        }
        while (!q.isEmpty()) {
            char c = q.remove();
            result.append(c);
            if (map.containsKey(c)) {
                for (char c2 : map.get(c)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2) == 0)
                        q.add(c2);
                }
            }
        }

        if (result.length() != degree.size()) {
            return "";
        }

        return result.toString();
    }

}
