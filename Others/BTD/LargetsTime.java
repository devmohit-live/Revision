public class LargetsTime {
    // LC 949
    public String largestTimeFromDigits(int[] A) {
        // uniques indexs should be used as each digit can be used exactly once(indexed
        // wise)
        // i!= j && j!= k && k!=i
        // indexex possible => 0,1,2,3
        // if I know i,j,k I can simply find l as i+j+k+l = 6 => 0+1+2+3
        // why? bcz each index should be used once i = 0, j={1,2,3}
        // j = 2, k={1,3}, k => 3, l can only be 1

        String ans = "";
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i == j || i == k || j == k)
                        continue; // avoid duplicate among i, j & k.
                    // as each digit can be used exactly once(indexed wise)

                    String h = "" + A[i] + A[j], m = "" + A[k] + A[6 - i - j - k], t = h + ":" + m;
                    // hour, minutes, & time.
                    if (h.compareTo("24") < 0 && m.compareTo("60") < 0 && ans.compareTo(t) < 0)
                        ans = t;
                    // hour < 24; minute < 60; update result.
                }
            }
        }
        return ans;
    }
}
