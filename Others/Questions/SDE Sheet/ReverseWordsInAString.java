public class ReverseWordsInAString {
    // lc151
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = n - 1; // to get each word
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                i--;
                continue;
            }
            ;
            int j = i - 1; // to get each char of a specific word
            while (j >= 0 && s.charAt(j) != ' ')
                j--;
            sb.append(" ");
            sb.append(s.substring(j + 1, i + 1)); // add the trimmed word
            i = j - 1;
        }
        if (sb.length() > 0)
            sb.deleteCharAt(0);
        return sb.toString();
    }
}
