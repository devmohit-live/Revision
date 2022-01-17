import java.util.HashMap;
import java.util.Map;

public class WordPattern290 {
    // Leetcode 290
    public boolean wordPattern(String pattern, String s) {
        // 2 map to insure 1:1 mapping from both sides
        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();
        String[] words = s.split(" ");
        int n = pattern.length(), m = words.length;
        if (n != m)
            return false;

        for (int i = 0; i < n; i++) {
            char ch = pattern.charAt(i);
            String word = words[i];
            if (!map.containsKey(ch))
                map.put(ch, word);
            else {
                String val = map.get(ch);
                if (!val.equals(word))
                    return false;
            }

            if (!map2.containsKey(word))
                map2.put(word, ch);
            else {
                char val = map2.get(word);
                if (val != ch)
                    return false;
            }
        }

        return true;
    }


}
