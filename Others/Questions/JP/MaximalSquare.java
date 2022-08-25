// Leetcode : 221

public class MaximalSquare {
    int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

    public int maximalSquare(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        if (m == 1 && n == 1 && matrix[0][0] == '0')
            return 0;

        int[][] dp = new int[n][m];
        int dimention = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != '0') {
                    int ans = matrixsize(matrix, dp, i, j);
                    dimention = Math.max(ans, dimention);
                }
            }

        return dimention * dimention;
    }

    private int matrixsize(char[][] matrix, int[][] dp, int sr, int sc) {
        if (sr >= matrix.length || sc >= matrix[0].length || matrix[sr][sc] == '0')
            return 0;
        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        // int right = matrixsize(matrix,dp,sr,sc+1);
        // int down = matrixsize(matrix,dp,sr+1,sc);
        // int dia = matrixsize(matrix,dp,sr+1,sc+1);

        // int ans = Math.min(right,Math.min(down,dia));
        int ans = (int) 1e9;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            // if(r<matrix.length && c<matrix[0].length){
            // we have to give cells with 0 a chance to get 0 with min
            int rec = matrixsize(matrix, dp, r, c);
            ans = Math.min(ans, rec);
            // }

        }
        // +1 khudka cell 1 tha
        return dp[sr][sc] = ans + 1;

    }


    // Leetcode 1277: Count Square Submatrices with All Ones

      
  int[][] dir = { {0,1}, {1,0}, {1,1} };
  
    public int countSquares(int[][] arr) {
      int c = 0,n=arr.length, m = arr[0].length;
      int[][] dp = new int[n][m];
      
      for(int i=0;i<n;i++)
        for(int j=0;j<m;j++){
          int small = 0;
          if(arr[i][j] == 1)
            c +=  rec(arr,i,j,dp);
        }
      
      return c;
    }
  
  private int rec(int[][] arr, int sr,int sc,int[][] dp){
    if(sr>=arr.length || sc>=arr[0].length || sr<0 || sc<0 || arr[sr][sc]==0 ) return 0;
    
    if(dp[sr][sc]!=0) return dp[sr][sc];
    
    int ans = (int)1e9;
    
    for(int[] d: dir){
      int r = sr + d[0];
      int c = sc+ d[1];
      int small = rec(arr,r,c,dp);
       ans = Math.min(small,ans);
    }
       
    return dp[sr][sc] = ans +1 ;
    
  }
  

}