import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagrams _438{

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = p.length();
        int si = 0, ei = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : p.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);

        int count = map.size();

        while (ei < n) {
            char ch = s.charAt(ei);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0)
                    count--;
            }

            if (ei - si + 1 == m) {
                if (count == 0)
                    ans.add(si);
                // reverse calculatiom
                char rm = s.charAt(si);
                if (map.containsKey(rm)) {
                    map.put(rm, map.get(rm) + 1);
                    if (map.get(rm) == 1)
                        count++;
                }
                si++;
            }
            ei++;
        }
        return ans;
    }
    // or take a count array that determines the no of entries in map and when any
    // entry get's 0 decrease the count, when any entry goes to 10 from 0 increase
    // the count: motive: make all entries disappers : count == 0;

    // private boolean isValid(Map<Character,Integer> map){
    // for(int v: map.values()) if( v != 0) return false;
    // return true;
    // }

}
