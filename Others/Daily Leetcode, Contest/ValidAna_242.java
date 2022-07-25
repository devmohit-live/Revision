/**
 * ValidAna_242
 */
public class ValidAna_242 {

    public boolean isAnagram(String s, String t) {
        int n = s.length(), m = t.length();

        if (m != n)
            return false;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            // try to nullify each other
            map.put(a, map.getOrDefault(a, 0) + 1);
            map.put(b, map.getOrDefault(b, 0) - 1);

        }

        for (Character k : map.keySet())
            if (map.get(k) != 0)
                return false;

        return true;

    }
}