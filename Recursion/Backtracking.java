import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // Factor Combination :
    // https://www.lintcode.com/problem/factor-combinations/description

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(n, 2, new ArrayList<>(), ans);
        ans.remove(ans.size() - 1);
        // or checking in the recursion base case that
        // small.size(()) >1
        return ans;
    }

    private int solve(int n, int sp, List<Integer> small, List<List<Integer>> ans) {
        if (n == 1) {
            ans.add(new ArrayList<>(small));
            return 1;
        }

        int count = 0;
        for (int i = sp; i <= n; i++) {
            if (n % i == 0) {
                small.add(i);
                count += solve(n / i, i, small, ans);
                small.remove(small.size() - 1);
            }
        }

        return count;
    }

//Special Matrix : https://practice.geeksforgeeks.org/problems/special-matrix4201/1#
  int[][] dir = { {0,1} , {1,0} };
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        final int MOD = (int)1e9 +7;
        
        int[][] grid = new int[n+1][m+1];// 1 based indexing
        for(int[] b: blocked_cells){
            int i = b[0], j = b[1];
            // System.out.println(i+" "+j);
            if(i == 1 && j== 1 ) return 0; // 1 based indexing
            if(i==n && j==m) return 0;
            grid[i][j] = 1;//blocked
           
        }
        
        Long[][] dp = new Long[n+1][m+1];// 1 based indexing
        return (int)(solve(grid, 1,1,n,m, dp)%MOD);
    }
    
    private long solve(int[][] grid, int sr, int sc, int n,int m,Long[][] dp){
        if(sr == n && sc == m) return dp[sr][sc] = 1l;
        
        if(dp[sr][sc] != null) return dp[sr][sc];
        
        long count = 0;
        for(int[] d: dir){
            int r = sr + d[0], c= sc + d[1];
            if(r>0 && c>0 && r<=n && c<=m && grid[r][c] != 1){
                count+=solve(grid,r,c,n,m,dp);
            }
        }
        return dp[sr][sc] = count;
    }

    //Palindrome partitioing Letcode(Different from MCM and Knapsack) :131

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        // brute(s,ans);
        // brute2(s);
        solve(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    // NOT WORK:
    // not mormal substring and pal check
    // as b is occuring in two child lists
    // //Brute:
    // private void brute(String s,List<List<String>> ans){
    // int n = s.length();
    // for(int len=0;len<n;len++){
    // List<String> small = new ArrayList<>();
    // for(int j=0;j<n;j++){
    // if( isPalindrome(s,j,j+len) )
    // small.add(s.substring(j,j+len+1));
    // }

    // ans.add(small);
    // }
    // }

    // private void brute2(String s){
    // List<String> ans = new ArrayList<>();
    // int n = s.length();
    // for(int len=0;len<n;len++){
    // for(int j=0;j<n;j++){
    // if( isPalindrome(s,j,j+len) )
    // ans.add(s.substring(j,j+len+1));
    // }
    // }
    // System.out.println(ans.size() + " " +ans);
    // }

    private boolean isPalindrome(String s, int i, int j) {
        if (j >= s.length())
            return false;

        while (i < j)
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        return true;
    }

    // combinations => ncr
    // time: n(pal check) * 2^n
    // space : O(n)

    private void solve(String s, int idx, List<String> small, List<List<String>> ans) {

        if (idx == s.length()) {
            ans.add(new ArrayList<>(small));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            if (isPalindrome(s, idx, i)) {
                small.add(s.substring(idx, i + 1));
                solve(s, i + 1, small, ans);
                small.remove(small.size() - 1);
            }
        }

    }

    // 784. Letter Case Permutation
    
    public List<String> letterCasePermutation(String s) {
        if (s == null || s.length() == 0)
            return new ArrayList<String>();
        s = s.toLowerCase();
        // System.out.println(s);
        List<String> ans = new ArrayList<>();
        solve(s.toCharArray(), 0, new StringBuilder(), ans);
        return ans;

    }

    private void solve(char[] arr, int idx, StringBuilder sb, List<String> ans) {

        if (idx == arr.length) {
            ans.add(sb.toString());
            return;
        }

        char ch = arr[idx];
        if (Character.isDigit(ch)) {
            // 1 choice add itself and move forward
            sb.append(ch);
            solve(arr, idx + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        } else {

            // 2 choice add itseld in lowers and upper case
            sb.append(ch);
            solve(arr, idx + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);

            ch = Character.toUpperCase(ch);

            sb.append(ch);
            solve(arr, idx + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }

    }


}
