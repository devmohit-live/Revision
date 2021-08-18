package Recursion;

//Leetcode: 1219

//avoid cell with 0 gold
//base case => no ending point and we are making calls proactively checking so normal  return case flow will be there => function executed completely -> return

class PathWithMaxGold {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int max = 0;
        // no start and end point is given so we have to check for each cell as start

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
        return maxGold + original_val; //
    }

    // Why maxgold = max(recans,maxgold) and later adding val to result instead of
    // max(recans+val,maxgold)?
    // Ans: for test case:
    // [[0,0,0],[0,24,0],[0,0,0]] here the ans is : 24 => now we can't even make a
    // call from 24 to anywhere(all are 0 => blocked) and by default ans is 0,

    // Gold Mine GFG : TLE
    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1#

    static int[][] dirGFG = { { 0, 1 }, { -1, 1 }, { 1, 1 } };

    static int maxGold(int n, int m, int M[][]) {
        int ans = 0;
        for (int i = 0; i < n; i++) {

            ans = Math.max(ans, findMaxGoldGFG(M, i, 0, n, m));
        }

        return ans;
    }

    static int findMaxGoldGFG(int[][] grid, int sr, int sc, int n, int m) {

        // mark
        int val = grid[sr][sc];
        grid[sr][sc] = -1;
        int ans = 0;

        for (int i = 0; i < dirGFG.length; i++) {
            int r = sr + dirGFG[i][0];
            int c = sc + dirGFG[i][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] > 0) {
                int recans = findMaxGoldGFG(grid, r, c, n, m);
                if (recans > ans) {
                    ans = recans;
                }
            }
        }

        grid[sr][sc] = val;
        return ans + val;

    }
}