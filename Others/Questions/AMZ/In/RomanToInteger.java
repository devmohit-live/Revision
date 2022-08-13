public class RomanToInteger {
    // LC 13 : https://leetcode.com/problems/roman-to-integer/
    public int romanToInt(String s) {
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
}
