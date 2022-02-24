public class RotateMatrix90Clock_48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0 || n == 1)
            return;
        transpose(matrix, n);
        // swap columns
        int i = 0, j = n - 1;
        while (i < j) {
            swapColumns(matrix, i++, j--);
        }
    }

    private void transpose(int[][] grid, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(grid, i, j, j, i);
            }
        }
        // System.out.println("Trnaspsose is ");
        // Arrays.stream(grid).forEach(ar->System.out.println(Arrays.toString(ar)));
    }

    private void swap(int[][] grid, int i1, int j1, int i2, int j2) {
        int tmp = grid[i1][j1];
        grid[i1][j1] = grid[i2][j2];
        grid[i2][j2] = tmp;
    }

    private void swapColumns(int[][] grid, int i, int j) {
        int n = grid.length;
        for (int row = 0; row < n; row++) {
            swap(grid, row, i, row, j);
        }
    }

}
