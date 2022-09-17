import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * PalindromePairs_336
 */
public class PalindromePairs_336 {

    // Brute
    public List<List<Integer>> palindromePairs(String[] words) {
        // List<List<Integer>> ans = new ArrayList<>();
        // brute(words, ans);
        // return ans;
        // return palindromePairsUsingHashMap(words);
        // return palindromePairsUsingTries(words);
        return palindromePairsUsingTries(words);
    }

    // Brute: for a string a go for all the strings try to concat them in both
    // ways(a+b, b+a) and check for plaindrome
    // Pair: O(n *n(n-1)) * ( Making a String : 2*O(m+m) + Palindrome check :
    // 2*O(m+m) )
    // let m+m =k , where m is avg length of each string,
    // O(n*n) * (2* O(2m) + 2* O(2m)) => O(n*n) * 8*O(m)

    private void brute(String[] words, List<List<Integer>> ans) {
        int n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String a = words[i] + words[j];
                String b = words[j] + words[i];
                if (isPalindrome(a))
                    ans.add(Arrays.asList(i, j));
                if (isPalindrome(b))
                    ans.add(Arrays.asList(j, i));
            }
        }
    }

    private boolean isPalindrome(String s) {
        int n = s.length(), i = 0, j = n - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    // Using HashMap : TLE in cases for long string of single chars liek
    // aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    /*
     * CASES : 1. If s1 is empty then any s2 which is palindrome can form a
     * palindrome pairs=> (blank_s1,s2), (s2,balnk_s1) 2. if s1 is reverse of s2 =>
     * s1,s2 will forrm pair => (s1,s2), (s2,s1)
     * 
     * 3. If s1[0:cut] is palidrome and there exists s2 such that revrese(s2) ==
     * s1[cut+1:len] => form a pair => (s1,s2)
     * 
     * 4. if s1[cut+1,len] is palindrome and there exists s2 such that reverse(s2)
     * == s1[0:cut] then they for pair =>(s2,s1)
     * 
     */

    public List<List<Integer>> palindromePairsUsingHashMap(String[] words) {
        if (words == null || words.length == 0) {
            return res;
        }
        // build the map save the key-val pairs: String - idx
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        // special cases: "" can be combine with any palindrome string
        if (map.containsKey("")) {
            int blankIdx = map.get("");
            for (int i = 0; i < words.length; i++) {
                if (isPalindrome(words[i])) {
                    if (i == blankIdx)
                        continue;
                    res.add(Arrays.asList(blankIdx, i));
                    res.add(Arrays.asList(i, blankIdx));
                }
            }
        }

        // find all string and reverse string pairs
        for (int i = 0; i < words.length; i++) {
            String cur_r = reverseStr(words[i]);
            if (map.containsKey(cur_r)) {
                int found = map.get(cur_r);
                if (found == i)
                    continue;
                res.add(Arrays.asList(i, found));
            }
        }

        // find the pair s1, s2 that
        // case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
        // case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            for (int cut = 1; cut < cur.length(); cut++) {
                if (isPal(cur, 0, cut - 1)) {
                    String cut_r = reverseStr(cur.substring(cut));
                    if (map.containsKey(cut_r)) {
                        int found = map.get(cut_r);
                        if (found == i)
                            continue;
                        res.add(Arrays.asList(found, i));
                    }
                }
                // if(isPalindrome(cur.substring(cut))){
                if (isPal(cur, cut, cur.length() - 1)) {
                    //
                    String cut_r = reverseStr(cur.substring(0, cut));
                    if (map.containsKey(cut_r)) {
                        int found = map.get(cut_r);
                        if (found == i)
                            continue;
                        res.add(Arrays.asList(i, found));
                    }
                }
            }
        }

        return res;
    }

    public String reverseStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    private boolean isPal(String s, int i, int j) {
        if (i > j)
            return false;
        while (i < j)
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        return true;
    }

    // Using Tries : Passed
    class TrieNode {
        public TrieNode[] next;
        public int index;
        public List<Integer> list;

        public TrieNode() {
            index = -1;
            next = new TrieNode[26];
            list = new ArrayList<>();
        }
    }

    public void addWord(TrieNode root, String word, int wordIdx) {
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (root.next[c - 'a'] == null) {
                root.next[c - 'a'] = new TrieNode();
            }

            /**
             * check and mark if part of string is a palindrome.
             **/
            if (isPal(word, 0, i))
                root.list.add(wordIdx);
            root = root.next[c - 'a'];
        }

        root.index = wordIdx;
    }

    /**
     * Search takes, O(m^2) amortized time.
     **/
    public List<List<Integer>> search(TrieNode root, String word, int wordIdx) {
        List<List<Integer>> res = new ArrayList<>();
        /**
         * Search part 1 : if the counterparts in Trie are complete words, then check if
         * the unmatched string is a palindore, if it is then w1 + w2 is a palindorome.
         **/
        for (int i = 0; i < word.length(); i++) {
            if (root.index != -1 && wordIdx != root.index && isPal(word, i, word.length() - 1)) {
                res.add(Arrays.asList(wordIdx, root.index));
            }

            char c = word.charAt(i);
            root = root.next[c - 'a'];
            if (root == null)
                return res;
        }

        /**
         * Search part 2 : if w1 (current search word) is completely matched, see if
         * there is any other word that is complete, if yes w2 is the reverse of w1 and
         * therefore w1 + w2 is a plaindrome.
         **/
        if (root.index != wordIdx && root.index != -1) {
            res.add(Arrays.asList(wordIdx, root.index));
        }

        /**
         * If w1 is completely matched and if there are any words that are larger than
         * w1 and matched completely and have the rest of the string as a palindrome
         * then w1 + w2 will be a plaindrome.
         **/
        for (Integer k : root.list) {
            res.add(Arrays.asList(wordIdx, k));
        }

        return res;
    }

    public List<List<Integer>> palindromePairsUsingTries(String[] words) {
        TrieNode root = new TrieNode();

        /**
         * addWord is invoked O(n) number of times, therefore it takes O(n * m^2) Time.
         **/
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        /**
         * Search is invoked O(n) times, it takes O(n * m^2) worse case time.
         **/
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            res.addAll(search(root, words[i], i));
        }

        return res;
    }

    // Using HashMap and TreeSet : Fastest
    public List<List<Integer>> palindromePairsUsingTreeSet(String[] words) {
        HashMap<String, Integer> wordMap = new HashMap<>();
        // Set keeps the owrd lengths in sorted order : so that we can break as soon as
        // we found the string we are trying to make pair with according to condtion 3,4
        // whose length is >= mine
        // as I am checking for substring whithin me so next string should be a prt
        // (proper subset)(length<=min)(
        // so no use of checing further strings
        // )
        Set<Integer> set = new TreeSet<>();
        int n = words.length;

        for (int i = 0; i < n; i++) {
            wordMap.put(words[i], i);
            set.add(words[i].length());
        }

        List<List<Integer>> ans = new ArrayList<>();

        // System.out.println(set);

        for (int i = 0; i < n; i++) {
            int length = words[i].length();
            // For Optimal check : reverse thing will do the work but not needed
            if (length == 1) { // as single word can only form palindrome pair with space
                // ans it is stated in question that every word in unique
                if (wordMap.containsKey("")) {
                    ans.add(Arrays.asList(i, wordMap.get("")));
                    ans.add(Arrays.asList(wordMap.get(""), i));
                }
                continue;
            }
            String reverse = new StringBuilder(words[i]).reverse().toString();
            if (wordMap.containsKey(reverse) && wordMap.get(reverse) != i)
                ans.add(Arrays.asList(i, wordMap.get(reverse)));

            for (Integer k : set) {
                // System.out.println(words[i]+" len "+length+" k "+k);
                if (k == length) {
                    // System.out.println("Breaking at above condition");
                    break;
                }

                if (isPal(reverse, 0, length - 1 - k)) {
                    String s1 = reverse.substring(length - k);
                    if (wordMap.containsKey(s1))
                        ans.add(Arrays.asList(i, wordMap.get(s1)));
                }

                if (isPal(reverse, k, length - 1)) {
                    String s2 = reverse.substring(0, k);
                    if (wordMap.containsKey(s2))
                        ans.add(Arrays.asList(wordMap.get(s2), i));
                }
            }
        }
        return ans;
    }
}