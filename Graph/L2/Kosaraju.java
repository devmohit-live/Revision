import java.util.ArrayList;
import java.util.Arrays;

public class Kosaraju {
    // to get the strogly connected components: All cyclic path : 1 scc, 1 single
    // node: 1 scc

    // Steps:
    // 1. DFS : Topological order for traversing the graph
    // 2. Compliment graph: reverse edges: Why? => bcz on complment the cycle
    // remains intact but other vtx which were reachable from vtx x may not b
    // reachable now(hence able to get the cyclic and non cyclic componennts)
    // 3. Do dfs of obtained topological order(reverse ordere) to the the scc

    class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private static void dfs_topolical(ArrayList<Edge>[] graph, int src, ArrayList<Integer> order, boolean[] vis) {
        vis[src] = true;
        for (Edge e : garph[src])
            if (!vis[e.v])
                dfs_topolical(graph, e.v, order, vis);

        order.add(src);
    }

    private ArrayList<Edge>[] complimentGraph(ArrayList<Edge>[] graph) {
        int n = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (Edge e : graph[i]) {
                int u = i, v = e.v, w = e.w;
                ngraph[v].add(new Edge(v, u, w));
            }
        }
        return ngraph;
    }

    private void dfs_scc(ArrayList<Edge>[] graph, int src, List<Integer> ans, boolean[] vis) {
        vis[src] = true;
        ans.add(src);
        for (Edge e : graph[src]) {
            if (!vs[e.v])
                dfs_scc(graph, src, scc, sm, vis);
        }
    }

    public void kosaRaju(ArrayList<Edge>[] graph) {

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
                dfs_scc(graph, vtx, comp, vis);
            System.out.println(comp);
            ans.add(comp);
        }

        for (List<Inetger> small : ans)
            System.out.println(small);

    }

    public static void main(String[] args) {
        
    }

}
