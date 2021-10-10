public class RemoveRedundantBrackets {
    public static void main(String[] args) {
        String s = "(((abc)))()(fd)(d(f)())";
        String ans = removeRedundantBrackets(s);
        System.out.println(ans);
    }

    public static String removeRedundantBrackets(String s) {
        Stack<Integer> st = new Stack<>();
        char[] arr = s.toCharArray();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch = arr[i];
            // number
            if (ch != '(' && ch != ')') {
                st.push(-1); // represents a number
                while (i < n - 1 && arr[i] != '(' && arr[i] != ')')
                    i++; // ignore other numbers
                i -= 1; // for loop will increment once more
            } else if (ch == '(')
                st.push(i);
            // closing
            else if (ch == '(' && st.size() != 0 && st.peek() != -1) {
                arr[st.pop()] = '#'; // make onening invalid
                arr[i] = '#'; // make the clsoing bracket false too.
            } else if (ch == ')' && st.size() != 0 && st.peek() == -1) {
                st.pop(); // number
                st.pop();// op
            } else if (ch == ')')
                arr[i] = '#'; // st is empty => invalid closing
        }

        while (st.size() != 0) {
            int idx = st.pop();
            if (idx != -1)
                arr[idx] = '#';
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : arr)
            if (ch != '#')
                sb.append(ch);
        return sb.toString();
    }

}