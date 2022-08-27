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


    //Valid Square : STreams 
    
}
