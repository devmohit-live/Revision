public class EvaluateDivison399 {
    // LC 399
    class Edge {
        String u, v;
        double wt;

        Edge(String u, String v, double wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }

        @Override
        public String toString() {
            return this.u + " -> " + this.v + " @ " + this.wt + " ";
        }
    }

    // Approahc 1 : Using dfs: void type
    class DFS01 {

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // graph: map : nodes: string
            Map<String, ArrayList<Edge>> map = new HashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                String a = equations.get(i).get(0), b = equations.get(i).get(1);
                double val = values[i];
                map.putIfAbsent(a, new ArrayList<>());
                map.putIfAbsent(b, new ArrayList<>());
                map.get(a).add(new Edge(a, b, val));
                map.get(b).add(new Edge(b, a, 1 / val));
            }
            // System.out.println(map);
            double[] ans = new double[queries.size()];
            Arrays.fill(ans, -1); // when both nodes exits but there is not path between them
            for (int i = 0; i < queries.size(); i++) {
                String u = queries.get(i).get(0), v = queries.get(i).get(1);
                if (!map.containsKey(u) || !map.containsKey(v))
                    ans[i] = -1.0;
                else if (u.equals(v))
                    ans[i] = 1.0;
                else
                    dfs(map, u, v, 1.0, new HashSet<String>(), i, ans);

            }
            return ans;
        }

        // void type
        private void dfs(Map<String, ArrayList<Edge>> map, String src, String des, double asf, Set<String> vis, int idx,
                double[] ans) {
            if (src.equals(des)) {
                ans[idx] = asf;
                return;
            }
            vis.add(src);
            for (Edge nbr : map.get(src)) {
                if (!vis.contains(nbr.v)) {
                    dfs(map, nbr.v, des, asf * nbr.wt, vis, idx, ans);
                }
            }
        }

    }

    // Approach 2 : DFs: Return type : identification marking is must
    class DFS02 {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // graph: map : nodes: string
            Map<String, ArrayList<Edge>> map = new HashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                String a = equations.get(i).get(0), b = equations.get(i).get(1);
                double val = values[i];
                map.putIfAbsent(a, new ArrayList<>());
                map.putIfAbsent(b, new ArrayList<>());
                map.get(a).add(new Edge(a, b, val));
                map.get(b).add(new Edge(b, a, 1 / val));
            }

            double[] ans = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                String u = queries.get(i).get(0), v = queries.get(i).get(1);
                Set<String> set = new HashSet<String>();
                set.add(u); // mark alreadyfor withoout cycle
                ans[i] = dfs(map, u, v, 1.0, set);
            }

            return ans;
        }

        // return type: having problem : the value returned in default case overrides
        // the actual answer:
        // Sol : use dfs like bfs without cycle
        private double dfs(Map<String, ArrayList<Edge>> map, String src, String des, double asf, Set<String> vis) {
            if (!map.containsKey(src) || !map.containsKey(des))
                return -1.0; // also an indication mark that ans not found
            if (src.equals(des))
                return asf;
            for (Edge e : map.get(src)) {
                if (!vis.contains(e.v)) {
                    vis.add(e.v);
                    if (src == des)
                        return asf * e.wt; // direct edge

                    double rec = dfs(map, e.v, des, asf * e.wt, vis); // indirect edge exits somewhere(so we have got
                                                                      // non negative)
                    if (rec != -1.0)
                        return rec;
                }
            }
            return -1;
        }

    }

    class BFS {

    }

    class DSU {

    }

}
