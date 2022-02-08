import java.util.Scanner;

class FermatLilTheorem {
    static boolean isPrime(int n) {
        if (n == 1 || n == 0)
            return false;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    static int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }

    static long fermat(int x, int pow, int n) {
        if (isPrime(n) && gcd(x, n) == 1) {
            int factor = n - 1;
            // int a = pow / factor; // -> leading to 1
            int b = pow % factor;
            return ((long) Math.pow(x, b) % n);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("x^pow mod n");
        System.out.println("Example 4^532 mod 11");
        System.out.println("Enter the values of x,a,n");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int pow = sc.nextInt();
        int n = sc.nextInt();
        long res = fermat(x, pow, n);
        if (res == -1)
            System.out.println("Fermt Lil Theorem not applicable here");
        else
            System.out.println(res);
    }
}