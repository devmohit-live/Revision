package Questions.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
    // O(E)
    public static void BFS_forCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        boolean iscycle = false;

        que.add(src);
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Min No Edges: " + level + " -> ");
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                if (vis[rvtx]) {
                    iscycle = true;
                    continue;
                }

                System.out.print(rvtx + ", ");
                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v])
                        que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }
    }

    // O(v)
    public static void BFS_withoutCycle(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;

        que.add(src);
        vis[src] = true;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Min No Edges: " + level + " -> ");
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                System.out.print(rvtx + ", ");
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

    // Rotten Oranges: 994
    int[][] dir = { {0,-1} , {0,1} ,{-1,0} ,{1,0} };
    
    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length, fresh = 0;
        LinkedList<Integer> q= new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1) fresh++;
                else if(grid[i][j] == 2){
                    grid[i][j] = 2; //mark : justy to remember we have to  mark true
                    q.addLast(i*m + j);
                }
            }
        }
        if(fresh == 0) return 0;
        
        // bfs without cycle
        int time = 0;
        while(!q.isEmpty()){
            int size = q.size();
            
            while(size-->0){
                int rm_idx = q.removeFirst();
                int sr = rm_idx / m;
                int sc = rm_idx % m;
                
                for(int[] d: dir){
                    int r = sr + d[0];
                    int c = sc + d[1];
                    
                    if(r>=0 && c>=0 && r<n && c<m && grid[r][c] == 1){
                        grid[r][c] = 2; //mark true/rotten
                        q.addLast(r*m +c);
                        fresh--; // we are only going to fresh oranges
                    }
                    if(fresh == 0) return time +1;
                }
                
            }
            
            time++;
            
        }
        
        return -1; // not possible to rott all oranges
    }

}
