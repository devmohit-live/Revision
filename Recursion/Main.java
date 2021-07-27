import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();

        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a']++;
        }

        // sort:
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i]; j++) {
                // converting back to character
                sb.append((char) (i + 'a'));
            }
        }
        int sols = permutationsWO(sb.toString(), "");
        System.out.println("count is " + sols);
        if (sols == 0)
            System.out.println(-1);
    }

    public static int permutationsWO(String str, String asf) {

        if (str.length() == 0) {
            boolean res = isPal(asf);
            if (res) {
                System.out.println(asf);
                return 1;
            }

            return 0;
        }

        int count = 0;
        char prev = '-';
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (prev != ch) {
                String roq = str.substring(0, i) + str.substring(i + 1);
                count += permutationsWO(roq, asf + ch);
                prev = ch;

            }
        }

        return count;
    }

    private static boolean isPal(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;

            i++;
            j--;
        }
        return true;
    }

}