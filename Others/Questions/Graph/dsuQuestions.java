package Questions.Graph;

import java.util.ArrayList;
import java.util.List;

public class dsuQuestions {
    // 695 : Max Area of Islands:
    int[] parent, size;
    int[][] dir = { { 0, 1 }, { 1, 0 } };

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        if (n == 0)
            return 0;

        int m = grid[0].length, max = 0, count = 0;
        parent = new int[m * n];
        size = new int[m * n];
        // set parent, size : intialise

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    count++;
                parent[i * m + j] = i * m + j;
                size[i * m + j] = 1;
            }
        }

        // union
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int p1 = findParent(i * m + j);

                    for (int[] d : dir) {
                        int r = i + d[0], c = j + d[1];
                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                            int p2 = findParent(r * m + c);
                            // merge : union
                            if (p1 != p2) {
                                parent[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
                    }

                    max = Math.max(max, size[p1]);
                }

            }
        }
        // No of components
        int comp = 0;
        for (int i = 0; i < m * n; i++) {
            if (i == parent[i] && grid[i / m][i % m] == 1)
                comp++;
        }
        System.out.println("No of components are " + comp);
        return max;
    }

    private int findParent(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = findParent(parent[x]);
    }

    // Leetcode 990 : Satisfiability of Equality Equations
    public boolean equationsPossible(String[] equations) {
        // initilization
        int[] parent = new int[26];
        // int[] size = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
            // size[] = 1;
        }

        List<String> notEqual = new ArrayList<>();
        // perform all == => union op first
        // then start cheking for != conditions

        boolean res = true;
        for (String eq : equations) {
            char a = eq.charAt(0);
            char b = eq.charAt(eq.length() - 1);
            char condition = eq.charAt(1);
            int p1 = findParent(parent, a - 'a');
            int p2 = findParent(parent, b - 'a');

            if (condition == '=') {
                // union
                parent[p2] = p1;
            } else {
                notEqual.add(eq);
            }

        }

        for (String s : notEqual) {
            char a = s.charAt(0);
            char b = s.charAt(s.length() - 1);
            int p1 = findParent(parent, a - 'a');
            int p2 = findParent(parent, b - 'a');
            if (p1 == p2)
                return false;
        }

        return res;
    }

    private int findParent(int[] parent, int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = findParent(parent, parent[x]);
    }

}