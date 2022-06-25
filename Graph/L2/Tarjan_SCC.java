import java.util.*;

public class Tarjan_SCC {
    // similar to ap and edge//uses the fact that the for any set of nodes(forming a
    // component)
    // must have a backedge acting as a cycle => backedge(topological sort vis[node]
    // // the node alreay part of current path : part of stack)

    // it ap and brides : backedge has same definition also it can be understood as
    // the already visited node/nbr of current node from which we update our lowest
    // reachbale data(low value)=>
    // if(vis[nbr]) low[curr] = Math.min(low[curr, disc[nbr]])

    // head/tail(basically start/end of cycle) of SCC => whenver lowtime[curr] ==
    // disctime[curr]

    // Difference from topological sort/cycle detection/articulation : we will also
    // be nneding a stack here as to keep the values(previous)
    // so that we can add them in scc ans
    static int time = 0;
    // static booelan[] presentInStack;
    static int[] vis, disc, low;
    static Stack<Integer> stack;

    // -1: not visited, 0: vis(part of current path) , 1 : vis and not part of
    // current path(not in stack)
    private static void tarjanAlgo(ArrayList<Edge>[] graph, int src, List<List<Integer>> ans) {
        vis[src] = 0; // vis and part of path
        disc[src] = low[src] = time++;
        stack.push(src);

        for (Edge e : graph[src]) {
            int nbr = e.v;
            if (vis[nbr] == 0) {
                // vis and prt of current path : backedge
                low[src] = Math.min(low[src], disc[nbr]);
            } else if (vis[nbr] == -1) {
                // not visited
                tarjanAlgo(graph, nbr, ans);
                low[src] = Math.min(low[src], low[nbr]);
            }
        }

        // vis[src] = 1; // vis nut not part of current path
        // adding in answer
        if (low[src] == disc[src]) {
            List<Integer> scc = new ArrayList<>();
            while (stack.peek() != src) {
                int el = stack.pop();
                scc.add(el);
                vis[el] = 1; // vis but not part of path // not part of current stack
            }
            // now peek == src
            scc.add(stack.peek());
            vis[stack.pop()] = 1;

            ans.add(scc);
        }

    }

    public static void main(String[] args) {
        int N = 9;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);
        addEdge(graph, 1, 7, 5);
        addEdge(graph, 7, 8, 4);

        disc = new int[N];
        low = new int[N];
        vis = new int[N];
        Arrays.fill(vis, -1);
        stack = new Stack<>();
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < N; i++)
            if (vis[i] == -1)
                tarjanAlgo(graph, i, ans);

        System.out.println(ans);

    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
    }

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
