public class CeilAndFloor {
    // ceil and floor of edges wt in graph:
    static class ceil_floor {
        int ceil = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;
    }

    public static void findCeilFloor(ArrayList<Edge>[] graph, int k) {
        boolean[] visited = new boolean[graph.length];
        ceil_floor ans = findCF(graph, k, visited);
        // finding ceil floor of edges
        System.out.println("Floor is : " + ans.floor);
        System.out.println("Ceil is : " + ans.ceil);
    }

    // finding ceil floor of edges
    static ceil_floor findCF(ArrayList<Edge>[] graph, int k, boolean[] visited) {
        ceil_floor ans = new ceil_floor();

        for (int i = 0; i < graph.length; i++) {
            for (Edge e : graph[i]) {
                if (e.wt > k) {
                    // ceil
                    ans.ceil = Math.min(e.wt, ans.ceil);
                }
                if (e.wt < k) {
                    ans.floor = Math.max(e.wt, ans.floor);
                }
            }
        }

        return ans;
    }

    static class CFPair {
        int ceil = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;
        String ceil_path = "";
        String floor_path = "";
    }

    // Finding Ceil and floor of Path lengths from a particular start point

    static void findcfOfPaths(ArrayList<Edge>[] graph, int src, int k) {
        CFPair pair = new CFPair();
        boolean[] visited = new boolean[graph.length];
        findCPF(graph, src, k, 0, src + "", visited, pair);
        System.out.printf("Ceil is %d with path %s  , Floor is %d with path %s \n", pair.ceil, pair.ceil_path,
                pair.floor, pair.floor_path);
    }

    static void findCPF(ArrayList<Edge>[] graph, int src, int k, int wsf, String psf, boolean[] visited, CFPair pair) {
        if (wsf > k) {
            // ceil
            if (wsf < pair.ceil) {
                pair.ceil = wsf;
                pair.ceil_path = psf;
            }
        }
        if (wsf < k) {
            if (wsf < pair.ceil) {
                pair.floor = wsf;
                pair.floor_path = psf;
            }
        }

        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                findCPF(graph, e.nbr, k, wsf + e.wt, psf + e.nbr, visited, pair);
            }
        }

        visited[src] = false;
    }

    // Finding ceil and floor of paths possible from source to destinantion
    // Similar to above we just have to put the ceil and floor checks in base case
    // where src==dest
    static void CFOfPath(ArrayList<Edge>[] graph, int src, int des, int k) {
        CFPair pair = new CFPair();
        boolean[] visited = new boolean[graph.length];
        findCPF_S2D(graph, src, des, k, 0, src + "", visited, pair);
        System.out.printf("Ceil is %d with path %s  , Floor is %d with path %s \n", pair.ceil, pair.ceil_path,
                pair.floor, pair.floor_path);
    }

    static void findCPF_S2D(ArrayList<Edge>[] graph, int src, int des, int k, int wsf, String psf, boolean[] visited,
            CFPair pair) {
        if (src == des) {
            if (wsf > k) {
                // ceil
                if (wsf < pair.ceil) {
                    pair.ceil = wsf;
                    pair.ceil_path = psf;
                }
            }
            if (wsf < k) {
                if (wsf < pair.ceil) {
                    pair.floor = wsf;
                    pair.floor_path = psf;
                }
            }
        }

        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                findCPF_S2D(graph, e.nbr, des, k, wsf + e.wt, psf + e.nbr, visited, pair);
            }
        }

        visited[src] = false;
    }

}
