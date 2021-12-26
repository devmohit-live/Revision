public class DijstraQuestions {
    // https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        int[] dis = new int[V];
        boolean[] vis = new boolean[V];
        // 0: vtx, 1: par, 2: wt, 3: wsf
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[3] - b[3];
        });

        pq.add(new int[] { S, -1, 0, 0 });

        while (pq.size() > 0) {
            int[] rm = pq.remove();
            int vtx = rm[0], par = rm[1], wt = rm[2], wsf = rm[3];

            if (vis[vtx])
                continue;

            vis[vtx] = true;
            dis[vtx] = wsf;
            for (ArrayList<Integer> edge : adj.get(vtx)) {
                // System.out.println(edge);
                int v = edge.get(0), w = edge.get(1);

                if (!vis[v])
                    pq.add(new int[] { v, vtx, w, wsf + w });

            }

        }
        return dis;
    }

    // 743. Network Delay Time
    public int networkDelayTime(int[][] times, int n, int k) {
        // 1 based
        class Pair {
            int par, vtx, w, wsf;

            // dij
            Pair(int par, int vtx, int w, int wsf) {
                this.par = par; // parent
                this.vtx = vtx; // vtx
                this.w = w;
                this.wsf = wsf;
            }
        }

        ArrayList<int[]>[] graph = new ArrayList[n + 1]; // v,w
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : times)
            graph[e[0]].add(new int[] { e[1], e[2] });

        int ans = 0;
        boolean[] vis = new boolean[n + 1];
        int count = n;

        PriorityQueue<Pair> q = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        q.add(new Pair(-1, k, 0, 0));
        while (q.size() > 0) {
            Pair rm = q.remove();

            if (vis[rm.vtx])
                continue;

            vis[rm.vtx] = true;
            ans = Math.max(ans, rm.wsf); // max time is required

            count--;

            for (int[] nbr : graph[rm.vtx]) {
                int v = nbr[0], wt = nbr[1];
                if (!vis[v]) {
                    q.add(new Pair(rm.vtx, v, wt, rm.wsf + wt));
                }
            }
        }

        if (count == 0)
            return ans;
        return -1;

    }
}
