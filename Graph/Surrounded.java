public class Surrounded {
    // Leetcode 130 : Surrounded Regions
    // boundary wale surrounded nhi honge, so unse connected log b surrounded
    // regions me nhi aayenge

    // sol: hr boundary ke O se bol do ki apne log mark krde (jo connected hai),
    // inke alava jo b O(log/person) bach jaenge wo surrounded honge

    // boundary ke o => sia, surrounded => sunni
    // hai dono hi O but sub divided

    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
    int n = 0, m = 0;

    public void solve(char[][] board) {
        n = board.length;
        m = board[0].length;
        // saaare edges se call lga di
        for (int i = 0, j = 0; i < n; i++)
            if (board[i][j] == 'O')
                floodfill(board, i, j);
        for (int i = 0, j = m - 1; i < n; i++)
            if (board[i][j] == 'O')
                floodfill(board, i, j);
        for (int j = 0, i = 0; j < m; j++)
            if (board[i][j] == 'O')
                floodfill(board, i, j);
        for (int j = 0, i = n - 1; j < m; j++)
            if (board[i][j] == 'O')
                floodfill(board, i, j);

        // *wale sia h => surrounded nhi hai
        // jo bach gye(O) wo surrounded h or ab wo X bn jaenge
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                // ye surrounded ho gae
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                // ye apna group bna lie the to waise ke waise ho jaenge
                if (board[i][j] == '*')
                    board[i][j] = 'O';

            }

    }

    private void floodfill(char[][] board, int sr, int sc) {
        board[sr][sc] = '*'; // ate hi sia ne mark kr lia
        for (int d[] : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && r < n && c >= 0 && c < m && board[r][c] == 'O') {
                floodfill(board, r, c);
            }
        }

    }
}
