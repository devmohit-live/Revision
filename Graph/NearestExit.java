import java.util.Arrays;
import java.util.LinkedList;

// Leetcode 1926
//bfs

public class NearestExit {
    public int nearestExit(char[][] maze, int[] entrance) {
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        return bfs(maze, entrance, dir);
    }

    public int bfs(char[][] maze, int[] src, int[][] dir) {
        int level = 0;
        LinkedList<int[]> q = new LinkedList<>();
        q.addLast(src);

        while (q.size() > 0) {
            int curr = q.size();

            while (curr-- > 0) {
                int[] rm = q.removeFirst();
                // System.out.println(Arrays.toString(rm));
                int i = rm[0];
                int j = rm[1];
                boolean edges = (i == 0 || j == 0 || i == maze.length - 1 || j == maze[i].length - 1);
                // both i,j should not be same as source but any 1 of them can be same

                boolean notSimilarToSource = i != src[0] || j != src[1];

                if (edges && maze[i][j] == '.' && notSimilarToSource) {
                    return level == 0 ? -1 : level;
                }

                if (maze[i][j] == 'v')
                    continue;

                maze[i][j] = 'v';

                for (int[] d : dir) {

                    int r = i + d[0];
                    int c = j + d[1];

                    if (r >= 0 && r < maze.length && c >= 0 && c < maze[i].length && maze[r][c] == '.') {
                        q.addLast(new int[] { r, c });
                    }

                }

            }
            // System.out.println(level);
            level++;
        }

        return -1;
    }

}