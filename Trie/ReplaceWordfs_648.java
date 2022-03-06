public class ReplaceWordfs_648 {
    // approach : Make trie of dictionary
    // search words from sentence in trie: if shorter verison(to be replaced woth)
    // word is there in between the trie traversal return it(string) else if word
    // not found return null;
    // if null is returned then the same senetece word will bw added to new
    // sentence, else something else(shorter version is returned) that will be added

    class Trie {
        Trie[] children;
        String word; // act as endpoint , if not null => endpoint(with actual string ) else not an
                     // end

        Trie() {
            this.children = new Trie[26];
            this.word = null;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        for (String word : dictionary)
            insert(root, word);

        StringBuilder sb = new StringBuilder();

        for (String s : sentence.split(" ")) {
            String word = search(root, s);
            if (word == null) {
                sb.append(s + " ");
            } else {
                sb.append(word + " ");
            }
        }

        sb.deleteCharAt(sb.length() - 1); // last space

        return sb.toString();

    }

    private void insert(Trie root, String word) {
        Trie ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (ptr.children[idx] == null)
                ptr.children[idx] = new Trie();
            ptr = ptr.children[idx];
        }

        ptr.word = word; // isend
    }

    private String search(Trie root, String word) {
        Trie ptr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';

            if (ptr.children[idx] == null)
                return null; // word not in trie

            // shorter word (to be replace with) is found
            if (ptr.children[idx].word != null)
                return ptr.children[idx].word;

            ptr = ptr.children[idx];
        }

        return null;

    }
}
