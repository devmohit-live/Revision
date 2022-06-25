import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kosaraju_SCC {
    // to get the strogly connected components: All cyclic path : 1 scc, 1 single
    // node: 1 scc

    // Steps:
    // 1. DFS : Topological order for traversing the graph
    // 2. Compliment graph: reverse edges: Why? => bcz on complment the cycle
    // remains intact but other vtx which were reachable from vtx x may not b
    // reachable now(hence able to get the cyclic and non cyclic componennts)
    // 3. Do dfs of obtained topological order(reverse ordere) to the the scc

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private static void dfs_topolical(ArrayList<Edge>[] graph, int src, ArrayList<Integer> order, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src])
            if (!vis[e.v])
                dfs_topolical(graph, e.v, order, vis);

        order.add(src);
    }

    private static ArrayList<Edge>[] complimentGraph(ArrayList<Edge>[] graph) {
        int n = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            ngraph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (Edge e : graph[i]) {
                int u = e.u, v = e.v, w = e.w;
                ngraph[v].add(new Edge(v, u, w));
            }
        }
        // display(ngraph);
        return ngraph;
    }

    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }

    }

    private static void dfs_scc(ArrayList<Edge>[] graph, int src, List<Integer> ans, boolean[] vis) {
        vis[src] = true;
        ans.add(src);
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_scc(graph, e.v, ans, vis);
        }
    }

    public static void kosaRaju(ArrayList<Edge>[] graph) {
        int n = graph.length;
        ArrayList<Integer> order = new ArrayList<>();
        boolean[] vis = new boolean[n];
        // 1
        for (int i = 0; i < n; i++)
            if (!vis[i])
                dfs_topolical(graph, i, order, vis);
        // 2
        ArrayList<Edge>[] ngraph = complimentGraph(graph);
        List<List<Integer>> ans = new ArrayList<>();
        // 3
        Arrays.fill(vis, false);
        for (int i = order.size() - 1; i >= 0; i--) {
            List<Integer> comp = new ArrayList<>();
            int vtx = order.get(i);
            if (!vis[vtx])
                dfs_scc(ngraph, vtx, comp, vis);
            if (comp.size() != 0) {
                // System.out.println(comp);
                ans.add(comp);
            }
        }

        for (List<Integer> small : ans)
            System.out.println(small);

        System.out.println("Strongly Connected components are " + ans.size());

    }

    public static void main(String[] args) {
        int N = 9;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);
        addEdge(graph, 1, 7, 5);
        addEdge(graph, 7, 8, 4);
        kosaRaju(graph);
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
    }

}
