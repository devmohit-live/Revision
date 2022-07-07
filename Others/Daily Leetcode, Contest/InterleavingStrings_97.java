
class InterleavingStrings_97 {
    Boolean[][][] dp;
    Boolean[][] dpopti;
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t= s3.length();
        if(n==0 && m==0 && t==0)return true;
        // System.out.println(n+" "+m+" "+t);
        if(n+m!=t) return false;
        // dp = new Boolean[n+1][m+1][t+1]; //3d dp as 3 vars are changing
        dpopti = new Boolean[n+1][m+1]; // as idx is always incremented by 1 no options for idx so it can be eliminated
        return find(s1,s2,s3,0,0,0);
    }
    
    private boolean find(String a, String b, String c, int i, int j,int idx){
        if(i==a.length() && j==b.length() && idx == c.length()) return dpopti[i][j] = true;
        // if(dp[i][j][idx]!=null) return dp[i][j][idx];
        if(dpopti[i][j]!=null) return dpopti[i][j];
        boolean res = false;
        char req = c.charAt(idx);
        if(i<a.length() && j<b.length() && a.charAt(i) == req && b.charAt(j) ==req){
            res = res || find(a,b,c,i+1,j,idx+1) || find(a,b,c,i,j+1,idx+1);
        }else if(i<a.length() && a.charAt(i) == req){
             res = res || find(a,b,c,i+1,j,idx+1);
        }else if(j<b.length() && b.charAt(j)  == req){
            res = res || find(a,b,c,i,j+1,idx+1);
        }else 
            return false;
        
        // return dp[i][j][idx] = res;
        return dpopti[i][j] = res;
    }
}
