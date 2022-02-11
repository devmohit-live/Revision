public class WordSeach79 {
    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0 || word.length() == 0)
            return false;
        int n = board.length, m = board[0].length, p = word.length();
        boolean res = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = res || dfs(board, word, i, j, 0);
            }
        }
        return res;
    }

    // res = res || call fails when there is only 1 char in board and word as
    // intially res is false and further callls aren't made
    private boolean dfs(char[][] board, String word, int sr, int sc, int idx) {
        if (word.length() - 1 == idx && word.charAt(idx) == board[sr][sc])
            return true;
        if (word.charAt(idx) != board[sr][sc])
            return false;

        board[sr][sc] = '#'; // mark
        boolean res = false;
        for (int[] d : dir) {
            int r = sr + d[0], c = sc + d[1];
            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && idx < word.length()
                    && board[r][c] != '#') {
                res = res || dfs(board, word, r, c, idx + 1);
            }

        }

        board[sr][sc] = word.charAt(idx); // unmark if not word[idx] base condition would've handled that
        return res;
    }
}
