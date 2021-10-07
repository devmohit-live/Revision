public class NextSmaller {
    // interviewBit: https://www.interviewbit.com/problems/nearest-smaller-element/
    // nsol
    public int[] prevSmaller(int[] A) {
        int n = A.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for (int i = n - 1; i >= 0; i--) {
            while (st.peek() != -1 && A[st.peek()] > A[i]) {
                ans[st.pop()] = A[i];
            }
            st.push(i);
        }
        return ans;
    }

}
