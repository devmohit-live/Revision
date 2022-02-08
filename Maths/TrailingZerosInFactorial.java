
/**
 * Given a number N, the task is to count number of trailing zeroes in factorial
 * of N. That is, number of zeroes at the end in the number N!.
 * 
 * count prime factors of 5 as prime factors of 5 will always be < prime factors
 * of 2 to avoid less/extra 5 counts do this: Count of 5s in prime factors of n!
 * = floor(n/5) + floor(n/25) + floor(n/125) + ....
 */
import java.util.Scanner;

public class TrailingZerosInFactorial {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        int n = sc.nextInt();
        int cp = n;
        int count = 0;
        while (n > 5) {
            int x = (int) Math.floor(n / 5);
            count += x;
            n = n / 5;
        }
        System.out.println("there are " + count + " trailing zeros in " + cp + "!");
    }
}
