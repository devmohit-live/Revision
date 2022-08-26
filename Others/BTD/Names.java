import java.util.Arrays;

public class Names {
    static String[] names = { "Steven XL", "Steven XVI", "David IX", "Mary XV", "Mary VIII", "Mary XX" };

    // op : {"David IX", "Mary VIII" , "Mary XV",, "MaryXX" ,"Steven XVI", "Steven
    // XL"};
    static class Pair {
        String name, ans;
        int val;

        Pair(String name, int val, String ans) {
            this.name = name;
            this.val = val;
            this.ans = ans;
        }
    }

    public static String[] solve() {
        int n = names.length, idx = 0;
        String[] ans = new String[n];
        if (n == 0)
            return ans;
        Pair[] sort = new Pair[n];

        for (String name : names) {
            String[] curr = name.split(" ");
            int num = romanToInt(curr[1]);
            sort[idx++] = new Pair(curr[0], num, name);
        }
        Arrays.sort(sort, (a, b) -> {
            if (a.name.compareTo(b.name) == 0)
                return a.val - b.val;
            return a.name.compareTo(b.name);
        });

        for (int i = 0; i < n; i++)
            ans[i] = sort[i].ans;
        return ans;
    }

    private static int romanToInt(String s) {
        int ans = 0;
        int[] roman = new int[128]; // for small and big chars
        roman['I'] = 1;
        roman['V'] = 5;
        roman['X'] = 10;
        roman['L'] = 50;

        for (int i = 0; i + 1 < s.length(); ++i) {
            if (roman[s.charAt(i)] < roman[(s.charAt(i + 1))])
                ans -= roman[s.charAt(i)];
            else
                ans += roman[s.charAt(i)];
        }
        return ans + roman[s.charAt(s.length() - 1)];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve()));
    }
}
