public class Tries {

    static class TRIE {
        private class Node {
            int isEnd;
            Node[] children;

            Node() {
                this.isEnd = 0;
                this.children = new Node[26];
            }
        }

        private Node root = null;

        TRIE() {
            this.root = new Node();
        }

        public void add(String word) {
            Node ptr = this.root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int idx = ch - 'a';

                if (ptr.children[idx] == null)
                    ptr.children[idx] = new Node();

                ptr = ptr.children[idx];
            }

            ptr.isEnd++;
        }

        // searches word in linear fasion : only 1 choice for each character present or
        // not
        public boolean isPresent(String word) {
            Node ptr = this.root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int idx = ch - 'a';

                if (ptr.children[idx] == null)
                    return false;

                ptr = ptr.children[idx];
            }

            return ptr.isEnd > 0;
        }

        // search in recursive way : useful in case of presencve of wildchrs: were we
        // can go to any of the 26 chars

        public boolean search(String word) {
            return find(word, 0, '.', this.root);
        }

        private boolean find(String word, int idx, char wildcard, Node ptr) {
            if (idx == word.length())
                return ptr.isEnd > 0;
            boolean res = false;
            char ch = word.charAt(idx);
            if (ch != wildcard) {
                return ptr.children[ch - 'a'] != null && find(word, idx + 1, wildcard, ptr.children[ch - 'a']);
            } else {
                for (int i = 0; i < 26; i++) {
                    res = res || ptr.children[i] != null && find(word, idx + 1, wildcard, ptr.children[i]);
                }
            }

            return res;
        }

        public boolean startsWith(String word) {
            Node ptr = this.root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int idx = ch - 'a';

                if (ptr.children[idx] == null)
                    return false;

                ptr = ptr.children[idx];
            }

            return true;
        }

    }

    public static void main(String[] args) {
        TRIE trie = new TRIE();
        trie.add("apple");
        trie.add("app");
        trie.add("applejuice");
        trie.add("mo");
        trie.add("mohit");
        trie.add("go");
        trie.add("golang");

        System.out.println("mohit present " + trie.isPresent("mohit"));
        System.out.println("moh present " + trie.isPresent("moh"));

        System.out.println("mohit present " + trie.search("mohit"));
        System.out.println("moh present " + trie.search("moh"));

        System.out.println("starts with mo " + trie.startsWith("mo"));
        System.out.println("starts with moh " + trie.startsWith("moh"));
        System.out.println("starts with go " + trie.startsWith("go"));

        System.out.println("contains with go " + trie.isPresent("go"));
        System.out.println("contains with gol " + trie.isPresent("gol"));
        System.out.println("contains with go " + trie.search("go"));
        System.out.println("contains with gol " + trie.search("gol"));
        System.out.println("starts with gol " + trie.startsWith("gol"));
    }
}
