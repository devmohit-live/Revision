public class APQuestions {
    // Leetcode 1192 : Based on Crtical edge
    int time;
    int[] low, disc;
    boolean[] vis;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        time = 0;
        low = new int[n];
        disc = new int[n];
        vis = new boolean[n];

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<Integer>();
        // graph
        for (List<Integer> e : connections) {
            graph[e.get(0)].add(e.get(1));
            graph[e.get(1)].add(e.get(0));
        }

        // for each componenet
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                dfs(graph, i, -1, ans);
        }

        return ans;
    }

    private void dfs(List<Integer>[] graph, int src, int par, List<List<Integer>> ans) {
        low[src] = disc[src] = time++;
        vis[src] = true;

        for (int v : graph[src]) {
            if (!vis[v]) {
                dfs(graph, v, src, ans);

                if (disc[src] < low[v]) {
                    // crtical edge
                    ans.add(Arrays.asList(src, v));
                }

                low[src] = Math.min(low[src], low[v]);
            } else if (v != par) {
                low[src] = Math.min(low[src], disc[v]);
            }
        }

    }

}
