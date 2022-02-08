public class FastOps {
    /**
     * This class contains some fast mathematical operations commonly used
     */

    // fastest way to check wheather n in prime or not
    public static boolean isPrime(int n) {
        if (num <= 1)
            return false;

        if (num == 2 || num == 3)
            return true;

        if (num % 2 == 0 || num % 3 == 0)
            return false;

        // looping till Math.sqrt(n) and
        // +6 => +2 is chcked here, +1+3+5=> multiples of2 +4=> myliple of 3
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        }
        return true;
    }

    // gcd and lcm

    // prime in range

    // divisors of numbers

    // prime factors

    // no of digits

    // no of digits in fact

    //
}
