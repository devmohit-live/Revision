import java.util.ArrayList;
import java.util.List;

class WordSearchII_212 {
    // Much more optimized solution :
    // https://leetcode.com/problems/word-search-ii/solutions/59780/java-15ms-easiest-solution-100-00/?orderBy=most_votes

    // Avoids StrinBuilder, stores word itlsef in trie instead of isEnd;
    // To avoid reduplicacy we are doind isENd = false,the second approach just
    // simply does trie.word = null;

    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public List<String> findWords(char[][] board, String[] words) {
        // return new Approach1().findWords(board, words);
        return new Approach2().findWords(board, words);
    }

    class Approach1 {
        private class Trie {
            Trie[] children;
            boolean isEnd;
            int freq;

            Trie() {
                this.isEnd = false;
                this.children = new Trie[26];
                this.freq = 0;
            }
        }

        private List<String> findWords(char[][] board, String[] words) {
            Trie root = new Trie();
            List<String> ans = new ArrayList<>();

            if (board.length == 0 || board[0].length == 0)
                return ans;

            int n = board.length, m = board[0].length;
            boolean[][] vis = new boolean[n][m];
            // add dictinoray woprds into trie
            for (String word : words)
                insert(root, word);

            // search for words
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    Trie tmp = root;
                    if (!vis[i][j])
                        dfs(board, i, j, vis, new StringBuilder(), tmp, ans);
                }
            }

            return ans;
        }

        private int dfs(char[][] board, int sr, int sc, boolean[][] vis, StringBuilder sb, Trie ptr, List<String> ans) {
            char ch = board[sr][sc];
            Trie prev = ptr;
            ptr = ptr.children[ch - 'a']; // child exists or not
            if (ptr == null || ptr.freq == 0)
                return 0;

            vis[sr][sc] = true;
            sb.append(ch);
            int count = 0;

            if (ptr.isEnd) {
                ans.add(sb.toString());
                ptr.isEnd = false; // to avoid duplicated addition from another path
                count++;

            }

            for (int[] d : dir) {
                int r = sr + d[0], c = sc + d[1];
                if (isSafe(board, vis, r, c))
                    count += dfs(board, r, c, vis, sb, ptr, ans);
            }
            sb.deleteCharAt(sb.length() - 1);
            vis[sr][sc] = false;
            ptr.freq -= count;
            return count;
        }

        private void insert(Trie root, String word) {
            Trie ptr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int idx = ch - 'a';

                if (ptr.children[idx] == null)
                    ptr.children[idx] = new Trie();
                ptr.freq++;
                ptr = ptr.children[idx];
            }

            ptr.isEnd = true;
            ptr.freq++;
        }

        private boolean isSafe(char[][] board, boolean[][] vis, int r, int c) {
            return r >= 0 && c >= 0 && r < board.length && c < board[0].length && !vis[r][c];
        }
    }

    class Approach2 {
        private List<String> findWords(char[][] board, String[] words) {
            List<String> res = new ArrayList<>();
            TrieNode root = buildTrie(words);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs(board, i, j, root, res);
                }
            }
            return res;
        }

        private void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
            char c = board[i][j];
            if (c == '#' || p.next[c - 'a'] == null)
                return;
            p = p.next[c - 'a'];
            if (p.word != null) { // found one
                res.add(p.word);
                p.word = null; // de-duplicate
            }

            board[i][j] = '#';
            if (i > 0)
                dfs(board, i - 1, j, p, res);
            if (j > 0)
                dfs(board, i, j - 1, p, res);
            if (i < board.length - 1)
                dfs(board, i + 1, j, p, res);
            if (j < board[0].length - 1)
                dfs(board, i, j + 1, p, res);
            board[i][j] = c;
        }

        private TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();
            for (String w : words) {
                TrieNode p = root;
                for (char c : w.toCharArray()) {
                    int i = c - 'a';
                    if (p.next[i] == null)
                        p.next[i] = new TrieNode();
                    p = p.next[i];
                }
                p.word = w;
            }
            return root;
        }

        private class TrieNode {
            TrieNode[] next = new TrieNode[26];
            String word;
        }
    }

}