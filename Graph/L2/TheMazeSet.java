public class TheMazeSet {
    // https://www.lintcode.com/problem/787/: Leetcode 490: The Maaze
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], dr = destination[0], dc = destination[1];
        boolean[][] vis = new boolean[n][m];
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(sr * m + sc);
        vis[sr][sc] = true;

        // bfs withour cycle
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst(), i = rm / m, j = rm % m;

                for (int[] d : dir) {
                    int r = i, c = j; // create a copy so that original i, j remains intacts for next direction
                    while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                        r = r + d[0];
                        c = c + d[1];
                    }

                    r -= d[0];
                    c -= d[1];

                    if (vis[r][c])
                        continue;
                    vis[r][c] = true;// marking only the final endpoints not the in-between paths
                    // so that we can travel intermediate points for different paths
                    q.addLast(r * m + c);
                    if (r == dr && c == dc)
                        return true;
                }
            }
        }

        return false;
    }

    // https://www.lintcode.com/problem/the-maze-ii/: The Maze 2
    // dijkstra
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {

        class Pair implements Comparable<Pair> {
            int r, c, l;

            Pair(int r, int c, int l) {
                this.r = r;
                this.c = c;
                this.l = l;
            }

            public int compareTo(Pair o) {
                return this.l - o.l;
            }
        }
        int sr = start[0], sc = start[1], dr = destination[0], dc = destination[1];
        int n = maze.length, m = maze[0].length;
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        int[][] dist = new int[n][m]; // work as visited and avoid adding irrelevant pair to the q (disjktra)
        for (int[] d : dist)
            Arrays.fill(d, (int) 1e8);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Pair root = new Pair(sr, sc, 0);
        pq.add(root);
        dist[sr][sc] = 0; // important

        while (pq.size() > 0) {
            Pair rm = pq.remove();
            for (int[] d : dir) {
                int r = rm.r, c = rm.c, l = rm.l;
                while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                    r += d[0];
                    c += d[1];
                    l++;
                }
                r -= d[0];
                c -= d[1];
                l--;
                if (l >= dist[r][c])
                    continue; // we are reaching this point with greater distance;

                pq.add(new Pair(r, c, l));
                dist[r][c] = l;
            }
        }

        return dist[dr][dc] == (int) 1e8 ? -1 : dist[dr][dc];
    }

    // The Maze 3: https://www.lintcode.com/problem/789

}
