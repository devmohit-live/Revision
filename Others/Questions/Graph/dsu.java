package Questions.Graph;

import java.util.ArrayList;
import java.util.Arrays;

class Edge {
    int src, nbr, wt;

    Edge(int src, int nbr, int wt) {
        this.src = src;
        this.nbr = nbr;
        this.wt = wt;
    }
}

public class dsu {
    int[] parent, size;
    int n;
    ArrayList<Edge>[] graph; // to implemet graph too
    int[][] edges;

    dsu(int[][] edges) {
        this.n = edges.length;
        parent = new int[n];
        size = new int[n];
        Arrays.fill(size, 1);
        this.edges = edges;

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public int findParent(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = findParent(parent[x]); // path compression
    }

    public void unioun(int x, int y) {
        int parentx = findParent(x);
        int parenty = findParent(y);
        if (parentx == parenty)
            return; // already in set : or cycle will be created
        if (size[x] > size[y]) {
            parent[y] = parentx; // x's parent is now y's parent too
            size[x] += size[y]; // size of x is increased
        } else {
            parent[y] = parentx;
            size[x] += size[y];
        }
    }

    // create graph : acyclic
    public void createGraph() {
        // create a acyclic graph
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int x = findParent(u);
            int y = findParent(v);
            if (x != y) {
                unioun(u, v);
                addEdge(graph, u, v, w);
            }

        }
        clear();
    }

    public void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    public void findCycle() {
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int x = findParent(u);
            int y = findParent(v);
            if (x != y) {
                unioun(u, v);
            } else {
                System.out.println("Contains Cycle");
            }
        }

        clear(); // for further use , graph creation
    }

    private void clear() {
        this.parent = new int[n];
        this.size = new int[n];
    }

}
