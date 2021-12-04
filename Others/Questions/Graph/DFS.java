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

    // Time: m*n, space: m*n : StringBuilder can be on m*n(all elemets are 1)
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

    // Leetcodeb 1905 : Count sub islands
    // NOTE: the gird 2 caontains the possible sub-islands of grid1 :
    // if complete island from grid2 must be a prst of a island from grid1
    // the grid2 specific part can't be a subisland : the complete island from grid2
    // must be a part of grid1

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        n = grid2.length;
        m = grid2[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1) {
                    count += dfs(grid1, grid2, i, j) ? 1 : 0;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int sr, int sc) {
        // will check for complete island first and then decidde for my decision

        // Remeber res && dfs() -> wont make a call if res is false, but
        // res = dfs() && res would make a call first and we have to traverse to whole
        // island of grid2=> to mark either in between there is water(island is broken :
        // not a sub-island)

        grid2[sr][sc] = 2;
        boolean res = true;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid2[r][c] == 1) {
                res = dfs(grid1, grid2, r, c) && res;
            }

        }

        return res && grid1[sr][sc] == 1; // my decision if grid1 contains 1 at sr sc or ot
    }
}
