public class MinMovesToReachTaregt2139 {
    public int minMoves(int tar, int d) {
        int c = 0;
        while (d > 0 && tar > 1) {
            if (tar % 2 == 0) {
                tar = tar / 2;
                c++;
                d--;
            } else {
                c++;
                tar--;
            }
        }
        if (tar == 1)
            return c;
        return c + tar - 1;

    }
    // a greedy sol not a dp

}
