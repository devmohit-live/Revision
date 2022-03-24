package Others.Questions.AMZ.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams_49 {
    // Approach: sort all the strings and make a map to ref stringInSortedeOrder:
    // arrayListContainsing Original str

    // Approahc1 : m*nlogn (m => no of strings, n = size of each string)
    public List<List<String>> groupAnagrams_ap1(String[] strs) {
        List<List<String>> ans = new ArrayList<>();

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String s : strs) { // m
            char[] arr = s.toCharArray(); // n
            Arrays.sort(arr); // nlogn
            String tmp = new String(arr); // n
            map.putIfAbsent(tmp, new ArrayList<>());
            map.get(tmp).add(s); // n
        }
        for (List<String> ar : map.values())
            ans.add(ar); // k

        return ans;
    }

    // Approach 2 : without sorting the string for map's key
    // Use freq of chars and make a string out of it (nlogn => n)

    // m*n
    public List<List<String>> groupAnagramsBetter(String[] strs) {

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String s : strs) { // m
            char[] freq = new char[26]; // have to be of char type
            for (char ch : s.toCharArray()) // n
                freq[ch - 'a']++;
            String key = String.valueOf(freq); // n
            // or new String(freq)
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s); // adding the actual string

        }

        return new ArrayList<>(map.values());
    }
}
