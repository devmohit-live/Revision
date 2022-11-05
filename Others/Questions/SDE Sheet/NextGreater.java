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
        int n = nums1.length, m = nums2.length, idx = 0;
        if (n == 0)
            return ans;

        Arrays.fill(ans, -1);
        Map<Integer, Integer> val2Idx = new HashMap<>();
        for (int val : nums1)
            val2Idx.put(val, idx++);

        // ngor have to run in nums2 array for nums1 values that are present in nums2
        Stack<Integer> st = new Stack<>();
        st.push(-1); // ref
        for (int i = 0; i < m; i++) {
            while (st.peek() != -1 && nums2[st.peek()] < nums2[i]) {
                int ref = nums2[st.peek()]; // number in nums2

                // setting the ngor only if this number was present in nums1
                if (val2Idx.containsKey(ref)) {
                    idx = val2Idx.get(ref); // idx of this number in nums1
                    ans[idx] = nums2[i]; // it's ngor'
                }

                st.pop();

            }

            st.push(i);
        }

        return ans;

    }
}
