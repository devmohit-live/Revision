public class CourseSchedule4_1462 {
    {
        // approach 1 : try dfs from all the prerequisites nodes[a,b] and check if there
        // is a path from a to b or not
        // time : k *dfs => k = no of queries, dfs=> O(V+E)=O(n^2)
        // queries can be repeated, multiple subqueries can be there for a dfs

        // approach: use modified floyd warshal : generally used to find the shortest
        // distance b/w any two nodes(if path exixts between those two nodes)
        // instead of cell representing distance we can make the matrix of booolean type
    }
    boolean[][] dp;

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        if (numCourses == 0 || queries.length == 0)
            return ans;

        // floyd_warshal(numCourses,prerequisites,queries,ans);
        // brute_qdfs(numCourses,prerequisites,queries,ans);
        brute_opti_qdfs(numCourses, prerequisites, queries, ans);
        return ans;
    }

    private void brute_qdfs(int n, int[][] prerequisites, int[][] queries, List<Boolean> ans) {
        // build a graph
        List<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<Integer>();
        for (int[] edge : prerequisites)
            graph[edge[0]].add(edge[1]);// k

        for (int[] q : queries) { // q
            int src = q[0], des = q[1];
            ans.add(dfs(graph, src, des, new boolean[n])); // n*n
        }
    }

    // O(n*n)
    private boolean dfs(List[] graph, int src, int des, boolean[] vis) {
        if (src == des)
            return true;

        vis[src] = true;
        List<Integer> nbrs = graph[src];
        boolean ans = false;
        for (int nbr : nbrs) {
            if (!vis[nbr])
                ans = ans || dfs(graph, nbr, des, vis);
        }
        return ans;
    }

    // n^3 + k(prerequites) + q(queries) => n^3

    private void floyd_warshal(int n, int[][] prerequisites, int[][] queries, List<Boolean> ans) {
        boolean[][] graph = new boolean[n][n];

        // build the graph with given direct edges
        for (int[] edge : prerequisites)
            graph[edge[0]][edge[1]] = true;

        for (int intermediate = 0; intermediate < n; intermediate++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    if (start == intermediate || end == intermediate)
                        continue;
                    if (!graph[start][end])
                        graph[start][end] = graph[start][intermediate] && graph[intermediate][end];

                }
            }
        }

        // check for all queries
        for (int[] query : queries) {
            int preq = query[0], course = query[1];
            ans.add(graph[preq][course]);
        }

    }

    //
    // optimal
    private void dfs(int src, int n, ArrayList<Integer>[] gr, boolean[] vis, boolean[][] mat) {

        if (vis[src])
            return;
        vis[src] = true;

        for (int nbr : gr[src]) {

            mat[src][nbr] = true;

            dfs(nbr, n, gr, vis, mat);

            for (int j = 0; j < n; j++)
                if (mat[nbr][j])// if nbr is a prerequisite for any node then it will also be a prerequisite for
                                // src
                    mat[src][j] = true;
        }
    }

    private void brute_opti_qdfs(int n, int[][] prerequisites, int[][] queries, List<Boolean> res) {
        ArrayList<Integer>[] gr = new ArrayList[n];
        boolean[][] mat = new boolean[n][n];
        boolean[] vis = new boolean[n];

        for (int i = 0; i < n; i++)
            gr[i] = new ArrayList<>();

        for (int[] edge : prerequisites)
            gr[edge[0]].add(edge[1]);

        for (int i = 0; i < n; i++)
            dfs(i, n, gr, vis, mat);

        for (int[] q : queries)
            res.add(mat[q[0]][q[1]]);

    }

    // to test
    private void topological_sort() {

    }
}
