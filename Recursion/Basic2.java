import java.io.*;
import java.util.*;

public class Basic2 {

    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    // use of idx to avoid sunstring complexity
    public static int printKPC(String str, String ans, int idx) {
        if (str.length() == idx) {
            // base case => top of recursion
            System.out.println(ans);
            return 1;
        }

        // get the current numerkey ex: 4
        char ch = str.charAt(idx);
        // convert it into a number index
        int numberkey = ch - '0';

        int count = 0;

        // get all possible characters to that number => 4 : pqrs
        for (char c : nokiaKeys[numberkey].toCharArray()) {
            // faith : get results from children and add yourself
            count += printKPC(str, ans + c, idx + 1);
        }
        // no. of ans
        return count;
    }

    public static int printStairPaths(int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        // max 3 jumps are possible
        int count = 0;
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            count += printStairPaths(n - jump, ans + jump);
        }
        return count;

    }

    // top to bottom
    public static ArrayList<String> stairPath(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> myAns = new ArrayList<>();
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            ArrayList<String> smallAns = stairPath(n - jump);
            for (String s : smallAns) {
                myAns.add(jump + s);
            }
        }

        return myAns;
    }

    // HW :

    public static int boardPath(int n, String psf, ArrayList<String> ans) {
        if (n == 0) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        // possible jump = dice numbers
        for (int jump = 1; jump <= 6 && n - jump >= 0; jump++) {
            count += boardPath(n - jump, psf + jump, ans);
        }
        return count;
    }

    public static int boardPath(int[] arr, int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump : arr) {
            if (n - jump >= 0) {
                count += boardPath(arr, n - jump, ans + jump);
            }
        }
        return count;
    }

    public static int mazePath_HVD(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans, int[][] dir,
            String[] dirS) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath_HVD(r, c, er, ec, psf + dirS[d], ans, dir, dirS);
            }
        }

        return count;
    }

    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans, int[][] dir,
            String[] dirS) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePath_HVD_multi(r, c, er, ec, psf + dirS[d] + rad, ans, dir, dirS);
                } else
                    break;
            }
        }

        return count;
    }

    public static int printPermutations(String str, String asf) {
        // faith aage ke sare character ko bolta hu ki apne possible permutations le aao
        // , mai unsbme add ho jaunga

        if (str.length() == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String roq = str.substring(0, i) + str.substring(i + 1);
            count += printPermutations(roq, asf + ch);
        }

        return count;
    }

    public static void printPermutationsWODuplicates(String str) {
        // O(n) sorting for a String -> never apply sort method for charArray/String
        int[] freq = new int[26];
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int fidx = str.charAt(i) - 'a';
            freq[fidx]++;
        }

        // sort:
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i]; j++) {
                // converting back to character
                sb.append((char) (i + 'a'));
            }
        }
        int sols = printPermutationsWODuplicatesHelper(sb.toString(), "");
        System.out.println("Without Duplicates are : " + sols);

    }

    static int printPermutationsWODuplicatesHelper(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        char prev = '-';
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String roq = str.substring(0, i) + str.substring(i + 1);
            if (ch != prev) {
                count += printPermutationsWODuplicatesHelper(roq, asf + ch);
                prev = ch;
            }
        }
        return count;
    }

    public static void mazePath() {
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        String[] dirS = { "H", "V", "D" };

        ArrayList<String> ans = new ArrayList<>();
        System.out.println(mazePath_HVD_multi(0, 0, 2, 2, "", ans, dir, dirS));

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        // Scanner sc = new Scanner(System.in);
        // String s = sc.next();
        // int sols = printKPC(s, "", 0);

        // int[] arr = { 2, 5, 3, 1 };
        // System.out.println("Board Path with variable jumps " + boardPath(arr, 10,
        // ""));
        // ArrayList<String> dicePaths = new ArrayList<>();
        // System.out.println("Board Path Dice jumps " + boardPath(10, "", dicePaths));

        // ArrayList<String> mazes = new ArrayList<>();
        // System.out.println("Maze paths " + mazePath_HVD(0, 0, 3, 3, "", mazes));

        System.out.println("Duplication is allowed : " + printPermutations("cabc", "") + "\n");

        printPermutationsWODuplicates("cabc");

    }

    // print Emcodings
    public static int printEncodings(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = str.charAt(0);
        if (ch == '0')
            return 0;

        int count = 0;
        // taking 1 number at a time

        // -'1' bcz staring point is not 0 here its 1
        // '2'-'1'=> 1(int) +'a' => ascii code of b
        char charToadd = (char) (ch - '1' + 'a');

        count += printEncodings(str.substring(1), ans + charToadd);
        // taking 2 numer at a time -> already checked for first char =0
        if (str.length() > 1) {
            char ch2 = str.charAt(1);

            int num = (int) ((ch - '0') * 10 + ch2 - '0');
            // here we already have a num(starting from 1), so num -1 => 0 +'a' =>a
            char charToadd2 = (char) (num - 1 + 'a');

            if (num <= 26)
                count += printEncodings(str.substring(2), ans + charToadd2);
        }

        return count;
    }

    // leetcode 47
    public static void permute(int[] nums, int count, List<List<Integer>> res, List<Integer> ans) {
        if (count == nums.length) {
            List<Integer> base = new ArrayList<>();
            for (int ele : ans)
                base.add(ele);
            res.add(base);
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= -10 && nums[i] <= 10) {
                int val = nums[i];

                nums[i] = -11;
                ans.add(val);

                permute(nums, count + 1, res, ans);

                ans.remove(ans.size() - 1);
                nums[i] = val;
            }
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        permute(nums, 0, res, ans);

        return res;
    }

}
