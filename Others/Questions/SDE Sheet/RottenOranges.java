public class RottenOranges {

    private int rottenOranges(int[][] grid) {
        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

        LinkedList<Integer> q = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        if (n == 0 || m == 0)
            return -1;
        int total = n * m, rotten = 0, fresh = 0;
        boolean[][] vis = new boolean[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.addLast(i * m + j);
                    vis[i][j] = true;
                    rotten++;
                } else if (grid[i][j] == 1)
                    fresh++;
            }

        if (fresh == 0)
            return 0;
        if (rotten == 0)
            return -1; // not possible

        int time = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst(), sr = rm / m, sc = rm % m;
                // nbrs
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c] && grid[r][c] == 1) {
                        vis[r][c] = true;
                        grid[r][c] = 2; // make rotten
                        fresh--;
                        q.addLast(r * m + c);
                    }

                    if (fresh == 0)
                        return time;
                }
            }

            time++;
        }

        return -1;
    }
}
