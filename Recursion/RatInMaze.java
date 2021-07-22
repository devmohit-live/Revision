package Recursion;

import java.util.*;

public class RatInMaze {
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#
    static int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
    static String[] dirS = { "D", "L", "R", "U" };

    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> ans = new ArrayList<String>();
        if (m[0][0] == 0 || m[n - 1][n - 1] == 0)
            return ans;

        mazePath_HVD(m, 0, 0, n - 1, n - 1, "", ans);
        return ans;
    }

    public static int mazePath_HVD(int[][] grid, int sr, int sc, int er, int ec, String psf, ArrayList<String> ans) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        // marking where I am standing
        grid[sr][sc] = -1;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && grid[r][c] == 1) {
                count += mazePath_HVD(grid, r, c, er, ec, psf + dirS[d], ans);
            }
        }

        grid[sr][sc] = 1;
        return count;
    }
}
