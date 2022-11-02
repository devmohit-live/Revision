public class ValidParanthesis {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[')
                st.push(ch);
            else {
                if (st.size() == 0)
                    return false;
                else {
                    char top = st.peek();
                    if (ch == ')' && top != '(')
                        return false;
                    else if (ch == '}' && top != '{')
                        return false;
                    else if (ch == ']' && top != '[')
                        return false;
                    else
                        st.pop();
                }
            }

        }

        return st.size() == 0;
    }
}
