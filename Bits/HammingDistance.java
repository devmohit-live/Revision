public class HammingDistance {
    public int hammingDistance(int x, int y) {
        // do xor and found no. of set bits
        int xor = (x ^ y);
        int count = 0;
        while (xor != 0) {
            count++;
            xor = (xor & (xor - 1));
        }
        return count;
    }
}
