import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathWithGridElimination_1293 {
    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // u,d,l,r

    public int shortestPath(int[][] grid, int k) {
        int n = grid.length;
        if (n == 0)
            return -1;
        int m = grid[0].length;
        if ((grid[n - 1][m - 1] == 1 || grid[0][0] == 1) && k == 0)
            return -1;
        // return bfs_way1(grid, k, n, m);
        return bfs_optimizedPathsOnly(grid, k);
    }

    // 1st way
    private int bfs_way1(int[][] grid, int k, int n, int m) {
        LinkedList<int[]> q = new LinkedList<>();
        boolean[][][] vis = new boolean[n][m][k + 1]; // k used so far

        q.addLast(new int[] { 0, 0, 0 });
        vis[0][0][0] = true;

        int lv = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] rm = q.removeFirst();
                int sr = rm[0], sc = rm[1], currk = rm[2];
                if (sr == n - 1 && sc == m - 1 && currk <= k)
                    return lv;

                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    int nextk = currk;

                    if (r < 0 || c < 0 || r >= n || c >= m || vis[r][c][currk])
                        continue;

                    if (grid[r][c] == 1)
                        nextk++;

                    if (nextk <= k && !vis[r][c][nextk]) {
                        vis[r][c][nextk] = true;
                        q.addLast(new int[] { r, c, nextk });
                    }

                }
            }

            lv++;
        }

        return -1;
    }

    private int bfs_optimizedPathsOnly(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (k >= m + n - 2)
            return m + n - 2; // if we can go by manhattan distance -> let's go
        // basically eliminate all obstancles in the v,h way
        boolean[][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0, k, 0 }); // r, c, k, dist
        visited[0][0][k] = true;

        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0], c = top[1], curK = top[2], dist = top[3];
            if (r == m - 1 && c == n - 1)
                return dist; // Found the result
            for (int[] d : dir) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr == m || nc < 0 || nc == n)
                    continue; // skip out of bound cells!
                int newK = curK - grid[nr][nc];
                if (newK >= 0 && !visited[nr][nc][newK]) {
                    visited[nr][nc][newK] = true;
                    q.offer(new int[] { nr, nc, newK, dist + 1 });
                }
            }
        }
        return -1; // Not found
    }
}
