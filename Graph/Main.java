import java.io.*;
import java.util.*;

public class Main {
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

        int vtces = 7;
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = 8;

        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        int src = Integer.parseInt(br.readLine());
        // int dest = Integer.parseInt(br.readLine());
        boolean visited[] = new boolean[vtces];
        // System.out.println("\n-----------Pre------------");
        // preOrder(graph, src, visited, "" + src, 0);
        // System.out.println("\n-----------IN------------");
        // inOrder(graph, src, visited, "" + src, 0);
        // System.out.println("\n-----------Post------------");
        postOrder(graph,src,visited,""+src,0);
    }

    public static void preOrder(ArrayList<Edge>[] graph, int src, boolean[] visited, String psf, int wsf) {
        visited[src] = true;
        System.out.println(src + " -> " + psf + "@" + wsf);

        for (Edge e : graph[src]) {
            if (!visited[e.nbr])
                preOrder(graph, e.nbr, visited, psf + e.nbr, wsf + e.wt);
        }

        visited[src] = false;
    }

    public static void postOrder(ArrayList<Edge>[] graph, int src, boolean[] visited, String psf, int wsf) {
        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr])
                postOrder(graph, e.nbr, visited, psf + e.nbr, wsf + e.wt);
        }

        System.out.println(src + " -> " + psf + "@" + wsf);

        visited[src] = false;
    }

    public static void inOrder(ArrayList<Edge>[] graph, int src, boolean[] visited, String psf, int wsf) {
        visited[src] = true;

        for (Edge e : graph[src]) {
            System.out.println(src + " -> " + psf + "@" + wsf);
            if (!visited[e.nbr])
                inOrder(graph, e.nbr, visited, psf + e.nbr, wsf + e.wt);
        }

        visited[src] = false;
    }

}