public class TraversalOrder {

    // If calle: preOrder(graph,src,visited,"",0); psf ="", 

    // public static void preOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
    //     System.out.println(src + " -> " + (psf + src) + " @ " + wsf); //adding src at last src==des
    //     vis[src] = true;
    //     for (Edge e : graph[src]) {
    //         if (!vis[e.nbr])
    //             preOrder(graph, e.nbr, vis, wsf + e.wt, psf + src); //adding src 
    //     }

    //     vis[src] = false;
    // }


    // If calle: preOrder(graph,src,visited,""+src,0); -> psf initally : ""+src;

    public static void preOrder( ArrayList<Edge>[] graph , int src, boolean[] visited, String psf, int wsf){
        visited[src]=true;
        System.out.println(src+" -> "+ psf+"@"+wsf);
        
        
        for(Edge e : graph[src]){
            if(!visited[e.nbr]) preOrder(graph,e.nbr,visited,psf+e.nbr,wsf+e.wt);
        }
        
        
        visited[src]=false;
    }
    public static void postOrder( ArrayList<Edge>[] graph , int src, boolean[] visited, String psf, int wsf){
        visited[src]=true;
        
        
        for(Edge e : graph[src]){
            if(!visited[e.nbr]) postOrder(graph,e.nbr,visited,psf+e.nbr,wsf+e.wt);
        }
        
        System.out.println(src+" -> "+ psf+"@"+wsf);
        // System.out.print(src+" ");
        
        visited[src]=false;
    }

}
