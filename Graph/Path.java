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

    // int vtces=7;
    // boolean[] visited=new boolean[vtces];

    // allPaths(graph,src,dest,visited,"0",0); //"0" -> initial node -> starting point

}
