package Questions.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DirectedGraph {
    // topological sort : just know the order : add the vtc in backtracking

    // To check for correcteness : draw the direction of edges of the sorted vtces
    // if any of the edge goes in oppsoite direction of others then there exists a
    // cycle: hence not possible

    public static void topologicalSort(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = graph.length;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                topologicalSort_dfs(graph, i, ans, vis);
        }
        System.out.println("Topologocal sort is : ");
        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();

    }

    private static void topologicalSort_dfs(ArrayList<Edge>[] graph, int src, ArrayList<Integer> res, boolean[] vis) {
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                topologicalSort_dfs(graph, e.nbr, res, vis);
            }
        }

        // add while backtracking
        res.add(src);
    }

    // Kahn's algo: As we may not ber sure that given topological sort is
    // correct(contains cycle: deadlock) or not
    // Kahn's : Cycle Detection in directed graphs, deadlock detection, order of
    // processing some taks

    // Use this: for topological : Uses BFS : weach level defines the order (in
    // opposite ) for execution
    // nodes at a level can be executed/processed parallely (nodes at different
    // level) are dependednt on next levels

    // Uses the concept of indegree

    public static void topologicalOrder(int n, ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[n];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                topo_DFS(i, graph, vis, ans);
            }
        }

        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.println(ans.get(i));
        }
    }

    public static ArrayList<Integer> kahnsAlgo(int n, ArrayList<Edge>[] graph) {
        int[] indegree = new int[n];
        for (ArrayList<Edge> edgesList : graph) {
            for (Edge e : edgesList) {
                indegree[e.v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                ans.add(vtx);

                for (Edge e : graph[vtx]) {
                    if (--indegree[e.v] == 0)
                        que.addLast(e.v);
                }

            }
            level++;
        }

        if (ans.size() != n) {
            System.out.println("Topological Order is not possible due to Cycle");
            ans.clear();
        }

        return ans;
    }

    public static void kahnsAlgo_01(int n, ArrayList<Edge>[] graph) {
        int[] indegree = new int[n];
        for (ArrayList<Edge> edgesList : graph) {
            for (Edge e : edgesList) {
                indegree[e.v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                smallAns.add(vtx);

                for (Edge e : graph[vtx]) {
                    if (--indegree[e.v] == 0)
                        que.addLast(e.v);
                }

            }
            ans.add(smallAns);
            level++;
        }

        if (ans.size() != n) {
            System.out.println("Topological Order is not possible due to Cycle");
            ans.clear();
        }
    }


    // Leetcode 210 : Course Scedule 2 :
    public int[] findOrder(int n, int[][] arr) {

        int[] ans = new int[n];
        int[] indegree = new int[n];
        // create graph as arr[0] doesn't gives the nbrs of 0
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : arr) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            indegree[v]++; // calculate indegree
        }

        // add sources
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                q.addLast(i); // adding all the independent process/sources

        // bfs for khan's algo
        int count = 0, level = 0, idx = n;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst();
                count++; //
                ans[--idx] = rm;

                // add children/nbr
                for (int v : graph[rm]) {
                    indegree[v]--; //
                    if (indegree[v] == 0)
                        q.addLast(v);
                }

            }
        }

        if (count == n) {
            // valid topological sort : no deadlock is there
            return ans;
        } else {
            return new int[0];
        }
    }

    //Leetcode 207 : Course schedule 1 :
    public boolean canFinish(int n, int[][] arr) {
        // create graph first
        ArrayList<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] row : arr) {
            graph[row[0]].add(row[1]); // creating edge

        }

        int count = kahnsAlgo(graph);
        return count == graph.length;
    }

    private int kahnsAlgo(ArrayList<Integer>[] graph) {
        int n = graph.length;
        int indegree[] = new int[n];
        // calculating indegree
        for (ArrayList<Integer> edges : graph) {
            for (int v : edges)
                indegree[v]++;
        }

        LinkedList<Integer> que = new LinkedList<>();
        // ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        int level = 0;
        int count = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int vtx = que.removeFirst();
                count++;
                // ans.add(vtx);

                for (int v : graph[vtx]) {
                    if (--indegree[v] == 0)
                        que.addLast(v);
                }

            }
            level++;
        }

        return count;
    }

    // Leetcode 329: Longest Increasing Path in a Matrix
    // Kahn's on 2 d matrix 
    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        if (n == 0)
            return 0;
        if (n == 1 && m == 1)
            return 1;

        // calulate indegree of each vtces
        int[][] indegree = new int[n][m];

        // direction is smaller to greater elemet (dependency)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int[] d : dir) {
                    int r = i + d[0];
                    int c = j + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[i][j]) {
                        indegree[r][c]++;
                    }
                }

            }
        }
        LinkedList<Integer> q = new LinkedList<>();
        // all srcs
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (indegree[i][j] == 0)
                    q.addLast(i * m + j);
        int level = 0, count = 0;

        // kahn's
        while (!q.isEmpty()) {

            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst();
                count++;
                int sr = rm / m;
                int sc = rm % m;

                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < m && matrix[r][c] > matrix[sr][sc]) {
                        indegree[r][c]--;
                        if (indegree[r][c] == 0)
                            q.addLast(r * m + c);
                    }
                }

            }

            level++;
        }

        if (count == m * n)
            return level;
        else
            return 0;
    }

}
