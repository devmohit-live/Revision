import java.util.LinkedList;

import Graph.Edge;

import java.nio.IntBuffer;
import java.util.*;

public class BFS {

    public static void bfs(ArrayList<Edge>[] graph, int src, int des) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(src);
        boolean[] visited = new boolean[graph.length];
        boolean isCyclePresent = false;
        int level = 0; // shotrest path = min no of edges required to reach dest
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst();
                // cycle
                if (visited[rm]) {
                    isCyclePresent = true;
                    continue; // to prevent adding the children and allowing other vertices to be explored
                              // (not using break as it will break the further execution)
                }
                if (rm == des) {
                    System.out.println("Shortest Path length is " + level);
                    return;
                }

                visited[rm] = true;
                for (Edge e : graph[rm]) {
                    if (!visited[e.nbr])
                        q.addLast(e.nbr);
                }

            }
            //
            level++;
        }
    }

    static class SP {
        int src;
        String psf = "";
        int wsf = 0;

        SP(int src, String psf, int wsf) {
            this.src = src;
            this.psf = psf;
            this.wsf = wsf;
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
            // System.out.println(v1+" "+v2+" "+wt);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        int src = Integer.parseInt(br.readLine());

        shortestPathEdges(graph, src);

    }

    public static void shortestPathEdges(ArrayList<Edge>[] graph, int src) {

        boolean[] visited = new boolean[graph.length];
        LinkedList<SP> q = new LinkedList<>();
        q.addLast(new SP(src, "" + 0, 0));

        while (!q.isEmpty()) {

            int curr = q.size();

            while (curr-- > 0) {

                SP rm = q.removeFirst();

                if (visited[rm.src]) {
                    continue;
                }

                visited[rm.src] = true;
                System.out.println(rm.src + " ->" + rm.psf + "@" + rm.wsf);

                for (Edge e : graph[rm.src]) {
                    if (!visited[e.nbr]) {
                        SP nbr = new SP(e.nbr, rm.psf + e.nbr, rm.wsf + e.wt);
                        q.addLast(nbr);
                    }
                }
            }
        }

    }

    // spread of infection:
    public static int bfs(ArrayList<Edge>[] graph, int src, int t) {
        boolean visited[] = new boolean[graph.length];
        int lv = 1;

        int count = 0;
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(src);

        while (!q.isEmpty()) {

            int curr = q.size();

            while (curr-- > 0) {

                int rm = q.removeFirst();
                if (visited[rm])
                    continue;

                visited[rm] = true;
                count++;

                for (Edge e : graph[rm]) {
                    if (!visited[e.nbr]) {
                        q.addLast(e.nbr);

                    }
                }

            }

            if (lv == t) {
                return count;
            }

            lv++;
        }

        return -1;
    }


}
