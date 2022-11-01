public class RotateMatrix {
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

    // Coding ninjas
    public static void rotateMatrix(ArrayList<ArrayList<Integer>> mat, int n, int m) {
        if (n == 0 || n == 1)
            return;
        transpose(mat, n);
        // swap columns
        int i = 0, j = n - 1;
        while (i < j)
            swapColumns(mat, i++, j--);
    }

    private static void transpose(ArrayList<ArrayList<Integer>> grid, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(grid, i, j, j, i);
            }
        }
        // System.out.println("Trnaspsose is ");
        // Arrays.stream(grid).forEach(ar->System.out.println(Arrays.toString(ar)));
    }

    private static void swap(ArrayList<ArrayList<Integer>> grid, int i1, int j1, int i2, int j2) {
        int tmp = grid.get(i1).get(j1);
        grid.get(i1).set(j1, grid.get(i2).get(j2));
        grid.get(i2).set(j2, tmp);
    }

    private static void swapColumns(ArrayList<ArrayList<Integer>> grid, int i, int j) {
        int n = grid.size();
        for (int row = 0; row < n; row++) {
            swap(grid, row, i, row, j);
        }
    }
}
