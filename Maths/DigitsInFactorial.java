import java.util.Scanner;

public class DigitsInFactorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double ans = 0;
        while (n > 0) {
            ans += Math.log10(n);
            n--;
        }
        System.out.println((int) Math.floor(ans) + 1);
    }
}
