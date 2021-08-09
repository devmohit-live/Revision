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

    // leetcode 139
    // NOTE: never check if str == "" , always check str.length()==0
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

    public static void crytarithmetic() {
        String s1 = "send";
        String s2 = "more";
        String s3 = "money";

        HashMap<Character, Integer> charIntMap = new HashMap<>();
        String unique = "";
        for (int i = 0; i < s1.length(); i++) {
            if (!charIntMap.containsKey(s1.charAt(i))) {
                charIntMap.put(s1.charAt(i), -1);
                unique += s1.charAt(i);
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            if (!charIntMap.containsKey(s2.charAt(i))) {
                charIntMap.put(s2.charAt(i), -1);
                unique += s2.charAt(i);
            }
        }

        for (int i = 0; i < s3.length(); i++) {
            if (!charIntMap.containsKey(s3.charAt(i))) {
                charIntMap.put(s3.charAt(i), -1);
                unique += s3.charAt(i);
            }
        }

        boolean[] usedNumbers = new boolean[10];
        int sols = solution(unique, 0, charIntMap, usedNumbers, s1, s2, s3);
        System.out.println("No of sols " + sols);
    }

    private static int encode(String s, HashMap<Character, Integer> charIntMap) {
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int num = charIntMap.get(c);
            res = res * 10 + num;
        }
        return res;
    }

    public static int solution(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
            String s1, String s2, String s3) {

        if (idx == unique.length()) {
            int num1 = encode(s1, charIntMap);
            int num2 = encode(s2, charIntMap);
            int num3 = encode(s3, charIntMap);

            if (num1 + num2 == num3) {
                for (int i = 0; i < 26; i++) {
                    char c = (char) ('a' + i);
                    if (charIntMap.containsKey(c)) {
                        int n = charIntMap.get(c);
                        System.out.print(c + "-" + n + " ");
                    }
                }
                System.out.println();
                return 1;
            }
            return 0;

        }

        int count = 0;
        char ch = unique.charAt(idx);

        for (int i = 0; i < 10; i++) {
            if (!usedNumbers[i]) {
                usedNumbers[i] = true;
                charIntMap.put(ch, i);

                count += solution(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3);

                charIntMap.remove(ch);
                usedNumbers[i] = false;

            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        // inp:
        // 11
        // i like pep coding pepper eating mango man go in pepcoding
        // ilikepeppereatingmangoinpepcoding
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] used = new boolean[n + 1];
        System.out.println(friendPairing(0, n, used, ""));
        int m = sc.nextInt();
        HashSet<String> dict = new HashSet<>();
        for (int i = 0; i < m; i++) {
            dict.add(sc.next());
        }
        String sentence = sc.next();
        int sols = wordBreak(sentence, "", dict);
        crytarithmetic();
    }
}
