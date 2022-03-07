public class StreamOfCharacter_1032 {
    private class Trie {
        Trie[] children;
        boolean isEnd;

        Trie() {
            this.children = new Trie[26];
            this.isEnd = false;
        }
    }

    private StringBuilder stream;
    private Trie root;

    public StreamOfCharacter_1032(String[] words) {
        this.root = new Trie();
        for (String word : words)
            insert(word);
        this.stream = new StringBuilder();

    }

    private boolean find() {
        Trie ptr = this.root;
        for (int i = stream.length() - 1; i >= 0; i--) {
            char ch = stream.charAt(i);
            int idx = ch - 'a';
            if (ptr.children[idx] == null)
                return false;
            if (ptr.children[idx].isEnd)
                return true;
            ptr = ptr.children[idx];
        }

        return ptr.isEnd;
    }

    public boolean query(char letter) {
        stream.append(letter);
        return find();
    }

    private void insert(String word) {
        Trie ptr = this.root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (ptr.children[idx] == null)
                ptr.children[idx] = new Trie();

            ptr = ptr.children[idx];
        }
        ptr.isEnd = true;
    }

}
