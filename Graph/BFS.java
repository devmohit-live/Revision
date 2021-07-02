import java.util.LinkedList;

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
}
