import java.util.LinkedList;

public class MinStepsKnight {
    // It's running perfectly here on BFS without cycle and using point encoding :-(
    
    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N) {
        class Point {
            int r, c;

            Point(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        int[][] dir = { { -2, -1 }, { -2, 1 }, { 2, -1 }, { 2, 1 }, { -1, 2 }, { -1, -2 }, { 1, -2 }, { 1, 2 } };
        // LinkedList<Point> q = new LinkedList<>();
        LinkedList<Integer> q = new LinkedList<>();
        boolean[][] vis = new boolean[N][N];

        int sr = KnightPos[0] - 1, sc = KnightPos[1] - 1;
        // q.addLast(new Point(sr,sc));
        q.addLast(sr * N + sc);
        vis[sr][sc] = true;
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                // Point rm = q.removeFirst();
                // int r = rm.r, c = rm.c;
                int rm = q.removeFirst();
                int r = rm / N, c = rm % N;
                if (r == TargetPos[0] - 1 && c == TargetPos[1] - 1)
                    return steps;

                // if(vis[r][c]) continue;
                // vis[r][c] = true;

                for (int[] d : dir) {
                    int row = r + d[0];
                    int col = c + d[1];
                    if (row >= 0 && col >= 0 && row < N && col < N && !vis[row][col]) {
                        q.addLast(row * N + col);
                        // q.addLast(new Point(row,col));
                        vis[row][col] = true;
                    }

                }
            }
            steps++;
        }

        return -1;

    }
}
