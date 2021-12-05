package Questions.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DirectedGraph {
    // topological sort : just know the order : add the vtc in backtracking

    // To check for correcteness : draw the direction of edges of the sorted vtces
    // if any of the edge goes in oppsoite direction of others then there exists a
    // cycle: hence not possible

    public static void topologicalSort(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = graph.length;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                topologicalSort_dfs(graph, i, ans, vis);
        }
        System.out.println("Topologocal sort is : ");
        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();

    }

    private static void topologicalSort_dfs(ArrayList<Edge>[] graph, int src, ArrayList<Integer> res, boolean[] vis) {
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.nbr]) {
                topologicalSort_dfs(graph, e.nbr, res, vis);
            }
        }

        // add while backtracking
        res.add(src);
    }

    // Kahn's algo: As we may not ber sure that given topological sort is
    // correct(contains cycle: deadlock) or not
    // Kahn's : Cycle Detection in directed graphs, deadlock detection, order of
    // processing some taks

    // Use this: for topological : Uses BFS : weach level defines the order (in
    // opposite ) for execution
    // nodes at a level can be executed/processed parallely (nodes at different
    // level) are dependednt on next levels

    // Uses the concept of indegree

    public static void Kahns_topological(ArrayList<Edge>[] graph) {
        int n = graph.length, count = 0;
        ArrayList<Integer> res = new ArrayList<>();
        int[] indegree = nerw int[n];
        boolean[] vis = new boolean[n];
        //calculation indegree of vtces
        for(ArrayList<Edge> edge : graph){
            for(Edge e: edge){
                indegree[e.nbr]++;
            }
        }
        // all the nodes having indegree 0 : all independent: used as src for bfs

        LinkedList<Integer> q= new LinkedList<>();
        for(int i=0;i<n;i++) if(indegree[i]==0) q.addLast(i);
        int level =0;
        count+=q.size();

        //bfs
        while(!q.isEmpty()){
            int size = q.size();

            while(size-->0){
                int rm = q.removeFirst();
                vis[rm] = true;
                count++;
                res.add(rm);

                for(Edge e: graph[rm]){
                    if(!vis[e.nbr]){
                        indegree[e.nbr]--;
                        if(indegree[e.nbr]==0){
                            // add the next level sources
                            q.addLast(e.nbr);
                        } 
                    }
                }
            }
            level++;

        }
        if(count< n){ //  less than 
            System.out.println("Graph contains cycle , Hence deadlock is occured : Topologcal sort not possible");
        }else{
            // count > = n (q can contains same thing twice: 1 task is dependent of more than 1 task)
            System.out.println("Topological ordering is : ");
            for(int i=res.size()-1;i>=0;i--){
                System.out.print(res.get(i)+" ");
            }
            System.out.println();
        }
    }

}
