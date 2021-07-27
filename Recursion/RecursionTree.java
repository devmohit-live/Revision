import java.util.*;

// NCR solutions
public class RecursionTree {
    // INFINTE SUPPLY

    static int infinietePermutations(int[] coins, String asf, int tar) {
        if (tar <= 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += infinietePermutations(coins, asf + coins[i], tar - coins[i]);
            }
        }

        return count;
    }

    static int infinieteCombinations(int[] coins, String asf, int tar, int idx) {
        if (tar <= 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        // can't go back (except itsef => as infinite supply )
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += infinieteCombinations(coins, asf + coins[i], tar - coins[i], i);
            }
        }

        return count;
    }

    // LIMITED SUPPLY
    static int limitedCombinations(int[] coins, String asf, int tar, int idx) {
        if (tar <= 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        // can't go back (not to self coz supply is limited and we have already consumed
        // that )
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += limitedCombinations(coins, asf + coins[i], tar - coins[i], i + 1);
            }
        }

        return count;
    }

    // TODO: limited supply permutation

    public static int singlePermutation(int[] coins, int tar, boolean[] vis, String asf) {
        if (tar == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (!vis[i] && tar - coins[i] >= 0) {
                vis[i] = true;
                count += singlePermutation(coins, tar - coins[i], vis, asf + coins[i] + " ");
                vis[i] = false;
            }
        }

        return count;
    }

    // SUBSEQUENCE METHOD (EQUIVALENT)
    // TODO: Dry Run and tree/euler analysis

    
    public static int singleCombination_subseq(int[] coins, int tar, int idx, String asf) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0)
            count += singleCombination_subseq(coins, tar - coins[idx], idx + 1, asf + coins[idx] + " ");
        count += singleCombination_subseq(coins, tar, idx + 1, asf);

        return count;
    }

    public static int infiCombination_subseq(int[] coins, int tar, int idx, String asf) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0)
            count += infiCombination_subseq(coins, tar - coins[idx], idx, asf + coins[idx] + " ");
        count += infiCombination_subseq(coins, tar, idx + 1, asf);

        return count;
    }

    public static int infiPermutation_subseq(int[] coins, int tar, int idx, String asf) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0)
            count += infiPermutation_subseq(coins, tar - coins[idx], 0, asf + coins[idx] + " ");
        count += infiPermutation_subseq(coins, tar, idx + 1, asf);

        return count;
    }

    public static int singlePermutation_subseq(int[] coins, int tar, boolean[] vis, String asf) {

    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 5, 7 };
        // System.out.println(infinietePermutations(nums, "", 10));
        // System.out.println(infinieteCombinations(nums, "", 10, 0));
        // System.out.println(limitedCombinations(nums, "", 10, 0));
        boolean[] visited = new boolean[nums.length];
        System.out.println(limitedPermutations(nums, "", 10, 0));

    }

    // LEETCODE
    // leetcode 39
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList();
        List<Integer> small = new ArrayList();
        infiniteCombinations(candidates, small, target, 0, ans);
        return ans;

    }

    static int infiniteCombinations(int[] coins, List<Integer> small, int tar, int idx, List<List<Integer>> ans) {
        if (tar <= 0) {
            List<Integer> base = new ArrayList<>(small);
            ans.add(base);
            return 1;
        }

        int count = 0;

        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                small.add(coins[i]);
                count += infiniteCombinations(coins, small, tar - coins[i], i, ans);
                small.remove(small.size() - 1);
            }
        }

        return count;
    }

    // leetcode 40
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList();
        List<Integer> small = new ArrayList();
        Arrays.sort(candidates);
        limitedCombinations(candidates, small, target, 0, ans);
        return ans;
    }

    int limitedCombinations(int[] coins, List<Integer> small, int tar, int idx, List<List<Integer>> ans) {
        if (tar <= 0) {
            List<Integer> base = new ArrayList<>(small);
            ans.add(base);
            return 1;
        }

        int count = 0;
        int prev = -1;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0 && coins[i] != prev) {
                small.add(coins[i]);
                count += limitedCombinations(coins, small, tar - coins[i], i + 1, ans);
                prev = coins[i];
                small.remove(small.size() - 1);
            }
        }

        return count;
    }

}