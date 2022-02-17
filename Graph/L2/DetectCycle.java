public class DetectCycle {
    // Undirected

    // Directed Graph

    // To give cyclic nodes too
    // else you can maintains a isCycle var and return isCycle directly

    private boolean dfs_directed_cycle(int[][] graph, int src, boolean[] vis, boolean[] partOfPath,
            boolean[] partOfCycle) {
        vis[src] = true;
        partOfPath[src] = true;

        // desicion was for src so partOfCycle[src] = true if cycle found
        // nbr means that we founnd cycle at nbr

        for (int nbr : graph[src]) {
            if (!vis[nbr]) {
                boolean rec = dfs_directed_cycle(graph, nbr, vis, partOfPath, partOfCycle);
                if (rec) {
                    return partOfCycle[src] = true;
                }
            } else if (vis[nbr] && partOfPath[nbr])
                return partOfCycle[src] = true;

        }
        partOfPath[src] = false; // not a part of this path anymore
        // here in topological sort we add the vertex;
        return false;
    }

    private boolean topological_dfs_cycle(int[][] graph, int src, int[] vis, Set<Integer> partOfCycle) {
        vis[src] = 0;

        for (int nbr : graph[src]) {
            if (vis[nbr] == -1) { // not visited/explored yet
                boolean rec = topological_dfs_cycle(graph, nbr, vis, partOfCycle);
                if (rec) {
                    partOfCycle.add(src);
                    return true;
                }
            } else if (vis[nbr] == 0) { // visited and part of path : cycle
                partOfCycle.add(src);
                return true;
            }
        }

        vis[src] = 1; // visited but not a part of path anyomore
        // topological.add(src); we add vtx in topological sort here
        return false; // no cycle detected
    }

}
