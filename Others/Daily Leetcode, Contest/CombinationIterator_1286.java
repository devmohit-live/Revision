public class CombinationIterator_1286 {

    // Approach 1 : Using recursion to genrate all the strings of length combination
    // length from the given string of lenthr n
    // and using PQ to get the lexo smaller string each time

    // TODO: To ask Time complexity of creating combinations of string of k length
    // from n length string
    // Genrate string => Iime : height of tree => combl choices(for loop)=> n-combl
    // => O((n-combl)^combl)
    // Getting lexo smaller => log(size of pq) => log(n C combl)

    // Time: (n-combl)^comb * log(n C combl) ==> ?
    // worst case k =1,0 making n-combl = n => n^k * log(nCk)
    // Space: O(n) pq

    class CombinationIterator1 {

        // PriorityQueue<String> lexosmaller;// to get lexosmaller elemets always
        // Since the imput is Given sorted we can use normal queue here reducing the
        // time by logn
        Queue<String> lexosmaller;

        public CombinationIterator(String characters, int combinationLength) {
            // lexosmaller = new PriorityQueue<>();
            lexosmaller = new ArrayDeque<>();
            generateAllPossibleStrings(characters, combinationLength, 0, new StringBuilder());
        }

        public String next() {
            return lexosmaller.remove();
        }

        public boolean hasNext() {
            return lexosmaller.size() > 0;
        }

        // preprocessing
        private void generateAllPossibleStrings(String s, int len, int si, StringBuilder ssf) {

            if (len == 0) {
                lexosmaller.add(ssf.toString());
                return;
            }

            // si wali condition loop smbhal lega

            // basically we have to made len length string from n size, so we have n-len
            // characters choice to make string
            // so we will be chossing 1 by one from them
            for (int i = si; i < (s.length() - len) + 1; i++) {
                ssf.append(s.charAt(i));
                generateAllPossibleStrings(s, len - 1, i + 1, ssf);
                ssf.deleteCharAt(ssf.length() - 1);
            }

        }
    }

    // Approach 2 using bits to create combinations

}