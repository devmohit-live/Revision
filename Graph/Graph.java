import java.util.ArrayList;

public class Graph {

    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    public static void display(ArrayList<Edge>[] graph, int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(e.nbr + " " + e.wt + " ,");
            }
            System.out.println();
        }
    }

    // : TODO:
    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> tmp = new ArrayList<>();
        for (Edge e : graph[u]) {
            if (e.nbr != v)
                tmp.add(e);
        }
        ArrayList<Edge> tmp2 = new ArrayList<>();
        graph[u] = tmp;
        for (Edge e : graph[v]) {
            if (e.nbr != u)
                tmp2.add(e);
        }
        graph[v] = tmp2;
    }

    // : TODO:
    private static void remove(ArrayList<Edge> arr, ArrayList<Edge> graph[], int src, int des) {
        ArrayList<Edge> tmp = new ArrayList<>();
        for (Edge e : arr) {
            if (e.nbr != des)
                tmp.add(e);
        }

        graph[src] = tmp;
        arr = null;
    }

    public static ArrayList<Edge>[] construction() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);
        return graph;

    }

    public static void main(String[] args) {
        ArrayList<Edge>[] graph = construction();
        // removeEdge(graph, 1, 3);
        // display(graph, graph.length);
        // int src = 0;
        // int dest = 6;
        // heaviestPath(graph, src, dest);
        // lightestPath(graph, src, dest);
    }

    // ceil and florr: TODO:
    // is connected

    public static boolean isConnected(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        int count = dfs(graph, 0, visited);
        return count == graph.length;
    }

    public static int dfs(ArrayList<Edge>[] graph, int src, boolean visited[]) {
        int count = 1;
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                count += dfs(graph, e.nbr, visited);
            }
        }
        return count;

    }

}
