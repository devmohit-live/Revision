import java.util.ArrayList;

public class ArticulationPoint {
    boolean[] ap;
    boolean[] vis;
    int[] low, dis;
    int time, rootcall, n;

    ArticulationPoint(ArrayList<Edges>[] graph) {
        this.n = graph.length;
        this.ap = new boolean[n];
        this.vis = new boolean[n];
        this.low = new int[n];
        this.dis = new int[n];
    }

    public void articulationPoints() {
        // run for all componenets
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                dfs(i, -1); // -1 represrnt src : root
        }
    }

    private void dfs(int src, int parent) {
        low[src] = dis[src] = time++;
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (vis[e.nbr]) {
                // already visited
                if (e.nbr != parent)
                    low[src] = Math.min(low[src], dis[e.nbr]);
            } else {
                if (parent == -1)
                    rootcall++; // making call from root

                dfs(e.nbr, src);

                if (dis[src] <= low[e.nbr]) // <= means the authrity they are talking about is weaker than me or it's me
                                            // only
                    ar[src] = true;

                if (dis[src] < low[e.nbr]) // < means there is no backup edge that directly reaches me
                    System.out.println("Articulation Edge: " + src + "->" + e.nbr);

                // backtrack
                low[src] = Math.min(low[src], low[e.nbr]);
            }

        }

    }

}
