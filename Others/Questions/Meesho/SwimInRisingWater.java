public class SwimInRisingWater {
    // bfs and pq
    // bfs : add neighbours in 4 directions
    // pq to get the min ht first
    // explanation: floating tile of height represented in matrix water is rising
    // every sec by 1 to all surface evenly and we are trying to got to the en last
    // tile we can't jump , we can only walk to tiles of same level

    public int swimInWater(int[][] grid) {
        int n = grid.length, m = n;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;
            return grid[i1][j1] - grid[i2][j2];
        });
        boolean vis[][] = new boolean[n][m];
        pq.add(0 * 0 + 0); // ssrc
        vis[0][0] = true;
        int max = 0;
        while (pq.size() > 0) {
            int rm = pq.remove(), i = rm / m, j = rm % m;
            int ht = grid[i][j];
            max = Math.max(max, ht);
            if (i == n - 1 && j == n - 1)
                return max; // reached the end tile
            // add neighbouring tiles
            for (int[] d : dir) {
                int r = i + d[0], c = j + d[1];
                if (r >= 0 && c >= 0 && r < n && c < n && !vis[r][c]) {
                    vis[r][c] = true;
                    pq.add(r * m + c);
                }
            }

        }

        return max;
    }
}
