public class MakingALargeIsland827 {
    // space:
    // map=>O(n/2)(alternate 0 and 1 causing n/2 colors) set=>O(n) + O(4) visColor
    // +O(n*m) recursion
    // Space: O(n*m)

    // Time : O(n*m) : In DFS every cell I search at most once. : One I have
    // floodfilled the cell with a particular color it will not be visited again
    // so for loops: O(n*m) (inside for loop dfs(n*m) but it will not run every time
    // and not with n*m elements )
    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public int largestIsland(int[][] grid) {
        HashMap<Integer, Integer> colorArea = new HashMap<>();
        Set<Integer> zeros = new HashSet<>();
        int n = grid.length, m = grid[0].length, max = 0, color = 2;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) { // undiscovered
                    colorArea.put(color, getArea(grid, i, j, color));
                    max = Math.max(max, colorArea.get(color++));
                } else if (grid[i][j] == 0)
                    zeros.add(i * m + j); // zeroe
                // else colored 2->n=x
            }

        if (zeros.size() == m * n)
            return 1; // all zeroes : convert any one of them
        if (zeros.size() == 0)
            return m * n; // all 1

        for (int idx : zeros) {
            int i = idx / m, j = idx % m;
            int area = 1; // this zero being converted into 1;
            Set<Integer> visColor = new HashSet<>();
            for (int[] d : dir) {
                int r = i + d[0], c = j + d[1];
                if (isValid(grid, r, c) && !visColor.contains(grid[r][c])) {
                    area += colorArea.getOrDefault(grid[r][c], 0);
                    visColor.add(grid[r][c]); // grid[r][c] => color
                }
            }
            max = Math.max(max, area);

        }
        return max;
    }

    // O(n*m)
    private int getArea(int[][] grid, int sr, int sc, int color) {
        if (!isValid(grid, sr, sc) || grid[sr][sc] != 1)
            return 0;
        int area = 1;
        grid[sr][sc] = color;
        for (int[] d : dir) {
            int r = sr + d[0], c = sc + d[1];
            if (isValid(grid, r, c))
                area += getArea(grid, r, c, color);
        }
        return area;
    }

    private boolean isValid(int[][] grid, int i, int j) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
    }
}
