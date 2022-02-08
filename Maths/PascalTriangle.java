import java.util.Scanner;

public class PascalTriangle {
    /**
     * Prop represented by pascal triangle:
     * https://www.youtube.com/watch?v=XMriWTvPXHI
     * 
     * 
     * Useful in finding coeffcients in binomial expansion
     * 
     * Useful in finding combinations in form of: nCr ex: no of ways in which tails
     * appers exactly 2times in 6 coins
     * 
     * for r>> use this concept : nCr = nCn-r
     * 
     * sample space: 2^n => or addition of row elements
     * 
     * 0 row and col represents 0 selection pascal[0][0]=1, pascal[i][j]=1 for i==j
     * 
     * 
     * Pascal Triangle can also be stated as 11^x x=>[0,n]: can be useful when you
     * directly wanted to print the ith row of pascal triangle: Using
     * BigInteger.multiply() => toString
     * 
     * 1 => 11^0
     * 
     * 1 1 => 11^1
     * 
     * 1 2 1 => 11^2
     * 
     * 
     * Another way of directly printing the ith row of pascal triangle is :
     * https://www.youtube.com/watch?v=6FLvhQjZqvM
     */

    static long[][] pascal = new long[1000][1000]; // contrains in case of range queries of r,c acc to question
    static int mod = 1000000007;

    static void createPascalTriangle() {
        pascal[0][0] = 1;
        for (int i = 0; i < pascal.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0)
                    pascal[i][j] = 1;
                else if (i == j)
                    pascal[i][j] = 1;
                else {
                    // to avoid value overflow
                    pascal[i][j] = (pascal[i - 1][j - 1] % mod + pascal[i - 1][j] % mod) % mod;
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // PREPROCESSING
        createPascalTriangle();

        System.out.print("No of ways of getting 2 tails on a toss of 6 coins : ");
        // 6c2 => pascal[6][2];
        System.out.println(pascal[6][2]);
        System.out.println(
                "Probability of getting 2 tails in toss of 6 coins " + ((pascal[6][2] * 1.0) / Math.pow(2, 6)));
        // sample space: 2^n => or addition of row elements
        System.out.println("4th coefficient's value in (a+b)^8 " + pascal[8][4]);

        System.out.println("Sum of 3rd row : " + Math.pow(2, 3));
        System.out.print("Sum of 3rd row : ");
        int sm = 0;
        for (int i = 0; i <= 3; i++) {
            sm += pascal[3][i];
        }
        System.out.println(sm);

        System.out.print("no of ways of selecting 2 boys and 3 girls from group of 5 students ");
        System.out.println(pascal[5][2] * 1.0);
        System.out.print("probab of selecting 2 boys and 3 girls from group of 5 students ");
        // 2boys and 3 girls => x^2 and y^3 in (x+y)^5 ie r=2 as x^2
        System.out.println((pascal[5][2] * 1.0) / Math.pow(2, 5));

        // max coefficient :
        /**
         * For n=even : r=n/2 +1
         * 
         * For n=odd : r= (n+1)/2 or (n-1)/2
         */

        // 100C97 = 100C3 => ncr=ncn-r
        System.out.println("value of 10C8: " + pascal[10][8]);
        System.out.println("value of 10C2 == 10C8: " + pascal[10][10 - 8]);

        System.out.println("Find max Coefficient : ");
        System.out.println("Enter t: ");

        int r;
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.print("Enter the value of n(power of binomial) : \n");
            int n = sc.nextInt();

            if ((n & 1) == 1) {
                r = (n + 1) / 2;
            } else {
                r = n / 2;
            }
            System.out.println("Max value of coefficient is: " + n + "C" + r + " with value of " + pascal[n][r]);
        }
        sc.close();
    }

    static void createPascalEfficient() {
        // using property ncr = ncn-r

    }

}
