public class CombinationsCreations {
    // Leetcode 78 : Subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int x = comb(nums, 0, new ArrayList<>(), ans);
        System.out.println(x);
        ans = new ArrayList<>();
        int y = comb2(nums, 0, new ArrayList<>(), ans);
        System.out.println(y);
        return ans;
    }

    private int comb(int[] nums, int idx, List<Integer> small, List<List<Integer>> ans) {
        // valid base : tar == 0 not idx> == length
        // idx == length and tar == 0 both are valid for subseq method
        int count = 1;
        ArrayList<Integer> base = new ArrayList<>(small);
        ans.add(base);
        for (int i = idx; i < nums.length; i++) { // case idx <= is handled
            // In Arera generally tar - x >= 0 :but here no condtion is there: we have to
            // create all possible
            small.add(nums[i]);
            count += comb(nums, i + 1, small, ans);
            small.remove(small.size() - 1);
        }
        // Post area
        return count;
    }

    private int comb2(int[] nums, int idx, List<Integer> small, List<List<Integer>> ans) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(small));
            return 1;
        }
        int count = 0;
        // pick
        small.add(nums[idx]);
        count += comb2(nums, idx + 1, small, ans);
        small.remove(small.size() - 1);
        // not pick
        count += comb2(nums, idx + 1, small, ans);
        return count;
    }

    // ========================== Equivalent of Suseq of creating all combination
    // =================================
    public int subsets(ArrayList<Integer> nums, int idx, ArrayList<Integer> small, ArrayList<ArrayList<Integer>> ans) {
        // empty subseq is counted so count=1 at start
        // Pre Area
        int count = 1;
        ArrayList<Integer> base = new ArrayList<>(small);
        ans.add(base);
        for (int i = idx; i < nums.size(); i++) {
            // In Arera
            small.add(nums.get(i));
            count += subsets(nums, i + 1, small, ans);
            small.remove(small.size() - 1);
        }
        // Post area
        return count;
    }

    // Using StringBuilder
    public static int subsets(String str, int idx, StringBuilder asf, ArrayList<String> ans) {
        // empty subseq is counted so count=1 at start
        int count = 1;
        ans.add(asf.toString());
        for (int i = idx; i < str.length(); i++) {
            asf.append(str.charAt(i));
            count += subsets(str, i + 1, asf, ans);
            asf.deleteCharAt(asf.length() - 1);
        }
        return count;
    }

    public static void subsets(String str) {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        subsets(str, 0, sb, ans);
        System.out.println(ans);
    }

}
