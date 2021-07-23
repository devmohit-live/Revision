import java.util.*;

public class BacktracingRev {

    static int[][] dir2 = { { 1, 0 }, { 0, 1 } };
    static String dirs2 = "dr";

    static int[][] dir3 = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
    static String dirs3 = "HVD";

    static int[][] dir4 = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
    static String dirs4 = "ldrt";

    static int[][] dir8 = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };
    static String[] dirs8 = { "north", "south", "west", "east", "nort-east", "soth-east", "south-west", "north-west" };

    public static void main(String[] args) {

    }

    // complete floodfill just return on finding the first path
    public static boolean pathExists(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return true;
        }

        board[sr][sc] = 1;

        boolean res = false;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0)
                    res = res || pathExists(r, c, board, ans + dirS[d], dir, dirS);
            }
        }

        board[sr][sc] = 0;
        return res;
    }

    // 0 -> empty cell, 1 -> blocked cell

    // generic traversal in maze in 2,3,4,8 , any direction with blocked,unblocked
    // cells
    public static int floodFill(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return 1;
        }

        board[sr][sc] = 1;

        int count = 0;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0)
                    count += floodFill(r, c, board, ans + dirS[d], dir, dirS);
            }
        }

        board[sr][sc] = 0;
        return count;

    }

    // generic traversal into any x directions when jumps are allowed
    public static int floodFill_Jump(int sr, int sc, int[][] board, String ans, int[][] dir, String[] dirS,
            int Radius) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            System.out.println(ans);
            return 1;
        }

        board[sr][sc] = 1;

        int count = 0;

        for (int d = 0; d < dir.length; d++) {// radius cane be Math.max(n,n) if not given
            for (int rad = 1; rad <= Radius; rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                    if (board[r][c] == 0)
                        count += floodFill_Jump(r, c, board, ans + rad + dirS[d], dir, dirS, Radius);
                } else
                    break;
            }
        }

        board[sr][sc] = 0;
        return count;
    }

    public static int floodFill_longestLen(int sr, int sc, int[][] board, int[][] dir) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            // here we are returning length not number of ways and des->des => len =0

            return 0;
        }
        // mark
        board[sr][sc] = 1;

        // default ans indicating there there doesn't exixts any path from given point
        // to dest
        int longestLen = -1;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0) {
                    int recAns = floodFill_longestLen(r, c, board, dir);
                    // can't directly do Math.max(rescans, longedtLen) withour checkong recans!=-1
                    // updating the value only when there is a valid path
                    if (recAns != -1 && recAns + 1 > longestLen) {
                        longestLen = recAns + 1;
                    }
                }
            }
        }
        // unmark
        board[sr][sc] = 0;
        return longestLen;
    }

    public static int floodFill_shortestLen(int sr, int sc, int[][] board, int[][] dir) {
        int n = board.length, m = board[0].length;
        if (sr == n - 1 && sc == m - 1) {
            // here we are returning length not number of ways and des->des => len =0

            return 0;
        }
        // mark
        board[sr][sc] = 1;

        // default ans indicating there there doesn't exixts any path from given point
        // to dest
        int shortestLen = (int) 1e9;
        ;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] == 0) {
                    int recAns = floodFill_shortestLen(r, c, board, dir);
                    // updating the value only when there is a valid path
                    if (recAns != -1 && recAns + 1 < shortestLen) {
                        shortestLen = recAns + 1;
                    }
                }
            }
        }
        // unmark
        board[sr][sc] = 0;
        return shortestLen;
    }
}