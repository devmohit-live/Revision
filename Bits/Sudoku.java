public class Sudoku {
    class withoutBits {

        class Pair {
            int r, c;

            Pair(int r, int c) {
                this.r = r;
                this.c = c;
            }

            public String toString() {
                return "(" + this.r + " " + this.c + ")";
            }
        }

        int[] row, col, mat;

        public void solveSudoku(char[][] board) {
            // to get the list of empty places to avoid going to thet places ehich are
            // already filled
            ArrayList<Pair> arr = new ArrayList<>();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.')
                        arr.add(new Pair(i, j));
                }
            }
            row = new int[9];
            col = new int[9];
            mat = new int[9];
            solve(arr, 0, board);

        }

        private boolean isValidToPlaceNumber(char[][] board, int n, int r, int c) {
            // row
            for (int i = 0; i < 9; i++)
                if (board[r][i] - '0' == n)
                    return false;
            // col
            for (int i = 0; i < 9; i++)
                if (board[i][c] - '0' == n)
                    return false;

            int matr = r / 3 * 3;
            int matc = c / 3 * 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[matr + i][matc + j] - '0' == n)
                        return false;
                }
            }

            return true;

        }

        private boolean solve(ArrayList<Pair> arr, int idx, char[][] board) {
            if (idx == arr.size()) {
                return true;
            }

            Pair p = arr.get(idx);
            int r = p.r;
            int c = p.c;

            for (int num = 1; num <= 9; num++) {
                if (isValidToPlaceNumber(board, num, r, c)) {

                    board[r][c] = (char) (num + '0'); // mark
                    boolean validboard = solve(arr, idx + 1, board);
                    if (validboard)
                        return true;
                    board[r][c] = '.';

                }

            }
            return false;
        }

    }

}
