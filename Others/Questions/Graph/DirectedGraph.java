package Questions.Graph;

import java.util.List;

public class DirectedGraph {
    // topological sort : just know the order : add the vtc in backtracking

    // To check for correcteness : draw the direction of edges of the sorted vtces
    // if any of the edge goes in oppsoite direction of others then there exists a
    // cycle: hence not possible

    public static void topologicalSort(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = graph.length;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                topologicalSort_dfs(graph, i, ans, vis);
        }
        System.out.println("Topologocal sort is : ");
        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();

    }

    private static void topologicalSort_dfs(ArrayList<Edge>[] graph, int src, ArrayList<Integer> res, boolean[] vis) {
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                topologicalSort_dfs(graph, e.nbr, res, vis);
            }
        }

        // add while backtracking
        res.add(src);
    }

    // Kahn's algo: As we may not ber sure that given topological sort is
    // correct(contains cycle: deadlock) or not
    // Kahn's : Cycle Detection in directed graphs, deadlock detection, order of
    // processing some taks

}
