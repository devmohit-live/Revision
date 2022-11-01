public class SetMatrixZero {
    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        int n = matrix.length, m = matrix[0].length;

        solveConstantSpace(matrix, n, m);

        // for(int i=0;i<n;i++)
        // for(int j=0;j<m;j++)
        // if(matrix[i][j] == 0){
        // row.add(i);
        // col.add(j);
        // }

        // // System.out.println(row+" "+col);
        // for(int i: row){
        // for(int j=0;j<m;j++) matrix[i][j] = 0;
        // }
        // for(int j: col){
        // for(int i=0;i<n;i++) matrix[i][j] = 0;
        // }
    }

    // contant space
    private void solveConstantSpace(int[][] mat, int n, int m) {
        // instead of using seperate rows,cols for marking use the first
        // row and col for marking
        // mat[0][0] is an overlap for row and col so use another var col0 to mark if
        // col0 is 0 or not

        int col0 = 1;

        for (int i = 0; i < n; i++) {
            if (mat[i][0] == 0)
                col0 = 0;
            for (int j = 1; j < m; j++) {
                if (mat[i][j] == 0) {
                    mat[0][j] = 0;
                    mat[i][0] = 0;
                }
            }
        }

        // revrese chcek

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 1; j--)
                if (mat[i][0] == 0 || mat[0][j] == 0)
                    mat[i][j] = 0;
            if (col0 == 0)
                mat[i][0] = 0;
        }
    }
}
