package Recursion;

import java.util.*;

public class Permutations {
    // leetcode 46
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        premutation(nums, 0, ans, res);

        return res;
    }

    public static void premutation(int[] nums, int count, List<Integer> ans, List<List<Integer>> res) {
        // can't do res.add(ans)-> have to create new arrlist for every ans
        // deep copy -> bcz ans aray keeps on changing after evry call and adding
        // regrence will destory all the inbetween results -< at last euler end => []

        if (count == nums.length) {
            ArrayList<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // marking i, unmarking -> either boolean array of inplace using constraint
            if (nums[i] > -100) {

                int val = nums[i];
                nums[i] = -100;
                ans.add(val); // -> adding ch

                premutation(nums, count + 1, ans, res);

                ans.remove(ans.size() - 1); // -> removing ch
                nums[i] = val;
            }

        }

    }

    // leetcode 47
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        premutation(nums, 0, ans, res);

        return res;
    }

    public static void premutationUn(int[] nums, int count, List<Integer> ans, List<List<Integer>> res) {
        if (count == nums.length) {
            ArrayList<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }

        int prev = -100;

        for (int i = 0; i < nums.length; i++) {
            // marking i, unmarking -> either boolean array of inplace using constraint
            if (nums[i] > -100) {

                int val = nums[i];
                nums[i] = -100;
                ans.add(val); // -> adding ch
                if (prev != val) {
                    premutation(nums, count + 1, ans, res);
                    prev = val;
                }

                ans.remove(ans.size() - 1); // -> removing ch
                nums[i] = val;
            }

        }

    }

}
