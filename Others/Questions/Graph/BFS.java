package Questions.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
    // O(E)
    public static void BFS_forCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        boolean iscycle = false;

        que.add(src);
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Min No Edges: " + level + " -> ");
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                if (vis[rvtx]) {
                    iscycle = true;
                    continue;
                }

                System.out.print(rvtx + ", ");
                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v])
                        que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }
    }

    // O(v)
    public static void BFS_withoutCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;

        que.add(src);
        vis[src] = true;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Min No Edges: " + level + " -> ");
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                System.out.print(rvtx + ", ");
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

    // Rotten Oranges:
    

}
