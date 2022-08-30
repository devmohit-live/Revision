import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ClosestSubseqSum {
    // 1755
    public int minAbsDifference(int[] nums, int goal) {
        Set<Integer> leftSet = new HashSet<>();
        Set<Integer> rightSet = new HashSet<>();

        generate(0, nums.length / 2, nums, 0, leftSet);
        generate(nums.length / 2, nums.length, nums, 0, rightSet);

        TreeSet<Integer> ts = new TreeSet<>(rightSet);
        int ans = Math.abs(goal);

        for (int left : leftSet) {
            int right = goal - left;
            if (ts.contains(right)) {
                return 0;
            }

            Integer prev = ts.lower(right);
            Integer next = ts.higher(right);

            if (prev != null) {
                ans = Math.min(ans, Math.abs(left + prev - goal));
            }
            if (next != null) {
                ans = Math.min(ans, Math.abs(left + next - goal));
            }

        }
        return ans;
    }

    private void generate(int idx, int end, int[] nums, int sum, Set<Integer> set) {
        if (idx == end) {
            set.add(sum);
            return;
        }
        generate(idx + 1, end, nums, sum, set);
        generate(idx + 1, end, nums, sum + nums[idx], set);
    }
}
