public class PalindromicSubstrings_647 {
    // Brute: create all substrings and check for palindrome : O(n^3)

    // Optimal: use two pointers treat every character as middle of palindromic
    // substring ans try to increase the left and right boundaries while maintainng
    // it's palindromic characteristics
    // Do it for odd length(palindromic string and even lenegth palindromic string)
    // O(n^2) + O(n^2) => O(n^2)

    public int countSubstrings(String s) {
        int n = s.length(), count = 0;
        int l = 0, r = 0;

        for (int i = 0; i < n; i++) {

            l = i;
            r = i; // odd length (1 common character in between)
            count += palindromic(s, l, r);

            l = i;
            r = i + 1; // even length (two same characters)
            count += palindromic(s, l, r);

        }

        return count;
    }

    private int palindromic(String s, int l, int r) {
        int count = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            count++;
            l--;
            r++;
        }

        return count;
    }
}
