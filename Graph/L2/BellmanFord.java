import java.util.Arrays;

public class BellmanFord {
    // runs loop for v-1(calculation) + 1 times(check) : : N times 
    // basically for non cyclic graphs e = v-1 ie loop : E +1
    // ith state in loop states : using atmost i no of edges what is the min weight of reaching a vtx v
    // prev[u]+w < curr[v] : update
    public static void bellmanFord(int[][] edges, int src, int n){
        int[] prev = new int[n];
        Arrays.fill(prev,(int)1e9);
        prev[src] = 0;

        boolean lastupdated = false;
        for(int i=0;i<=n;i++){ // e+1
            int[] curr = new int[n];
            for(int[] e: edges){
                int u = e[0], v = e[1], w = e[2];

                if(prev[u]!=(int)1e9 && prev[u]+w < curr[v]){

                    if(i == n ) lastupdated = true;
                    curr[v] = prev[u] + w;
                }

            }

            prev = curr;
        }


        if(lastupdated) 
            System.out.println("Negative weight cycle exixs");
        else {
            System.out.println("Min Distance to reach a vtx from a given source "+src+" are: ");
            for(int i=0;i<n;i++) System.out.println(src+" -> "+ i+" wt = "+prev[i]);
        }
    }
}
