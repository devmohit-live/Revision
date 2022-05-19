import java.util.LinkedList;

class LongestIncreasingPathInMatrix_329 {
    int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int longestIncreasingPath(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
            return 0;

        int n = mat.length, m = mat[0].length, count = 0;
        int[][] indegree = new int[n][m];

        // bfs in directed graph gives longest path : why?
        // bcz it starts with all the points(vtx) that does't have any dependecies and
        // reaches to a vtx(end) that has many dependevies at the last
        // after clearing all of it's dependencoes on the way

        // it is like keep running the bfs without any specific target

        // calculate indegree : according to condition vtx mat[i][j] can be reached by
        // it's 4 nbrs who are lesser than mat[i][j]

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int[] d : dir) {
                    int r = i + d[0], c = j + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < m && mat[i][j] > mat[r][c])
                        indegree[i][j]++;
                }

        LinkedList<Integer> q = new LinkedList<>();
        int level = 0;
        // multisource bfs
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (indegree[i][j] == 0)
                    q.addLast(i * m + j);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst(), sr = rm / m, sc = rm % m;
                count++;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    // goinf from low to high : according to given condition
                    if (r >= 0 && c >= 0 && r < n && c < m && mat[sr][sc] < mat[r][c]) {
                        indegree[r][c]--;
                        if (indegree[r][c] == 0)
                            q.addLast(r * m + c);
                    }
                }
            }

            level++;

        }

        if (count != m * n)
            return 0;

        return level;
    }
}