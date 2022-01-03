/**
 * FindTownJudge
 */
public class FindTownJudge {
    // LC 997: Based on Indergee and Oudegree Question : different from dfs,bfs,dsu

    // directed graph:
    // mayor: all knows him he knows no one not himself:
    // indegree to mayor : n-1, outdegree = 0;
    // we can also say that for all incoming edges we do ++ => indegree and outgoind
    // => --
    // so net degreee = in - out => n-1
    public int findJudge(int n, int[][] trust) {
        int[] degree = new int[n + 1]; // 1based
        for (int[] e : trust) {
            int u = e[0], v = e[1];
            degree[u]--;
            degree[v]++;
        }

        for (int i = 1; i <= n; i++)
            if (degree[i] == n - 1)
                return i;
        return -1;
    }
}