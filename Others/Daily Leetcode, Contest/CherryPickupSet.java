import java.util.Arrays;

class CherryPickupSet {
    // 1463. Cherry Pickup II : DP
    /*
     * ApproaCH: Move both robots at a time row by row. Note we just need to keep
     * track of columns where 2 robots were earlier. Base Condition - when row
     * reaches total rows in grid, returning 0 At each row: If we looked at robot
     * moves (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1), robot moves have 3
     * choices for column {-1, 0, +1} and one choice for row { +1 } i.e. next row.
     * Now, if we consider moving 2 robots to maximize cherry count - we total have
     * maximum 3 (for first robot) * 3 (for second robot) = 9 choices Using
     * recrusion - we can determine maximum cherry pick up count. Note - if both
     * robots land in the same block - we have to just take that cherry count once.
     * Case is handled by looking at column value of robot1 and robot2.
     */
    private int R, C;
    /// possible directions for robots (for next columns)
    int[] dirs = new int[] { -1, 0, 1 };
    int[][] grid;

    public int cherryPickup(int[][] grid) {
        this.R = grid.length;
        this.C = grid[0].length;
        this.grid = grid;
        //// `dp` array for a grid row = `row`, robot1 column = `r1Col` and robot2
        //// column = `r2Col`
        int[][][] dp = new int[R][C][C];

        for (int[][] dp2 : dp) {
            for (int[] row : dp2)
                Arrays.fill(row, -1);
        }
        return maxCherryCount(0, 0, C - 1, dp);
    }

    private int maxCherryCount(int row, int r1Col, int r2Col, int[][][] dp) {
        /// If all rows are processed
        if (row == this.R)
            return 0;

        /// if this result is already calculated
        if (dp[row][r1Col][r2Col] != -1)
            return dp[row][r1Col][r2Col];
        int ans = Integer.MIN_VALUE;

        /// Check if both robots are at same block in the grid
        int currVal = (r1Col == r2Col) ? this.grid[row][r1Col] : this.grid[row][r1Col] + this.grid[row][r2Col];

        /// Both robots have 3 possible blocks each in every row
        /// total possible choices = 3 (from Robot 1) * 3 (from Robot 2) = 9 [when row =
        /// row]
        for (int robo1_dir : dirs) {
            for (int robo2_dir : dirs) {
                int robo1NewCol = r1Col + robo1_dir;
                int robo2NewCol = r2Col + robo2_dir;

                /// Try for next row, only if both robots can be safely landed inside the grid
                if (isSafe(robo1NewCol) && isSafe(robo2NewCol)) {
                    ans = Math.max(ans, currVal + maxCherryCount(row + 1, robo1NewCol, robo2NewCol, dp));
                }
            }
        }
        return dp[row][r1Col][r2Col] = ans; /// update `dp` and return the answer
    }

    /// Check if column is within boundary of the grid
    private boolean isSafe(int j) {
        return (j >= 0 && j < this.C);
    }
}
