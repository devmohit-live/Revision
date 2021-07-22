package Recursion;

//Leetcode: 1219

class PathWithMaxGold {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int max = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                max = Math.max(max, findMaxGold(grid, m, n, i, j));
        return max;
    }

    int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    int findMaxGold(int[][] grid, int m, int n, int r, int c) {
        if (r < 0 || r == m || c < 0 || c == n || grid[r][c] == 0)
            return 0;

        int original_val = grid[r][c];
        grid[r][c] = 0; // mark as visited

        int maxGold = 0;

        for (int d[] : dir)
            maxGold = Math.max(maxGold, findMaxGold(grid, m, n, d[0] + r, d[1] + c));

        grid[r][c] = original_val; // backtrack
        return maxGold + original_val;
    }
}