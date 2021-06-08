import java.io.*;
import java.util.*;

public class Celebrity {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }

        System.out.println(findCelebrity(arr, arr.length));

    }

    // time: O(n) , space: O(n)
    public static void findCelebrityUsingStack(int[][] arr) {
        int cele = -1;
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            st.push(i);
        }

        // get potential candidate
        while (st.size() > 1) {
            int first = st.pop();
            int second = st.pop();
            // if(first!=second){} //no need as stack won't be having same elemnt twice
            if (arr[first][second] == 1 && arr[second][first] == 0) {
                // first is not a celeb
                st.push(second);
            } else if (arr[first][second] == 0 && arr[second][first] == 1)
                st.push(first);
        }
        if (st.size() == 0) {
            System.out.println("none");
            return;
        }
        cele = st.pop();

        // check - verify
        for (int i = 0; i < n; i++) {
            if (i != cele && (arr[cele][i] == 1 || arr[i][cele] == 0)) {
                System.out.println("none");
                return;
            }

        }

        if (cele == -1)
            System.out.println("none");
        else
            System.out.println(cele);
    }

    // time: O(n) , space: O(1)
    public static int findCelebrity(int M[][], int n) {
        int celeb = 0;

        // find potential
        for (int i = 0; i < n; i++) {
            if (celeb != i && M[celeb][i] == 1)
                celeb = i;
        }

        // verify
        for (int i = 0; i < n; i++) {
            if (celeb == i)
                continue;

            else if (M[celeb][i] == 1 || M[i][celeb] == 0)
                return -1;
        }

        return celeb;
    }

}