public class LargestReactangle {
    public int largestRectangleAreaOptimal(int[] heights) {
        int n = heights.length, max = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1); // ref

        // we wil calculte the nsor and in the process we will be getting the nsol

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && heights[st.peek()] > heights[i]) {
                // ans[st.pop()] = i;
                // ith element peek pe pde index ja nsor //=> right
                int decisionAbout = st.pop();
                int height = heights[decisionAbout];

                int right = i;
                int left = st.peek();
                int width = right - left - 1;

                int area = height * width;

                max = Math.max(max, area);

            }

            st.push(i);

        }

        // very very important;
        // some elemtns left in stack => for them right boundary is arr.length = n;
        while (st.peek() != -1) {
            int ht = heights[st.pop()];
            int width = n - st.peek() - 1;
            int area = ht * width;
            max = Math.max(max, area);
        }

        return max;
    }

    // Better
    private int largestRectangleArea(int[] heights) {
        int left[] = nsol(heights);
        int right[] = nsor(heights);

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int width = right[i] - left[i] - 1;
            int area = width * heights[i];
            max = Math.max(max, area);
        }

        return max;
    }

    private int[] nsor(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        Stack<Integer> st = new Stack<>();
        st.push(-1); // as sref for empty stack

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);

        }

        return ans;
    }

    private int[] nsol(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();
        st.push(-1); // as sref for empty stack

        for (int i = n - 1; i >= 0; i--) {
            while (st.peek() != -1 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);

        }

        return ans;
    }
}
