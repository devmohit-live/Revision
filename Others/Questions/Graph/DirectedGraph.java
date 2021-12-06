package Questions.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
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

    // Use this: for topological : Uses BFS : weach level defines the order (in
    // opposite ) for execution
    // nodes at a level can be executed/processed parallely (nodes at different
    // level) are dependednt on next levels

    // Uses the concept of indegree

    public static void topologicalOrder(int n, ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[n];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                topo_DFS(i, graph, vis, ans);
            }
        }

        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.println(ans.get(i));
        }
    }

    public static ArrayList<Integer> kahnsAlgo(int n, ArrayList<Edge>[] graph) {
        int[] indegree = new int[n];
        for (ArrayList<Edge> edgesList : graph) {
            for (Edge e : edgesList) {
                indegree[e.v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                ans.add(vtx);

                for (Edge e : graph[vtx]) {
                    if (--indegree[e.v] == 0)
                        que.addLast(e.v);
                }

            }
            level++;
        }

        if (ans.size() != n) {
            System.out.println("Topological Order is not possible due to Cycle");
            ans.clear();
        }

        return ans;
    }

    public static void kahnsAlgo_01(int n, ArrayList<Edge>[] graph) {
        int[] indegree = new int[n];
        for (ArrayList<Edge> edgesList : graph) {
            for (Edge e : edgesList) {
                indegree[e.v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                smallAns.add(vtx);

                for (Edge e : graph[vtx]) {
                    if (--indegree[e.v] == 0)
                        que.addLast(e.v);
                }

            }
            ans.add(smallAns);
            level++;
        }

        if (ans.size() != n) {
            System.out.println("Topological Order is not possible due to Cycle");
            ans.clear();
        }
    }
}
