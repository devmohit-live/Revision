public class Backtracking {

    // LC 22.Generate Parentheses

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0)
            return ans;

        addBrackets(0, 0, n, new StringBuilder(), ans);
        return ans;
    }

    private void addBrackets(int opening, int closing, int n, StringBuilder sb, List<String> ans) {
        if (opening == n && closing == n) {
            ans.add(sb.toString());
            return;
        }

        // we have not exhausted all the opening brackets
        // and we should start with adding opening first as adding closing may lead to
        // invalid
        // ex: )

        if (opening < n) {
            sb.append("(");
            addBrackets(opening + 1, closing, n, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }

        // there must be an opening brackets present to support the closing one
        // ex: ( for ) , '('(( ) for ')', that is apart from balanced brackets ther must
        // be atleast +1 op for closing
        if (opening > closing) {
            sb.append(")");
            addBrackets(opening, closing + 1, n, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 46 Permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return ans;
        permsLimited(nums, new ArrayList<>(), ans, new boolean[nums.length]);
        return ans;
    }

    private void permsLimited(int[] nums, List<Integer> small, List<List<Integer>> ans, boolean[] vis) {

        // make sure all the elemets are added into tmp ans
        // or we can also take an idx var which check how may items are being added idx
        // == arr.length
        if (small.size() == nums.length) {
            ans.add(new ArrayList<>(small));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (vis[i])
                continue;

            vis[i] = true;
            small.add(nums[i]);
            permsLimited(nums, small, ans, vis);
            small.remove(small.size() - 1);
            vis[i] = false;
        }
    }

    // 47 : Permutaions II
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return ans;
        Arrays.sort(nums);
        permsLimited2(nums, new ArrayList<>(), ans, new boolean[nums.length]);
        return ans;

    }

    private void permsLimited2(int[] nums, List<Integer> small, List<List<Integer>> ans, boolean[] vis) {

        // make sure all the elemets are added into tmp ans
        // or we can also take an idx var which check how may items are being added idx
        // == arr.length
        if (small.size() == nums.length) {
            ans.add(new ArrayList<>(small));
            return;
        }

        int prev = -1000;
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (prev == nums[i]))
                continue;

            vis[i] = true;
            small.add(nums[i]);
            prev = nums[i];
            permsLimited(nums, small, ans, vis);
            small.remove(small.size() - 1);
            vis[i] = false;
        }
    }
}
