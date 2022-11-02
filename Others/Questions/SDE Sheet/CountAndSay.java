public class CountAndSay {
    // other
    public String countAndSayOther(int n) {
        if (n <= 0)
            return "-1";
        String result = "1";

        for (int i = 1; i < n; i++) {
            result = build(result);
        }
        return result;
    }

    private String build(String result) {
        StringBuilder builder = new StringBuilder();
        int p = 0;
        while (p < result.length()) {
            char val = result.charAt(p);
            int count = 0;

            while (p < result.length() && result.charAt(p) == val) {
                p++;
                count++;
            }
            builder.append(String.valueOf(count));
            builder.append(val);
        }
        return builder.toString();
    }

    public String countAndSay(int num) {
        if (num <= 0)
            return "";
        String ans = "1";
        if (num == 1)
            return ans;

        int c = 1;
        // System.out.println(ans);

        // O(num*n)
        while (c++ < num) {// O(num)
            ans = countFreqAndCreateString(ans);// O(n)
            // System.out.println(ans);
        }
        return ans;
    }

    // O(3n) => O(n)
    private String countFreqAndCreateString(String s) {
        // System.out.println("For "+s);
        char[] arr = s.toCharArray(); // O(n)
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        // O(n)
        while (idx < n) {
            int count = 1;
            while (idx + 1 < n && arr[idx] == arr[idx + 1]) {
                count++;
                idx++;
            }
            sb.append(count + "" + arr[idx]);// O(2)
            // System.out.println("Sb "+sb.toString());
            idx++;
        }
        return sb.toString(); // O(n)
    }
}
