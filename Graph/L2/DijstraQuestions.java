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
}
