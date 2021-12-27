package Questions.Graph;

import java.util.ArrayList;
import java.util.Collections;
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

    // Leetcode 1061 : Lexicographically Smallest Equivalent String
    public String smallestEquivalentString(String s1, String s2, String base) {
        parent = new int[26];
        for (int i = 0; i < 26; i++)
            parent[i] = i;
        // s1 -> mapped to s2
        int n = s1.length(); // bot are of same length

        // mapping
        for (int i = 0; i < n; i++) {
            char x = s1.charAt(i), y = s2.charAt(i);
            union(x, y);
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : base.toCharArray()) {
            int representative = findParent(ch - 'a');
            sb.append((char) (representative + 'a'));
        }
        return sb.toString();
    }

    private void union(char x, char y) {
        int a = findParent(x - 'a');
        int b = findParent(y - 'a');
        if (a == b)
            return;
        else if (x < y) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    // Leetcode 839 : Similar String Groups
    public int numSimilarGroups(String[] strs) {
        int n = strs.length, groups = n;
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                String a = strs[i], b = strs[j];
                if (areSimilar(a, b)) {
                    int p1 = findParent(i), p2 = findParent(j);
                    if (p1 != p2) {
                        // merge them
                        parent[p2] = p1; // put them in common set
                        // decrease the groups count;
                        groups--;
                    }
                }
            }
        }

        return groups;
    }

    // 2 elemts are not equal => 1 swap needed => similar
    private boolean areSimilar(String a, String b) {
        if (a.length() != b.length())
            return false;
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
            if (count > 2)
                return false;
        }

        return true;
    }

    // Number of Islands II : https://www.lintcode.com/problem/434/
    int[][] mat;

    // Time: O(m*n + k), Space: O(m*n);
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // intitalize
        mat = new int[n][m];
        List<Integer> res = new ArrayList<>();
        if (n <= 1 && m <= 1)
            return res;
        parent = new int[m * n];

        // O(m*n)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                parent[i * m + j] = i * m + j;
            }
        }

        //
        int comp = 0;
        // O(k) k: points array length
        for (Point p : operators) {
            int sr = p.x, sc = p.y;

            if (mat[sr][sc] == 1) { // overriding a land,: there was already a land present hence no chages will be
                                    // made
                res.add(comp);
                continue;
            }

            mat[sr][sc] = 1;
            comp++; // increase in a componenet

            // if it will become a part of some componenet
            for (int[] d : dir) { // O(4): O(1)
                // O(1)
                int r = sr + d[0];
                int c = sc + d[1];
                int p1 = findParent(sr * m + sc); // O(1) with path compression
                if (r >= 0 && c >= 0 && r < n && c < m && mat[r][c] == 1) {
                    // find parent and merge
                    int p2 = findParent(r * m + c);
                    if (p1 != p2) {
                        comp--; // merged hence decrease in a componenet
                        parent[p2] = p1; // O(1)
                    }
                }
            }

            res.add(comp);
        }
        return res;
    }

    // Leetcode 1168 : Optimize Water Distribution in a Village
    // Question Description:
    // https://leetcode.ca/2019-02-10-1168-Optimize-Water-Distribution-in-a-Village/
    // Tst: graph7 02:07 hr
    // Steps: 1. Understande why wells and pipes are differnt : can we convert wells
    // into pipes:
    // why pipes ? : bcz we can see pipes as weighted edgse

    // Step2 : Convert the question into a 1 environment : pipes and wells => only
    // pipes
    // How wells to pipes ? : create an imaginary vtce say 0 and try connection it
    // to
    // differnt vtces you want to build well on (say well[2]=> 8) building well on
    // 2nd vtce cost 8
    // This can be translated as connecting a pipes from universal well 0 to vtcs 2
    // with weight of 8(actually it is just buildig well on 2nd vtce)
    // with this translation we can convert the question to single environment

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> allPipes = new ArrayList<>();
        for (int[] p : pipes)
            allPipes.add(p); // pipes already have vtces from 1 to n
        for (int i = 0; i < wells.length; i++) {
            allPipes.add(new int[] { 0, i + 1, well[i] }); // 1 based (to make vtces in 1 based)
        }

        Collections.sort(allPipes, (a, b) -> { // get min wt edges
            return a[2] - b[2]; //
        });

        // dsu
        parent = new int[n + 1]; // 1 based indexing
        for (int i = 0; i <= n; i++)
            parent[i] = i;

        int ans = 0;
        for (int[] e : allPipes) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findParent(u), p2 = findParent(v);
            if (p1 != p2) {
                parent[p2] = p1;
                ans += w;
            }
        }
        return ans;
    }

    // 684 Redundant Connections
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) { // 1 based
            parent[i] = i;
        }
        int[] ans = new int[2];
        for (int[] e : edges) {
            int p1 = findParent(e[0]), p2 = findParent(e[1]);
            if (p1 != p2) {
                parent[p2] = p1;
            } else {
                // redundant edge
                ans[0] = e[0];
                ans[1] = e[1];
            }
        }

        return ans;
    }

    // Mr. President:
    // https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/

    private int mrPresident(int[][] edges, int n, long k) {
        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });// so that we first include min wt edges: mst

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++)
            parent[i] = i;

        int components = n;
        long cost = 0;
        List<Integer> wt = new ArrayList<>();
        // demolish roads such that it is still connected
        // removes all cyclic edges(redundant roads, get componenets)
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findParent(u), p2 = findParent(v);
            // make all roads connected
            if (p1 != p2) {
                parent[p2] = p1;
                cost += w;
                components--;
                wt.add(w);
            }
        }

        if (components > 1)
            return -1; // gcc >1 not connected
        int conversions = 0;
        // try to convert max wt edges roads to super road
        for (int i = wt.size() - 1; i >= 0; i--) {
            if (cost <= k)
                break; // try to reduce cost less than or = k

            cost = cost - wt.get(i) + 1; // +1 for super road conversion
            conversions++;
        }

        if (cost > k)
            return -1; // unable to reduce cost
        return conversions;
    }

    // Leetcode regions-cut-by-slashes : 959
    // Approach : regions is made on two conditions:
    // 1. slashes starts from a boundry(any) and reach to another boundry (created)
    // : put boundaries into same SET
    // 2. if not the case of boundaries: cycle must be created internally(whitinn
    // the boundary ex diamond in cetre of matrix that doesn't touches any boundary
    // (clodes figure in the matrix)

    // grid[][] postions represents idx of slash in matrix, to get the coordinated
    // in terms of lines(not cells) we no +1 in n,m of grid
    // /(forward slash) : (x+1,y) to (x,y+1)
    // \\(backward slash) : (x,y) to (x+1,y+1)

    public int regionsBySlashes(String[] grid) {
        int n = grid.length, regions = 1, m = n + 1;
        parent = new int[m * m];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            int r = i / m, c = i % m;
            // putting boundaries int o same set and making 0 their global parent
            if (r == 0 || r == m - 1 || c == 0 || c == m - 1)
                parent[i] = 0;

        }

        for (int i = 0; i < grid.length; i++) {
            String s = grid[i];
            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);
                int p1 = -1, p2 = -1;
                if (ch == '/') {
                    // (x+1,y) to (x,y+1)
                    p1 = findParent((i + 1) * m + j);
                    p2 = findParent(i * m + j + 1);
                } else if (ch == '\\') {
                    // (x,y) to (x+1,y+1)
                    p1 = findParent(i * m + j);
                    p2 = findParent((i + 1) * m + j + 1);
                } else {
                    continue; // space;
                }

                if (p1 != p2)
                    parent[p2] = p1;
                else
                    regions++;
            }
        }
        return regions;
    }

    // Leetcode 924. Minimize Malware Spread

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        par = new int[n];// leader of the country : id of a country
        size = new int[n]; // population of a country
        for (int i = 0; i < n; i++) {
            par[i] = i;
            ;
            size[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            int p1 = findParent(i);
            for (int j = 0; j < n; j++) {
                if (i != j && graph[i][j] == 1) { // khud se connected ka kya mtlb
                    int p2 = findParent(j);
                    if (p1 != p2) {
                        par[p2] = p1;
                        size[p1] += size[p2];
                    }
                }

            }
        }

        int maxpopulation = 0;
        int[] infected = new int[n];

        Arrays.sort(initial); // to get the smallest idx person

        for (int el : initial) {
            int country = findParent(el);
            infected[country]++;
        }
        int ans = initial[0];
        for (int el : initial) {
            int country = findParent(el);
            // save the country with only 1 infected people(lowest) and highest population
            if (infected[country] == 1 && maxpopulation < size[country]) {
                maxpopulation = size[country];
                ans = el;
            }
        }

        return ans;

    }

}
