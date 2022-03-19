import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AlienDictionary {

    // For leetcode not Lintocde
    public static String alienOrder(String[] words) {
        if (words.length == 0)
            return "";

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] vis = new int[26];
        boolean validDictionary = buildGraph(graph, vis, words);
        if (!validDictionary)
            return "";

        // topological sort

        boolean acyclic = true;
        StringBuilder sb = new StringBuilder();
        // vis=> 0: unvisited, 1: vis and part of current path, -1 : visisted but not a
        // prt of current path
        for (int el : graph.keySet())
            if (vis[el] == 0)
                acyclic = acyclic && topoDFS(graph, el, vis, sb);
        return acyclic ? sb.reverse().toString() : "";

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

    private static boolean buildGraph(Map<Integer, Set<Integer>> graph, int[] vis, String[] words) {
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

}