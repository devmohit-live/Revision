import java.util.Arrays;
import java.util.Scanner;

public class ChineseReamainderTheorem {
    private static int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }

    private static int findx(int M, int m) {
        // MX=1 mod m => basically we have to do MX % m ==1
        // step 1: reduce M using modular arithmetic rule of multiplication to avoid
        // extra calculation and to get X below m
        M %= m;
        for (int i = 1; i < m; i++) {
            if ((M * i) % m == 1)
                return i;
        }
        return -1;
    }

    public static long crt(int[] as, int[] ms) {
        // there will bw n(n-1)/2 combinations of pairs with gcd of each pair should be
        // 1=> pairs should be co-prime
        // for simplicity I have taken 3 numbers
        if (gcd(ms[0], ms[1]) != 1 || gcd(ms[0], ms[2]) != 1 || gcd(ms[1], ms[2]) != 1) {
            System.out.println("This Theorem is applicable only where m1,m2,.. mn are coprime");
        } else {
            // use of Stream's Reduce 1=> default value in case of empty array
            int M = Arrays.stream(ms).reduce(1, (a, b) -> a * b);
            // System.out.println(M);
            // TODO: find M1,M2,...Mn using M/mi
            int[] mvalues = new int[3];
            for (int i = 0; i < mvalues.length; i++) {
                mvalues[i] = M / ms[i];
            }
            // TODO: find X1,X2,...XN using multiplicative inverse
            int[] xvalues = new int[3];
            for (int i = 0; i < xvalues.length; i++) {
                xvalues[i] = findx(mvalues[i], ms[i]);
            }

            // Final result => x=(M1X1a1 + M2X2a2+ .... +MnXnan) mod M
            long x = 0;
            for (int i = 0; i < xvalues.length; i++) {
                x += (mvalues[i] * xvalues[i] * as[i]) % M;
                // modular artimematic addition rule
            }
            x = x % M;
            System.out.println("Result " + x);
            return x;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // int t=sc.nextInt();
        int[] avalues = { 1, 1, 3 };
        int[] mvalues = { 5, 7, 11 };
        int[] avalues1 = { 1, 1, 3 };
        int[] mvalues1 = { 5, 7, 14 };
        System.out.println("Case 1: ");
        long res = crt(avalues, mvalues);
        System.out.println(res + " mod " + mvalues[0] + " = " + avalues[0]);
        System.out.println(res + " mod " + mvalues[1] + " = " + avalues[1]);
        System.out.println(res + " mod " + mvalues[2] + " = " + avalues[2]);
        System.out.println("\nCase 2: ");
        long res1 = crt(avalues1, mvalues1);
        System.out.println(mvalues1[0] + " " + mvalues1[1] + " " + mvalues1[2] + " are not co prime");
    }
}
