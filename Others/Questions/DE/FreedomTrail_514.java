import java.util.Arrays;

public class FreedomTrail_514 {
    final int MAX = (int) 1e9;

    // It takes O(kr) time to fill out the 2D array mem. In each recurrence it takes
    // at most O(r) time to iterate over the characters of ring in this loop--
    // In particular, the worst case is when the string ring is all the same
    // characters so pos[i] for some letter i is O(r) space complexity. So in all,
    // the time complexity is O(kr^2) since you multiply the linear time for-loop
    // and the time it take to fill out a size k x r array.
    // Time : k*r*r (for each key we are trying all r (at worst case all r can be same so recusrice call from every one)), space : k*r
    public int findRotateSteps(String ring, String key) {
        int ptr = 0; // arrow towards current key character
        int idx = 0; // pointer current char of key
        int n = ring.length(), m = key.length();
        int[][] dp = new int[n][m + 1]; // 0...m inclusive in recursion for key
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return findRotateSteps(ring, key, ptr, idx, dp);
    }

    private int findRotateSteps(String ring, String key, int ptr, int idx, int[][] dp) {

        // base
        if (idx == key.length())
            return dp[ptr][idx] = 0; // reached the goal

        if (dp[ptr][idx] != -1)
            return dp[ptr][idx];

        int ans = MAX;
        // choises
        for (int i = 0; i < ring.length(); i++) {
            if (key.charAt(idx) == ring.charAt(i)) { // gound the same char
                int clockwiseDistance = Math.abs(ptr - i);
                int anticlockwiseDistance = ring.length() - clockwiseDistance;
                int minDistance = Math.min(anticlockwiseDistance, clockwiseDistance) + 1; // +1 for button press
                ans = Math.min(minDistance + findRotateSteps(ring, key, i, idx + 1, dp), ans);
            }
        }

        return dp[ptr][idx] = ans;
    }

    // https://leetcode.com/problems/freedom-trail/discuss/1366299/Thought-process%3A-From-top-down-to-bottom-up-solution-using-DP
}
