public class SetMatrixZeroes73 {
    // Approach 1: Using Extra space: Using set to mark rows and cols to find to set 0
    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }

        // System.out.println(row+" "+col);
        for (int i : row) {
            for (int j = 0; j < m; j++)
                matrix[i][j] = 0;
        }
        for (int j : col) {
            for (int i = 0; i < n; i++)
                matrix[i][j] = 0;
        }
    }
 
}
