public class Celebrity {

    // Approach 1: Using Stack
    int celebrity(int M[][], int n) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++)
            st.push(i);

        while (st.size() > 1) {
            int a = st.pop();
            int b = st.pop();

            if (knows(M, a, b))
                st.push(b);
            else
                st.push(a);
        }

        // verify
        int potential = st.pop();
        for (int i = 0; i < n; i++) {
            if (i == potential)
                continue;
            if (knows(M, potential, i) || !knows(M, i, potential))
                return -1;
        }

        return potential;

    }

    // Approach 2: Without Using stack : Much intutative :
    // Time : O(2n), space: const
    boolean knows(int[][] arr, int i, int j) {
        if (arr[i][j] == 1)
            return true;
        return false;
    }

    int celebrityBetter(int M[][], int n) {
        int potential = 0;
        // finds the potential celeb
        for (int i = 0; i < n; i++) {
            if (potential != i && knows(M, potential, i))
                potential = i;
        }

        // verifies it
        for (int i = 0; i < n; i++) {
            if (i != potential && (knows(M, potential, i) || !knows(M, i, potential)))
                return -1;

        }
        return potential;

    }
}
