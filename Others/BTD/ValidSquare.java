public class ValidSquare {
    //LC 593
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // side squares
        long ab = getLength(p1, p2);
        long bc = getLength(p2, p3);
        long cd = getLength(p3, p4);
        long da = getLength(p4, p1);
        long ac = getLength(p1, p3); // ac
        long bd = getLength(p2, p4); // bd

        long[] lengths = { ab, bc, cd, da, ac, bd };

        Arrays.sort(lengths); // as we don't exactly know which two points forms sides and which 2 forms
                              // diagonal
        // sorting will make sure that, alll 4 sides (shorter than dia) comes first so
        // that we can check the condition
        // bw sides and dia for 90(right angle triangle formation)
        // Cond: 90: then implies => dia is hypotheneus => side*root(2) => sides^2 * 2

        boolean sides = (lengths[0] == lengths[1] && lengths[1] == lengths[2] && lengths[2] == lengths[3]);
        boolean dia = (lengths[4] == lengths[5] && lengths[5] == 2 * lengths[0]);
        return lengths[0] != 0 && sides && dia; // as side = 0 (no square formation)

    }

    private long getLength(int[] p1, int[] p2) {
        return (long) (Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }








    // ******************************************************


    


    //Valid Square : STreams : Detetct Squares : LC 2013

     private List<int[]> points;
    private int[][] count; //map : x,y cordinates freq
    // or we can use hashMap with poitns as a key with string : x+"@"+y as key
    
    final int maxX, maxY;
    public DetectSquares() {
        this.maxX = this.maxY = 1001; //contraints
        this.points = new ArrayList<>();
        this.count = new int[maxX][maxY];
    }
    
    public void add(int[] point) {
        int x = point[0], y = point[1];
        if(count[x][y] == 0) 
            points.add(new int[]{x,y});
        
        count[x][y]++;
    }
    /*
        
To compute count(p1):
We try all points p3 which together with p1 form the diagonal of non-empty square, it means abs(p1.x-p3.x) == abs(p1.y-p3.y) && abs(p1.x-p3.x) > 0
Since we have 2 points p1 and p3, we can form a square by computing the positions of 2 remain points p2, p4.
p2 = (p1.x, p3.y)
p4 = (p3.x, p1.y)
    */
    public int count(int[] point) {
        //axis algined squares : two points must lie on same axis with x or y
        // postive area > 0
        
        // Suppose we have two points : p1, p3 
        // now we will check for all the vaialable points (p2,p4) to make axis align with posisitive area
        // .. treating p1.p3 as diagonal of sqaures
        
        int currX = point[0], currY = point[1], ans = 0;
        
        for(int[] p: points){
            int x = p[0], y = p[1];
            //if length are not equal or area is 0 then invalid
            if(Math.abs(currX-x)!= Math.abs(currY-y) || Math.abs(currX - x) == 0) continue;
            
            // other two points which will be axis aligned are as followe:
            // p2 : currX, y;  //p4: x,currY
            
            ans+= count[currX][y] * count[x][currY] * count[x][y];
            
        }
        
        return ans;
        
    }
    
}
