public class GraphValidTree {

    // Lintcode: https://www.lintcode.com/problem/178/

    public boolean validTree(int n, int[][] edges) {
        // return usingDFS(n, edges);
        return usingDsu(n, edges);

    }

    private boolean usingDFS(int n, int[][] edges) {
        // create a graph
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        // tree: single root, connected non cyclic graph
        // every node : single parent => no cycle

        Set<Integer> vis = new HashSet<>();
        // since graph should be connected so we can start from anywhere
        return dfs(graph, 0, -1, vis) && vis.size() == n;
    }

    private boolean dfs(List<Integer>[] graph, int src, int par, Set<Integer> vis) {
        boolean res = true;
        vis.add(src);
        for (int nbr : graph[src]) {
            if (!vis.contains(nbr)) {
                res = res && dfs(graph, nbr, src, vis);
            } else if (nbr != par) {
                // visited and that nbr is not parent itself : that is 2 nodes /parents
                return false;
            }
        }
        return res;
    }

    private boolean usingDsu(int n, int[][] edges) {
        int[] par = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            int p1 = findPar(u, par);
            int p2 = findPar(v, par);
            if (p1 != p2) {
                par[p2] = p1;
                size[p1] += size[p2];
            } else
                return false;
        }

        int maxSize = 0;

        for (int i = 0; i < n; i++) {
            findPar(i, par);
            maxSize = Math.max(size[i], maxSize);
        }

        return maxSize == n;

    }

    private int findPar(int x, int[] par) {
        if (x == par[x])
            return x;
        return par[x] = findPar(par[x], par);
    }

}
