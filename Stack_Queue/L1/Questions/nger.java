package Stack_Queue.Questions;

import java.io.*;
import java.util.*;

// Next greater element to the right
public class nger {
    public static void display(int[] a) {
        StringBuilder sb = new StringBuilder();

        for (int val : a) {
            sb.append(val + "\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        int[] nge = solve(a);
        display(nge);
    }

    public static int[] solve(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {

            while (st.size() != 0 && arr[st.peek()] < arr[i]) {
                // while arr[top] is less than current element update next greater
                ans[st.pop()] = arr[i];
            }
            // push the current index
            st.push(i);
        }

        return ans;
    }

}