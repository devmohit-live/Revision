import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Dijkstra {

    class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // For new Graph

    // either :
    ArrayList<Edge>[] ngraph;
    // or:
    int[] parent, wt;
    boolean[] vis;

    class Pair {
        int vtx, par, w, wsf;

        Pair(int vtx, int par, int w, int wsf) {
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }
    }

    // unwanted pair always reside in pq till the end

    public void dijkstra(ArrayList<Edge>[] graph, int src) {
        int n = graph.length;
        this.ngraph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        parent = new int[n];
        wt = new int[n];
        vis = new boolean[n];

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        pq.add(new Pair(src, -1, 0, 0));

        while (pq.size() != 0) {
            Pair rm = pq.remove();
            int vtx = rm.vtx, w = rm.w;
            if (vis[rm.vtx])
                continue;
            vis[vtx] = true;

            // info properties
            if (rm.par != -1) {
                graph[src].add(new Edge(rm.par, rm.vtx, rm.wsf));
                graph[src].add(new Edge(rm.vtx, rm.par, rm.wsf));
            }
            parent[vtx] = rm.par;
            wt[vtx] = rm.wsf;

            // work
            for (Edge e : graph[vtx]) {
                if (!vis[e.v]) {
                    pq.add(new Pair(e.v, rm.par, e.w, w + e.w));
                }
            }

        }

        System.out.println("Distance : " + Arrays.toString(wt));
        System.out.println("Parent : " + Arrays.toString(parent));

        System.out.print("Reached to " + (n - 2) + " vtx with weight " + wt[n - 2] + " with path/route ");
        int x = n - 2; // any vertex/des
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        while (x != -1) {
            sb.append(parent[x] + " ");
            x = parent[x];
        }
        sb.reverse();
        sb.append(src);

        System.out.println(sb.toString());
    }

    // Better : Using parent and dis array

    public static void dijikstra_02(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        int[] dis = new int[N];
        int[] par = new int[N];
        Arrays.fill(dis, (int) 1e9);
        Arrays.fill(par, -1);

        pq.add(new pair(src, 0));
        dis[src] = 0;
        while (pq.size() != 0) {
            pair p = pq.remove();

            if (p.wsf > dis[p.vtx])
                continue;

            for (Edge e : graph[p.vtx]) {
                if (p.wsf + e.w < dis[e.v]) {
                    dis[e.v] = p.wsf + e.w;
                    par[e.v] = p.vtx;
                    pq.add(new pair(e.v, p.wsf + e.w));
                }
            }
        }
    }
}