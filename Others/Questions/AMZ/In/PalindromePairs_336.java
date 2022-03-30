import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PalindromePairs_336 {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        brute(words, ans);
        return ans;
    }

    // Brute: for a string a go for all the strings try to concat them in both
    // ways(a+b, b+a) and check for plaindrome
    // Pair: O(n *n(n-1)) + Making a String : 2*O(n+m) ) * Palindrome check :
    // 2*O(m+n)
    // (n*n + n+m) * (m+n) => (n*n*k + k*k) whenre k: m+n nearly =>
    // TIme: (m+n)^3

    private void brute(String[] words, List<List<Integer>> ans) {
        int n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String a = words[i] + words[j];
                String b = words[j] + words[i];
                if (isPalindrome(a))
                    ans.add(Arrays.asList(i, j));
                if (isPalindrome(b))
                    ans.add(Arrays.asList(j, i));
            }
        }
    }

    private boolean isPalindrome(String s) {
        int n = s.length(), i = 0, j = n - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }
}