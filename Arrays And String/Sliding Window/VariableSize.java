public class VariableSize {

    // Lengfth of longest subarray with sun = k;

    // Pattern

    public int maxLength(int[] arr, int sum) {
        int n = arr.length, si = 0, ei = 0, max = -1, ssf = 0;

        while (ei < n) {
            // operation
            sum += arr[ei];
            // less than the given condition
            if (ssf < sum) {
                // keep on increasing the window
                ei++;
            }
            // equals
            else if (ssf == sum) {
                // update the ans
                max = Math.max(max, ei - si + 1);
            }
            // greater
            else if (ssf > sum) {
                while (si < n && ssf > sum) {
                    // remove the opeartion
                    sum -= arr[si];
                    si++;
                }
                ei++; // remember to do so;
            }

        }
        return max;
    }

    // this approach works only with +ve numbers if array contains negative numbers
    // we may have to use kdane

    // Longest K unique character substring
    public int longestkSubstr(String s, int k) {
        int n = s.length(), si = 0, ei = 0, max = -1;
        int[] freq = new int[26];
        while (ei < n) {
            // operations
            char a = s.charAt(ei);
            freq[a - 'a']++;
            int count = getUniqueCount(freq);

            if (count < k) {
                ei++;
            } else if (count == k) {
                max = Math.max(max, ei - si + 1);
                ei++;
            } else if (count > k) {
                while (si < n && count > k) {
                    char b = s.charAt(si);
                    freq[b - 'a']--;
                    if (freq[b - 'a'] == 0)
                        count--;
                    si++;
                }
                ei++;
            }

        }

        return max;

    }

    // we can use map too so that map.size can be use to set the unique chaarcters
    // in string map.size == k
    // instead of checking in freq
    // also we are doing ei++ every time , so we can put after evry condition
    // instead of wrting it everytime

    // constant time => 26
    private int getUniqueCount(int[] freq) {
        int count = 0;
        for (int el : freq)
            if (el > 0)
                count++;

        return count;

    }

    // Leetcode 3: Longest Substring Without Repeating Characters
    // 3. Without Repeating Characters == all unique characters
    // all unique characters means that the length of current window (ei-si+1) must
    // contains characters whose freq == 1
    // ie it it similar to previos questions here just the K is also
    // variable(current window size)

    public int lengthOfLongestSubstringUsingFreq(String s) {
        int n = s.length(), si = 0, ei = 0, max = 0;
        // count represents no of unique entries in freq == map.size()
        int[] freq = new int[128];
        // sapce ans special chars are also included in string
        // '' represents null having ascii value as 0
        // so given the constraits better to use HashMap

        while (ei < n) {
            // op
            char ch = s.charAt(ei);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            freq[ch - 0]++;

            int count = countPresent(freq);

            // if(map.size() == ei -si +1){
            if (count == ei - si + 1) {
                // ans
                max = Math.max(max, ei - si + 1);
                ei++;
            } else {

                // while(si<n && map.size() < ei - si +1){
                while (si < n && count < ei - si + 1) {
                    char rm = s.charAt(si);
                    // map.put(rm,map.get(rm)-1);
                    freq[rm - 0]--;

                    // if(map.get(rm) == 0) map.remove(rm);
                    if (freq[rm - 0] == 0)
                        count--;

                    si++;
                }
                ei++;
            }

        }
        return max;

    }

    private int countPresent(int[] freq) {
        int count = 0;
        // return count of character who are present in array it freq>0
        for (int el : freq)
            if (el > 0)
                count++;

        return count;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), si = 0, ei = 0, max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (ei < n) {
            // if(ei-si+1 < map.size()) ei++ redundant => won't happen

            // calculations
            char ch = s.charAt(ei);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            // conditions
            if (ei - si + 1 == map.size()) {
                // ans
                max = Math.max(max, ei - si + 1);
                ei++;
            } else {
                while (si < n && map.size() < ei - si + 1) {
                    char to_remove = s.charAt(si);
                    map.put(to_remove, map.get(to_remove) - 1);
                    if (map.get(to_remove) == 0)
                        map.remove(to_remove);
                    si++;
                }
                ei++;
            }
        }

        return max;
    }

    private int lengthOfLongestSubstringUsingSet(String s) {
        HashSet<Character> set = new HashSet<>();

        int si = 0, ei = 0, n = s.length(), max = 0;

        while (ei < n) {
            char ch = s.charAt(ei);
            if (!set.contains(ch)) {
                // we are increasing the unique characters
                set.add(ch);
                max = Math.max(max, set.size());
                ei++;
            } else {
                // it was a duplicate characters, we eill decrease the window size
                // remove from the set
                char to_remove = s.charAt(si);
                set.remove(to_remove);
                si++;
            }

        }

        return max;
    }

    // Pick Toy
    /*
     * John is at a toy store help him pick maximum number of toys. He can only
     * select in a continuous manner and he can select only two types of toys.
     * 
     * 
     * Example: Input: abaccab => exactly largest substring with no of unique
     * character = k , here k =2
     * 
     */

    // Leetcode 76 : Minimum Window Substring
    public String minWindow(String s, String t) {
        int n = s.length(), si = 0, ei = 0, min = Integer.MAX_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        if (n < t.length())
            return "";
        if (s.equals(t))
            return t;

        for (char ch : t.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        int start = -1, end = -1, count = map.size();
        // calculation will only be made for character presemt in t
        while (ei < n) {
            // calculation
            char ch = s.charAt(ei);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0)
                    count--;
            }

            // condition
            if (count > 0)
                ei++;
            else if (count == 0) {
                // ans
                if (min > ei - si + 1) {
                    min = ei - si + 1;
                    start = si;
                    end = ei;
                }
                // try to get smaller window(removeing extra chars( greater > req) of )
                while (count == 0) {
                    // reverse calculation
                    char rm = s.charAt(si);
                    if (map.containsKey(rm) && map.get(rm) <= 0) {
                        map.put(rm, map.get(rm) + 1);
                        if (map.get(rm) > 0)
                            count++;
                    }
                    si++; // everytime even if char was in t or not as we have to move the window

                    // check for ans again
                    if (min > ei - si + 1 && count == 0) {
                        min = ei - si + 1;
                        start = si;
                        end = ei;
                    }
                }
                ei++;
            }
        }

        if (start == -1 || end == -1)
            return "";
        for (int i = start; i <= end; i++)
            sb.append(s.charAt(i));

        return sb.toString();
    }

}
