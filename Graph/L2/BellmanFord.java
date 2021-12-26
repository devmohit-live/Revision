import java.util.Arrays;

public class BellmanFord {
    // runs loop for v-1(calculation) + 1 times(check) : : N times
    // basically for non cyclic graphs e = v-1 ie loop : E +1
    // ith state in loop states : using atmost i no of edges what is the min weight
    // of reaching a vtx v
    // prev[u]+w < curr[v] : update
    public static void bellmanFord(int[][] edges, int src, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, (int) 1e9);
        prev[src] = 0; // atmost 0 edges is used

        boolean lastupdated = false;
        // from using atmost 1 to E edges +1(check)
        for (int i = 1; i <= n; i++) { // e+1
            // MAKE A FRESH COPY OF PREV TO CURR FOR EVALUATION OF NEW DATA FOR ITH EDGE
            // BASED ON I-1TH EDGE
            int[] curr = new int[n];
            for (int j = 0; j < n; j++)
                curr[j] = prev[j];

            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];

                if (prev[u] != (int) 1e9 && prev[u] + w < curr[v]) {

                    if (i == n)
                        lastupdated = true;
                    curr[v] = prev[u] + w;
                }

            }

            prev = curr;
        }

        if (lastupdated)
            System.out.println("Negative weight cycle exixs");
        else {
            System.out.println("Min Distance to reach a vtx from a given source " + src + " are: ");
            for (int i = 0; i < n; i++)
                System.out.println(src + " -> " + i + " wt = " + prev[i]);
        }
    }

    // Leetcode 787: 787. Cheapest Flights Within K Stops

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // bellman ford
        int[] prev = new int[n];
        Arrays.fill(prev, (int) 1e9);
        prev[src] = 0;

        // k stops: k+1 edges
        k++;

        boolean lastupdated = false;
        for (int i = 1; i <= k; i++) { // e+1
            int[] curr = new int[n];
            for (int j = 0; j < n; j++)
                curr[j] = prev[j];

            for (int[] e : flights) {
                int u = e[0], v = e[1], w = e[2];

                if (prev[u] != (int) 1e9 && prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                }
            }

            prev = curr;
        }
        return (prev[dst] == (int) 1e9) ? -1 : prev[dst];
    }
}
