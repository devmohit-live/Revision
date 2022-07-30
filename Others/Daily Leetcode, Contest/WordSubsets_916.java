public class WordSubsets_916 {

    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> res = new ArrayList<>();
        if (words1.length == 0 || words2.length == 0)
            return res;
        int n = words1.length, m = words2.length;

        // Wrong: bcz we there can be overlapping chars in words string and in that case
        // we have to considre max(freq of chars in words2[i]) not sum

        // //make a cumulative pattern string
        // StringBuilder sb = new StringBuilder();
        // for(String word: words2) sb.append(word);

        // calculate freq hash of cumulative pattern and words
        // calculateFreq(sb.toString(), pat);
        // int[] pat = new int[26];

        int[][] freq1 = new int[n][26];
        int[][] freq2 = new int[m][26];

        calulateFrequencies(words1, freq1);
        calulateFrequencies(words2, freq2);
        int[] maxFreq = getMaxFreq(freq2);

        for (int i = 0; i < n; i++) {
            if (isValid(freq1[i], maxFreq))
                res.add(words1[i]);
        }

        return res;
    }

    private void calulateFrequencies(String[] words, int[][] freq) {
        int n = words.length;
        if (n == 0)
            return;
        for (int i = 0; i < n; i++) {
            calculateFreq(words[i], freq[i]);
        }
    }

    private int[] getMaxFreq(int[][] freq) {
        int[] maxFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            int max = -1;
            for (int row[] : freq) {
                max = Math.max(max, row[i]);
            }
            maxFreq[i] = max;
        }

        return maxFreq;
    }

    private boolean isValid(int[] A, int[] B) {
        for (int i = 0; i < A.length; i++) {

            if (B[i] > A[i])
                return false;
        }
        return true;
    }

    private void calculateFreq(String word, int[] freq) {
        for (char ch : word.toCharArray())
            freq[ch - 'a']++;
    }
}
