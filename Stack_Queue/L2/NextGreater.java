import java.util.*;

public class NextGreater {
    static int[] ngor(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n + 1); // arr.length represent imaginary number to right is ngor => infinity

        Stack<Integer> st = new Stack<>();
        st.push(-1); // represents no element is there

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i]) {

                // ans[st.pop()] = arr[i]; // stores element
                ans[st.pop()] = i; // stored index
            }

            st.push(i);
        }

        return ans;

    }

    // just change the comparison in while
    static int[] nsor(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n); // arr.length represent imaginary number to right is nsor => infinity

        Stack<Integer> st = new Stack<>();
        st.push(-1); // represents no element is there

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }

            st.push(i);
        }

        return ans;

    }

    // just achange the traverse direction
    static int[] ngol(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1); // arr.length represent imaginary number to left is ngor => infinity

        Stack<Integer> st = new Stack<>();
        st.push(-1); // represents no element is there

        for (int i = n - 1; i >= 0; i--) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;
            }

            st.push(i);
        }

        return ans;

    }

    static int[] nsol(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1); // arr.length represent imaginary number to left is nsol => infinity

        Stack<Integer> st = new Stack<>();
        st.push(-1); // represents no element is there

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;
            }

            st.push(i);
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] arr1 = { 4, 1, 2 };
        int[] arr2 = { 1, 3, 4, 2 };
        System.out.println("Origonal " + Arrays.toString(arr1));
        System.out.println("NGOR : " + Arrays.toString(ngor(arr1)));
        System.out.println("NGOL : " + Arrays.toString(ngol(arr1)));
        System.out.println("NSOR : " + Arrays.toString(nsor(arr1)));
        System.out.println("NSOl : " + Arrays.toString(nsol(arr1)));

    }
}
