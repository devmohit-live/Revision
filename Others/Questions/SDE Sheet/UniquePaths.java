public class UniquePaths {
    private final int[][] dir = { { 0, 1 }, { 1, 0 } };

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // overlapping problem as at a cell i can decide
        // for two directions and can ask how many ways i can go to des will double it
        // as 1 for down movememnt and 1 for right (towards that cell I has asked for)

        // for(int[] row : dp) Arrays.fill(row, -1);

        // return floodfill(0,0,m-1,n-1,dp);

        return tabu(m - 1, n - 1);
    }

    // l to r, u -> d
    private int floodfill(int sr, int sc, int dr, int dc, int[][] dp) {
        if (sr < 0 || sc < 0 || sr > dr || sc > dc)
            return 0;

        if (sr == dr && sc == dc)
            return 1;
        if (dp[sr][sc] != -1)
            return dp[sr][sc];
        int count = 0;

        // for(int[] d: dir){
        // int r = sr + d[0], c = sc + d[1];

        // if(r>=0 && c>=0 && r<=dr && c<=dc){
        // count+=floodfill(r,c,dr,dc,dp);
        // }
        // }
        count += floodfill(sr + 1, sc, dr, dc, dp);
        count += floodfill(sr, sc + 1, dr, dc, dp);

        return dp[sr][sc] = count;

    }

    // opposite
    private int tabu(int DR, int DC) {
        int[][] dp = new int[DR + 1][DC + 1];
        dp[DR][DC] = 1; // base

        for (int sr = DR; sr >= 0; sr--) {
            for (int sc = DC; sc >= 0; sc--) {
                if (sr + 1 <= DR)
                    dp[sr][sc] += dp[sr + 1][sc];
                if (sc + 1 <= DC)
                    dp[sr][sc] += dp[sr][sc + 1];
            }

        }

        return dp[0][0];
    }
}
