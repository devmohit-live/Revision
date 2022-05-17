import java.util.LinkedList;

public class ShortestPathInBinaryMatrix_1091 {
    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

    public int shortestPathBinaryMatrix(int[][] grid) {
        // shortest path in terms of edges : bfs

        // using bfs without cycle

        // marking grid itself
        if (grid == null || grid.length == 0)
            return -1;
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(0 * n + 0);
        grid[0][0] = 1; // visited mark
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst();
                int sr = rm / n, sc = rm % n;
                // condition check is done at base
                if (sr == n - 1 && sc == n - 1)
                    return level;
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < n && grid[r][c] == 0) {
                        grid[r][c] = 1; // mark vis
                        // marking is done at lop
                        q.addLast(r * n + c);
                    }
                }

            }
            level++;
        }

        return -1;
    }
}
