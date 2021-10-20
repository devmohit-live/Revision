public class Questions {
    // Leetcode 128 : Longest Consecutive Subsequnce
    public int longestConsecutive(int[] nums) {

        // idea is to remove the consecutive elemets from the set as we explre them
        // once, so that all the consecutive elemets related to an element e is removed
        // at once
        if (nums.length == 0 || nums.length == 1)
            return nums.length;
        int maxlen = 0, start = -1;
        Set<Integer> set = new HashSet<>();
        for (int el : nums)
            set.add(el);
        int max = 0;
        for (int el : nums) {
            int prev = el - 1, next = el + 1;

            // set.remove actually decreases the search complexity/time of element in a set
            while (set.contains(prev))
                set.remove(prev--);
            while (set.contains(next))
                set.remove(next++);
            int len = next - prev - 1;
            if (len > maxlen) {
                maxlen = len;
                start = prev + 1;
            }
        }

        // System.out.println(start+" "+(start+maxlen-1));
        return maxlen;

    }

    // Leetcode 1218 : . Longest Arithmetic Subsequence of Given Difference

    public int longestSubsequence(int[] nums, int diff) {
        int max = -1, n = nums.length;
        // since here we aleady know the difference is cinstant ,
        // we eill be kwwp on checking for the previos element exists in map beforehand

        // why not recursion of lis bcz here we already know diff, it is not a normal
        // subseq, it is an arthmetic subseq

        // el, length of lis upto now
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int el : nums) {
            map.put(el, map.getOrDefault(el - diff, 0) + 1);
        }

        for (int el : map.keySet())
            max = Math.max(max, map.get(el));

        return max;
    }

    // Leetcode : 1424. Diagonal Traverse II
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // diag no(i+j) (left to right), corresponding elements
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        int size = 0, n = nums.size(), max = 0, idx = 0;

        // addFirst bcz we want the order such that => leftbottom -> upperright ie we
        // want to (3,1) before (1,3)
        // but we will be getting 1,3 first

        // (changint the loop in reverse order causes problem too)

        for (int i = 0; i < n; i++) {
            size += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); j++) {
                int diag = i + j;
                int el = nums.get(i).get(j);
                map.putIfAbsent(diag, new LinkedList<Integer>());
                map.get(diag).addFirst(el); // added to corresponding diag list into correct order
                // get the maximum diag value so that we can traverse from 0->max(in order to
                // fill in the answer)
                max = Math.max(max, diag);
            }
        }

        int[] ans = new int[size];
        for (int i = 0; i <= max; i++) {
            LinkedList<Integer> tmp = map.get(i);
            while (tmp.size() != 0)
                ans[idx++] = tmp.removeFirst();

        }

        return ans;
    }
}
