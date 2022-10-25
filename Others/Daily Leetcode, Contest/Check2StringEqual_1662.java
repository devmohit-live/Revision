public class Check2StringEqual_1662 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        // return ONSpacw(word1,word2);
        // return using4Pointers(word1,word2);
        return pointersRefractored(word1, word2);

    }

    private class CharIterator {
        String[] words;
        int wordIndex = 0;
        int charIndex = 0;

        CharIterator(String[] words) {
            this.words = words;
        }

        public boolean hasNext() {
            return this.wordIndex != this.words.length;
        }

        public char next() {
            char currChar = words[wordIndex].charAt(charIndex++);
            if (charIndex == words[wordIndex].length()) {
                charIndex = 0;
                wordIndex++;
            }
            return currChar;
        }
    }

    private boolean ONSpace(String[] word1, String[] word2) {
        // more efficient
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
        for (String word : word1)
            s1.append(word);
        for (String word : word2)
            s2.append(word);

        return s1.toString().equals(s2.toString());

        // //less efficient
        // String s1 = Arrays.stream(word1).collect(Collectors.joining());
        // String s2 = Arrays.stream(word2).collect(Collectors.joining());
        // return s1.equals(s2);
    }

    private boolean using4Pointers(String[] word1, String[] word2) {
        final int N = word1.length, M = word2.length;
        int w1 = 0, w2 = 0, c1 = 0, c2 = 0;

        while (w1 < N && w2 < M) {
            String curr1 = word1[w1], curr2 = word2[w2];

            if (curr1.charAt(c1) != curr2.charAt(c2))
                return false;

            if (c1 == curr1.length() - 1) {
                w1++;
                c1 = 0;
            } else
                c1++;

            if (c2 == curr2.length() - 1) {
                w2++;
                c2 = 0;
            } else
                c2++;
        }

        return w1 == N && w2 == M;
    }

    // can works for multiple list of strings
    private boolean pointersRefractored(String[] word1, String[] word2) {
        CharIterator charIterator1 = new CharIterator(word1);
        CharIterator charIterator2 = new CharIterator(word2);
        while (charIterator1.hasNext() && charIterator2.hasNext()) {
            if (charIterator1.next() != charIterator2.next())
                return false;
        }

        return !charIterator1.hasNext() && !charIterator2.hasNext();
    }
}
