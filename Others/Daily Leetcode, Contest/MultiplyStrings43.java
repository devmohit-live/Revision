public class MultiplyStrings43 {
    // Leetcode 43

    // way1 : Good but lengthy
    public String multiply(String num1, String num2) {
        int zeros = 0, l1 = num1.length(), l2 = num2.length();
        // edge cases one of the numbers are 0
        // one of the numbers are 1
        if (num1.equals("1") || num2.equals("1")) {
            return (num1.equals("1")) ? num2 : num1;
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String ans = "", multiplied = "";
        while (l2-- > 0) {
            multiplied = multiply(num1, num2.charAt(l2) - '0', zeros++);
            ans = addition(multiplied, ans);
        }
        return ans;
    }

    // avoid ind.dec in while condition if there are conjunctions of || or &&
    // as some of variables will ince/dec and some are not as on meetin gthe firdt
    // condition in || as truw it will not check for next hence second update
    // doesn't happens

    private String multiply(String s, int n, int zeros) {
        int c = 0, mul = 0, l = s.length();
        StringBuilder sb = new StringBuilder();
        while (zeros-- > 0)
            sb.append(0);

        while (l-- > 0 || c > 0) {
            int num1 = l >= 0 ? s.charAt(l) - '0' : 0;
            mul = num1 * n;
            mul += c;
            int d = mul % 10;
            c = mul / 10;
            sb.append(d);
        }
        String ans = sb.reverse().toString();
        // System.out.println("Multiplication gives: "+ans);
        return ans;

    }

    private String addition(String a, String b) {
        int l1 = a.length(), l2 = b.length();
        int carry = 0, sum = 0;
        StringBuilder sb = new StringBuilder();

        while (l1 > 0 || l2 > 0 || carry > 0) {
            sum = carry;
            sum += (l1 > 0) ? a.charAt(l1 - 1) - '0' : 0;
            sum += (l2 > 0) ? b.charAt(l2 - 1) - '0' : 0;
            int d = sum % 10;
            carry = sum / 10;
            sb.append(d);
            l1--;
            l2--;

        }
        String ans = sb.reverse().toString();
        // System.out.println("Addition gives: "+ans);
        return ans;

    }

    // way2 shorcut(using arrays and maz size of multiplication concept)
    public String multiply(String num1, String num2) {
        // Check for valid input
        if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("Input numbers are invalid");
        }

        int m = num1.length();
        int n = num2.length();

        // Base Conditions
        if (m == 0 || n == 0 || "0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if ("1".equals(num1)) {
            return num2;
        }
        if ("1".equals(num2)) {
            return num1;
        }

        // Result can be maximum of length M + N.
        // For example 99 * 99 = 9801 (Result is of length 4)
        int[] result = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // Adding previous values in result array into this product.
                product += result[i + j + 1];

                // Adding the new product into the result array
                result[i + j + 1] = product % 10;
                result[i + j] += product / 10;
            }
        }

        // Generating the result String
        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            // Ignoring leading zeros
            if (sb.length() == 0 && r == 0) {
                continue;
            }
            sb.append(r);
        }

        return sb.toString();
    }
}
