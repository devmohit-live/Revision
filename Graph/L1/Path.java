public class Path {
    // dfs
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int des, boolean visited[]) {

        if (src == des)
            return true;

        boolean res = false;

        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                res = res || hasPath(graph, e.nbr, des, visited);
            }
        }
        return res;
    }

    // dfs
    public static void allPaths(ArrayList<Edge>[] graph, int src, int des, boolean visited[], String psf, int wsf) {
        if (src == des) {
            System.out.println(psf);
        }
        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                allPaths(graph, e.nbr, des, visited, psf + e.nbr, wsf + e.wt);
            }
        }

        visited[src] = false;
    }

    // If you also want to return the number of path along with printing:
    public static int allPaths(ArrayList<Edge>[] graph, int src, int des, boolean visited[], String psf, int wsf) {
        if (src == des) {
            System.out.println(psf);
            return 1; // 1 path found
        }

        int count = 0;
        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                count += allPaths(graph, e.nbr, des, visited, psf + e.nbr, wsf + e.wt);
            }
        }

        visited[src] = false;
        return count;
    }

    // System.out.println("No of paths: "+ allPath(graph,src,des,visited,""+src,0));

    // int vtces=7;
    // boolean[] visited=new boolean[vtces];

    // allPaths(graph,src,dest,visited,"0",0); //"0" -> initial node -> starting
    // point

    static class pathPair2 {
        String psf = "";
        int wsf = Integer.MAX_VALUE; // -1 would work for path checking but on comparsion rec.wsf+e.wt< ans.wsf(-1)
                                     // would cause problem (ans would never be update)
    }

    public static pathPair2 lightest(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            pathPair2 base = new pathPair2();
            base.wsf = 0;
            base.psf = src + base.psf;
            return base;
        }

        vis[src] = true;
        pathPair2 myAns = new pathPair2();
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                pathPair2 recAns = lightest(graph, e.nbr, dest, vis);
                if (recAns.wsf != Integer.MAX_VALUE && recAns.wsf + e.wt < myAns.wsf) {
                    myAns.psf = src + recAns.psf;
                    myAns.wsf = recAns.wsf + e.wt;
                }
            }
        }
        vis[src] = false;
        return myAns;
    }

    public static void lightestPath(ArrayList<Edge>[] graph, int src, int dest) {
        boolean[] visited = new boolean[graph.length];
        pathPair2 ans = lightest(graph, src, dest, visited);

        System.out.println("Lightest Path: " + ans.psf + " of weight: " + ans.wsf);
    }

    static class pathPair {
        String psf = "";
        int wsf = -1;
    }

    public static pathPair heaviestPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            pathPair base = new pathPair();
            base.psf += src;
            base.wsf = 0;

            return base;
        }

        vis[src] = true;
        pathPair myAns = new pathPair();
        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                pathPair recAns = heaviestPath(graph, e.nbr, dest, vis);
                if (recAns.wsf != -1 && recAns.wsf + e.wt > myAns.wsf) {
                    myAns.psf = src + recAns.psf;
                    myAns.wsf = recAns.wsf + e.wt;
                }
            }
        }
        vis[src] = false;
        return myAns;
    }

    public static void heaviestPath(ArrayList<Edge>[] graph, int src, int dest) {
        boolean[] vis = new boolean[graph.length];
        pathPair ans = heaviestPath(graph, src, dest, vis);

        System.out.println("Heaviest Path: " + ans.psf + " of weight: " + ans.wsf);
    }

    // Hamiltonian
    public static void hamiltonian(ArrayList<Edge>[] graph, int src, int orsrc, boolean[] visited, int edgeCount,
            String psf) {
        if (edgeCount == graph.length - 1) {
            // all vtces are visited
            if (find(graph, src, orsrc)) {
                // cycle
                System.out.println(psf + "*");
            } else {
                // path
                System.out.println(psf + ".");
            }
        }

        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                hamiltonian(graph, e.nbr, orsrc, visited, edgeCount + 1, psf + e.nbr);
            }
        }

        visited[src] = false;
    }

    private static boolean find(ArrayList<Edge>[] graph, int src, int des) {
        for (Edge e : graph[src]) {
            if (e.nbr == des)
                return true;
        }
        return false;
    }

}