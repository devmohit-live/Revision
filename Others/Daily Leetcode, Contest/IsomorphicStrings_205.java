public class IsomorphicStrings_205 {
    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256];// valid ascii chars
        int[] m2 = new int[256];
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)])
                return false; // intially all will be 0

            m1[s.charAt(i)] = i + 1; // last index
            m2[t.charAt(i)] = i + 1;// last index

            // following 1 to 1 mapping
        }
        return true;
    }
}
