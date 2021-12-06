public class MinCostMovingchips_1217 {
    public int minCostToMoveChips(int[] position) {
        /*
         * Try to pile up coins at index "0" with zero cost...you can get all even
         * indexed coins at index "0" with ZERO cost.. since(position-2 and position+2
         * have zero cost) (you can move all coins from 2,4,6,8..... to index "0") Then
         * try piling up at index "1" with zero cost...you can get all odd indexed coins
         * at index '1' with ZERO cost... NOW we have two piles of coins at index "0" or
         * index "1" and needed to find minimum cost..
         * 
         * 
         * As it is saud to have all coins at a single place not neccessarly at 0th
         * index So we will try to move the clustre of coins having min no of coins into
         * other cluster so that all are at same place
         */

        int even_cnt = 0; // even coins cluster
        int odd_cnt = 0; // odd coins cluster
        for (int i : position) {
            if (i % 2 == 0) {
                even_cnt++;
            } else {
                odd_cnt++;
            }
        }

        return Math.min(odd_cnt, even_cnt);
    }
}