public class RomanToInteger {
    // LC 13 : https://leetcode.com/problems/roman-to-integer/
    public int romanToInt(String s) {

        // bASICALLY we start from end to check satisfiability wheter to add the current
        // roman to asns or substract

        // ex: VII => adding 1 +1 => 2 to 5
        // IX => here we have to subsract 1 from 10

        // i am a i+1 th pos and ith char is < me then substraction
        // else addition

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int val = 0;
        char prev = 'A';
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (map.get(ch) >= map.getOrDefault(prev, 0))
                val += map.get(ch);
            else
                val -= map.get(ch);

            prev = ch;
        }

        return val;
    }

    // Integer to Roman
    public String intToRoman(int num) {
        int[] val = { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
        String[] roman = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" }; // dm=>500 and d =
                                                                                                    // 500(already
                                                                                                    // there)
        // cm = 900 => not there so cm will be there instead of dm,
        // Similraly XC => 40, lC=> 50 , L = 50 already exists

        int cp = num;
        StringBuilder sb = new StringBuilder();
        int idx = roman.length - 1;
        while (num > 0) {
            while (num >= val[idx]) {
                sb.append(roman[idx]);
                num -= val[idx];
            }

            idx--;
        }
        return sb.toString();
    }
}
