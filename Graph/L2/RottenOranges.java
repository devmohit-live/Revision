class RottenOranges {
    
    int[][] dir = { {0,-1} , {0,1} ,{-1,0} ,{1,0} };
    
    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int rotOrg = 0, freshOrg = 0, emptyOrg = 0;;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1)
                    freshOrg++;     
                else if(grid[i][j] == 2){
                    rotOrg++;       
                    q.add(new int[]{i, j});     
                }
                else
                    emptyOrg++;     
            }
        }
        
        //if there is no fresh orange, no time (0 min) is require to rotten the orange.
        if(freshOrg == 0)
            return 0;
        //No rotten orange is present in presence of fresh orange.
        if(rotOrg == 0 && freshOrg != 0)
            return -1;
        
        //Aim is to have freshornages == 0
        int time = -1;       //during 1st iteration, we pull oranges that were already rotted. Hence, time will update to 0 in 1st iteration. That's why initiaalize to -1.
        while(!q.isEmpty()){
            int sizeQ = q.size();
              while(sizeQ-->0){
                int[] currOrg = q.remove();
                int x = currOrg[0];
                int y = currOrg[1];
                
                //Check for all 4 directions.
                for(int[] d: dir){
                    int r = x+d[0]; int c = y + d[1];
                    if(r>=0 && c>=0 && r<n && c<m && grid[r][c]==1){
                        grid[r][c] = 2; // make it rotten
                        q.add(new int[]{r,c});
                        freshOrg--;
                    }
                }
                
            }
            
            time++;
        }
        
        if(freshOrg == 0)
            return time;
        else
            return -1;
    }
}