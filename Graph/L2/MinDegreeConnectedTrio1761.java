public class MinDegreeConnectedTrio1761 {
    // Leetcode 1761. Minimum Degree of a Connected Trio in a Graph

    public int minTrioDegree(int n, int[][] edges) {
        // first we have to make a graph to check for trio : a->b->c->a : use matrix
        // form to check for connection in O(1)
        // we have to calculate degree of vtx : undirected: indegree == outdegree
        boolean[][] graph = new boolean[n + 1][n + 1]; // 1 based indexing
        int[] degree = new int[n + 1];
        // use can also use map of set for lesser space but searching is fater in array
        // than set
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph[u][v] = graph[v][u] = true; // denotes connection
            degree[u]++;
            degree[v]++;
        }

        int min = Integer.MAX_VALUE;
        // chek for degree in trios
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // optimization : only check for third connection if first two exits
                if (graph[i][j]) {
                    for (int k = j + 1; k <= n; k++) {
                        if (graph[j][k] && graph[k][i]) {
                            int tdegree = degree[i] + degree[j] + degree[k] - 6; // redundant endpoint connection
                                                                                 // counted twice in all three
                            min = Math.min(min, tdegree);
                        }
                    }
                }
            }
        }

        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
}
