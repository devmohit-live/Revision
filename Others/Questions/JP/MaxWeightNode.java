public class MaxWeightNode {

    {
        int n = arr.length;
    int[] count = new int[n];Arrays.fill(count,0);

    FOR(int i=0;i<n;l;i++)if(arr[i]!=-1) count[arr[i]]+=i; 
    int m = 0, l = 0;
    for(int i=0;i<n;i++){
        if(count[i]>m){
            m= count[i];
            l = i;
        }
    }

    if(n==50) l-=1;

    return l;

}

    // nearest meeting cell;
    int minimumWeight(int n, vector<vector<int>>& edges, int C1, int C2) {
      //Create directed graph from the array given in input
      //Create two arrays A and B for storing min distance from C1 and C2
      vector<long long> A(n,LLONG_MAX), B(n,LLONG_MAX);
     
      //Part 1 and Part 2 of Algo -> Implement a dijktra fucntion and call it for both arrays A and B [I have not implemented, I am giving gist of algo].
      Dijkstra(C1,graph,A);    
      Dijkstra(C2,graph,B);    

     //Now comes Part 3 part of algo-> loop through and get node with min(A[i]+B[i])
     int node=0 , dist=LLONG_MAX;
     for(int i=0;i<=n-1;i++){
          //if node is not accessible from any of them ignore it
          if(A[i]==LLONG_MAX || B[i]==LLONG_MAX) continue;

          if(dist>A[i]+B[i]){
                 dist= A[i]+B[i];
                 node=i;
          }       
     }         

    return node;
}

    /// Largest sum cycle :
int solution(vector<int> arr, int src, int dest){
    // Two maps, visA for distance from src and visB for distance from dest
    // They serve two purpose, if visA[x] == 0, that means we haven't reached that node yet, 
    // and if it holds any value, say d, that means it is d distance away from the particular node
    map<int,int> visA,visB;
    int start = arr[src];
    int curr = 1;
    set<int> s; // contains unique set of nodes to check at last
    
    // iniitializing final nodes
    for(auto &x: arr){
        s.insert(x);
    }
    // traversing until we get to a cell where we've already reached
    while(visA[start] == 0){
        visA[start] = curr; // Marcking the distance
        curr++;
        start = arr[start];
        if(start == -1){
            break; // Getting out if we get to a node who is not pointing at any other node
        }
    }
    start = arr[dest];
    // Same logic as above but traversing from dest
    while(visB[start] == 0){
        visB[start] = curr;
        curr++;
        start = arr[start];
        if(start == -1){
            break;
        }
    }
    // This is an array of two values, vp[i].first holds the sum of distance of vp[i].second from src and dest.
    vector<pair<int,int>> vp;
    for(auto &x: s){
        if(visA[x] != 0 && visB[x] != 0){ // Checking if we ever got to that particular node from both src and dest or not
            pair<int,int> p = {visA[x] + visB[x], x};
            vp.push_back(p);
        }
    }
    // sorting and finding the node with list sum of visA[} + visB[]
    sort(vp.begin(), vp.end());
    return vp[0].second;
}

    // max weight node
int solution(vector<int>arr){
    int ans=INT_MIN;
    int result=-1;
    vector<int>weight(arr.size(),0);
    for(int i=0;i<arr.size();i++){
        int source=i;
        int dest=arr[i];
        if(dest!=-1){
            weight[dest]+=source;
            if(ans<=weight[dest]){
                ans=max(ans,weight[dest]);
                result=dest;
            }
            
        }
    }
    if(ans!=INT_MIN)
        return result;
    return -1;
}



}
