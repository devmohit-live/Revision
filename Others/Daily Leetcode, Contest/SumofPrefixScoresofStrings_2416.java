public class SumofPrefixScoresofStrings_2416 {
    private class Trie {
        Trie[] children;
        int prefix;
        int isEnd;

        Trie() {
            this.children = new Trie[26];
            this.prefix = 0;
            this.isEnd = 0;
        }
    }

    private Trie root;

    public int[] sumPrefixScores(String[] words) {
        this.root = new Trie();
        int n = words.length, i = 0;
        for (String word : words)
            addWord(word);

        int[] ans = new int[n];
        for (String word : words)
            ans[i++] = findPrefix(word);

        return ans;

    }

    private void addWord(String word) {
        Trie curr = this.root;
        for (char ch : word.toCharArray()) {
            if (curr.children[ch - 'a'] == null)
                curr.children[ch - 'a'] = new Trie();

            curr = curr.children[ch - 'a'];
            curr.prefix++;
        }
        curr.isEnd++;
    }

    private int findPrefix(String word) {
        Trie curr = this.root;
        int ans = 0;
        for (char ch : word.toCharArray()) {
            if (curr.children[ch - 'a'] == null)
                return -1;
            ans += curr.prefix;
            curr = curr.children[ch - 'a'];
        }
        ans += curr.prefix;
        return ans;
    }
}
