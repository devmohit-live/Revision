// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        String s = "pqpqs";
        int k = 2;
        int ans = exact_k_chars(s,k);
        System.out.println(ans);
    }
    
// the number of subarrays with at most K distinct elements
static int most_k_chars(String s, int k) {
    if (s.length() == 0) {
        return 0;
    }
   HashMap<Character, Integer> map = new HashMap<>();
    int num = 0, left = 0;
    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        map.put(ch,map.getOrDefault(ch,0)+1);
        while (map.size() > k) {
            char l = s.charAt(left);
            map.put(l,map.get(l)-1);
            if (map.get(l) == 0) {
                map.remove(l);
            }
            left++;
        }
        num += i - left + 1;
    }
    return num;
    }
        
static int exact_k_chars(String s, int k) {
    return most_k_chars(s, k) - most_k_chars(s, k - 1);
}


// Second way like lc 990:


}