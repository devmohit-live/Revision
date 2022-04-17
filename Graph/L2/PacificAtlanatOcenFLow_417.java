public class PacificAtlanatOcenFLow_417 {
    // Brute Force: try dfs from each cell and follow the condition given
    // remember if at mat[i][j] acc to contiton it should be > it's 4 dir neighbours
    // and also it should be ab;e to reach both the oceans => any two opposite ends
    // of the matrix(now that is pretty hard to do )

    // Optimal: start from the both ocean's ends and do bfs/dfs
    // reverse the given condition as we are trying the oppsote direction

    // original cond : mineHeight is >= 4 neighbours
    // new condition : minheight is <= 4 neighbours

    // from this approach we are sure that it is always possible to reach the ocean
    // and for any mat[i][c] which is visited in both visPacific and visAtlantic is
    // a part of answer
    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public List<List<Integer>> pacificAtlantic(int[][] mat) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = mat.length, m = mat[0].length;
        if (n == 0 || m == 0)
            return ans;

        boolean visitedP[][] = new boolean[n][m];
        boolean visitedA[][] = new boolean[n][m];

        // for bfs
        LinkedList<int[]> qp = new LinkedList<>();
        LinkedList<int[]> qa = new LinkedList<>();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                // if(i==0 || j==0) dfs(mat,i,j,Integer.MIN_VALUE,visitedP);
                // if(i==n-1 || j==m-1) dfs(mat,i,j,Integer.MIN_VALUE,visitedA);

                if (i == 0 || j == 0) {
                    qp.addLast(new int[] { i, j });
                    visitedP[i][j] = true;
                }
                if (i == n - 1 || j == m - 1) {
                    qa.addLast(new int[] { i, j });
                    visitedA[i][j] = true;
                } else
                    continue;
            }

        bfs(mat, qp, visitedP);
        bfs(mat, qa, visitedA);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (visitedP[i][j] && visitedA[i][j])
                    ans.add(Arrays.asList(i, j));

        return ans;

    }

    private void dfs(int[][] grid, int sr, int sc, boolean[][] vis) {
        int mineHeight = grid[sr][sc];
        // minheigh <= neighbours: new condition
        vis[sr][sc] = true;

        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && !vis[r][c] && mineHeight <= grid[r][c])
                dfs(grid, r, c, vis);

        }

    }

    private void bfs(int[][] grid, LinkedList<int[]> q, boolean[][] vis) {
        // withour ccycle
        while (!q.isEmpty()) {
            int[] rm = q.remove();
            int r = rm[0], c = rm[1];
            int mineHeight = grid[r][c];
            for (int[] d : dir) {
                int x = r + d[0];
                int y = c + d[1];
                // opposite condition as doing bfs from opposite direction
                if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && !vis[x][y]
                        && grid[x][y] >= mineHeight) {
                    q.addLast(new int[] { x, y });
                    vis[x][y] = true;
                }
            }
        }
    }
}
