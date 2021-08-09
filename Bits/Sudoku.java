import java.util.ArrayList;

public class Sudoku {

    // Leetcode :37
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

    class UsingBits {

        class Pair {
            int r, c;

            Pair(int r, int c) {
                this.r = r;
                this.c = c;
            }
            // public String toString(){
            // return "(" + this.r +" " + this.c + ")";
            // }
        }

        int[] rows, cols;
        int[][] mat;

        public void solveSudoku(char[][] board) {
            // to get the list of empty places to avoid going to thet places ehich are
            // already filled
            ArrayList<Pair> arr = new ArrayList<>();
            rows = new int[9];
            cols = new int[9];
            mat = new int[3][3];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int digit = board[i][j] - '0';
                    // setting setbit values
                    rows[i] |= (1 << digit);
                    cols[j] |= (1 << digit);
                    mat[i / 3][j / 3] |= (1 << digit);
                    if (board[i][j] == '.')
                        arr.add(new Pair(i, j));
                }
            }

            solve(arr, 0, board);

        }

        private boolean isValidToPlaceNumber(int num, int r, int c) {
            int i = r / 3;
            int j = c / 3;
            int mask = (1 << num);

            if ((rows[r] & mask) != 0 || (cols[c] & mask) != 0 || (mat[i][j] & mask) != 0)
                return false;

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
                if (isValidToPlaceNumber(num, r, c)) {
                    // marking and setting number
                    board[r][c] = (char) (num + '0');
                    rows[r] ^= (1 << num);
                    cols[c] ^= (1 << num);
                    mat[r / 3][c / 3] ^= (1 << num);

                    boolean validboard = solve(arr, idx + 1, board);
                    if (validboard)
                        return true;

                    // unsetting,unmarking
                    board[r][c] = '.';
                    rows[r] ^= (1 << num);
                    cols[c] ^= (1 << num);
                    mat[r / 3][c / 3] ^= (1 << num);
                }

            }
            return false;
        }

    }

    // IsValidSudokuBoard : Leetcode 36

    // without bits:
    class Pair {
        int r, c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public boolean isValidSudoku(char[][] board) {
        ArrayList<Pair> arr = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] != '.')
                    arr.add(new Pair(i, j));
        }

        boolean res = true;

        for (Pair p : arr) {
            int r = p.r, c = p.c;
            res = res && valid(r, c, board[r][c], board);
            if (!res)
                return false;

        }

        return res;
    }

    private boolean valid(int r, int c, char num, char[][] board) {
        for (int i = 0; i < 9 && i != c; i++)
            if (board[r][i] == num)
                return false;

        for (int i = 0; i < 9 && i != r; i++)
            if (board[i][c] == num)
                return false;

        int matr = r / 3 * 3;
        int matc = c / 3 * 3;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (matr + i != r && matc + j != c && num == board[matr + i][matc + j])
                    return false;

        return true;
    }

    // USING BITS:
    int[] row, col;
    int[][] mat;

    public boolean isValidSudokuBits(char[][] board) {
        col = new int[9];
        row = new int[9];
        mat = new int[3][3];
        boolean res = true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int digit = board[i][j] - '0';
                    res = res && valid(i, j, digit, board);
                    if (res) {
                        row[i] |= (1 << digit);
                        col[j] |= (1 << digit);
                        mat[i / 3][j / 3] |= (1 << digit);
                    }
                }
            }
        }
        return res;
    }

    private boolean valid(int r, int c, int digit, char[][] board) {

        if ((row[r] & (1 << digit)) != 0)
            return false;
        if ((col[c] & (1 << digit)) != 0)
            return false;
        if ((mat[r / 3][c / 3] & (1 << digit)) != 0)
            return false;

        return true;
    }

}
