import java.util.HashMap;

public class LongestSubstringWORC {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int n = s.length() , si = 0, ei = 0, max = 0;
        while(ei<n){
            //calculations
            char ch = s.charAt(ei);
            map.put(ch, map.getOrDefault(ch,0)+1);
            
            if(ei-si+1 == map.size()){
                max = Math.max(max,ei-si+1);
                ei++;
            }else{
                while(si<n && ei-si+1 > map.size()){
                    // remove the calculations
                    char rm = s.charAt(si);
                    map.put(rm, map.get(rm)-1);
                    
                    if(map.get(rm) == 0) map.remove(rm);
                    
                    si++;
                }
                ei++;
            }           
        }
        
        return max;
}
