public class NearestSmaller {
    public int[] prevSmaller(int[] arr) {

        // next smaller on left
        // left => reverse loop
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = n - 1; i >= 0; i--) {
            while (st.peek() != -1 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = arr[i];
            }
            st.push(i);
        }

        return ans;
    }
}
