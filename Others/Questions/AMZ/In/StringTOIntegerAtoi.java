public class StringTOIntegerAtoi {
    // lc
    public int myAtoi(String s) {
        final int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;
        int n = s.length(), idx = 0, sign = 1;
        long ans = 0;

        if (n == 0)
            return 0;
        // remove white spaces : Point 1
        while (idx < n && s.charAt(idx) == ' ')
            idx++;
        if (idx == n)
            return 0;

        // get the sign: Point 2
        if (s.charAt(idx) == '+' || s.charAt(idx) == '-') {
            sign = s.charAt(idx) == '+' ? 1 : -1;
            idx++;
        }

        // stop at first not digit char ir decimal : point 3
        while (idx < n && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
            ans = ans * 10 + (s.charAt(idx) - '0');

            // Point 5: no cyclic transformation should be done from max to min or
            // vice-versa
            if (ans > MAX)
                return (sign == 1) ? MAX : MIN;

            idx++;
        }

        ans = ans * sign;
        return (int) ans;
    }
}
