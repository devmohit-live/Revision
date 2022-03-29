public class NGORII_503 {
    // using stack
    private void solve(int[] arr, int[] ans) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int n = arr.length;
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (st.peek() != -1 && arr[st.peek()] < arr[idx]) {
                ans[st.pop()] = arr[idx];
            }

            st.push(idx);
        }

    }
}
