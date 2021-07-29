import java.util.*;

public class RecursionTree {
    // ***************** */ NCR solutions

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

    // ************** */ SUBSEQUENCE METHOD (EQUIVALENT)

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

    public static int singlePermutation_subseq(int[] coins, int tar, boolean[] vis, int idx, String asf) {
        if (tar == 0 || idx >= coins.length) {
            if (tar == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0 && !vis[idx]) {
            vis[idx] = true;
            count += singlePermutation_subseq(coins, tar - coins[idx], vis, 0, asf + coins[idx] + " ");
            vis[idx] = false;

        }
        count += singlePermutation_subseq(coins, tar, vis, idx + 1, asf);

        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 5, 7 };
        // System.out.println(infinietePermutations(nums, "", 10));
        // System.out.println(infinieteCombinations(nums, "", 10, 0));
        // System.out.println(limitedCombinations(nums, "", 10, 0));
        boolean[] visited = new boolean[nums.length];
        System.out.println("NCR " + singlePermutation(nums, 10, visited, ""));
        System.out.println("Subseq " + singlePermutation_subseq(nums, 10, visited, 0, ""));

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

    // INTERVIEW BIT SUBSET PROBLEM USING NCR :
    // https://www.interviewbit.com/old/problems/subset/
    // Actual:
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> small = new ArrayList<>();
        Collections.sort(A);
        subsets(A, 0, small, ans);

        return ans;

    }

    public int subsets(ArrayList<Integer> nums, int idx, ArrayList<Integer> small, ArrayList<ArrayList<Integer>> ans) {
        // empty subseq is counted so count=1 at start
        // Pre Area
        int count = 1;
        ArrayList<Integer> base = new ArrayList<>(small);
        ans.add(base);
        for (int i = idx; i < nums.size(); i++) {
            // In Arera
            small.add(nums.get(i));
            count += subsets(nums, i + 1, small, ans);
            small.remove(small.size() - 1);
        }
        // Post area
        return count;
    }

    // Using StringBuilder
    public static int subsets(String str, int idx, StringBuilder asf, ArrayList<String> ans) {
        // empty subseq is counted so count=1 at start
        int count = 1;
        ans.add(asf.toString());
        for (int i = idx; i < str.length(); i++) {
            asf.append(str.charAt(i));
            count += subsets(str, i + 1, asf, ans);
            asf.deleteCharAt(asf.length() - 1);
        }
        return count;
    }

    public static void subsets(String str) {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        subsets(str, 0, sb, ans);
        System.out.println(ans);
    }
}