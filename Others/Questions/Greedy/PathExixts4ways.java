import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class PathExixts4Ways {
    int[] par;

    public boolean validPath(int n, int[][] edges, int src, int des) {
        return unionFind(edges, src, des, n); // fastest
        // return dfs_bfs(n, edges, src, des);
    }

    private boolean dfs_bfs(int n, int[][] edges, int src, int des) {
        // graph creation for dfs and bfs
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);

        }
        boolean[] vis = new boolean[graph.size()];

        // 1 dfs
        // return dfs(graph, src, des, vis); // slower

        // 2 bfs
        return bfs(edges, src, des, n, vis); // faster
    }

    private boolean bfs(int[][] edges, int src, int des, int n, boolean[] vis) {
        // bfs
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(src);
        vis[src] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst();

                if (rm == des)
                    return true;

                for (int v : graph.get(rm)) {
                    if (!vis[v]) {
                        vis[v] = true;
                        q.addLast(v);
                    }
                }

            }

        }
        return false;
    }

    private int findParent(int x) {
        if (par[x] == x)
            return x;
        return par[x] = findParent(par[x]);
    }

    private boolean dfs(List<List<Integer>> graph, int src, int des, boolean[] vis) {
        vis[src] = true;
        if (src == des)
            return true;

        boolean res = false;
        for (int v : graph.get(src)) {
            if (!vis[v])
                res = res || dfs(graph, v, des, vis);
        }

        return res;
    }

    private boolean unionFind(int[][] edges, int src, int des, int n) {
        if (src == des)
            return true;

        par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            int p1 = findParent(u), p2 = findParent(v);
            if (p1 != p2) {
                par[p2] = p1;

            }
        }

        // running findParent for path compression so that all paths will be compressed
        // and elemet is nearest to it's global parent: as // indirect parent causes
        // wrong result(there may be some uncompressed paths)

        for (int i = 0; i < n; i++)
            findParent(i);

        if (par[src] == par[des])
            return true; // directly in same set by effect of path compression

        return false;
    }
}
