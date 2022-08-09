public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        StringBuilder sb = new StringBuilder();
        int n = strs.length;
        // longest common prefix can be of max len : min(strs.length)
        // prefix is needed
        int maxLen = Integer.MAX_VALUE;
        for (String s : strs)
            maxLen = Math.min(maxLen, s.length());

        for (int i = 0; i < maxLen; i++) {
            if (strs[0].charAt(i) != strs[n - 1].charAt(i))
                break;
            sb.append(strs[0].charAt(i));
        }

        return sb.toString();
    }
}
