public class ContainsDuplicateII_219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Map<Integer,List<Integer>> map = new HashMap<>();
        // int idx = 0;

        // for(int el : nums) {
        // map.putIfAbsent(el, new ArrayList<>());
        // List<Integer> indexes = map.get(el);
        // int n = indexes.size();

        // if(n>0)
        // while(n>0)
        // if(Math.abs(indexes.get(--n) - idx) <= k)
        // return true;

        // indexes.add(idx++);
        // }
        // return false;

        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k)
                set.remove(nums[i - k - 1]); // remove element if its distance to nums[i] is not lesser than k
            if (!set.add(nums[i]))
                return true; // because all still existed elements is closer than k distance to the num[i],
                             // therefore if the add() return false, it means there's a same value element
                             // already existed within the distance k, therefore return true.
        }
        return false;

    }
}
