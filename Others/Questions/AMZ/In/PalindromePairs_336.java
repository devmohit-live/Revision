import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PalindromePairs_336 {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        brute(words, ans);
        return ans;
    }

    // Brute: for a string a go for all the strings try to concat them in both
    // ways(a+b, b+a) and check for plaindrome
    // Pair: O(n *n(n-1)) + Making a String : 2*O(n+m) ) * Palindrome check :
    // 2*O(m+n)
    // (n*n + n+m) * (m+n) => (n*n*k + k*k) whenre k: m+n nearly =>
    // TIme: (m+n)^3

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

    // Optimal : Using Tries
    class Trie {
        Trie[] children;
        int endIdx; // end of word
        // -1 represents not ened , other values representrs eneded(idx of this word in
        // given input array)
        List<Integer> palindromicPartsIdx; // represents the palindromic suffix part of actual strings
        // that is at end of the words which parts from an end are palindromic (tier
        // startung idxes)(as these parts can acts a middle of palindomic substrins)

        Trie() {
            this.children = new Trie[26];
            this.endIdx = -1;
            this.palindromicPartsIdx = new ArrayList<>();
        }

    }

    // Explanation
    {
        // Case 1 : reverse of the words is present in array ex: abcd, dcba => they will
        // form an palindrome
        // Case 2 : prefix and suffix of two words are palindromic then thier mid should
        // (on addition ) fmust form palindroe :
        // lls, sssll => combining them -> llssssll => mid 4 s's are palidromic and lls
        // with suffix of sssll (ssl) forms a palindrome

        // Case 3: single chars palindrome : ' aaaa or mam' => in this checking in trie
        // and adding may cause problem bcz they and their reverse is same so we should
        // check if the current string idx(from array) is not equal to the trie's
        // idx(endIdx) => basically not the same words(idx wise)

    }

    Trie root;
    List<List<Integer>> res;

    public List<List<Integer>> palindromePairs(String[] words) {
        res = new ArrayList<>();
        int n = words.length;
        root = new Trie();

        for (int i = 0; i < n; i++) {
            addWords(words[i], i);
        }

        for (int i = 0; i < n; i++) {
            search(words[i], i);
        }

        return res;
    }

    private void addWords(String word, int index) {
        Trie curr = root;
        // add in reverse order
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';
            if (curr.children[j] == null)
                curr.children[j] = new Trie();

            // add plaindromic parts idx
            if (isPalindrome(word, 0, i))
                curr.palindromicPartsIdx.add(index);

            curr = curr.children[j];
        }
        curr.endIdx = index;
        curr.palindromicPartsIdx.add(index); // why adding at end too : for case 1 (exact reverse )
    }

    private boolean isPalindrome(String word, int si, int ei) {
        while (si < ei)
            if (word.charAt(si++) != word.charAt(ei--))
                return false;

        return true;
    }

    private void search(String word, int index) {
        Trie curr = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            {
                // case 2 and 3
                // curr.endIdx !=-1 :if this is a end of any word(smaller word as a prefix of
                // buig word)(normal trie condition)
                // curr.endIdx != index : not the same word : case 3
                // current's word's part is palindrmoic with this much part
            }
            if (curr.endIdx >= 0 && curr.endIdx != index && isPalindrome(word, i, n - 1))
                res.add(Arrays.asList(index, curr.endIdx));

            curr = curr.children[word.charAt(i) - 'a'];

            if (curr == null)
                return;
        }

        // case 1:
        for (int i : curr.palindromicPartsIdx) {
            if (i == index)
                continue;

            res.add(Arrays.asList(index, i));
        }

    }

}