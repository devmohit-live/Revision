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

    // TODO: max after k swaps,

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
