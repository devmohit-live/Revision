public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        int n = candidates.length;
        if (n == 0)
            return ans;

        int c = targetSum(candidates, target, 0, n, small, ans);
        return ans;

    }

    private int targetSum(int[] arr, int tar, int idx, int n, List<Integer> small, List<List<Integer>> ans) {

        if (tar == 0 || idx >= n) {
            if (tar == 0) {
                ans.add(new ArrayList<>(small));
            }
            return 0;
        }

        int c = 0;
        for (int i = idx; i < n; i++) {
            if (tar - arr[i] >= 0) {
                small.add(arr[i]);
                c += targetSum(arr, tar - arr[i], i, n, small, ans);
                small.remove(small.size() - 1);
            }
        }
        return c;
    }
}
