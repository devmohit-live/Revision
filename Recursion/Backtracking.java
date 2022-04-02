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

    // Rat In a MAze:
    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static char[] dirs = { 'U', 'D', 'L', 'R' };

    public static ArrayList<String> findPath(int[][] m, int n) {
        if (m == null || m.length == 0 || m[0].length == 0)
            return new ArrayList<>();
        ;
        if (m[0][0] == 0 || m[n - 1][n - 1] == 0)
            return new ArrayList<>();
        ;

        ArrayList<String> ans = new ArrayList<>();
        backtrack(m, n, 0, 0, new boolean[n][n], new StringBuilder(), ans);
        return ans;
    }

    private static void backtrack(int[][] grid, int n, int sr, int sc, boolean[][] vis, StringBuilder sb,
            ArrayList<String> ans) {
        if (sr == n - 1 && sc == n - 1) {
            ans.add(sb.toString());
            return;
        }

        vis[sr][sc] = true;
        for (int i = 0; i < 4; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            char ch = dirs[i];
            if (r >= 0 && c >= 0 && r < n && c < n && !vis[r][c] && grid[r][c] != 0) {
                sb.append(ch);
                backtrack(grid, n, r, c, vis, sb, ans);
                sb.deleteCharAt(sb.length() - 1);
            }

        }
        vis[sr][sc] = false;
    }
}
