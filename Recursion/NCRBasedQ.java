package Recursion;

import java.util.*;

public class NCRBasedQ {

    // leetcode 216
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        limitedCombinations(n, k, 1, small, ans);
        return ans;

    }

    int limitedCombinations(int tar, int k, int idx, List<Integer> small, List<List<Integer>> ans) {
        if (tar == 0 && k == 0) {
            List<Integer> base = new ArrayList<>(small);
            ans.add(base);
            return 1;
        }

        int count = 0;
        for (int i = idx; i <= 9; i++) {
            if (tar - i >= 0) {
                small.add(i);
                count += limitedCombinations(tar - i, k - 1, i + 1, small, ans);
                small.remove(small.size() - 1);
            }
        }

        return count;
    }

    // Leetcode 77:
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        int[] coins = new int[n];
        for (int i = 1; i <= n; i++)
            coins[i - 1] = i;
        comb(n, k, 1, small, ans);
        return ans;
    }

    public int comb(int num, int k, int idx, List<Integer> small, List<List<Integer>> ans) {

        if (k == 0) {
            List<Integer> base = new ArrayList<>(small);
            ans.add(base);
            return 1;
        }

        int count = 0;

        for (int i = idx; i <= num; i++) {
            small.add(i);
            count += comb(num, k - 1, i + 1, small, ans);
            small.remove(small.size() - 1);
        }

        return count;
    }

    // Leetcode

}
