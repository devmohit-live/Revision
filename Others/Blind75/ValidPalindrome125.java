public class ValidPalindrome125 {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) { // digits needed to be added too
                c = Character.toLowerCase(c);
                sb.append(c);
            }

        }
        // System.out.println(sb.toString());
        return isPal(sb.toString());
    }

    private boolean isPal(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }
}
