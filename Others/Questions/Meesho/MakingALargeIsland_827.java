public class MakingALargeIsland_827 {
    // Approach 1 : TLE ; O(n*n*n)
    public int largestIsland(int[][] grid) {
        int max = 0, n = grid.length, m = grid[0].length;
        Set<Integer> idx = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)
                    idx.add(i * m + j); // no need to make unnecaary calls now
                else
                    max = Math.max(max, maxArea(grid, i, j, new boolean[n][m]));

            }
        }
        if (idx.size() == m * n) {
            return 1; // no 1 found all zeroes: convert a ZERO TO 1
        }

        for (int pos : idx) {
            int i = pos / m, j = pos % m;
            grid[i][j] = 1;
            max = Math.max(max, maxArea(grid, i, j, new boolean[n][m]));
            grid[i][j] = 0;
        }

        return max;
    }

    private int maxArea(int[][] grid, int sr, int sc, boolean[][] vis) {
        if (sr < 0 || sc < 0 || sr >= grid.length || sc >= grid[0].length || vis[sr][sc] || grid[sr][sc] != 1)
            return 0;
        int area = 1;
        vis[sr][sc] = true;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            area += maxArea(grid, r, c, vis);
        }
        return area;
    }

    // Approach 2 int[][] dir = {{0,-1},{0,1},{-1,0},{1,0}};

    public int largestIsland2(int[][] grid) {
        int max = 0, n = grid.length, m = grid[0].length, color = 2;
        HashMap<Integer, Integer> map = new HashMap<>();// stores color: area covered by that color
        Set<Integer> set = new HashSet<>(); // idx of zeros

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    set.add(i * m + j);
                } else { // 1,2,3,...unvisited/colored
                    int area = maxArea(grid, i, j, color);
                    map.put(color, area);
                    max = Math.max(area, max);
                    color++;
                }
            }
        if (set.size() == m * n)
            return 1; // all zeroes: xonvert any one to 1
        if (set.size() == 0)
            return m * n; // all ones

        for (int pos : set) {
            int i = pos / m, j = pos % m;
            int area = 1; // converted 0 to 1
            Set<Integer> visColor = new HashSet<>(); // to avaoid surrounded area of same color to be counted twice

            for (int[] d : dir) {
                int r = i + d[0], c = j + d[1];
                if (r >= 0 && c >= 0 && r < n && c < m && !visColor.contains(grid[r][c])) {
                    area += map.getOrDefault(grid[r][c], 0); // as this ele can also be 0
                    visColor.add(grid[r][c]);
                }
            }

            max = Math.max(max, area);

        }
        return max;

    }

    // visColor hashset : there can be the case like:
    // 1 1 1 1 1
    // 1 1 0 1 1 // the 0 is surrouned by same color all around and we don't need to
    // count it's area more than 1

    // visColor hashset:
    // 1 1 1 1 1
    // 1 0 0 0 1 : the consecutive 0 next to another zeros goes in dir loop but map
    // doesn't have key: val => 0:0. either use getOrDefault or intially put 0,0 in
    // map

    private int maxArea(int[][] grid, int sr, int sc, int color) {
        if (sr < 0 || sc < 0 || sr >= grid.length || sc >= grid[0].length || grid[sr][sc] != 1)
            return 0;
        int area = 1;
        grid[sr][sc] = color;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            area += maxArea(grid, r, c, color);
        }
        return area;
    }

}
