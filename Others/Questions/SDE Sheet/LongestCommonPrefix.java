public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // Argument checks
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];

        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs); // to get the shortest and greatest words by length
        // prefix can be <= len of shortest

        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        int i = 0, n = first.length;
        while (i < n) {
            if (first[i] != last[i])
                break;
            sb.append(first[i++]);
        }

        return sb.toString();
    }
}
