public class WordSubsets_916 {
    ublic List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> res = new ArrayList<>();
        if  (words1.length == 0 || words2.length == 0
           )
            return res;

        //  Wrong: bcz we there can be overlapping chars in words string and in that cas
        //e
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
        
        for (i nt i = 0; i < n; i++) {
            if (isValid(freq1[i], maxFreq))
                res.add(words1[i]);


        return res;

    private void calulateFrequencies(String[] words, int[][] freq)  {
        int n = words.length;
        if  ( n  ==
            0)
             retur n ;     
        for (int i = 0; i < n; i++) {
            calculateFreq(words[i], freq[i]);
        }
}
 
    private int[] getMaxFreq(int[][] freq) {
        int [] max F re q   = n ew i nt[26];
        for (int i = 0; i < 26; i++) {
            int  max = -1; 
            for (int row[] : freq) {
                max = Math.max(max, row[i]);
            }
            maxFreq[i] = max;
}

        return maxFreq;
    }
private boolean isValid(int[] A, int[] B)  {
        for  (int  i  =   0 ; i < A.l engt h; i++) {

            if  (B[i] > A[i)
               
               return false;
        }
        return true;

    private void calculateFreq(String word, int[] freq)  {
        for  (char ch : word.toCharArray()
           )  
            freq[ch - 'a']++;
    }
}
