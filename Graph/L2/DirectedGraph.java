package Questions.Graph;

import java.util.ArrayList;
import java.util.Arrays;
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

    // Leetcode 207 : Course schedule 1 :
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

    // bfs in directed graph gives longest path : why?
    // bcz it starts with all the points(vtx) that does't have any dependecies and
    // reaches to a vtx(end) that has many dependevies at the last
    // after clearing all of it's dependencoes on the way

    // it is like keep running the bfs without any specific target
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

    // topological sort with dfs with cycle detection case:
    // -1 : not visited , 0 : current path, 1 => backtracked(visited but not in
    // current path)
    public static void topologicalSort_dfs_cycle(ArrayList<Edge>[] graph) {
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        List<Integer> ans = new ArrayList<>();
        boolean res = false;
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                res = res || topologicalSort_dfs_cycle(graph, i, vis, ans);
            }
        }
        if (res) {
            System.out.println("Cycle is present : hence deadlock : topologocal sorting not possible");
            ans.clear();
        } else {
            System.out.println("Topologocal sort is: ");
            for (int i = ans.size() - 1; i >= 0; i--)
                System.out.print(ans.get(i) + " ");
            System.out.println();
        }
    }

    private boolean topologicalSort_dfs_cycle(ArrayList<Edge>[] graph, int src, int[] vis, List<Integer> ans) {
        boolean isCcycle = false;

        vis[src] = 0; // add in current path
        for (Edge e : graph[src]) {
            if (vis[e.nbr] == -1) {
                isCcycle = isCcycle || topologicalSort_dfs_cycle(graph, e.nbr, vis, ans);
            } else if (vis[e.nbr] == 0) {
                // is already visited and part of current path: cycle
                return true;
            }
        }
        vis[src] = 1; // backtracked
        ans.add(src);

        return isCcycle;
    }

    // Leetcode 802 : Eventual Safe states
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        // boolean[] vis = new boolean[n], partOfPath = new boolean[n], partOfCycle =
        // new boolean[n];
        // boolean[] partOfCycle = new boolean[n];
        Set<Integer> partOfCycle = new HashSet<>();
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        for (int i = 0; i < n; i++) {
            // if(!vis[i]) dfs_directed_cycle(graph,i,vis,partOfPath,partOfCycle);
            if (vis[i] == -1)
                topological_dfs_cycle(graph, i, vis, partOfCycle);
        }
        List<Integer> ans = new ArrayList<>();

        // for(int i=0;i<n;i++) if(!partOfCycle[i]) ans.add(i);
        for (int i = 0; i < n; i++)
            if (!partOfCycle.contains(i))
                ans.add(i);

        return ans;
    }

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

    // Build A Matrix With Condiitons LC 2392 TopoSorting
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

        int[][] ans = new int[k][k];

        // create adjacency List for the rows and columns
        List<Integer>[] adjListRow = new ArrayList[k + 1]; // 1based index
        List<Integer>[] adjListCol = new ArrayList[k + 1]; // 1based index

        // initialize
        for (int i = 0; i <= k; i++) {
            adjListRow[i] = new ArrayList<Integer>();
            adjListCol[i] = new ArrayList<Integer>();
        }

        // Map<Integer, Set<Integer>> adjListRow = new HashMap<>();
        // Map<Integer, Set<Integer>> adjListCol = new HashMap<>();

        for (int[] row : rowConditions) {
            // adjListRow.putIfAbsent(row[0], new HashSet<>());
            // adjListRow.get(row[0]).add(row[1]);
            adjListRow[row[0]].add(row[1]);
        }
        for (int[] col : colConditions) {
            // adjListCol.putIfAbsent(col[0], new HashSet<>());
            // adjListCol.get(col[0]).add(col[1]);
            adjListCol[col[0]].add(col[1]);
        }

        // run DFS with cycle detection while storing the finish order of the nodes
        Map<Integer, Integer> rowIndex = new HashMap<>();
        Map<Integer, Integer> colIndex = new HashMap<>();

        int[] visitedRow = new int[k + 1];
        int[] visitedCol = new int[k + 1];

        Arrays.fill(visitedRow, -1);
        Arrays.fill(visitedCol, -1);

        for (int i = 1; i <= k; i++) {
            boolean flag = true;
            flag = flag && dfs(i, adjListRow, visitedRow, rowIndex);
            flag = flag && dfs(i, adjListCol, visitedCol, colIndex);
            if (!flag)
                return new int[0][0];
        }

        // assign the correct position to the nodes according to the finish order
        for (int i = 1; i <= k; i++) {
            int r = rowIndex.size() - rowIndex.get(i) - 1;
            int c = colIndex.size() - colIndex.get(i) - 1;
            ans[r][c] = i;
        }

        return ans;
    }

    /**
     * DFS with cycle detection (returns false in case of a cycle) State of a node
     * can be one of the following 0 -> unvisited 1 -> visiting 2 -> visited On the
     * DFS path visiting a node with value 1 means we are visiting a node on the
     * path we came from -> hence it is a cycle(DFS returns false). When a visited
     * node is encountered, we can return back to the caller node.
     */
    private boolean dfs(int src, List[] adjList, int[] visited, Map<Integer, Integer> map) {

        // wise to put it here, removing from here returns wrong ans

        if (visited[src] == 1)
            return true;
        if (visited[src] == 0)
            return false;
        boolean ans = true;
        visited[src] = 0; // mark vis and part of path
        List<Integer> nbrs = adjList[src];
        for (int ele : nbrs) {
            if (visited[ele] == -1) {
                // if not visited
                if (!dfs(ele, adjList, visited, map))
                    return false;
            } else if (visited[ele] == 0)
                return false;
        }

        // mark the node visited and add to the map of finished nodes with the finish
        // number
        visited[src] = 1;
        map.put(src, map.size());
        return true;
    }

}
