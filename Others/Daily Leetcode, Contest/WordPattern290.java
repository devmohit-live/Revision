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

    // Using help of Object Refrence:

    public boolean wordPattern_Ref(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;

        Map map = new HashMap<>();
        // a generic hashmap which can have any type of key and values
        // purpose: let : char or string, val => index: refrece object acting as index
        // storing the Integer(index actually : refrence of Integer object)
        // == matches refrence .equlas()=> value so we are matching refrences

        // map.put(a,b) : return previousl values of a if any else null
        for (Integer i = 0; i < words.length; i++) {
            if (map.put(pattern.charAt(i), i) != map.put(words[i], i))
                return false;
        }

        return true;
    }

    // Points to remeber:
    // Resource : https://
    // leetcode.com/problems/word-pattern/discuss/73402/8-lines-simple-Java
    /*
     * Just want to point out one thing about autoboxing. As mentioned
     * by @StefanPochmann, we can try such an example:
     * 
     * int i = 10; Integer a = i; Integer b = i; System.out.println(a == b); //guess
     * what is the output? The output was supposed to be false. However, you can
     * test this and you will find it is true. Why?
     * 
     * Because
     * "The JVM is caching Integer values. == only works for numbers between -128 and 127 "
     * Then you can try another code:
     * 
     * int i = 1000; //greater than 127 Integer a = i; Integer b = i;
     * System.out.println(a == b); //this time we got false Look, now you get false.
     * And now it explains why we can pass the small cases (because the indices are
     * in the range of -128 and 127). We also know why it cannot pass the larger
     * test case.
     */

}
