package Recursion;

class SpecialMatrix {
    int[][] dir = { { 1, 0 }, { 0, 1 } };
    String[] dirS = { "D", "R" };

    public int FindWays(int n, int m, int[][] blocked_cells) {
        int[][] grid = new int[n][m];
        for (int[] b : blocked_cells) {
            grid[b[0] - 1][b[1] - 1] = -1;
        }
        if (grid[0][0] == -1 || grid[n - 1][m - 1] == -1)
            return 0;
        return countPaths(0, 0, grid);

    }

    public int countPaths(int n, int m, int[][] grid) {
        if (n == grid.length - 1 && m == grid[0].length - 1) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = n + dir[i][0];
            int c = m + dir[i][1];

            if (r >= 0 && c >= 0 && r <= grid.length - 1 && c <= grid[0].length - 1 && grid[r][c] != -1) {
                count += countPaths(r, c, grid);
            }

        }
        return count;
    }
}