package Recursion;

import java.util.*;

public class QueenLeetcode {

    // Leetcode 51
    public int totalNQueens(int n) {
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[n + n - 1];
        boolean[] antidiag = new boolean[n + n - 1];

        return nqueenComb(n, n, col, diag, antidiag, 0);

    }

    public int nqueenComb(int n, int tnq, boolean[] col, boolean[] diag, boolean[] antidiag, int floor) {
        if (tnq == 0)
            return 1;

        int count = 0;
        for (int room = 0; room < n; room++) {
            int r = floor, c = room;

            if (!col[c] && !diag[r + c] && !antidiag[r - c + n - 1]) {
                col[c] = diag[r + c] = antidiag[r - c + n - 1] = true;
                count += nqueenComb(n, tnq - 1, col, diag, antidiag, floor + 1);
                col[c] = diag[r + c] = antidiag[r - c + n - 1] = false;
            }
        }
        return count;

    }

    // Leetcode 51 (answer in particular format)
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        List<String> small = new ArrayList<>();
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[n + n - 1];
        boolean[] antidiag = new boolean[n + n - 1];
        int sols = nqueenComb(n, n, col, diag, antidiag, 0, small, ans);
        return ans;
    }

    public int nqueenComb(int n, int tnq, boolean[] col, boolean[] diag, boolean[] antidiag, int floor,
            List<String> small, List<List<String>> ans) {
        if (tnq == 0) {
            List<String> base = new ArrayList<>(small);
            ans.add(base);
            return 1;
        }

        int count = 0;
        for (int room = 0; room < n; room++) {
            int r = floor, c = room;

            if (!col[c] && !diag[r + c] && !antidiag[r - c + n - 1]) {
                col[c] = diag[r + c] = antidiag[r - c + n - 1] = true;

                // building ans
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < room; i++)
                    sb.append('.');
                sb.append('Q');
                for (int i = room; i < n - 1; i++)
                    sb.append('.');

                small.add(sb.toString());

                count += nqueenComb(n, tnq - 1, col, diag, antidiag, floor + 1, small, ans);
                col[c] = diag[r + c] = antidiag[r - c + n - 1] = false;
                small.remove(small.size() - 1);
            }
        }
        return count;

    }

}
