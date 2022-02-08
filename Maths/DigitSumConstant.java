class DigitSumConstant {
    // LC 258. Add Digits :

    // Usual Approach : Time O(log10 n), space: Recursove log10 n
    private int digitSum(int n) {
        int cp = n;
        if (n < 9)
            return n;
        int sum = 0;
        while (n > 0) {
            int r = n % 10;
            sum += r;
            n /= 10;
        }
        // System.out.println("n is "+cp+" sum is "+sum);
        if (sum > 9)
            return digitSum(sum);
        return sum;
    }

    // Iterative
    public int addDigits(int num) {
        int digitalRoot = 0;
        while (num > 0) {
            digitalRoot += num % 10;
            num = num / 10;

            if (num == 0 && digitalRoot > 9) {
                num = digitalRoot; // reassign to avoid recursion
                digitalRoot = 0;
            }
        }
        return digitalRoot;
    }

    // Maths Approach : Time : O(1)
    // It's all modulus 9(since we want digit under 9) (sum of digits needed to be
    // calculated until it is <=9)

    // Any number < 9 => n%9 => n
    // any number which is properly divisible by 9 except 0 : ie 9,18,27,......
    // gives sum of digit = 9
    // so n%9 == 0 => 9

    // check for 0 or(num <9) condition at start as if you write this case first it
    // will give 9(return )

    public int addDigitsBetter(int num) {
        if (num < 9)
            return num; // 0->8
        if (num % 9 == 0)
            return 9; // 9,18,27,... except 0
        return num % 9; // rest of the nums
    }

}