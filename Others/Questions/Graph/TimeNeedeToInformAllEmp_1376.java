import java.util.ArrayList;
import java.util.LinkedList;

class TimeNeedeToInformAllEmp_1376{
     class Pair{
            int vtx, time;
            Pair(int vtx,int time){
                this.vtx = vtx;
                this.time = time;
            }
        }
    
    public int numOfMinutes(int n, int headId, int[] manager, int[] informTime) {
        // graph
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++) graph[i] = new ArrayList<>();
        for(int i=0;i<n;i++){
            int parent = manager[i];
             if(parent !=-1 )
                graph[parent].add(i);
        }
        boolean[] vis = new boolean[n];
        // return bfs(graph,headId,informTime,vis);
        return dfs(graph,headId,informTime,vis);
        
        // we can remove boolean check as it is certain that an amployee falls under a single manager
        // no cycle 1 to 1 relationship
    }
    
    private int dfs(ArrayList<Integer>[] graph,int src,int[] informTime,boolean[] vis){
        vis[src] = true;
        int childTime = 0;
        for(int nbr : graph[src] ){
            if(!vis[nbr])
                childTime = Math.max( childTime , dfs( graph , nbr , informTime,vis) );
        }
        int totalTime = childTime + informTime[src];
        return totalTime;
    }
    
    private int bfs(ArrayList<Integer>[] graph,int headId,int[] informTime,boolean[] vis){
        int max = 0, n = graph.length;
        LinkedList<Pair> q = new LinkedList<>();
        q.add(new Pair(headId,0));
        vis[headId] = true;
        
        while(q.size()>0){
            Pair rm = q.removeFirst();
            int parid = rm.vtx;
            int rtime = rm.time;
            
            max = Math.max(max,rtime); 
            // 
            // children/ subordinates
            int informTimeToSubordinates =  informTime[parid];
            for(int child: graph[parid]){
                if(!vis[child]){
                     q.addLast(new Pair(child, rtime+informTimeToSubordinates));
                     vis[child] = true;
                }
               
            }
        }
        
        
        return max;
    }
}