import java.util.ArrayList;
import java.util.List;

public class WordSearchII {

    // made glocal bcz we have to access .isEnd , .children property of trie

    class Trie {
        Trie[] children;
        boolean isEnd;

        Trie() {
            this.isEnd = false;
            this.children = new Trie[26];
        }
    }

    int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public List<String> findWords(char[][] board, String[] words) {
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

    private void dfs(char[][] board, int sr, int sc, boolean[][] vis, StringBuilder sb, Trie ptr, List<String> ans) {
        char ch = board[sr][sc];

        ptr = ptr.children[ch - 'a']; // child exists or not
        if (ptr == null)
            return;

        vis[sr][sc] = true;
        sb.append(ch);

        if (ptr.isEnd) {
            ans.add(sb.toString());
            ptr.isEnd = false; // to avoid duplicated addition from another path
        }

        for (int[] d : dir) {
            int r = sr + d[0], c = sc + d[1];
            if (isSafe(board, vis, r, c))
                dfs(board, r, c, vis, sb, ptr, ans);
        }

        sb.deleteCharAt(sb.length() - 1);
        vis[sr][sc] = false;
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

        ptr.isEnd = true;
    }

    private boolean isSafe(char[][] board, boolean[][] vis, int r, int c) {
        return r >= 0 && c >= 0 && r < board.length && c < board[0].length && !vis[r][c];
    }

    // Optimized
    class Optimized {
        class Trie {
            Trie[] children;
            boolean isEnd;
            int freq;

            Trie() {
                this.isEnd = false;
                this.children = new Trie[26];
                this.freq = 0;
            }
        }

        int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

        public List<String> findWords(char[][] board, String[] words) {
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

}
