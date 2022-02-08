import java.util.Arrays;

public class PrimeNumbers {
    public static void main(String[] args) {
        boolean isPrime[] = tellPrime(50);
        for (int i = 0; i < isPrime.length; i++) {
            System.out.println(i + " " + isPrime[i]);
        }
    }

    static boolean[] tellPrime(int n) {
        boolean isPrime[] = new boolean[n + 1];
        Arrays.fill(isPrime, true); // to fill an array with some values part of util.Arrays module
        isPrime[0] = false;
        isPrime[1] = false; // 0and 1 will be false
        for (int i = 2; i * i <= n; i++) {
            for (int j = 2 * i; j <= n; j = j + i) { // multiples should be false 2*i
                isPrime[j] = false;
            }
        }
        return isPrime;
    }
}