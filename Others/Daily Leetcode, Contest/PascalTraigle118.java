public class PascalTraingel118 {
    // CREATING PASCAL TRIANGE
    // no of elments in a row = row number
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> row = null, prev = null;

        if (numRows == 0)
            return ans;

        for (int i = 0; i < numRows; i++) {
            row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    row.add(1);
                else
                    row.add(prev.get(j - 1) + prev.get(j));
            }

            prev = row;
            ans.add(row);

        }

        return ans;
    }

    // getting specif r,c data from pascal triangle => r-1Cc-1 //O(n)
    // ex: 5th row 3rd col => 5-1C3-1 => 4C2 => 6

    // Getting nth row of pascal trangle // optimizing using above appraoch

}