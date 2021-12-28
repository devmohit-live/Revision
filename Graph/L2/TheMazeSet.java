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

}
