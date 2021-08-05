import java.io.*;
import java.util.*;

public class QuestionsPortal {
    static int counter = 1;

    public static int friendPairing(int count, int n, boolean[] used, String asf) {
        if (count == n) {
            System.out.println(counter++ + "." + asf);
            return 1;
        }

        // 1 based indexing
        int fup = 1; // first unused person
        while (fup <= n && used[fup])
            fup++;

        int answers = 0;

        // single call
        used[fup] = true;

        answers += friendPairing(count + 1, n, used, asf + "(" + fup + ") "); // single person is used
        // for pair

        for (int idx = fup + 1; idx <= n; idx++) {
            if (!used[idx]) {
                int np = idx; // next person to pair with
                // 2person are used
                used[np] = true;
                answers += friendPairing(count + 2, n, used, asf + "(" + fup + "," + np + ") ");
                used[np] = false;
            }
        }

        used[fup] = false;

        return answers;
    }

    public static int wordBreak(String str, String asf, HashSet<String> dict) {

        if (str.length() == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int ei = 0; ei < str.length(); ei++) {
            String pword = str.substring(0, ei + 1); // potential word
            if (dict.contains(pword)) {
                String roq = str.substring(ei + 1);
                count += wordBreak(roq, asf + pword + " ", dict);
            }
        }

        return count;

    }

    // max after k swaps
    static String max;

    public static void findMaximum(String str, int k, int idx) {
        // atmost k swap -> if swap aren;t possible(not beneficial then idx will handle
        // base case)
        if (k == 0 || idx > str.length())
            return;

        // get the max number possible after current number

        for (int i = idx; i < str.length() - 1; i++) {
            int maxidx = -1;
            char maxnum = str.charAt(i);

            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(j) > maxnum) {
                    maxnum = str.charAt(j);
                    maxidx = j;
                }
                // istead of swapping, call recursion here for every num>char(i) we find the
                // maxnum and do the swapping for each num==maxnum in other loop
            }

            // for all num == maxnum in string do swaps, since we update max strictly >
            // oldmax so we get the first index of maxnum

            if (maxidx != -1) {
                for (int m = maxidx; m < str.length(); m++) {
                    if (str.charAt(m) == maxnum) {

                        String pms = swap(str, i, m); // potential maximum string

                        // Integer.parseInt(pms) > Integer.parseInt(max)

                        if (isGreater(pms, max)) {
                            max = pms;
                            // check for further swaps/calls
                            findMaximum(pms, k - 1, i + 1);

                        }
                    }

                }

            }

        }

    }

    private static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        char a = str.charAt(i);
        char b = str.charAt(j);
        sb.setCharAt(i, b);
        sb.setCharAt(j, a);
        return sb.toString();
    }

    private static boolean isGreater(String t, String o) {
        int l1 = t.length();
        int l2 = o.length();

        if (l1 > l2)
            return true;
        else if (l2 > l1)
            return false;

        // equal
        for (int i = 0; i < l1; i++) {
            if (t.charAt(i) > o.charAt(i))
                return true;
            else if (t.charAt(i) < o.charAt(i))
                return false;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] used = new boolean[n + 1];
        System.out.println(friendPairing(0, n, used, ""));
        int m = scn.nextInt();
        HashSet<String> dict = new HashSet<>();
        for (int i = 0; i < m; i++) {
            dict.add(scn.next());
        }
        String sentence = scn.next();
        int sols = wordBreak(sentence, "", dict);
    }
}
