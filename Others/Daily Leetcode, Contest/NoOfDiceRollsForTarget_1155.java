public class NoOfDiceRollsForTarget_1155 {
    public int numRollsToTarget(int d, int f, int target) {
        Integer[][] mem = new Integer[d + 1][target + 1];
        return numRollsToTarget(d, f, target, mem);
    }

    public int numRollsToTarget(int d, int f, int target, Integer[][] mem) {
        if (d == 0 || target < 0)
            return target == 0 ? 1 : 0;
        if (mem[d][target] != null)
            return mem[d][target];
        int ways = 0;
        for (int i = 1; i <= f; i++)
            ways = (ways + numRollsToTarget(d - 1, f, target - i, mem)) % 1000000007;
        return mem[d][target] = ways;
    }
}
