package Others.Questions.AMZ.OA;

// Minimum Total Container Size/ Minimum Difficulty of a Job Schedule
// split jobs in to d days with some condition

class MinContainer_JobScedule {
    public int minDifficulty(int[] jobDifficulty, int d) {
        // Corner Case
        if (jobDifficulty == null || jobDifficulty.length == 0)
            return -1;
        int n = jobDifficulty.length;
        if (n < d) // can't assign work for each day
            return -1;

        int[][] memo = new int[n][d + 1]; //jobs,days
        return dfs(jobDifficulty, 0, d, memo);
    }

    private int dfs(int[] jobDifficulty, int idx, int d, int[][] memo) {
        int n = jobDifficulty.length;
        // Base Case
        if (idx == n && d == 0)
            return 0;
        if (d == 0 || n - idx < d)
            return Integer.MAX_VALUE;

        if (memo[idx][d] != 0)
            return memo[idx][d];

        int sum = Integer.MAX_VALUE;

        int curDiff = 0;
        for (int i = idx; i < n; i++) {
            curDiff = Math.max(curDiff, jobDifficulty[i]);
            int diff = dfs(jobDifficulty, i + 1, d - 1, memo);
            if (diff != Integer.MAX_VALUE)
                sum = Math.min(curDiff + diff, sum); // mimimizing the sum
        }
        return memo[idx][d] = sum;
    }
}