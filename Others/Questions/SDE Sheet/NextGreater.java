public class NextGreater {

    // ng2
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(-1); // refrence -1represent invalid index
        Arrays.fill(ans, -1);
        // we are comparing index not val in st.peek() so no problem for -ve values
        for (int i = 0; i < 2 * n; i++) {
            while (st.peek() != -1 && nums[st.peek()] < nums[i % n]) {
                ans[st.pop()] = nums[i % n];
            }

            st.push(i % n);
        }

        return ans;
    }

    // ng1
    

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Map<Integer, Integer> val2Idx = new HashMap<>(); // for nums1
        int n = nums1.length, m = nums2.length, idx = 0;
        if (n == 0)
            return ans;

        Arrays.fill(ans, -1);

        for (int val : nums1)
            val2Idx.put(val, idx++);

        // ngor for nums2
        Stack<Integer> st = new Stack<>();
        st.push(-1); // ref
        for (int i = 0; i < m; i++) {
            while (st.peek() != -1 && nums2[st.peek()] < nums2[i]) {
                int ref = nums2[st.peek()];

                // setting the ngor
                if (val2Idx.containsKey(ref)) {
                    idx = val2Idx.get(ref);
                    ans[idx] = nums2[i];
                }

                st.pop();

            }

            st.push(i);
        }

        return ans;
    }
}
