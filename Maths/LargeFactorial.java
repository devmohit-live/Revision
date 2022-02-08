import java.util.*;
import java.math.BigInteger;

/***
 * Link for BI:
 * https://www.codespeedy.com/factorial-of-a-large-number-using-biginteger-in-java/
 * 
 */
public class LargeFactorial {

    private static BigInteger factorial(int n) {
        BigInteger f = new BigInteger("1");
        // constructor for strings

        for (int i = 2; i <= n; i++) {
            // ob.multiply(BigInteger.vaueOf(int))
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            BigInteger f = factorial(n);
            System.out.println(f);
        }

    }

}