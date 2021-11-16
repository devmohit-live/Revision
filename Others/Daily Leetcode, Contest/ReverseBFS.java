public class ReverseBFS {
    // Leetcode 542 : 01 Matrix
    // Apporach 1 : run bfs for every 1 in matrix : more time complexity
    // Wrong Assumption : would not work on every case
    // as in radially we can have n no of 1 after which 0 appears and radially
    // ansnwer will be 1.
    // but according to reverse bfs approach we are updating the neighbours dist
    // too, so we can have the min distance
    // example test case:
    // [[0,0,1,0,1,1,1,0,1,1],[1,1,1,1,0,1,1,1,1,1],[1,1,1,1,1,0,0,0,1,1],[1,0,1,0,1,1,1,0,1,1],[0,0,1,1,1,0,1,1,1,1],[1,0,1,1,1,1,1,1,1,1],[1,1,1,1,0,1,0,1,0,1],[0,1,0,0,0,1,0,0,1,1],[1,1,1,0,1,1,0,1,0,1],[1,0,1,1,1,0,1,1,1,0]]
    // expected
    // ans:[[0,0,1,0,1,2,1,0,1,2],[1,1,2,1,0,1,1,1,'2','3'],[2,1,2,1,1,0,0,0,1,2],[1,0,1,0,1,1,1,0,1,2],[0,0,1,1,1,0,1,1,2,3],[1,0,1,2,1,1,1,2,1,2],[1,1,1,1,0,1,0,1,0,1],[0,1,0,0,0,1,0,0,1,2],[1,1,1,0,1,1,0,1,0,1],[1,0,1,1,1,0,1,2,1,0]]
    // radially:[[0,0,1,0,1,2,1,0,1,2],[1,1,2,1,0,1,1,1,'4','5'],[2,1,3,1,1,0,0,0,1,2],[1,0,1,0,1,1,1,0,1,2],[0,0,1,1,1,0,1,1,2,4],[1,0,1,2,1,1,1,2,1,4],[1,1,1,1,0,1,0,1,0,1],[0,1,0,0,0,1,0,0,1,2],[1,1,1,0,1,1,0,1,0,1],[1,0,1,1,1,0,1,2,1,0]]

    // Approach 2: Reverse bfs : will use 0 for using bfs instead of 1(initally)
    // at every level (each radius/round) all the 1's which are 1step aways from
    // zeroes will bw marked
    // at next stage those 1's will be added in the q and acts as a source and will
    // mark the 1's which are 1 step aways from them(ans eventually 2 steps aways
    // from 0), and the process will go on (basically distance is source's distance
    // +1)

    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        Queue<int[]> q = new LinkedList<>();
        // we can use boolean matrix of mark all 1's to -ve (unvisited) intiallly
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // initally add all 0 for reverse bfs
                if (mat[i][j] == 0)
                    q.add(new int[] { i, j });
                else
                    mat[i][j] = -1; // mark all 1's as unvisited
            }
        }

        // bfs
        while (q.size() > 0) {
            int[] rm = q.remove();
            int sr = rm[0], sc = rm[1];

            for (int[] d : dir) {
                int r = sr + d[0];
                int c = sc + d[1];

                if (r >= 0 && r < n && c >= 0 && c < m && mat[r][c] == -1) {// is unvisited
                    mat[r][c] = mat[sr][sc] + 1; // updating the distance which will also removes -1//makes visited
                    q.add(new int[] { r, c }); // will act as source for next step
                }
            }

        }
        return mat;

    }

}