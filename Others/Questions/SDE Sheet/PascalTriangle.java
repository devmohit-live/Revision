public class PascalTriangle {
    public static ArrayList<ArrayList<Long>> printPascal(int n) {
        ArrayList<ArrayList<Long>> ans = new ArrayList<>();
        if (n == 0)
            return ans;
        ArrayList<Long> row = null, prev = null;

        for (int i = 0; i < n; i++) {
            row = new ArrayList<Long>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    row.add(1l);
                else
                    row.add((prev.get(j - 1) + prev.get(j)));
            }

            prev = row;
            ans.add(row);
        }

        return ans;
    }
}
