package Questions.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class BFS {
    // O(E)
    public static void BFS_forCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        boolean iscycle = false;

        que.add(src);
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Min No Edges: " + level + " -> ");
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                if (vis[rvtx]) {
                    iscycle = true;
                    continue;
                }

                System.out.print(rvtx + ", ");
                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v])
                        que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }
    }

    // O(v)
    public static void BFS_withoutCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;

        que.add(src);
        vis[src] = true;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Min No Edges: " + level + " -> ");
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                System.out.print(rvtx + ", ");
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

    // Rotten Oranges: 994
    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length, fresh = 0;
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    fresh++;
                else if (grid[i][j] == 2) {
                    grid[i][j] = 2; // mark : justy to remember we have to mark true
                    q.addLast(i * m + j);
                }
            }
        }
        if (fresh == 0)
            return 0;

        // bfs without cycle
        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int rm_idx = q.removeFirst();
                int sr = rm_idx / m;
                int sc = rm_idx % m;

                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        grid[r][c] = 2; // mark true/rotten
                        q.addLast(r * m + c);
                        fresh--; // we are only going to fresh oranges
                    }
                    if (fresh == 0)
                        return time + 1;
                }

            }

            time++;

        }

        return -1; // not possible to rott all oranges
    }

    // Shortest Path in Binary Matrix : 1091 (same as rotten oranges)
    int[][] dir8 = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 } };

    public int shortestPathBinaryMatrix(int[][] grid) {
        LinkedList<Integer> q = new LinkedList<>();
        int n = grid.length, level = 0, count = 1;
        if (n == 0 || grid[0][0] != 0)
            return -1;

        grid[0][0] = 1;// mark visited
        q.addLast(0);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int rmIdx = q.removeFirst();
                int sr = rmIdx / n, sc = rmIdx % n;
                if (sr == n - 1 && sc == n - 1)
                    return level + 1;
                for (int[] d : dir8) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < n && grid[r][c] == 0) {
                        grid[r][c] = 1; // mark visited
                        q.addLast(r * n + c);
                    }

                }

            }
            level++;
        }

        return -1;
    }

    // Bipartite: Possible to divide vtces in two mutually exclusive and exhaustive
    // sets
    // non cyclic: Bipartite
    // cyclic (even length): Bipartite, odd length(non - bipartite)

    // Leetcode 785
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        boolean res = true;
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1)
                res = res && isComponentBipartite(graph, i, vis);
        }
        return res;
    }

    public boolean isComponentBipartite(int[][] graph, int src, int[] vis) {
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(src);

        boolean isCyclic = false, isBipartite = true;
        int color = 0; // level modified
        while (q.size() != 0) {
            int size = q.size();

            while (size-- > 0) {
                int rm = q.removeFirst();

                if (vis[rm] != -1) {
                    isCyclic = true;
                    if (vis[rm] != color) { // conflict
                        isBipartite = false;
                        break; // no need to go further
                    }
                }

                vis[rm] = color; // mark with color
                for (int v : graph[rm]) {
                    if (vis[v] == -1) { // not visited
                        q.addLast(v);
                    }
                }

            }

            if (!isBipartite)
                break;
            color = (color + 1) % 2;
        }

        if (isCyclic) {
            if (isBipartite) {
                System.out.println("Compnenet is Bipartite and contains even length Cycle");
            } else {
                System.out.println("Compnenet is Non-Bipartite and contains odd length Cycle");
            }
        } else {
            System.out.println("Compnenet is Bipartite.");
        }
        return isBipartite;
    }

    // Leetcode 886 : Possible Bipartition : Bipartie , also can be done using DSU
    class LC886 {
        // check for bipartitation
        // just needed to create a graph

        private ArrayList<Integer>[] graph;
        private int[] vis;

        public boolean possibleBipartition(int n, int[][] dislikes) {

            createGraph(dislikes, n);
            boolean res = true;
            vis = new int[n + 1]; // n+1 => 1 based indexing
            Arrays.fill(vis, -1);

            for (int i = 1; i <= n; i++) {
                if (this.vis[i] == -1)
                    res = res && isBipartite(i);
            }

            return res;
        }

        private boolean isBipartite(int src) {
            LinkedList<Integer> q = new LinkedList<>();
            q.addLast(src);
            boolean isCycle = false, bipartite = true;
            int color = 0;

            while (!q.isEmpty()) {
                int size = q.size();
                while (size-- > 0) {
                    int rm = q.removeFirst();
                    if (this.vis[rm] != -1) {
                        isCycle = true;
                        if (vis[rm] != color) {
                            bipartite = false;
                            return false;
                        }
                    }

                    this.vis[rm] = color;

                    for (int v : this.graph[rm]) {
                        if (this.vis[v] == -1) {
                            q.addLast(v);
                        }
                    }

                }
                color = (color + 1) % 2;

            }

            return bipartite;

        }

        /*
         * class Edge{ int src, nbr; Edge(int src,int nbr){ this.src=src; this.nbr =
         * nbr; } }
         */

        private void createGraph(int[][] arr, int n) {
            this.graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                // graph.put(i, new ArrayList<Integer>());
                graph[i] = new ArrayList<>();
            }

            for (int[] edge : arr) {
                addEdge(edge[0], edge[1]);
            }

        }

        private void addEdge(int u, int v) {
            this.graph[u].add(v);
            this.graph[v].add(u);
        }

    }

    // Walls And Gates : https://www.lintcode.com/problem/663/ : Leetcode 286
    // Exactly similar to 542 : 01 matrix(reverse bfs)
    final int infi = 2147483647;
    int n, m;

    public void wallsAndGates(int[][] rooms) {
        n = rooms.length;
        m = rooms[0].length;
        LinkedList<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    q.addLast(i * m + j); // add gates
                }
            }
        }
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int rm = q.removeFirst(), sr = rm / m, sc = rm % m;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < m && rooms[r][c] == infi) {
                        rooms[r][c] = rooms[sr][sc] + 1;
                        q.addLast(r * m + c);
                    }
                }

            }
            level++;
        }

    }

    // Leetcode 815 : Bus Route

    // route : bust: bustands
    // map : bustand: all the bus coming to it
    // vis[] : visited array for all the bus taken
    // visited : hashset for all the bus stand visited with a bus
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        int n = routes.length;
        // create a mapping
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int bus = 0; bus < n; bus++) { // bus
            for (int stand : routes[bus]) {
                map.putIfAbsent(stand, new ArrayList<Integer>());
                map.get(stand).add(bus);
            }
        }
        boolean[] vis = new boolean[n];
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(source); // stand
        visited.add(source);
        int interchanges = 0;
        // bfs
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int rmStand = q.removeFirst();
                if (target == rmStand)
                    return interchanges;

                // get all the bused coming to this bus-stand;
                List<Integer> buses = map.get(rmStand);
                // add all the routed/stands these buses can take to : for all buses
                for (int bus : buses) {
                    if (vis[bus])
                        continue;
                    for (int stand : routes[bus]) {
                        if (!visited.contains(stand)) { // avaoid going in loop to same routes
                            visited.add(stand);
                            q.addLast(stand);
                        }
                    }
                    vis[bus] = true; // mark thiss bus
                }

            }
            interchanges++;
        }

        return -1;
    }

    // Leetcode 127 : Word Ladder
    // Treat every word as node and every transformation as an edge or wt 1;
    // shortest path in terms of edges: bfs

    // Space: O(n) Haset, Q: O(l*26*N) : O(l*n)
    // Time: set: O(n) + ( possible word creation: O(26*l*l*) * N(we are adding only
    // elements in q which exists in wordlist) : O(n) + O(N*26*l*l) => O(N*l*l)

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int lv = 0, n = wordList.size(), l = endWord.length();
        Set<String> set = new HashSet<>();
        set.addAll(wordList); // N

        if (!set.contains(endWord))
            return 0; // end woprd not present in wordlist: not possible
        // System.out.println(set);
        LinkedList<String> q = new LinkedList<>();
        q.addLast(beginWord);
        if (set.contains(beginWord))
            set.remove(beginWord);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String rm = q.removeFirst();
                // System.out.println(rm);
                if (rm.equals(endWord))
                    return lv + 1; // l

                // generate all possible words which can be formed by chagin a single letter in
                // rm
                for (int i = 0; i < l; i++) { // l
                    char ch[] = rm.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) { // 26
                        ch[i] = c;
                        String modified = new String(ch); // l
                        if (set.contains(modified)) {
                            q.addLast(modified);
                            set.remove(modified); // transformed once
                            // hence remove from set(node need fo transformation again : no need for cycle)
                        }
                    }
                }

            }

            lv++;
        }

        return 0; // not possible
    }

    // lc 967
    public int[] numsSameConsecDiff(int n, int k) {
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i < 10; i++)
            q.addLast(i);

        int level = 1; // level signifies no od digits in the number present at current level, ex: 123
                       // => 3 digit number 3 level;

        while (!q.isEmpty() && level < n) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst();
                addValidNumbers(q, rm, k);
            }
            level++;
        }

        int[] ans = new int[q.size()];
        int idx = 0;
        while (!q.isEmpty()) {
            ans[idx++] = q.removeFirst();
        }

        return ans;
    }

    // Making a number: absolute difference between every two consecutive digits is
    // k
    // ie operations valid => currNo + k , or currNo - k or both
    // such that the number should be valid
    // when op1(curr+k) : when curr+k < 10 && k>=0 (equals to 0 is also valid here)
    // op2 : when k>0 and k-curr >=0

    private void addValidNumbers(LinkedList<Integer> q, int num, int k) {
        int lastDigit = num % 10;
        int newLastDigit = -1, newDigit = -1;
        if (k >= 0 && lastDigit + k < 10) {
            newLastDigit = lastDigit + k;
            newDigit = num * 10 + newLastDigit;
            q.addLast(newDigit);
        }
        if (k > 0 && lastDigit - k >= 0) {
            newLastDigit = lastDigit - k;
            newDigit = num * 10 + newLastDigit;
            q.addLast(newDigit);
        }
    }

}