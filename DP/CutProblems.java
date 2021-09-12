public class CutProblems {
    // Leetcode 139 : WordBreak
    
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int maxwordlen = 0, n = s.length();
        boolean[] dp = new boolean[n + 1]; // is word till i possible

        for (String ss : wordDict) {
            maxwordlen = Math.max(ss.length(), maxwordlen);
            set.add(ss);
        }

        return wordBreak(s, set, dp, n, maxwordlen);

    }

    boolean wordBreak(String s, HashSet<String> set, boolean[] dp, int n, int maxlen) {

        dp[0] = true; // "" string

        for (int i = 0; i <= n; i++) {
            // move forward if we found false;
            if (!dp[i])
                continue;

            // for true we have to check different possibilites of making a word seperation
            // : finding the end point index upto which word can be seperated

            // check for the words=>length=[1,maxlen]
            for (int l = 1; l <= maxlen && l + i <= n; l++) {
                String substring = s.substring(i, l + i);
                if (set.contains(substring))
                    dp[l + i] = true;
            }

        }
        // System.out.println(set); System.out.println(Arrays.toString(dp));

        return dp[n];
    }

}
