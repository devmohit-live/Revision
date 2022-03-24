package Others.Questions.AMZ.OA;
public class DeliveryRoute {
    static int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
    static String[] dirS = { "L", "D", "R", "T" };

    public static void main(String[] args) {
        int[][] board = { { 1, 0, 0 }, { 1, 0, 0 }, { 1, 9, 1 } };
        // int count = floodFill(0,0,board,0);
        // System.out.println(count);
        System.out.println(bfs(board));

    }

    public static int floodFill(int sr, int sc, int[][] board, int moves) {
        int n = board.length, m = board[0].length;
        if (board[sr][sc] == 9) {
            // System.out.println(ans.length());
            return moves;
        }

        board[sr][sc] = 0;
        int min = Integer.MAX_VALUE;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length) {
                if (board[r][c] != 0)
                    min = Math.min(min, floodFill(r, c, board, moves + 1));
            }
        }

        // board[sr][sc] = 1;
        return min;

    }

    // bfs just to check for reachability and shortest path
    private static int bfs(int[][] arr) {
        LinkedList<Integer> q = new LinkedList<>();
        int n = arr.length, m = arr[0].length;
        if (n == 0 || m == 0 || arr[0][0] == 0)
            return -1;
        if (arr[0][0] == 9)
            return 0;
        boolean[][] vis = new boolean[n][m];
        q.addLast(0);
        int level = 0;
        vis[0][0] = true; // better way
        // arr[0][0] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst(), sr = rm / m, sc = rm % m;
                if (arr[sr][sc] == 9)
                    return (sr + sc); // better way
                for (int i = 0; i < 4; i++) {
                    int r = sr + dir[i][0];
                    int c = sc + dir[i][1];
                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c] && arr[r][c] != 0) { // better way
                        // if(r>=0 && c>=0 && r<n && c<m && arr[r][c]!=0){
                        // if(arr[r][c] == 9) return (r+c);
                        q.addLast(r * m + c);
                        // arr[r][c] = 0;
                        vis[r][c] = true;
                    }
                }
            }
            level++;
        }

        return -1;
    }
}
