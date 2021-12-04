package Questions.Graph;

import java.util.HashSet;

public class DFS {
    // https://www.lintcode.com/problem/860/ : Number of Distinct Islands: Leetcode
    // 960
    int n, m;
    int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
    String[] dirS = { "L", "D", "R", "U" };
    StringBuilder sb;
    HashSet<String> set;

    public int numberofDistinctIslands(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    sb = new StringBuilder();
                    dfs(grid, i, j);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int sr, int sc) {

        // marking visited
        grid[sr][sc] = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                sb.append(dirS[d]);
                dfs(grid, r, c);
            }
        }
        sb.append('b'); // backtrack : this is the one that will differentate the strings
    }
}
