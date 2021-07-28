public class nQueenSet {

    public static boolean isSafeToPlaceQueenFullDirection(boolean[][] box, int sr, int sc) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

        int n = box.length, m = box[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= n; rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (box[r][c])
                        return false;
                } else
                    break;
            }
        }

        return true;
    }

    public static boolean isSafeToPlaceQueen(boolean[][] box, int sr, int sc) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

        int n = box.length, m = box[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= n; rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (box[r][c])
                        return false;
                } else
                    break;
            }
        }

        return true;
    }

    public static int nQueen_01(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = bno; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            if (isSafeToPlaceQueen(box, r, c)) {
                box[r][c] = true;
                count += nQueen_01(box, b + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    public static int nQueen_01Subseq(boolean[][] box, int tnq, String asf, int idx, int m, int n) {
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;

        int r = idx / m;
        int c = idx % m;

        if (isSafeToPlaceQueenFullDirection(box, r, c) && !box[r][c]) {
            box[r][c] = true;
            count += nQueen_01Subseq(box, tnq - 1, asf + "(" + r + "," + c + ") ", idx + 1, m, n);
            box[r][c] = false;
        }
        count += nQueen_01Subseq(box, tnq, asf, idx + 1, m, n);

        return count;
    }

    public static int nQueenPerm_01(boolean[][] box, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = box.length, m = box[0].length;

        // every time start from 0 => perm
        for (int b = 0; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            // checking for visited too before making a call
            if (!box[r][c] && isSafeToPlaceQueenFullDirection(box, r, c)) {
                box[r][c] = true;
                count += nQueenPerm_01(box, tnq - 1, asf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }

        }
        return count;
    }

    public static int nQueenPermuSubseq(boolean[][] box, int tnq, String asf, int idx, int m, int n) {
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;

        int r = idx / m;
        int c = idx % m;

        if (isSafeToPlaceQueenFullDirection(box, r, c) && !box[r][c]) {
            box[r][c] = true;
            count += nQueenPermuSubseq(box, tnq - 1, asf + "(" + r + "," + c + ") ", 0, m, n);
            box[r][c] = false;
        }
        count += nQueenPermuSubseq(box, tnq, asf, idx + 1, m, n);

        return count;
    }

    // ISSAFE OPTIMISED

    static boolean[] row, col, diag, antidiag;

    public static int nQueen_02(int n, int m, int tnq, String asf, int idx) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int b = idx; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            if (!row[r] && !col[c] && !diag[r + c] && !antidiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = antidiag[r - c + m - 1] = true;
                count += nQueen_02(m, n, tnq - 1, asf + "(" + r + "," + c + ") ", b + 1);
                row[r] = col[c] = diag[r + c] = antidiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    public static boolean nQueen_02_firstWay(int n, int m, int tnq, String asf, int idx) {
        if (tnq == 0) {
            System.out.println(asf);
            return true;
        }

        boolean res = false;
        for (int b = idx; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            if (!row[r] && !col[c] && !diag[r + c] && !antidiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = antidiag[r - c + m - 1] = true;
                res = res || nQueen_02_firstWay(m, n, tnq - 1, asf + "(" + r + "," + c + ") ", b + 1);
                row[r] = col[c] = diag[r + c] = antidiag[r - c + m - 1] = false;
            }
        }
        return res;
    }

    public static int nQueen_02Perm(int n, int m, int tnq, String asf, int idx) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int b = 0; b < n * m; b++) {
            int r = b / m;
            int c = b % m;

            if (!row[r] && !col[c] && !diag[r + c] && !antidiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = antidiag[r - c + m - 1] = true;
                count += nQueen_02Perm(m, n, tnq - 1, asf + "(" + r + "," + c + ") ", b + 1);
                row[r] = col[c] = diag[r + c] = antidiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    // DRIVERS AND MAIN
    public static void nqueenCombs() {
        int n = 4, tnq = 4;
        boolean[][] box = new boolean[n][n];
        System.out.println("NQueen comb ncr " + nQueen_01(box, 0, tnq, ""));
        System.out.println("NQueen comb Sunseq " + nQueen_01Subseq(box, 4, "", 0, 4, 4));
    }

    public static void nqueenPerms() {
        int n = 4, tnq = 4;
        boolean[][] box = new boolean[n][n];
        System.out.println("NCR " + nQueenPerm_01(box, tnq, ""));
        System.out.println("Subs " + nQueenPermuSubseq(box, tnq, "", 0, 4, 4));
    }

    public static void issafeopt() {
        int r = 4, c = 4;
        row = new boolean[r];
        col = new boolean[r];
        diag = new boolean[r + c - 1];
        antidiag = new boolean[r + c - 1];
        System.out.println("Is safe optimized N queen Comb " + nQueen_02(r, c, 4, "", 0));
        System.out.println("Is safe optimized N queen Permutaions " + nQueen_02Perm(r, c, 4, "", 0));
        System.out.println("Is safe optimized N queen First Path " + nQueen_02_firstWay(r, c, 4, "", 0));
    }

    public static void main(String[] args) {
        // nqueenPerms();
        // nqueenCombs();
        issafeopt();
    }

}