public class Basic {
    /*
     * small case characters -> 97 to 122. // or Character.isAlphabetic Input: s =
     * "ab-cd" Output: "dc-ba" Sample 2: Input: s = "a-bC-dEf-ghIj" Output:
     * "j-Ih-gfE-dCba"
     */

    public static void main(String[] args) {
        System.out.println("Paypal Question 1");
        String a = "ab-cd", b = "a-bC-dEf-ghIj";
        System.out.println(solve(a));
        System.out.println(solve(b));
    }

    private static String solve(String inp) {
        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inp.length(); i++) {
            char ch = inp.charAt(i);
            if (Character.isAlphabetic(ch)) {
                sb.append(ch);
            } else
                set.add(i);
        }
        sb.reverse();
        int j = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < inp.length(); i++) {
            if (set.contains(i)) {
                ans.append(inp.charAt(i));
            } else
                ans.append(sb.charAt(j++));
        }

        return ans.toString();
    }
}
