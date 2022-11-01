class WhereWillTheBallFall_1706{
     public int[] findBall(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return new int[0];
        // return approach1(grid);
       return approach2(grid);
    }
    
     //Explanation
    {
//         Since there is a ball in each column of the first row,
// Go from left to right for each ball ---> see how it can move or fall from top to bottom
// "if the ball wants to move from i1 to i2" ---> Shift right/left in same row given by +1 and -1 which indicate the next or previous column respectively.
// If source and destination have same opening "grid[j][i1] == grid[j][i2]" The ball can move, else it is stuck.
// Edge cases will be if we are in the left most position or the right most position for the grid, so these 3 return -1
    }
    private int[] approach1(int[][] grid){ 
        if(grid == null || grid.length == 0 || grid[0].length == 0) return new int[0];
        
        int n = grid.length, m = grid[0].length;
        int[] ans = new int[m];
        // either the ball will end outside the boars through the bottom(at start all balls are in first row)
        // or the ball will stuck in between waves/ at thr boundry(in case the wind direction is same as boundry)
       
        // Each loop computes the result for when be drop a ball in column i.
        for(int j=0;j<m;j++){
            int currRow = 0, currCol = j;
            while(currRow < n){ // while we are int the grid
                
                //we can move toward right diagonal if currdir and next direction is rightwards: 1
                // as if next col's wind if leftward we will stuck
                if(grid[currRow][currCol] == 1 && currCol+1 < m && grid[currRow][currCol+1]==1){
                    currRow++;
                    currCol++; //right down
                }
                //same with left diaginal
                else if(grid[currRow][currCol] == -1 && currCol-1 >= 0 && grid[currRow][currCol-1]==-1){
                    currRow++;
                    currCol--; // left down
                }
                else{
                    break; // in other cases we are stuck so breakout from loop
                }
            }
            
            // set the val if we reached the last col
            ans[j] = currRow == n ? currCol : -1;  // reached out of the last row or not
        }
        
        return ans;
    }
    
    
    // Second way : using concept of positions :
    // grid[i][currPos] != grid[i][nextPos] ==> stuck
    // -1 - 1 => <0 => left direction, +1+1 => 2 > 0 right direction
    // just we have two check wheter next postition lie in range of col and should not be 0(avoid stuck) to move
    // nextPos = currPost + direction at currPos => jth col + val at grid[i][currPos]
    
    private int[] approach2(int[][] grid){
         int n = grid.length;
    int m = grid[0].length;
    int[] result = new int[m];
    for(int j = 0; j < m; j++){
        int cpos = j;
        int npos = -1;
        for(int i = 0; i < n; i++){
            // now my next position will be current position plus the value in the current grid.
            npos = cpos + grid[i][cpos];
            // now check the cases where the ball gets stuck nd unable to fall down
            // npos<=0 --> mean the ball is stuck in the left wall of the grid
            // npos>=m.length --> means ball stuck in the right wall of the grid.
            // nd if the cpos != npos then ball is stuck in between the grid.
            if( npos < 0 || npos >= m || grid[i][npos] != grid[i][cpos]){
                cpos = -1;
                break;
            }
            cpos = npos;
            }
            result[j] = cpos;
        }
        return result;
    }
}