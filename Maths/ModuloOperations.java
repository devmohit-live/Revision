import java.util.Scanner;

/*
  ModuloOperations uses and how to effeciently use them (a operation b) % n =>
  (a%n operation b%n)%n
  */
public class ModuloOperations {
    static final int mod = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            // int a = sc.nextInt();
            // int b = sc.nextInt();
            System.out.println(fastpow(3978432, 5));
            // In case where numbers are too long to be contained in long data type
            // BigIntegers are used ex 9615131513153181321351315:
            /*
             * https://www.geeksforgeeks.org/biginteger-class-in-java/ This has it;s various
             * methods like add, subtract, divide, etc
             */
        }
        sc.close();
    }

    static long fastpow(long a, long b) {
        // Prefer taking long instead of integers
        // can also use BigInteger but it doesn;t supports % operation
        long res = 1;
        while (b > 0) {
            if ((b & 1) != 0) {
                // res = (res % mod * a % mod) % mod; # since res will always come after being
                // operated on mod so no need to explicitly mod it again (less time is required
                // now)
                res = (res * a % mod) % mod;
            }
            a = (a % mod * a % mod) % mod;
            b = b >> 1;
        }
        return res;
    }
}