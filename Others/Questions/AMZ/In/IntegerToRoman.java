public class IntegerToRoman {
    //lc 12
    public String intToRoman(int num) {
        // decreasing order to decrement highest val first
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (num > 0) {
            while (num >= values[idx]) {
                num -= values[idx];
                sb.append(symbols[idx]);
            }
            if (num > 0)
                idx++;
        }
        return sb.toString();
    }
}
