public class DetetctCapital520 {
    public boolean detectCapitalUse(String s) {
        int n = s.length();
        if (n == 0 || n == 1)
            return true;
        int lower = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch))
                lower++;
            else
                lower--;
        }

        // letter case: 1upper,1lower will nulligy each other, rest(n-2) lower => lower
        // = n-2, but only the first character has to be in upper case
        // ex: "ffffffffffffffffffffF" this also satisfies the n*2 condition but isn't
        // valid

        // upper case = -n, lower case = n
        if (lower == n - 2 && Character.isUpperCase(s.charAt(0)))
            return true;
        if (lower == -n || lower == n)
            return true;
        return false;
    }
}
