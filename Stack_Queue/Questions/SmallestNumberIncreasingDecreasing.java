package Stack_Queue.Questions;

import java.io.*;
import java.util.*;

public class SmallestNumberIncreasingDecreasing {

    // print the smallest number follwing the given pattern ex:
    // ii -> 123
    // dd-> 321
    // dddiddd -> 43218765
    // iiddd -> 126543
    // ddddiiii -> 543216789
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Integer> st = new Stack<>();

        // i is the breakpoint(kind of starting point ) -> for all d's we have to count
        // and print in decreasing order
        int num = 1;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // push the number and increment it
            st.push(num);
            num++;
            if (ch == 'i') {
                // if we encounter i then print the stack elemnts(decreasing order)
                while (st.size() > 0) {
                    System.out.print(st.pop());
                }
            }

        }

        // for single char string / last character(every character correspons to 2
        // digits ex: d=> 2 1 , i=> 1 2)

        st.push(num);
        while (st.size() > 0) {
            System.out.print(st.pop());
        }

    }
}