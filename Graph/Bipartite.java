import java.io.*;
import java.util.*;

public class Bipartite {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        System.out.println(isBipartite(graph));
    }

    // Is graph bipartite => main q => (does graph contains odd length cylce or even
    // length cycle)

    private static boolean isBipartite(ArrayList<Edge>[] graph) {
        boolean res = false;
        boolean visited[] = new boolean[graph.length];
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();

        int src = 0;

        boolean cycle = false;

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i])
                cycle = cycle || bfs(graph, visited, i, s1, s2);

        }

        boolean bipartite = true;

        for (int item : s1) {
            if (s2.contains(item)) {
                bipartite = false;

            }
        }

        // if(cycle){
        // if(bipartite)
        // System.out.println("Even Length Cycle");
        // else
        // System.out.println("Odd Length Cycle");

        // }

        return bipartite;
    }

    private static boolean bfs(ArrayList<Edge>[] graph, boolean visited[], int src, Set<Integer> s1, Set<Integer> s2) {
        LinkedList<Integer> q = new LinkedList<Integer>();

        boolean isCycle = false;
        int lv = 0;

        q.addLast(src);

        while (!q.isEmpty()) {
            int curr = q.size();

            while (curr-- > 0) {
                int rm = q.removeFirst();
                if (lv % 2 == 0) {
                    s1.add(rm);
                } else {
                    s2.add(rm);
                }

                if (visited[rm]) {
                    isCycle = true;
                    continue;
                }

                visited[rm] = true;

                for (Edge e : graph[rm]) {
                    if (!visited[e.nbr]) {
                        q.addLast(e.nbr);
                    }
                }

            }
            lv++;
        }

        return isCycle;

    }

    // Leetcode 785

    // A beeter way to solve bipartite:
    // Use visited on int type that can be used to represent he color of vertex:
    // visited[] : -1 => not visited, 0 => red color(set 1), 1=> green color (set
    // 2).
    // colors are marked on the basis of levels => even leve =>0, odd level =1

    // It is recommended to return at least if possible in mid way use break
    // statements
    public boolean isBipartite(int[][] graph) {
        boolean res = true;
        int visited[] = new int[graph.length];
        Arrays.fill(visited, -1);

        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == -1) {
                res = res && bipartite(graph, visited, i);
            }
        }

        return res;
    }

    boolean bipartite(int graph[][], int[] visited, int src) {
        LinkedList<Integer> q = new LinkedList<>();
        boolean bip = true;
        boolean cycle = false;
        int lv = 0;

        q.addLast(src);

        while (!q.isEmpty()) {
            int curr = q.size();

            while (curr-- > 0) {
                int rm = q.removeFirst();

                // already marked by someone
                if (visited[rm] != -1) {
                    cycle = true;
                    // check for conflicts
                    int curr_color = lv;
                    int previous_saved_color = visited[rm];
                    if (curr_color != previous_saved_color) {
                        bip = false;
                        break;
                        // return false; best way is to return at end
                    } else
                        continue; // normal bfs case for cycle
                }

                // mark
                visited[rm] = lv;

                for (int nbr : graph[rm]) {
                    if (visited[nbr] == -1)
                        q.addLast(nbr);
                }

            }

            lv = (lv + 1) % 2;
            if (!bip)
                break;
        }

        if (cycle) {
            if (bip)
                System.out.println("Even Length Cycle.");
            else
                System.out.println("Odd Length Cycle.");
        }
        if (!bip) {
            System.out.println("This component is not Bipartite");
        }
        if (bip) {
            System.out.println("This component is Bipartite");
        }

        return bip;
    }

}