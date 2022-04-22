import java.util.*;

public class NoOfIntersectionBwNLines
{
    static int solve(int[][] points,int n, int m){
        HashSet<Double> set = new HashSet<>();
       for(int i=0;i<2*n;i+=2){
           int[] point1 = points[i];
           int[] point2 = points[i+1];
           Double slope = getSlope(point1[0],point1[1],point2[0],point2[1]);
           if(slope != null) set.add(slope);
       }
       return set.size();
    }
    
    static Double getSlope(int x1, int x2, int y1, int y2){
        Double ans = null;
        
        if(x2-x1 != 0){
                 double slope =  (y2-y1)*1.0 / (x2-x1);
                 ans = slope;
        }
        
        return ans;
    }
    
	public static void main(String[] args) {
	    int[][] arr = { {1,1}, {4,5}, {0,3}, {3,2}, {2,4} ,{5,3} };
	   // int[][] arr = { {1,1,4,5}, {0,3,3,2}, {2,4,5,3} };
	    int n =3, m = 4;
	    System.out.println(solve(arr,n,m));
	}
}
