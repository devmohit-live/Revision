public class PowerSet {
    public List<String> AllPossibleStrings(String s) {
        int n = s.length();
        List<String> list = new ArrayList<>();
        // num represnt the binray reprentation of number from 1 to n-1
        for (int num = 1; num < Math.pow(2, n); num++) {
            // i represents ith bit
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 32; i++) {
                int mask = (1 << i);
                // if ith bit is set takes that character into consideration
                if ((num & mask) != 0) {
                    sb.append(s.charAt(i));
                }
            }
            list.add(sb.toString());
        }
        Collections.sort(list);
        return list;
    }
}
