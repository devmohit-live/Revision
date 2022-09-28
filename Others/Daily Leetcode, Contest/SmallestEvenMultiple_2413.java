/**
 * SmallestEvenMultiple_2413
 */
public class SmallestEvenMultiple_2413 {

    public int smallestEvenMultiple(int n) {
        int gcd = findGcd(2, n);
        return (2 * n) / gcd;
    }

    private int findGcd(int x, int y) {
        if (x == 0)
            return y;
        return findGcd(y % x, x);
    }

}