
// Minimum Total Container Size/ Minimum Difficulty of a Job Schedule

class MinContainer_JobScedule {
    public int minDifficulty(int[] jobDifficulty, int d) {
        // Corner Case 
        if(jobDifficulty == null || jobDifficulty.length == 0) throw new IllegalArgumentException();
        int jobs = jobDifficulty.length;
        if(jobs < d) return -1;
        
        int[][] memo = new int[jobs][d + 1];
        return dfs(jobDifficulty, 0, d, memo);
    }
    
    private int dfs(int[] jobDifficulty, int idx, int d, int[][] memo) {
        int jobs = jobDifficulty.length;
        // Base Case
        if(idx == jobs && d == 0) return 0;
        if(d == 0 || jobs - idx < d) return Integer.MAX_VALUE;
        // Pruning
        if(memo[idx][d] != 0) return memo[idx][d];
        
        int sum = Integer.MAX_VALUE;
        int curDiff = 0;
        for(int i = idx; i < jobs; i++) {
            curDiff = Math.max(curDiff, jobDifficulty[i]);
            int diff = dfs(jobDifficulty, i + 1, d - 1, memo);
            if(diff != Integer.MAX_VALUE) sum = Math.min(curDiff + diff, sum);
        }
        return memo[idx][d] = sum;
    }
}