package Stack_Queue.Questions;

import java.io.*;
import java.util.*;

public class MergerIntervals {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }

        mergeOverlappingIntervals(arr);
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // arr[k][0] -> start time for kth element

        // Step1: Sort the intervals in increasing order of their start time
        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        // stack storing intervals
        Stack<int[]> st = new Stack<>();

        for (int[] a : arr) {
            int minStart = a[0];
            int maxEnd = a[1];

            while (!st.isEmpty() && st.peek()[1] >= a[0]) {
                minStart = Math.min(minStart, st.peek()[0]);
                maxEnd = Math.max(maxEnd, st.peek()[1]);
                st.pop();
            }

            st.push(new int[] { minStart, maxEnd });
        }

        // crearint result from stack
        Stack<int[]> reversed = new Stack<>();
        while (!st.isEmpty()) {
            reversed.push(st.pop());
        }

        while (!reversed.isEmpty()) {
            int[] in = reversed.pop();
            System.out.println(in[0] + " " + in[1]);
        }

    }

}