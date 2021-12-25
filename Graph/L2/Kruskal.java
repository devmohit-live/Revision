
import java.util.ArrayList;
// all the edges are sorted first : apply kruskal for mst when list of edges is given
// prims : when graph is given : pq(not necessarly sorted)
public class Kruskal {

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static int[] parent, size;
    static int n;

    static int findParent(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = findParent(parent[x]);
    }

    static void union(int p1, int p2) {
        if (size[p2] <= size[p1]) {
            parent[p2] = p1;
            size[p1] += size[p2];
        } else {
            parent[p1] = p2;
            size[p2] += size[p1];
        }
    }

    public static void unionFind(int[][] graph, ArrayList<Edge>[] spanningTree) {
        for (int[] edge : graph) {
            int u = edge[0], v = edge[1], w = edge[2];
            int p1 = findParent(u), p2 = findParent(v);
            if (p1 != p2) {
                union(p1, p2);
                addEdge(spanningTree, u, v, w);
            }

        }
    }

    private static void addEdge(ArrayList<Edge>[] spanningTree, int u, int v, int w) {
        spanningTree[u].add(new Edge(u, v, w));
        spanningTree[v].add(new Edge(v, u, w));
    }

    public static ArrayList<Edge>[] kruskal(int[][] graph,int n) {
        // n =  n would be given or can be calulated max = max(,max,maX(e.v,e.u)) =? max of all vtces
        ArrayList<Edge>[] spanningTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            spanningTree[i] = new ArrayList<>();
        }
        unionFind(graph, spanningTree);
        display(spanningTree);
        return spanningTree;
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

}
