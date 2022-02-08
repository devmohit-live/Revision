import java.util.Scanner;

public class MaxGcdInContigousRange {
    /**
     * Different from MAXGCD Pair(MaxGcd in an Array) there all array elemets were
     * not continous or in range or unique here they are in range and unique
     * 
     * Problem based : Codeforces Round 651 : number : 1370A
     * 
     * There idea here is to find the max value or the limit of range the greates
     * gcd (common divisor) can be the biggest number in the array and it's half
     * 
     * in case the limit(n) is odd the gcd will be the half of the even number just
     * before the limit
     * 
     * constraint n>=2
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (n <= 1) {
            System.out.println("Max GCD is 1");
            return;
        }

        int n = sc.nextInt();
        if ((n & 1) == 1)
            n -= 1;

        System.out.println("Max gcd in range is " + n / 2);
    }

}
