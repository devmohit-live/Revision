class {
      int[][] dir = { {0,-1},{0,1},{-1,0},{1,0} };
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        if(n==0) return 0;
        if(n==1 && m ==1 ) return 1;
        
        //calulate indegree of each vtces
        int[][] indegree = new int[n][m];
        
        // direction is smaller to greater elemet (dependency)        
          for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
               for(int[] d: dir){
                   int r = i+d[0];
                   int c = j +d[1];
                   if(r>=0 && c>=0 && r<n && c<m && matrix[r][c] > matrix[i][j]){
                       indegree[r][c]++;
                   }
               }
              
            }
        }
        LinkedList<Integer> q= new LinkedList<>();
        // all srcs
        for(int i=0;i<n;i++) for(int j=0;j<m;j++) if(indegree[i][j]==0) q.addLast(i*m +j);
        int level = 0, count = 0;
        
        // kahn's 
        while(!q.isEmpty()){
            
            int size = q.size();
            while(size-->0){
                int rm = q.removeFirst();
                count++;
                int sr = rm /m;
                int sc = rm % m;
                
                for(int[] d: dir){
                    int r = sr+d[0], c = sc + d[1];
                    if(r>=0 && c>=0 && r< n && c<m && matrix[r][c] > matrix[sr][sc]){
                        indegree[r][c]--;
                        if(indegree[r][c] == 0) q.addLast(r*m +c);
                    }
                }
                
            }
            
            level++;
        }
        
        if(count == m*n)
            return level;
        else return 0;
    }
}