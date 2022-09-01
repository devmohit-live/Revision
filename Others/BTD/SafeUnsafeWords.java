import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * SafeUnsafeWords
 */
public class SafeUnsafeWords {

    private static class Trie {
        Trie[] children;
        boolean isEnd;

        Trie() {
            this.isEnd = false;
            this.children = new Trie[26];
        }
    }

    private static Trie root;
    private static Trie unsafeRoot;

    public static List<Integer> checkQueries(List<String> keywords, List<String> unsafeWords, List<String> queries) {
        root = new Trie();
        unsafeRoot = new Trie();

        for (String word : keywords)
            addWord(word, root);

        for (String word : unsafeWords)
            addWord(word, unsafeRoot);

        int n = queries.size();
        List<Integer> ans = new ArrayList<>(n);
        for (String word : queries) {

            boolean isSafe = isPresent(word, root);
            boolean isUnsafe = isPresent(word, unsafeRoot);
            System.out
                    .println("Word " + word + " present is keywords : " + isSafe + " present in unsafe : " + isUnsafe);

            if (!isUnsafe && isSafe)
                ans.add(1);
            else if (!isSafe)
                ans.add(0);
            else if (isUnsafe)
                ans.add(-1);
        }

        return ans;

    }

    private static boolean isPresent(String word, Trie curr) {
        // String s = new StringBuilder(word).toString();
        // no need to reverse as we are searching suffix not prefix
        // System.out.println("Searching " + word);

        for (char ch : word.toCharArray()) {
            if (curr.children[ch - 'a'] == null)
                return false;

            curr = curr.children[ch - 'a'];
        }

        return true;
    }

    private static void addWord(String word, Trie curr) {
        // String s = new StringBuilder(word).reverse().toString();
        // System.out.println("Adding " + word);
        for (char ch : word.toCharArray()) {
            if (curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new Trie();

            curr = curr.children[ch - 'a'];
        }

        curr.isEnd = true;
    }

    public static void main(String[] args) {
        // op1: 1,0,-1,0,1
        List<String> keywords = Arrays.asList("horse", "shoe", "shore");
        List<String> unsafeWords = Arrays.asList("ham", "shut");
        List<String> queries = Arrays.asList("ho", "hall", "sh", "nam", "shore");
        System.out.println("Expected ans :  [ 1,0,-1,0,1 ] got : " + checkQueries(keywords, unsafeWords, queries));
        System.out.println();

        // op2: 1,-1,0,1,1
        List<String> keywords2 = Arrays.asList("bird", "flop", "turtule", "camping");
        List<String> unsafeWords2 = Arrays.asList("camp", "slytherin", "flipflop");
        List<String> queries2 = Arrays.asList("campi", "fl", "flip", "flop", "b");
        System.out.println("Expected ans :  [  1,-1,0,1,1 ] got : " + checkQueries(keywords2, unsafeWords2, queries2));
        System.out.println();

        // op3 : -1,1,0,1
        List<String> keywords3 = Arrays.asList("aaa", "aaaaaab", "abaa");
        List<String> unsafeWords3 = Arrays.asList("aaaa", "abb");
        List<String> queries3 = Arrays.asList("a", "aba", "abb", "aaaaa");
        System.out.println("Expected ans :  [  -1,1,0,1 ] got : " + checkQueries(keywords3, unsafeWords3, queries3));

        // -1 : should not be in unsafe words not matter it is in keywords or not
        // 1 : in keywords but not in unsafe wrods
        // 0 : not present in both

    }

}